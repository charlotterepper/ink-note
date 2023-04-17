import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";
import {useState} from "react";
import {useNavigate, useParams} from "react-router-dom";

export default function UpdateNote() {
    const {noteId} = useParams();
    const principal = localStorage.getItem("principal");
    const principalEmail = principal.slice(1, principal.length-1);
    const [data, setData] = useState({
        id: noteId,
        title: localStorage.getItem("noteTitle"),
        description: localStorage.getItem("noteDescription"),
        userDTO: {
            email: ""
        }
    });
    const navigate = useNavigate();

    function updateData(updatedData) {
        setData({
            ...data,
            ...updatedData
        });
        data.userDTO.email = principalEmail;
        console.log("updatedData title: " + updatedData.title)
        console.log("updatedData description: " + updatedData.description)
    }

    async function updateNote() {
        try {
            const result = await fetch("http://localhost:8080/notes/update", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify(data)
            })
            if (result.status !== 200) {
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
            <Container className="mt-5">
                <h1>Update Note</h1>
                <Row style={{marginTop: "50px"}} xs lg="2">
                    <Form>
                        <Form.Group className="mb-3" controlId="title">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text" defaultValue={localStorage.getItem("noteTitle")}
                                          onChange={(e) => updateData({title: e.target.value})}/>
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
