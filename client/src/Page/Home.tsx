import { Box, Text } from "@chakra-ui/react"
import Navbar from "../components/Navbar";

const Home = () => {
    return (
        <>
            <Navbar />
            <Box 
                display="flex"
                flexDirection="column"
                width="100%"
                height="100%"
                alignItems="center"
                justifyContent="center"
            >
                <Text fontSize='2xl' textAlign="center">
                    Welcome to Infokey, your personal password manager. 
                </Text>
                <Text fontSize='2xl' textAlign="center">
                    Let's get started 
                </Text>
            </Box>
        </>
        
    )
}

export default Home;