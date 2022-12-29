import React, { useContext } from "react";
import styled from "styled-components";
import { UserContext } from "../../Context/UserContext";
import { UserContextState } from "../../Interfaces/User";
import { Link } from "react-router-dom";
import GBLogo_White from "../../Assets/GoodBank-Logo_White.png";
import { DarkModeContext } from "../../Context/DarkModeContext";
import { DarkModeContextState } from "../../Interfaces/DarkMode";
import SunIcon from "../../Assets/sun-icon.png";
import MoonIcon from "../../Assets/moon-icon.png";

const Container = styled.div`
    position: sticky;
    top: 0;
    background: ${(props) => props.theme.body};
`;
const Menu = styled.div`
    display: flex;
    justify-content: flex-end;
    align-items: center;
    padding: 0.5rem;
    padding-right: 1rem;
`;
const Icon = styled.img`
    width: 1.1rem;
    cursor: pointer;
`;
const MenuItem = styled.div`
    margin-left: 1rem;
    text-decoration: none;
    color: ${props => props.theme.color};
    font-weight: bold;
    cursor: pointer;

    & a {
        text-decoration: none;
        color: ${(props) => props.theme.color};
        font-weight: bold;
    }
`;
const Banner = styled.div`
    background: ${(props) => props.theme.primaryDark};
    box-shadow: 0 10px 5px ${(props) => props.theme.background};
    margin-bottom: 1.5rem;
    border-radius: 3px;
`;
const Logo = styled.img`
    width: 8rem;
    margin-top: -0.25rem;
    margin-bottom: -0.5rem;
`;

const Navbar: React.FC = () => {
    const { currentUser, logoutUser } = useContext(UserContext) as UserContextState;
    const { mode, toggleDarkMode } = useContext(DarkModeContext) as DarkModeContextState;

    return (
        <Container>
            <Menu>
                <Icon
                    src={mode === "Light" ? MoonIcon : SunIcon}
                    onClick={toggleDarkMode}
                />
                {currentUser.type === "REP" && (
                    <MenuItem>
                        <Link to="/register">Register A New User</Link>
                    </MenuItem>
                )}
                {currentUser.type === "MEMBER" && (
                    <>
                        <MenuItem>
                            <Link to="/about-us">About Us</Link>
                        </MenuItem>
                        <MenuItem>
                            <Link to="/accounts/summary">Account Summary</Link>
                        </MenuItem>
                        <MenuItem>
                            <Link to="/accounts/settings">
                                Account Settings
                            </Link>
                        </MenuItem>
                        <MenuItem onClick={logoutUser}>Log Out</MenuItem>
                    </>
                )}
                {currentUser.type === "" && (
                    <MenuItem>
                        <Link to="/login">Log In</Link>
                    </MenuItem>
                )}
            </Menu>
            <Banner>
                <Link to={"/"}>
                    <Logo src={GBLogo_White} alt="White GoodBank logo with mountains" />
                </Link>
            </Banner>
        </Container >
    );
};

export default Navbar;
