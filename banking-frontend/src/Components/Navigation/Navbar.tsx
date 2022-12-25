import React, { useContext } from "react";
import styled from "styled-components";
import { UserContext } from "../../Context/UserContext";
import { UserContextState } from "../../Interfaces/User";
import { Link } from "react-router-dom";
import GBLogo_White from "../../Assets/GoodBank-Logo_White.png";

const Container = styled.div`
    position: sticky;
    top: 0;
    background: ${props => props.theme.background};
`;
const Menu = styled.div`
    display: flex;
    justify-content: flex-end;
    padding: 0.5rem;
    padding-right: 1rem;
    border-bottom: 1px solid ${props => props.theme.border};
`;

const MenuItem = styled.div`
    & a {
        text-decoration: none;
        color: ${props => props.theme.color};
        font-weight: bold;
    }
`;
const Banner = styled.div`
    background: ${props => props.theme.primaryDark};
    box-shadow: 0 10px 5px ${props => props.theme.background};
    margin-bottom: 0.5rem;
`;
const Logo = styled.img`
    width: 8rem;
    margin-top: -0.25rem;
    margin-bottom: -0.5rem;
`;

const Navbar: React.FC = () => {
    const { currentUser } = useContext(UserContext) as UserContextState;

    return (
        <Container>
            {currentUser.type === "" && (
                <Menu>
                    <MenuItem>
                        <Link to="/login">Log In</Link>
                    </MenuItem>
                </Menu>
            )}
            {currentUser.type === "MEMBER" && (
                <Menu>
                    <MenuItem>
                        <Link to="/about-us">About Us</Link>
                    </MenuItem>
                    <MenuItem>
                        <Link to="/account/summary">Account Summary</Link>
                    </MenuItem>
                    <MenuItem>
                        <Link to="/account/settings">Account Settings</Link>
                    </MenuItem>
                    <MenuItem>
                        <Link to="/logout">Log Out</Link>
                    </MenuItem>
                </Menu>
            )}
            {currentUser.type === "REP" && (
                <Menu>
                    <MenuItem>
                        <Link to="/register">Register A New User</Link>
                    </MenuItem>
                </Menu>
            )}
            <Banner>
                <Link to={currentUser.type === "" ? "/" : "/home"}>
                    <Logo src={GBLogo_White} alt="White GoodBank logo with mountains" />
                </Link>
            </Banner>
        </Container>
    );
};

export default Navbar;
