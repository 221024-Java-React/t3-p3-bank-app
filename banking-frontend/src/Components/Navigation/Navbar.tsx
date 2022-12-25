import React, { useContext } from "react";
import styled from "styled-components";
import { UserContext } from "../../Context/UserContext";
import { UserContextState } from "../../Interfaces/User";
import { Link } from "react-router-dom";

const Container = styled.div`
    border-bottom: 1px solid ${props => props.theme.border};
    position: sticky;
    top: 0;
    margin-bottom: 5rem;
    display: flex;
    justify-content: space-between;
    padding: 1rem;
`;
const Brand = styled.div`
    & a {
        text-decoration: none;
    }
`;
const Menu = styled.div``;
const MenuItem = styled.div`
    & a {
        text-decoration: none;
    }
`;

const Navbar: React.FC = () => {
    const { currentUser } = useContext(UserContext) as UserContextState;

    return (
        <Container>
            <Brand>
                <Link to={currentUser.type === "" ? "/" : "/home"}>GoodBank</Link>
            </Brand>
            <Menu>
                {currentUser.type === "" && (
                    <MenuItem>
                        <Link to="/login">Log In</Link>
                    </MenuItem>
                )}
                {currentUser.type === "REP" && (
                    <MenuItem>
                        <Link to="/register">Register A New User</Link>
                    </MenuItem>
                )}
            </Menu>
        </Container>
    );
};

export default Navbar;
