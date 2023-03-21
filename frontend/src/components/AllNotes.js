import {useEffect, useState} from "react";
import Card from "react-bootstrap/Card"
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import NavBar from "./NavBar";

export default function AllNotes() {
    const [notes, setNotes] = useState();

    function fetchNotes() {
        return fetch("http://localhost:8080/notes/all")
            .then(response => response.json())
            .then(data => setNotes(data));
    }

    useEffect(() => {
        fetchNotes();
    }, []);

    return (
        <>
            <NavBar />
            <Container>
                <Row>
                    {notes?.map((note, index) => {
                        return (
                            <Col key={index}>
                                <Card style={{width: "18rem", marginTop: "42px"}}>
                                    <Card.Body>
                                        <Card.Title>{note.title}</Card.Title>
                                        <Card.Text>{note.description}</Card.Text>
                                        <Button variant="primary">Update Note</Button>
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
