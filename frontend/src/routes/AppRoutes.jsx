import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import InstitutionList from "../pages/InstitutionList";
import InstitutionCreate from "../pages/InstitutionCreate";
import InstitutionEdit from "../pages/InstitutionEdit";
import EventList from "../pages/EventList";
import EventCreate from "../pages/EventCreate";
import EventEdit from "../pages/EventEdit";

const AppRoutes = () => (
	<Router>
		<Routes>
			{/* Define a rota raiz da aplicação */}
			<Route path="/" element={<Navigate to="/instituicao" replace />} />

			{/* Instituição */}
			<Route path="/instituicao" element={<InstitutionList />} />
			<Route path="/instituicao/create" element={<InstitutionCreate />} />
			<Route path="/instituicao/edit/:id" element={<InstitutionEdit />} />
			
			{/* Evento */}
			<Route path="/instituicao/:instituicaoId/evento" element={<EventList />} />
			<Route path="/instituicao/:instituicaoId/evento/create" element={<EventCreate />} />
			<Route path="/instituicao/:instituicaoId/evento/edit/:id" element={<EventEdit />} />
		</Routes>
	</Router>
);

export default AppRoutes;