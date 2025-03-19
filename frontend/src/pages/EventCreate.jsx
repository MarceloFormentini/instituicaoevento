import { useState } from "react";
import { createEvento } from "../services/api";
import { useNavigate, useParams } from "react-router-dom";
import EventForm from "../components/EventForm";

const EventCreate = () => {
	const { instituicaoId } = useParams();
	const [formData, setFormData] = useState({ nome: "", dataInicio: "", dataFim: "", instituicaoId });
	const [errors, setErrors] = useState({});
	const navigate = useNavigate();

	const handleSubmit = async (e) => {
		e.preventDefault();
		setErrors({});

		try {
		await createEvento(formData);
		navigate(`/instituicao/${instituicaoId}/eventos`);
		} catch (error) {
		if (error.response && error.response.status === 400) {
			setErrors(error.response.data);
		}
		}
	};

	return (
		<div>
		<h1>Cadastrar Evento</h1>
		<EventForm formData={formData} setFormData={setFormData} onSubmit={handleSubmit} errors={errors} />
		</div>
	);
};

export default EventCreate;