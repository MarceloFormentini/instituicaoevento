import { useNavigate } from "react-router-dom";
import "../styles/EventForm.css";

const EventForm = ({ title, formData, setFormData, onSubmit, errors }) => {
	const navigate = useNavigate();

	return (
		<div className="container-form">
			<h1 className="title">{title}</h1>
			<form onSubmit={onSubmit} className="form">
				<div className="input-container">
					{/* <label htmlFor="nome" className="input-label">Nome do Evento</label> */}
					<input type="text" id="nome" className="input-field"
						placeholder="Digite o nome do evento"
						value={formData.nome}
						onChange={(e) => setFormData({ ...formData, nome: e.target.value })}
						required
					/>
					{errors?.nome && <p style={{ color: "red" }}>{errors.nome}</p>}
				</div>

				<div className="input-container">
					<input
						type="datetime-local"
						id="dataInicial"
						className="input-field"
						value={formData.dataInicial}
						onChange={(e) => setFormData({ ...formData, dataInicial: e.target.value })}
						required
					/>
					{errors?.dataInicial && <p style={{ color: "red" }}>{errors.dataInicial}</p>}
				</div>

				<div className="input-container">
					<input
						type="datetime-local"
						id="dataFinal"
						className="input-field"
						value={formData.dataFinal}
						onChange={(e) => setFormData({ ...formData, dataFinal: e.target.value })}
						required
					/>
					{errors?.dataFinal && <p style={{ color: "red" }}>{errors.dataFinal}</p>}
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

export default EventForm;
