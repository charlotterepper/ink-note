import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import {useEffect, useState} from "react";

export default function NavBar() {
    const principal = localStorage.getItem("principal");
    const token = localStorage.getItem("token");
    const [principalRole, setPrincipalRole] = useState(null);

    function handleLogout() {
        localStorage.removeItem("token");
        localStorage.removeItem("principal");
        alert("You have logged out successfully!")
        window.location.reload();
    }

    function fetchPrincipalRole() {
        return fetch("http://localhost:8080/user/role/" + principal.slice(1, principal.length - 1), {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
                Authorization: "Bearer " + localStorage.getItem("token")
            }
        })
            .then(response => response.text())
            .then(data => {
                setPrincipalRole(data);
                localStorage.setItem("role", data);
            });
    }

    useEffect(() => {
        if (principal) {
            fetchPrincipalRole();
        }
    }, [principal]);

    return (
        <>
            <Navbar bg="light" variant="light">
                <Container>
                    <Navbar.Brand href="/">InkNote</Navbar.Brand>
                    <Nav className="me-auto">
                        {!token && <Nav.Link href="/registration">Sign up</Nav.Link>}
                        {principalRole === "ADMIN" && <Nav.Link href="/admin/users/all">All Users</Nav.Link>}
                        {token && <>
                            <Nav.Link href="/notes/all">All Notes</Nav.Link>
                            <Nav.Link href="/notes/add">Add Note</Nav.Link>
                            <Nav.Link onClick={handleLogout}>Logout</Nav.Link>
                        </>}
                    </Nav>
                    {
                        principal
                            ? <>
                                <Nav className="ml-auto">
                                    <Navbar.Text className="ml-auto">
                                        Welcome <strong>{principal.slice(1, principal.length - 1)}</strong>!
                                    </Navbar.Text>
                                </Nav>
                            </>
                            : ""
                    }
                </Container>
            </Navbar>
        </>
    );
}
