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
    email: "",
    password: "",
};

const initLoginAuthInputs = {
    email: "",
    passcode: 0,
};

const Login: React.FC = () => {
    const [loginAuth, setLoginAuth] = useState<boolean>(false);
    const [loginInputs, setLoginInputs] = useState(initLoginInputs);
    const [loginAuthInputs, setLoginAuthInputs] = useState(initLoginAuthInputs);

    const { setCurrentUser, loginUser } = useContext(UserContext) as UserContextState;
    const navigate = useNavigate();

    const handleLoginFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setLoginInputs(prev => ({ ...prev, [name]: value }));
    };

    const handleLoginFormSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const { data: thisUser } = await axInst.post("/users/login", loginInputs);

            loginUser(thisUser);
            setLoginInputs(initLoginInputs);
            return setLoginAuth(true);
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
            const { email, passcode } = loginAuthInputs;

            const { data: thisUser } = await axInst.post("/login_Auth", {
                email,
                passcode,
            });

            if (thisUser) {
                setCurrentUser(thisUser);
                return navigate("/home");
            }
        } catch (e) {
            console.log(e);
        }
    };

    return (
        <Container>
            {!loginAuth && (
                <Form onSubmit={handleLoginFormSubmit}>
                    <Label htmlFor="email_login">Email</Label>
                    <Input
                        type="text"
                        name="email_login"
                        id="email_login"
                        value={loginInputs.email}
                        onChange={handleLoginFormChange}
                    />
                    <Label htmlFor="password_login">Password</Label>
                    <Input
                        type="password"
                        name="password_login"
                        id="password_login"
                        value={loginInputs.password}
                        onChange={handleLoginFormChange}
                    />
                    <SubmitButton type="submit" value="Log In" />
                </Form>
            )}
            {loginAuth && (
                <Form onSubmit={handleLoginAuthFormSubmit}>
                    <Label htmlFor="email_loginAuth">Email</Label>
                    <Input
                        type="text"
                        name="email_loginAuth"
                        id="email_loginAuth"
                        value={loginInputs.email}
                        onChange={handleLoginAuthFormChange}
                    />
                    <Label htmlFor="passcode_loginAuth">Enter Temporary Passcode</Label>
                    <Input
                        type="number"
                        name="passcode_loginAuth"
                        id="passcode_loginAuth"
                        value={loginAuthInputs.passcode}
                        onChange={handleLoginAuthFormChange}
                    />
                </Form>
            )}
        </Container>
    );
};

export default Login;
