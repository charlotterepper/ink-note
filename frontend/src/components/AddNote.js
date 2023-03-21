import NavBar from "./NavBar";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";
import {useState} from "react";

export default function AddNote() {
    const [data, setData] = useState({title: "", description: ""});

    async function submitNote(e) {
        e.preventDefault();
        try {
            let result = await fetch("http://localhost:8080/notes/add", {
                method: "POST",
                body: JSON.stringify(data),
                header: {"Content-Type": "application/json"}
            })
            if (result.status === 200) {
                alert("Your note has been created successfully!");
            } else {
                alert("An error has occurred: " + result.status);
            }
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <>
            <NavBar/>
            <Container>
                <Row style={{marginTop: "50px"}} xs lg="2">
                    <Form onSubmit={submitNote}>
                        <Form.Group className="mb-3" controlId="title">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text"/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="description">
                            <Form.Label>Description</Form.Label>
                            <Form.Control as="textarea" rows={3}/>
                        </Form.Group>
                        <Button type="submit">Create Note</Button>
                    </Form>
                </Row>
            </Container>
        </>
    );
}
