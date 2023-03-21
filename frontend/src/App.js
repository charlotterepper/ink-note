import './App.css';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AllNotes from "./components/AllNotes";
import AddNote from "./components/AddNote";

export default function App() {
  return(
    <Router>
        <Routes>
            <Route path="/" component={AllNotes}></Route>
            <Route path="/" component={AddNote}></Route>
        </Routes>
    </Router>
  );
}
