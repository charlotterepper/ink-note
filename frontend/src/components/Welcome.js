import {useEffect, useState} from "react";
import Container from "react-bootstrap/Container";
import Login from "./Login";
import AllNotes from "./AllNotes";

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
            <Container>
                {
                    !localStorage.getItem("token")
                    ? <><h1 className="text-primary mt-5">{message}</h1><Login /></>
                    : <AllNotes />
                }
            </Container>
        </>
    );
}
