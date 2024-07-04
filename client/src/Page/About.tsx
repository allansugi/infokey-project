import { Container, Text } from "@chakra-ui/react"
import Navbar from "../components/Navbar";

const About = () => {
    return (
        <>
            <Navbar />
            <Container>
                <Text>This is About page</Text>
            </Container>
        </>
        
    )
}

export default About;