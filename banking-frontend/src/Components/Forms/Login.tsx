import React, { useContext, useState } from "react";
import styled from "styled-components";
import { UserContext } from "../../Context/UserContext";
import { UserContextState } from "../../Interfaces/User";
import { axInst } from "../../Util/axInstance";
import { useNavigate } from "react-router";

const Container = styled.div`
    display: grid;
    place-items: center;
    height: 100%;
    color: ${props => props.theme.color};
`;
const Form = styled.form`
    display: flex;
    flex-direction: column;
`;
const Label = styled.label`
    margin: 3px;
`;
const Input = styled.input`
    margin: 10px;
    background: transparent;
    border: 1px solid ${props => props.theme.border};
    color: ${props => props.theme.color};
    padding: 5px;
    outline: none;
`;
const SubmitButton = styled.input`
    margin: 10px;
`;

const initInputs = {
    email: "",
    password: "",
    passcode: "",
};

const Login = () => {
    const [showAuthScreen, setShowAuthScreen] = useState<boolean>(false);
    const [inputs, setInputs] = useState(initInputs);

    const { loginUser, authenticateUser } = useContext(UserContext) as UserContextState;
    const navigate = useNavigate();

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setInputs(prev => ({ ...prev, [name]: value }));
    };

    const handleLoginFormSubmit = (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();

        const { email, password } = inputs;
        loginUser(email, password);

        setShowAuthScreen(true);
    };

    const handleAuthFormSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();

        const { email, passcode } = inputs;
        authenticateUser(email, passcode);

        setInputs(initInputs);
        navigate("/");
    };

    return (
        <Container>
            {!showAuthScreen && (
                <Form onSubmit={handleLoginFormSubmit}>
                    <Label htmlFor="email">Email</Label>
                    <Input type="text" name="email" id="email" onChange={handleInputChange} />
                    <Label htmlFor="password_login">Password</Label>
                    <Input
                        type="password"
                        name="password"
                        id="password"
                        onChange={handleInputChange}
                    />
                    <SubmitButton type="submit" value="Log In" />
                </Form>
            )}
            {showAuthScreen && (
                <Form onSubmit={handleAuthFormSubmit}>
                    <Label htmlFor="passcode">Enter Twilio Passcode</Label>
                    <Input type="text" name="passcode" id="passcode" onChange={handleInputChange} />
                    <SubmitButton type="submit" value="Submit Passcode" />
                </Form>
            )}
        </Container>
    );
};

export default Login;
