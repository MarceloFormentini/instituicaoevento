import { useState } from "react";
import { createInstituicao } from "../services/api";
import { useNavigate } from "react-router-dom";
import InstitutionForm from "../components/InstitutionForm";

const InstitutionCreate = () => {
	const [formData, setFormData] = useState({ nome: "", tipo: "" });
	const [errors, setErrors] = useState({});
	const navigate = useNavigate();

	const handleSubmit = async (e) => {
		e.preventDefault();
		setErrors({});

		try{
			console.log("cadastro de instituição", formData);
			await createInstituicao(formData)
			navigate("/");
		}catch(error){
			if (error.response && error.response.status === 400) {
				setErrors(error.response.data);
			}
			else{
				console.log("Erro inesperado", error);
			}
		}
	};

	return (
		<div>
			<InstitutionForm title={"Cadastrar Instituição"} formData={formData} setFormData={setFormData} onSubmit={handleSubmit} errors={errors}/>
		</div>
	);
};

export default InstitutionCreate;
