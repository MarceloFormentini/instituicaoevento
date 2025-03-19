import { useEffect, useState } from "react";
import { getInstituicoes, deleteInstituicao } from "../services/api";
import { useNavigate } from "react-router-dom";
import "../styles/InstitutionList.css";

const InstitutionList = () => {
	const [instituicoes, setInstituicoes] = useState([]);
	const [page, setPage] = useState(0);
	const [totalPages, setTotalPages] = useState(1);
	const navigate = useNavigate();

	useEffect(() => {
		carregarInstituicoes();
	}, [page]); // atualiza a lista de instituições toda vez que a página muda

	const carregarInstituicoes = () => {
		getInstituicoes(page)
			.then((response) => {
				setInstituicoes(response.data.content); // content -> contém os dados da página atual
				setTotalPages(response.data.totalPages); // totalPages -> contém o total de páginas
			})
			.catch((error) => {
				console.error("Erro ao carregar instituições:", {
					mensagem: error.message,
					status: error.response?.status,
					url: error.config?.url,
				});
				alert("Erro ao carregar instituições.");
			});
	}

	const handleDelete = async (id) => {
		if (window.confirm("Tem certeza que deseja excluir esta instituição?")) {
			try {
				await deleteInstituicao(id);
				carregarInstituicoes();
			} catch (error) {
				console.error("Erro ao excluir:", error);
				alert("Erro ao excluir instituição.");
			}
		}
	};

	return (
		<div className="container">
			<h1 className="title">Instituições</h1>
			<div className="button-container">
				<button className="add-button" onClick={() => navigate("/create")}>
					Nova Instituição
				</button>
			</div>
			<table className="institution-list">
				<thead>
					<tr>
						<th className="table-header">Nome</th>
						<th className="table-header">Tipo</th>
						<th className="table-header">Ações</th>
					</tr>
				</thead>
				<tbody>
					{instituicoes.map((inst) => (
						<tr key={inst.id} className="table-row">
							<td className="table-cell">{inst.nome}</td>
							<td className="table-cell">{inst.tipo}</td>
							<td className="table-cell action-buttons">
								<button onClick={() => navigate(`/evento/${inst.id}`)} className="action-button view-button">
									Eventos
								</button>
								<button onClick={() => navigate(`/edit/${inst.id}`)} className="action-button edit-button">
									Editar
								</button>
								<button onClick={() => handleDelete(inst.id)} className="action-button delete-button">
									Excluir
								</button>
							</td>
						</tr>
					))}
				</tbody>
			</table>
			<div className="pagination">
				<button onClick={() => setPage(page - 1)} disabled={page === 0}>
					Anterior
				</button>
				<span> Página {page + 1} de {totalPages} </span>
				<button onClick={() => setPage(page + 1)} disabled={page === totalPages - 1}>
					Próxima
				</button>
			</div>
		</div>
	);
};

export default InstitutionList;
