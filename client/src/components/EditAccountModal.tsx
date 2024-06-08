import { useDisclosure, Button, Modal, ModalOverlay, ModalContent, ModalHeader, ModalCloseButton, ModalBody, ModalFooter, Input, Stack, InputGroup, InputRightElement } from "@chakra-ui/react"
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const EditAccountModal = () => {
    const {onClose} = useDisclosure();
    const navigate = useNavigate();
    const [show, setShow] = useState(false);

    const handleClose = () => {
        onClose();
        navigate(-1);
    }

    const handleShowPassword = () => {
        setShow(!show);
    }

    return (
        <>
        <Modal isOpen={true} onClose={onClose}>
            <ModalOverlay />
            <ModalContent>
            <ModalHeader>Edit your account</ModalHeader>
            <ModalCloseButton onClick={handleClose}/>

            {/* TODO: add input for account */}
            <ModalBody>
                <Stack spacing="12px">
                    <Input placeholder="Account name"/>
                    <Input placeholder="Username"/>
                    <InputGroup size='md'>
                        <Input
                            pr='4.5rem'
                            type={show ? 'text' : 'password'}
                            placeholder='Enter password'
                        />
                        <InputRightElement width='4.5rem'>
                            <Button h='1.75rem' size='sm' onClick={handleShowPassword}>
                            {show ? 'Hide' : 'Show'}
                            </Button>
                        </InputRightElement>
                    </InputGroup>
                </Stack>
                
            </ModalBody>

            <ModalFooter>
                <Button colorScheme='blue' mr={3} onClick={handleClose}>
                Close
                </Button>
                <Button variant='ghost'>Submit</Button>
            </ModalFooter>
            </ModalContent>
        </Modal>
        </>
    )
}

export default EditAccountModal;