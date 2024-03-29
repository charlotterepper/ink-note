import {useEffect, useState} from "react";
import Card from "react-bootstrap/Card"
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import editImg from "../img/pencil.png";
import deleteImg from "../img/trash.png";
import {Link, useNavigate} from "react-router-dom";

export default function AllNotes() {
    const [notes, setNotes] = useState();
    const navigate = useNavigate();

    function fetchNotes() {
        return fetch("http://localhost:8080/notes/all", {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                Authorization: "Bearer " + localStorage.getItem("token")
            }
        })
            .then(response => response.json())
            .then(data => setNotes(data));
    }

    function setNoteData(noteData) {
        localStorage.setItem("noteTitle", noteData.title);
        localStorage.setItem("noteDescription", noteData.description);
        navigate("/notes/update/" + noteData.id);
    }

    async function deleteNote(noteId) {
        try {
            const result = await fetch("http://localhost:8080/notes/delete/" + noteId, {
                method: "DELETE",
                headers: {
                    "Accept": 'Access-Control-Allow-Origin',
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            if (result.status !== 200) {
                alert("An error has occurred: " + result.status);
            }
            return result;
        } catch (error) {
            console.log(error);
        }
    }

    async function handleDelete(noteId) {
        const result = await deleteNote(noteId);
        if (result && result.status === 200) {
            navigate("/notes/all");
        }
    }

    useEffect(() => {
        fetchNotes();
    }, []);

    return (
        <>
            {notes?.length === 0 ? (
                <Container>
                    <div className="mt-5">You don't have any notes yet.</div>
                </Container>
            ) : (
                <Container>
                    <Row md={4}>
                        {notes?.map((note, index) => {
                            return (
                                <Col key={index}>
                                    <Card className="w-100 mt-5">
                                        <Card.Body>
                                            <Card.Title>{note.title}</Card.Title>
                                            <Card.Text>{note.description}</Card.Text>
                                            <Link to={"/notes/update/" + note.id} style={{marginRight: "10px"}}>
                                                <Button className="mr-2" variant="secondary"
                                                        onClick={() => setNoteData({
                                                            title: note.title,
                                                            description: note.description
                                                        })}>
                                                    <img src={editImg} alt="pencil" width="20"/>
                                                </Button>
                                            </Link>
                                            <Link to={"/notes/delete/" + note.id}>
                                                <Button variant="danger" onClick={() => handleDelete(note.id)}>
                                                    <img src={deleteImg} alt="trash" width="20"/>
                                                </Button>
                                            </Link>
                                        </Card.Body>
                                    </Card>
                                </Col>
                            );
                        })}
                    </Row>
                </Container>
            )}
        </>
    );
}
