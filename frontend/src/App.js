import './App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import AllNotes from "./components/AllNotes";
import AddNote from "./components/AddNote";
import 'bootstrap/dist/css/bootstrap.min.css';
import UpdateNote from "./components/UpdateNote";
import DeleteNote from "./components/DeleteNote";
import Registration from "./components/Registration";
import Welcome from "./components/Welcome";
import PrivateRoutes from "./components/PrivateRoutes";
import NavBar from "./components/NavBar";
import AllUsers from "./components/AllUsers";
import AdminRoutes from "./components/AdminRoutes";

export default function App() {
    return (
        <Router>
            <NavBar/>
            <Routes>
                <Route element={<PrivateRoutes/>}>
                    <Route path="/notes/all" exact element={<AllNotes/>}/>
                    <Route path="/notes/add" exact element={<AddNote/>}/>
                    <Route path="/notes/update/:noteId" exact element={<UpdateNote/>}/>
                    <Route path="/notes/delete/:noteId" exact element={<DeleteNote/>}/>
                </Route>
                <Route element={<AdminRoutes/>}>
                    <Route path="/admin/users/all" exact element={<AllUsers/>}/>
                </Route>
                <Route path="/" exact element={<Welcome/>}/>
                <Route path="/registration" exact element={<Registration/>}/>
            </Routes>
        </Router>
    );
}
