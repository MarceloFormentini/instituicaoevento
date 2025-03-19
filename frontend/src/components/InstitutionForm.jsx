import { useNavigate } from "react-router-dom";
import "../styles/InstitutionForm.css";

const InstitutionForm = ({ title, formData, setFormData, onSubmit, errors }) => {
	const navigate = useNavigate();

	return (
		<div className="container">
			<h1 className="title">{title}</h1>
			<form onSubmit={onSubmit} className="form">
				<div className="input-container">
					<input type="text" id="nome" className="input-field"
						placeholder="Digite o nome da instituição"
						value={formData.nome}
						onChange={(e) => setFormData({ ...formData, nome: e.target.value })}
						required
					/>
					{errors?.nome && <p style={{ color: "red" }}>{errors.nome}</p>} {/* Exibe erro do nome */}
				</div>
				<div className="input-container">
					<input type="text"
						placeholder="Digite o tipo da instituição"
						id="tipo"
						className="input-field"
						value={formData.tipo}
						onChange={(e) => setFormData({ ...formData, tipo: e.target.value })}
						required
					/>
					{errors?.tipo && <p style={{ color: "red" }}>{errors.tipo}</p>} {/* Exibe erro do tipo */}
				</div>
				<div className="button-container">
					<button type="button" className="back-button" onClick={() => navigate(-1)}>
						Voltar
					</button>
					<button type="submit" className="save-button">
						Salvar
					</button>
				</div>
			</form>
		</div>
	);
};

export default InstitutionForm;