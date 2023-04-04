import Row from "react-bootstrap/Row";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

export default function Login() {
    const [loginData, setLoginData] = useState({email: "", password: ""});
    const navigate = useNavigate();

    async function login(event) {
        event.preventDefault();
        const response = await fetch("https://localhost:8080/token", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData),
            mode: "cors"
        })
        if (response.ok) {
            const token = await response.text();
            localStorage.setItem("token", token);
            console.log(token);
            localStorage.setItem("principal", JSON.stringify(loginData.email));
            console.log(loginData);
            navigate("/notes/all");

        } else {
            alert("Wrong username or password!");
        }
    }

    function handleChange(newValue) {
        setLoginData({...loginData, ...newValue});
        console.log(loginData)
    }


    return (
      <>
          <Container>
              <Row className="mt-5" lg="4">
                  <Form onSubmit={() => login}>
                      <Form.Group className="mb-3" controlId="formBasicEmail">
                          <Form.Label>Email address</Form.Label>
                          <Form.Control onChange={(e) => handleChange({email: e.target.value})} type="email" placeholder="Enter email" required/>
                      </Form.Group>

                      <Form.Group className="mb-3" controlId="formBasicPassword">
                          <Form.Label>Password</Form.Label>
                          <Form.Control onChange={(e) => handleChange({password: e.target.value})} type="password" placeholder="Password" required/>
                      </Form.Group>
                      <Button type="submit" variant="primary">
                          Login
                      </Button>
                  </Form>
              </Row>
          </Container>
      </>
    );
}
