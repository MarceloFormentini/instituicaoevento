import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getEventoById, updateEvento } from "../services/api";
import EventForm from "../components/EventForm";

const EventEdit = () => {
	const { instituicaoId, id } = useParams(); // Obtém os IDs da URL
	const [formData, setFormData] = useState({ nome: "", dataInicio: "", dataFim: "", instituicaoId });
	const [errors, setErrors] = useState({});
	const navigate = useNavigate();

	useEffect(() => {
		getEventoById(id)
		.then((response) => setFormData(response.data))
		.catch((error) => console.error("Erro ao buscar evento:", error));
	}, [id]);

	const handleSubmit = async (e) => {
		e.preventDefault();
		setErrors({});

		try {
			await updateEvento(formData);
			navigate(`/instituicao/${instituicaoId}/eventos`);
		} catch (error) {
			if (error.response && error.response.status === 400) {
				setErrors(error.response.data);
			} else {
				console.error("Erro inesperado ao atualizar evento:", error);
			}
		}
	};

	return (
		<div>
		<h1>Editar Evento</h1>
		<EventForm formData={formData} setFormData={setFormData} onSubmit={handleSubmit} errors={errors} />
		</div>
	);
};

export default EventEdit;
