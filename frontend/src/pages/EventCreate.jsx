import { useState } from "react";
import { createEvento } from "../services/api";
import { useNavigate, useParams } from "react-router-dom";
import EventForm from "../components/EventForm";

const EventCreate = () => {
	const { instituicaoId } = useParams();
	const [formData, setFormData] = useState({ nome: "", dataInicial: "", dataFinal: ""});
	const [errors, setErrors] = useState({});
	const navigate = useNavigate();

	// Função para formatar a data antes de enviar
	const formatDate = (date) => {
		if (!date) {
			console.error("Data inválida recebida:", date);
			return "";
		}
		return new Date(date).toISOString().slice(0, 19); // Converte para yyyy-MM-ddTHH:mm:ss
	};

	const handleSubmit = async (e) => {
		e.preventDefault();
		setErrors({});
		// console.error("ID da instituição:", instituicaoId);

		// Formata as datas antes de enviar
		const eventoFormatado = {
			...formData,
			dataInicial: formatDate(formData.dataInicial),
			dataFinal: formatDate(formData.dataFinal),
		};

		try {
			// console.log("cadastro de evento", formData);
			// console.log("cadastro de evento formatado", eventoFormatado);
			await createEvento(instituicaoId, eventoFormatado);
			navigate(`/instituicao/${instituicaoId}/evento`);
		} catch (error) {
			if (error.response && error.response.status === 400) {
				setErrors(error.response.data);
			}
		}
	};

	return (
		<div>
			<EventForm title={"Cadastrar Evento"} formData={formData} setFormData={setFormData} onSubmit={handleSubmit} errors={errors} />
		</div>
	);
};

export default EventCreate;