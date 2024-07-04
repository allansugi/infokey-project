import { Container, Heading } from "@chakra-ui/react"
import Navbar from "../components/Navbar";

const Home = () => {
    return (
        <>
            <Navbar />
            <Container>
                <Heading fontFamily="roboto">Welcome to Infokey, your password manager</Heading>
                <Heading fontFamily="roboto">Login to get started</Heading>
            </Container>
        </>
        
    )
}

export default Home;