import Container from "react-bootstrap/Container";
import Login from "./Login";
import AllNotes from "./AllNotes";

export default function Welcome() {
    return (
        <>
            <Container>
                {
                    !localStorage.getItem("token")
                    ? <><h1 className="text-primary mt-5">Welcome to InkNote</h1><Login /></>
                    : <AllNotes />
                }
            </Container>
        </>
    );
}
