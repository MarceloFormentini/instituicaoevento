import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getInstituicaoById, updateInstituicao } from "../services/api";
import InstitutionForm from "../components/InstitutionForm";

const InstitutionEdit = () => {
	const { id } = useParams();
	const [formData, setFormData] = useState({ id:"", nome: "", tipo: "" });
	const [errorMessage, setErrorMessage] = useState("");
	const navigate = useNavigate();

	useEffect(() => {
		getInstituicaoById(id).then((response) => setFormData(response.data))
		.catch((error) => console.log("Erro ao carregar registro: ", error));
	}, [id]);

	const handleSubmit = async (e) => {
		e.preventDefault();
		try {
			const response = await updateInstituicao(formData);
			console.log("Sucesso:", response.data);
			navigate("/");
		} catch (error) {
			console.error("Erro ao atualizar:", error);
	  
			if (error.response) {
				console.error("Status:", error.response.status);
				console.error("Dados:", error.response.data);
				console.error("Cabeçalhos:", error.response.headers);
				setErrorMessage(`Erro: ${error.response.data.message || "Falha ao atualizar instituição."}`);
			} else if (error.request) {
				console.error("Sem resposta do servidor:", error.request);
				setErrorMessage("Erro: O servidor não respondeu.");
			} else {
				console.error("Erro inesperado:", error.message);
				setErrorMessage("Erro inesperado ao tentar atualizar a instituição.");
			}
		}
	};

	return (
		<div>
			<InstitutionForm title={"Editar Instituição"} formData={formData} setFormData={setFormData} onSubmit={handleSubmit} />
		</div>
	);
};

export default InstitutionEdit;
