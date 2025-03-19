import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getEventosByInstituicao, deleteEvento } from "../services/api";
import "../styles/EventList.css";

const EventList = () => {
	const { instituicaoId } = useParams();
	const [eventos, setEventos] = useState([]);
	const [page, setPage] = useState(0);
	const [totalPages, setTotalPages] = useState(1);
	const navigate = useNavigate();

	useEffect(() => {
		carregarEventos();
	}, [page]);

	const carregarEventos = () => {
		getEventosByInstituicao(instituicaoId, page)
		.then((response) => {
			setEventos(response.data.content);
			setTotalPages(response.data.totalPages);
		})
		.catch((error) => console.error("Erro ao buscar eventos:", error));
	};

	const handleDelete = async (id) => {
		if (window.confirm("Tem certeza que deseja excluir este evento?")) {
		try {
			await deleteEvento(id);
			carregarEventos();
		} catch (error) {
			console.error("Erro ao excluir evento:", error);
		}
		}
	};

	return (
		<div className="container">
			<h1 className="title">Eventos da Instituição</h1>
			<div className="button-container">
				<button className="add-button" onClick={() => navigate(`/instituicao/${instituicaoId}/evento/create`)}>
					Novo Evento
				</button>
			</div>
			<table className="event-list">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Data Inicial</th>
						<th>Data Final</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					{eventos.map((evento) => (
					<tr key={evento.id}>
						<td>{evento.nome}</td>
						<td>{evento.dataInicial}</td>
						<td>{evento.dataFinal}</td>
						<td>
							<button
							onClick={() => navigate(`/instituicao/${instituicaoId}/evento/edit/${evento.id}`)}
							className="action-button edit-button">
								Editar
							</button>
							<button onClick={() => handleDelete(evento.id)} className="action-button delete-button">
		 						Excluir
	 						</button>
						</td>
					</tr>
				))}
				</tbody>
			</table>
			<div className="pagination-container">
				<button className="back-button" onClick={() => navigate(-1)}>
					Voltar
				</button>
				<div className="pagination">
					<button disabled={page === 0} onClick={() => setPage(page - 1)}>Anterior</button>
					<span>Página {page + 1} de {totalPages}</span>
					<button disabled={page + 1 >= totalPages} onClick={() => setPage(page + 1)}>Próxima</button>
				</div>
			</div>
		</div>
	);
};

export default EventList;
