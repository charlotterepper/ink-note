import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";

export default function NavBar () {
    return(
      <>
          <Navbar bg="light" variant="light">
              <Container>
                  <Navbar.Brand href="/">InkNote</Navbar.Brand>
                  <Nav className="me-auto">
                      <Nav.Link href="/notes/all">All Notes</Nav.Link>
                      <Nav.Link href="/notes/add">Add Note</Nav.Link>
                      <Nav.Link href="/registration">Sign up</Nav.Link>
                  </Nav>
              </Container>
          </Navbar>
      </>
    );
}
