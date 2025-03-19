import "../styles/InstitutionForm.css";

const InstitutionForm = ({ title, formData, setFormData, onSubmit, errors }) => {
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
				<button type="submit" className="save-button">
					Salvar
				</button>
			</form>
		</div>
		);
	};

	export default InstitutionForm;