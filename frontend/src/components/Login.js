import NavBar from "./NavBar";
import Row from "react-bootstrap/Row";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";

export default function Login() {
    return (
      <>
        <NavBar/>
          <Container>
              <Row style={{marginTop: "50px"}} xs lg="4">
                  <Form>
                      <Form.Group className="mb-3" controlId="formBasicEmail">
                          <Form.Label>Email address</Form.Label>
                          <Form.Control type="email" placeholder="Enter email"/>
                      </Form.Group>

                      <Form.Group className="mb-3" controlId="formBasicPassword">
                          <Form.Label>Password</Form.Label>
                          <Form.Control type="password" placeholder="Password"/>
                      </Form.Group>
                      <Button variant="primary">
                          Login
                      </Button>
                  </Form>
              </Row>
          </Container>
      </>
    );
}
