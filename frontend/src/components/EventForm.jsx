const EventForm = ({ formData, setFormData, onSubmit, errors }) => {
	return (
	<form onSubmit={onSubmit}>
		<div>
			<input
				type="text"
				placeholder="Nome"
				value={formData.nome}
				onChange={(e) => setFormData({ ...formData, nome: e.target.value })}
				required
			/>
			{errors?.nome && <p style={{ color: "red" }}>{errors.nome}</p>}
		</div>

		<div>
			<input
				type="datetime-local"
				value={formData.dataInicio}
				onChange={(e) => setFormData({ ...formData, dataInicio: e.target.value })}
				required
			/>
			{errors?.dataInicio && <p style={{ color: "red" }}>{errors.dataInicio}</p>}
		</div>

		<div>
			<input
				type="datetime-local"
				value={formData.dataFim}
				onChange={(e) => setFormData({ ...formData, dataFim: e.target.value })}
				required
			/>
			{errors?.dataFim && <p style={{ color: "red" }}>{errors.dataFim}</p>}
		</div>

		<button type="submit">Salvar</button>
	</form>
	);
};

export default EventForm;
