import { useEffect, useState } from "react";
import { data, useNavigate, useParams } from "react-router-dom";
import { getEventoById, updateEvento } from "../services/api";
import EventForm from "../components/EventForm";

const EventEdit = () => {
	const { instituicaoId, id } = useParams();
	const [formData, setFormData] = useState({ id, nome: "", dataInicial: "", dataFinal: "", instituicaoId });
	const [errors, setErrors] = useState({});
	const navigate = useNavigate();

	useEffect(() => {
		const fetchEvento = async () => {
			try{
				const response = await getEventoById(instituicaoId, id)
				const evento = response.data

				const formattedData = {
					...evento,
					dataInicial: formatDateForInput(evento.dataInicial),
					dataFinal: formatDateForInput(evento.dataFinal),
				};

				setFormData(formattedData);

			} catch (error) {
				console.error("Erro ao buscar evento por id:", error);
			}
		};
		fetchEvento();
	}, [id, instituicaoId]);

	const formatDateForInput = (dateString) => {
		if (!dateString) return "";
		const [datePart, timePart] = dateString.split(" ");
		const [day, month, year] = datePart.split("/");
		return `${year}-${month}-${day}T${timePart.slice(0, 5)}`;
	};

	const handleSubmit = async (e) => {
		e.preventDefault();
		setErrors({});

		try {
			// envio dos dados para a API
			const formattedData = {
				...formData,
				dataInicial: formData.dataInicial.replace(" ", "T") + ":00", // Converte para o formato esperado pela API
				dataFinal: formData.dataFinal.replace(" ", "T") + ":00",
			};

			await updateEvento(instituicaoId, formattedData);
			navigate(`/instituicao/${instituicaoId}/evento`);

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
			<EventForm title={"Editar Evento"} formData={formData} setFormData={setFormData} onSubmit={handleSubmit} errors={errors} />
		</div>
	);
};

export default EventEdit;
