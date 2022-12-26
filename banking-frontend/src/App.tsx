import { useContext, useState } from "react";
import { Route, Routes } from "react-router-dom";
import styled, { ThemeProvider } from "styled-components";
import Login from "./Components/Forms/Login/Login";
import Register from "./Components/Forms/Register/Register";
import Navbar from "./Components/Navigation/Navbar";
import { vars, lightTheme, darkTheme } from "./Util/Themes";
import WelcomePage from "./Components/WelcomePage/WelcomePage";
import MemberHome from "./Components/Homepages/MemberHome";
import { UserContext } from "./Context/UserContext";
import { UserContextState } from "./Interfaces/User";
import RepHome from "./Components/Homepages/RepHome";
import Footer from "./Components/Navigation/Footer";
import AccountSummary from "./Components/AccountPage/AccountSummary";

const DarkModeProvider = styled.div`
    background-color: ${props => props.theme.body};
    color: ${props => props.theme.text};
`;
const Body = styled.div`
    width: ${props => props.theme.bodyWidth};
    min-height: 100vh;
    margin: 0 auto;
`;
const DarkModeButton = styled.button`
    position: absolute;
    bottom: 1.5rem;
    right: 1rem;
`;

function App() {
    const [theme, setTheme] = useState<String>("Light");
    const { currentUser } = useContext(UserContext) as UserContextState;

    const toggleTheme = () => {
        if (localStorage.getItem("theme") === "Light") {
            setTheme("Dark");
            localStorage.setItem("theme", "Dark");
        } else {
            setTheme("Light");
            localStorage.setItem("theme", "Light");
        }
    };

    return (
        <ThemeProvider theme={theme === "Light" ? lightTheme : darkTheme}>
            <ThemeProvider theme={vars}>
                {/* <DarkModeButton onClick={toggleTheme}>{theme}</DarkModeButton> */}
                <DarkModeProvider>
                    <Navbar />
                    <Body>
                        {currentUser.type === "REP" && (
                            <Routes>
                                <Route path="/" element={<RepHome />} />
                                <Route path="/register" element={<Register />} />
                            </Routes>
                        )}
                        {currentUser.type === "MEMBER" && (
                            <Routes>
                                {/* <Route path="/" element={<MemberHome />} /> */}
                                {/* <Route path='/checking' element={<AccountPage account={currentUser.accounts.checking}/>} />
                    <Route path='/savings' element={<AccountPage account={currentUser.accounts.savings} />} /> */}
                            </Routes>
                        )}
                        {currentUser.type === "" && (
                            <Routes>
                                <Route path="/" element={<WelcomePage />} />
                                {/* <Route path="/" element={<MemberHome />} /> */}
                                <Route path="/login" element={<Login />} />

                                <Route path="accounts/summary" element={<AccountSummary />} />
                            </Routes>
                        )}
                    </Body>
                    <Footer />
                </DarkModeProvider>
            </ThemeProvider>
        </ThemeProvider>
    );
}

export default App;
