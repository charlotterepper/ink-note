import {useEffect, useState} from "react";
import Container from "react-bootstrap/Container";
import NavBar from "./NavBar";
import Login from "./Login";

export default function Welcome() {
    const [message, setMessage] = useState("");

    function fetchWelcomeMessage() {
        return fetch("http://localhost:8080/", {
            headers: {
                "Content-Type": "text/plain"
            }
        })
            .then(response => response.text())
            .then(data => setMessage(data));
    }

    useEffect(() => {
        fetchWelcomeMessage();
    }, []);

    return (
        <>
            <NavBar />
            <Container className="mt-5">
                <h1 className="text-primary">{message}</h1>
                <h2>Please login to your account</h2>
                <Login />
            </Container>
        </>
    );
}
