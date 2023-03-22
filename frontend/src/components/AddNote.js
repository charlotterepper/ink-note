import NavBar from "./NavBar";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";
import {useState} from "react";

export default function AddNote() {
    const [data, setData] = useState({title: "", description: ""});

    function updateData(updatedData) {
        setData({...data, ...updatedData});
    }

    async function submitNote() {
        try {
            let result = await fetch("http://localhost:8080/notes/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
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
                            <Form.Control type="text" onChange={(e) => updateData({title: e.target.value})}/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="description">
                            <Form.Label>Description</Form.Label>
                            <Form.Control as="textarea" rows={3}
                                          onChange={(e) => updateData({description: e.target.value})}/>
                        </Form.Group>
                        <Button type="submit">Create Note</Button>
                    </Form>
                </Row>
            </Container>
        </>
    );
}
