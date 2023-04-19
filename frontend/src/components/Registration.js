import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

export default function Registration() {
    const [user, setUser] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
    });
    const navigate = useNavigate();

    async function registerUser() {
        try {
            const result = await fetch("http://localhost:8080/registration", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(user)
            })
            if (result.status !== 200) {
                alert("An error has occurred: " + result.status);
            }
        } catch (error) {
            console.log(error);
        }
    }

    function updateUser(updatedUser) {
        setUser({
            ...user,
            ...updatedUser
        });
    }

    async function isEmailTaken() {
        const response = await fetch("http://localhost:8080/email-taken/" + user.email, {
            method: "GET"
        });
        const emailTaken = await response.text();
        console.log(emailTaken)
        console.log(emailTaken === "true");
        return emailTaken === "true";

    }

    async function handleSubmit() {
        const emailTaken = await isEmailTaken();
        console.log("emailTaken " + emailTaken)
        if (emailTaken === true) {
            alert("User with this email already exists");
        } else {
            const result = await registerUser();
            if (result && result.status === 200) {
                navigate("/");
            }
        }
    }

    return (
        <>
            <Container className="mt-5">
                <h1>Registration</h1>
                <Row className="mt-5 w-25">
                    <Form>
                        <Form.Group className="mb-3" controlId="formBasicFirstName">
                            <Form.Label>First name</Form.Label>
                            <Form.Control type="firstName" placeholder="Enter first name"
                                          onChange={(e) => updateUser({firstName: e.target.value})}/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formBasicLastName">
                            <Form.Label>Last name</Form.Label>
                            <Form.Control type="lastName" placeholder="Enter last name"
                                          onChange={(e) => updateUser({lastName: e.target.value})}/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Email address</Form.Label>
                            <Form.Control type="email" placeholder="Enter email"
                                          onChange={(e) => updateUser({email: e.target.value})}/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" placeholder="Enter password"
                                          onChange={(e) => updateUser({password: e.target.value})}/>
                        </Form.Group>
                        <Button variant="primary" onClick={handleSubmit}>Sign up</Button>
                    </Form>
                </Row>
            </Container>
        </>
    );
}
