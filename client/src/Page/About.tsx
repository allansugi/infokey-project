import { Container, Heading, Link, Stack, Text } from "@chakra-ui/react"
import Navbar from "../components/Navbar";
import { EmailIcon } from "@chakra-ui/icons";


const About = () => {
    return (
        <>
            <Navbar />
            <Container>
                <Stack>
                    <Heading>What is InfoKey</Heading>
                    <Text>
                        InfoKey is a password manager web application
                        where user can store account details and password
                        so you don't need to remember your account details
                        the next time you login. makes your login experience easier
                    </Text>
                    <Heading>Motivation of InfoKey</Heading>
                    <Text>
                        This web application is part of my personal project that i spend
                        in my free time. I am making this project to learn how to store and obtain
                        data securely. Since this project is for educational purposes, It is recommended to
                        not to put your personal or sensitive data inside this application 
                        (although feel free to play with this application) and instead use established
                        ones like bitwarden or keepassXC.
                    </Text>
                    <Heading>Contact</Heading>
                    <Text>Email <EmailIcon /> : allansugi@gmail.com</Text>
                    <Text>GitHub: <Link href="https://github.com/allansugi">allansugi</Link></Text>
                    
                </Stack>
                
            </Container>
        </>
        
    )
}

export default About;