import { useContext } from "react";
import { Route, Routes } from "react-router-dom";
import styled, { ThemeProvider } from "styled-components";
import Login from "./Components/Forms/Login";
import Register from "./Components/Forms/Register";
import Navbar from "./Components/Navigation/Navbar";
import { vars, lightTheme, darkTheme } from "./Util/Themes";
import WelcomePage from "./Components/Homepages/WelcomePage";
import MemberHome from "./Components/Homepages/MemberHome";
import { UserContext } from "./Context/UserContext";
import { UserContextState } from "./Interfaces/User";
import RepHome from "./Components/Homepages/RepHome";
import Footer from "./Components/Navigation/Footer";
import AccountSummary from "./Components/AccountPage/AccountSummary";
import AccountHistory from "./Components/AccountPage/AccountHistory";
import CreditCardApplication from "./Components/Forms/CreditCardApplication";
import TransferFunds from "./Components/AccountPage/TransferFunds";
import { DarkModeContext } from "./Context/DarkModeContext";
import { DarkModeContextState } from "./Interfaces/DarkMode";
import PageNotFound from "./Components/PageNotFound";

const DarkModeProvider = styled.div`
    background-color: ${(props) => props.theme.background};
    color: ${(props) => props.theme.text};
`;
const Body = styled.div`
    width: ${(props) => props.theme.bodyWidth};
    min-height: 100vh;
    margin: 0 auto;
    color: ${(props) => props.theme.color};
`;

function App() {
    const { currentUser } = useContext(UserContext) as UserContextState;
    const { mode } = useContext(DarkModeContext) as DarkModeContextState;

    return (
        <ThemeProvider theme={mode === "Light" ? lightTheme : darkTheme}>
            <ThemeProvider theme={vars}>
                <DarkModeProvider>
                    <Navbar />
                    <Body>
                        <Routes>
                            {currentUser.type === "REP" && (
                                <>
                                    <Route path="/" element={<RepHome />} />
                                    <Route
                                        path="/register"
                                        element={<Register />}
                                    />
                                </>
                            )}
                            {currentUser.type === "MEMBER" && (
                                <>
                                    <Route path="/" element={<MemberHome />} />
                                    <Route
                                        path="accounts/summary"
                                        element={<AccountSummary />}
                                    />
                                    <Route
                                        path="/accounts/checking"
                                        element={<AccountHistory />}
                                    />
                                    <Route
                                        path="/accounts/savings"
                                        element={<AccountHistory />}
                                    />
                                    <Route
                                        path="/accounts/credit"
                                        element={<AccountHistory />}
                                    />
                                    <Route
                                        path="/accounts/transfer"
                                        element={<TransferFunds />}
                                    />
                                    <Route
                                        path="/credit-card-application"
                                        element={<CreditCardApplication />}
                                    />
                                    <Route path="/login" element={<Login />} />
                                </>
                            )}
                            {currentUser.type === "" && (
                                <>
                                    <Route path="/" element={<WelcomePage />} />
                                    <Route path="/login" element={<Login />} />
                                    <Route path="/register" element={<Register />} />
                                    <Route path="/accounts/transfer" element={<TransferFunds />} />
                                    <Route
                                        path="/register"
                                        element={<Register />}
                                    />
                                </>
                            )}
                            <Route path="*" element={<PageNotFound />} />
                            <Route path="/cc" element={<CreditCardApplication />} />
                        </Routes>
                    </Body>
                    <Footer />
                </DarkModeProvider>
            </ThemeProvider>
        </ThemeProvider>
    );
}

export default App;
