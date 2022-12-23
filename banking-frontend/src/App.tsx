import { useContext, useEffect, useState } from "react";
import { Route, Routes } from "react-router-dom";
import styled, { ThemeProvider } from "styled-components";
import Login from "./Components/Forms/Login/Login";
import Register from "./Components/Forms/Register/Register";
import Navbar from "./Components/Navbar/Navbar";
import { lightTheme, darkTheme } from "../src/Util/Theme";
import WelcomePage from "./Components/WelcomePage/WelcomePage";
import Home from "./Components/Home/Home/Home";
import { UserContext } from "./Context/UserContext";
import { UserContextState } from "./Interfaces/User";

const Container = styled.div`
    background-color: ${props => props.theme.body};
    color: ${props => props.theme.text};
    height: 100vh;
    width: 100vw;
    display: flex;
    flex-direction: column;
`;
const ThemeButton = styled.button`
    position: absolute;
    bottom: 20px;
    right: 15px;
`;

function App() {
    const [theme, setTheme] = useState("light");
    const { currentUser } = useContext(UserContext) as UserContextState;

    const themeToggler = () => {
        if (localStorage.getItem("theme") === "light") {
            setTheme("dark");
            localStorage.setItem("theme", "dark");
        } else {
            setTheme("light");
            localStorage.setItem("theme", "light");
        }
    };

    useEffect(() => {
        themeToggler();
    }, []);

    return (
        <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
            <ThemeButton onClick={themeToggler}>
                {theme === "light" ? <div>Light</div> : <div>Dark</div>}
            </ThemeButton>
            <Container>
                <Navbar />
                <Routes>
                    <Route path="/" element={<WelcomePage />} />
                    {
                        currentUser.type === "REP" &&
                        <Route path="/register" element={<Register />} />
                    }
                    <Route path="/login" element={<Login />} />
                    {/* <Route path='/checking' element={<AccountPage account={currentUser.accounts.checking}/>} />
                    <Route path='/savings' element={<AccountPage account={currentUser.accounts.savings} />} /> */}

                    <Route path="/home" element={<Home />} />
                </Routes>
            </Container>
        </ThemeProvider>
    );
}

export default App;
