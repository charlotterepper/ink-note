import NavBar from "./NavBar";
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";

export default function AddNote() {
    return (
        <>
            <NavBar/>
            <Container>
                <Row style={{marginTop: "50px"}} xs lg="2">
                    <Form>
                        <Form.Group className="mb-3" controlId="title">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text"/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="description">
                            <Form.Label>Description</Form.Label>
                            <Form.Control as="textarea" rows={3}/>
                        </Form.Group>
                        <Button>Create Note</Button>
                    </Form>
                </Row>
            </Container>
        </>
    );
}
