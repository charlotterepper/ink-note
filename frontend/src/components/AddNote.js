import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

export default function AddNote() {
    const principal = localStorage.getItem("principal");
    const principalEmail = principal.slice(1, principal.length-1);

    const [data, setData] = useState({
        id: "",
        title: "",
        description: "",
        userDTO: {
            email: ""
        }
    });
    const navigate = useNavigate();

    function updateData(updatedData) {
        setData({...data, ...updatedData});
        data.userDTO.email = principalEmail;
    }

    async function submitNote() {
        try {
            const result = await fetch("http://localhost:8080/notes/add", {
                method: "POST",
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
        const result = await submitNote();
        if (result && result.status === 200) {
            navigate("/notes/all");
        }
    }

    return (
        <>
            <Container>
                <Row className="mt-5 w-50">
                    <h1>Add Note</h1>
                    <Form className="mt-4">
                        <Form.Group className="mb-3" controlId="title">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text" onChange={(e) => updateData({title: e.target.value})}/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="description">
                            <Form.Label>Description</Form.Label>
                            <Form.Control as="textarea" rows={3}
                                          onChange={(e) => updateData({description: e.target.value})}/>
                        </Form.Group>
                        <Button onClick={handleSubmit}>Create Note</Button>
                    </Form>
                </Row>
            </Container>
        </>
    );
}
