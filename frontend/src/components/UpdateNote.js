import NavBar from "./NavBar";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";
import {useState} from "react";
import {useNavigate, useParams} from "react-router-dom";

export default function UpdateNote() {
    const {noteId} = useParams();
    const [data, setData] = useState({id: noteId, title: "", description: ""});
    const navigate = useNavigate();

    function updateData(updatedData) {
        setData({...data, ...updatedData});
    }

    async function updateNote() {
        try {
            const result = await fetch("http://localhost:8080/notes/update/" + noteId, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
            if (result.status === 200) {
                alert("Your note has been updated successfully!");
            } else {
                alert("An error has occurred: " + result.status);
            }
            return result;
        } catch (error) {
            console.log(error);
        }
    }

    async function handleSubmit() {
        const result = await updateNote();
        if (result && result.status === 200) {
            navigate("/notes/all");
        }
    }

    return (
        <>
            <NavBar/>
            <Container>
                <Row style={{marginTop: "50px"}} xs lg="2">
                    <Form>
                        <Form.Group className="mb-3" controlId="title">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text" defaultValue={localStorage.getItem("noteTitle")} onChange={(e) => updateData({title: e.target.value})}/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="description">
                            <Form.Label>Description</Form.Label>
                            <Form.Control as="textarea" rows={3} defaultValue={localStorage.getItem("noteDescription")}
                                          onChange={(e) => updateData({description: e.target.value})}/>
                        </Form.Group>
                        <Button onClick={handleSubmit}>Update Note</Button>
                    </Form>
                </Row>
            </Container>
        </>
    );
}
