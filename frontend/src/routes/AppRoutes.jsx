import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import InstitutionList from "../pages/InstitutionList";
import InstitutionCreate from "../pages/InstitutionCreate";
import InstitutionEdit from "../pages/InstitutionEdit";
import EventList from "../pages/EventList";
import EventCreate from "../pages/EventCreate";
import EventEdit from "../pages/EventEdit";

const AppRoutes = () => (
	<Router>
		<Routes>
			<Route path="/" element={<InstitutionList />} />
			<Route path="/create" element={<InstitutionCreate />} />
			<Route path="/edit/:id" element={<InstitutionEdit />} />
			<Route path="/evento/:instituicaoId" element={<EventList />} />
			<Route path="/evento/:id" element={<EventCreate />} />
			<Route path="/evento/:instituicaoId" element={<EventEdit />} />
		</Routes>
	</Router>
);

export default AppRoutes;