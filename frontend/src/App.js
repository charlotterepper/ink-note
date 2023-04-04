import './App.css';
import {BrowserRouter as Router, Link, Route, Routes, Navigate} from "react-router-dom";
import AllNotes from "./components/AllNotes";
import AddNote from "./components/AddNote";
import 'bootstrap/dist/css/bootstrap.min.css';
import UpdateNote from "./components/UpdateNote";
import DeleteNote from "./components/DeleteNote";
import Registration from "./components/Registration";
import Login from "./components/Login";
import Welcome from "./components/Welcome";
import jwt_decode from 'jwt-decode';
import PrivateRoutes from "./components/PrivateRoutes";

export default function App() {
    return (
        <Router>
            <Routes>
                <Route element={<PrivateRoutes />}>
                    <Route path="/notes/all" exact element={<AllNotes/>}/>
                    <Route path="/notes/add" exact element={<AddNote/>}/>
                    <Route path="/notes/update/:noteId" exact element={<UpdateNote/>}/>
                    <Route path="/notes/delete/:noteId" exact element={<DeleteNote/>}/>
                </Route>
                <Route path="/" exact element={<Welcome/>}/>
                <Route path="/registration" exact element={<Registration/>}/>
            </Routes>
        </Router>
    );
}
