import Row from "react-bootstrap/Row";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import {useState} from "react";

export default function Login() {
    const [loginData, setLoginData] = useState({email: "", password: ""});

    async function submitLoginData(event) {
        event.preventDefault();
        const response = await fetch("http://localhost:8080/auth/token", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData),
            mode: "cors"
        });
        if (response.ok) {
            const token = await response.text();
            localStorage.setItem("token", token);
            localStorage.setItem("principal", JSON.stringify(loginData.email));
            window.location.reload();
        } else {
            alert("Wrong username or password!");
        }
    }

    function handleChange(newValue) {
        setLoginData({...loginData, ...newValue});
    }

    return (
        <>
            <h2>Please login to your account</h2>
            <Row className="mt-5 w-25">
                <Form onSubmit={(event) => submitLoginData(event)}>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control onChange={(e) => handleChange({email: e.target.value})} type="email"
                                      placeholder="Enter email" required/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control onChange={(e) => handleChange({password: e.target.value})} type="password"
                                      placeholder="Enter password" required/>
                    </Form.Group>
                    <Button type="submit" variant="primary">
                        Login
                    </Button>
                </Form>
            </Row>
        </>
    );
}
