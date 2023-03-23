import './App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import AllNotes from "./components/AllNotes";
import AddNote from "./components/AddNote";
import 'bootstrap/dist/css/bootstrap.min.css';
import UpdateNote from "./components/UpdateNote";

export default function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" exact element={<AllNotes/>}/>
                <Route path="/notes/all" exact element={<AllNotes/>}/>
                <Route path="/notes/add" exact element={<AddNote/>}/>
                <Route path="/notes/update/:id" exact element={<UpdateNote/>}/>
            </Routes>
        </Router>
    );
}
