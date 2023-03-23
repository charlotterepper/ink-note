import {useEffect, useState} from "react";
import Card from "react-bootstrap/Card"
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import NavBar from "./NavBar";
import editImg from "../img/pencil.png";
import {Link, useNavigate} from "react-router-dom";

export default function AllNotes() {
    const [notes, setNotes] = useState();
    const navigate = useNavigate();

    function fetchNotes() {
        return fetch("http://localhost:8080/notes/all")
            .then(response => response.json())
            .then(data => setNotes(data));
    }

    function setNoteData(noteData) {
        localStorage.setItem("noteId", noteData.id);
        localStorage.setItem("noteTitle", noteData.title);
        localStorage.setItem("noteDescription", noteData.description);
        navigate("/notes/update/" + noteData.id);
    }

    useEffect(() => {
        fetchNotes();
    }, []);

    return (
        <>
            <NavBar />
            <Container>
                <Row md={4}>
                    {notes?.map((note, index) => {
                        return (
                            <Col key={index}>
                                <Card style={{width: "18rem", marginTop: "42px"}}>
                                    <Card.Body>
                                        <Card.Title>{note.title}</Card.Title>
                                        <Card.Text>{note.description}</Card.Text>
                                        <Link to={"/notes/update/" + note.id}>
                                            <Button variant="primary" onClick={() => setNoteData({id: note.id, title: note.title, description: note.description})}>
                                                <img src={editImg} alt="pencil" width="20"/>
                                            </Button>
                                        </Link>
                                    </Card.Body>
                                </Card>
                            </Col>
                        );
                    })}
                </Row>
            </Container>
        </>
    );
}
