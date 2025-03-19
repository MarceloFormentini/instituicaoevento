import axios from "axios";

const API_URL = "http://localhost:9090"; // Ajuste conforme necessário

const api = axios.create({
	baseURL: API_URL,
	headers: { "Content-Type": "application/json" },
});

// INSTITUICAO
export const getInstituicoes = (page = 0, size = 10, sort = "id") => {
	return api.get(`/instituicao?page=${page}&size=${size}&sort=${sort}`);
};
export const getInstituicaoById = (id) => api.get(`/instituicao/${id}`);
export const createInstituicao = (data) => api.post("/instituicao", data);
export const updateInstituicao = (data) => api.put("/instituicao", data);
export const deleteInstituicao = (id) => api.delete(`/instituicao/${id}`);

// EVENTO
export const getEventosByInstituicao = (instituicaoId, page = 0, size = 10, sort = "dataInicial,asc") => {
	return api.get(`/evento/${instituicaoId}?page=${page}&size=${size}&sort=${sort}`);
};
export const getEventoById = (instituicaoId, id) => api.get(`/evento/${instituicaoId}/${id}`);
export const createEvento = (instituicaoId, data) => api.post(`/evento/${instituicaoId}`, data);
export const updateEvento = (instituicaoId, data) => api.put(`/evento/${instituicaoId}`, data);
export const deleteEvento = (id) => api.delete(`/evento/${id}`);

export default api;