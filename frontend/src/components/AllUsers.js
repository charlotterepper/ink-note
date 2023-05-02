import {useEffect, useState} from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import {Table} from "react-bootstrap";

export default function AllNotes() {
    const [users, setUsers] = useState();

    function fetchUsers() {
        return fetch("http://localhost:8080/admin/users/all", {
            headers: {
                'Content-Type': 'application/json',
                Authorization: "Bearer " + localStorage.getItem("token")
            }
        })
            .then(response => response.json())
            .then(data => setUsers(data));
    }

    useEffect(() => {
        fetchUsers();
    }, []);

    return (
        <>
            {users?.length === 0 ? (
                <Container>
                    <div className="mt-5">You don't have any notes yet.</div>
                </Container>
            ) : (
                <Container className="mt-5">
                    <Row md={4}>
                        <Table striped bordered>
                            <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Role</th>
                            </tr>
                            </thead>
                            <tbody>
                            {users?.map((user, index) => {
                                return (
                                    <tr key={index}>
                                        <td>{user.firstName}</td>
                                        <td>{user.lastName}</td>
                                        <td>{user.email}</td>
                                        <td>{user.role}</td>
                                    </tr>
                                );
                            })}
                            </tbody>
                        </Table>
                    </Row>
                </Container>
            )}
        </>
    );
}
