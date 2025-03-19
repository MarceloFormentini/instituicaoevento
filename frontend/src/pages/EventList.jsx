import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getEventosByInstituicao, deleteEvento } from "../services/api";

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

	// return (
	// 	<div>
	// 		<h1>Eventos da Instituição</h1>
	// 		<button onClick={() => navigate(`/instituicao/${instituicaoId}/eventos/create`)}>Novo Evento</button>
	// 		{eventos.length === 0 ? (
	// 			<p>Nenhum evento cadastrado para esta instituição.</p>
	// 		) : (
	// 			<ul>
	// 				{eventos.map((evento) => (
	// 					<li key={evento.id}>
	// 					<span onClick={() => navigate(`/evento/${instituicaoId}${evento.id}`)}>
	// 						{evento.nome} - {new Date(evento.dataInicial).toLocaleString()} até {new Date(evento.dataFinal).toLocaleString()}
	// 					</span>
	// 					<button onClick={() => handleDelete(evento.id)} style={{ marginLeft: "10px" }}>
	// 						Excluir
	// 					</button>
	// 					</li>
	// 				))}
	// 			</ul>
	// 		)}
	// 	</div>
	// );
	return (
		<div>
			<h1>Eventos da Instituição</h1>
			<button onClick={() => navigate(`/instituicao/${instituicaoId}/eventos/create`)}>Novo Evento</button>
			{eventos.length === 0 ? (
				<p>Nenhum evento cadastrado para esta instituição.</p>
			) : (
				<ul>
					{eventos.map((evento) => (
						<li key={evento.id}>
							{evento.nome} - {new Date(evento.dataInicial).toLocaleString()} até {new Date(evento.dataFinal).toLocaleString()}
						</li>
					))}
				</ul>
			)}

			{/* Controles de Paginação */}
			<div className="pagination">
				<button disabled={page === 0} onClick={() => setPage(page - 1)}>⬅ Anterior</button>
				<span>Página {page + 1} de {totalPages}</span>
				<button disabled={page + 1 >= totalPages} onClick={() => setPage(page + 1)}>Próxima ➡</button>
			</div>
		</div>
	);
};

export default EventList;
