import React, { useContext } from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import { AccountContext } from "../../../Context/AccountContext";
import { UserContext } from "../../../Context/UserContext";
import { AccountContextState } from "../../../Interfaces/Account";
import { UserContextState } from "../../../Interfaces/User";
import CreateAccount from "../../AccountPage/CreateAccount";
import TransferFunds from "../../AccountPage/TransferFunds";

const Container = styled.div``;

const Home: React.FC = () => {
    const { setCurrentAccount } = useContext(AccountContext) as AccountContextState;
    const { currentUser } = useContext(UserContext) as UserContextState;

    return (
        <Container>
            <CreateAccount />
            {currentUser?.accounts.map(a => {
                return (
                    <Link to={`/${a.type}`} onClick={() => setCurrentAccount(a)}>
                        <div>{a.type}</div>
                        <div>{a.accountId}</div>
                        <div>{a.balance}</div>
                    </Link>
                );
            })}
            <TransferFunds />
        </Container>
    );
};

export default Home;
