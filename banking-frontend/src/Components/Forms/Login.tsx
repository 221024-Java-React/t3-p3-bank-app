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
`;
const SubmitButton = styled.input`
    margin: 10px;
`;

const initLoginInputs = {
    email_login: "",
    password_login: "",
};

const initLoginAuthInputs = {
    passcode: "",
};

const Login: React.FC = () => {
    const [loginAuth, setLoginAuth] = useState<boolean>(false);
    const [loginInputs, setLoginInputs] = useState(initLoginInputs);
    const [loginAuthInputs, setLoginAuthInputs] = useState(initLoginAuthInputs);

    const { setCurrentUser, loginUser, currentUser } = useContext(UserContext) as UserContextState;
    const navigate = useNavigate();

    const handleLoginFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setLoginInputs(prev => ({ ...prev, [name]: value }));
    };

    const handleLoginFormSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const { email_login: email, password_login: password } = loginInputs;

            const { data: thisUser } = await axInst.post("/users/login", { email, password });
            setLoginInputs(initLoginInputs);
            setCurrentUser(thisUser);

            setLoginAuth(true);
        } catch (e) {
            console.log(e);
        }
    };

    const handleLoginAuthFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setLoginAuthInputs(prev => ({ ...prev, [name]: value }));
    };

    const handleLoginAuthFormSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const { passcode } = loginAuthInputs;
            console.log(passcode);

            const { data: thisUser } = await axInst.post("/users/login_Auth", {
                email: currentUser.email,
                token: passcode,
            });

            loginUser(thisUser);
            setLoginAuthInputs(initLoginAuthInputs);
            navigate("/");
        } catch (e) {
            console.log(e);
        }
    };

    return (
        <Container>
            {loginAuth === false && (
                <Form onSubmit={handleLoginFormSubmit}>
                    <Label htmlFor="email_login">Email</Label>
                    <Input
                        type="text"
                        name="email_login"
                        id="email_login"
                        onChange={handleLoginFormChange}
                    />
                    <Label htmlFor="password_login">Password</Label>
                    <Input
                        type="password"
                        name="password_login"
                        id="password_login"
                        onChange={handleLoginFormChange}
                    />
                    <SubmitButton type="submit" value="Log In" />
                </Form>
            )}
            {loginAuth === true && (
                <Form onSubmit={handleLoginAuthFormSubmit}>
                    <Label htmlFor="passcode">Enter Twilio Passcode</Label>
                    <Input
                        type="text"
                        name="passcode"
                        id="passcode"
                        onChange={handleLoginAuthFormChange}
                    />
                    <SubmitButton type="submit" value="Submit Passcode" />
                </Form>
            )}
        </Container>
    );
};

export default Login;
