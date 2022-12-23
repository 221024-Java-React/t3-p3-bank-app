import React, { useContext, useEffect, useState } from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import { AccountContext } from "../../../Context/AccountContext";
import { UserContext } from "../../../Context/UserContext";
import { Account, AccountContextState } from "../../../Interfaces/Account";
import { UserContextState } from "../../../Interfaces/User";
import CreateAccount from "../../Forms/CreateAccount";
import axios from "axios";
import { axInst } from "../../../Util/axInstance";

const Container = styled.div``;

const Home: React.FC = () => {
    const { setCurrentAccount } = useContext(AccountContext) as AccountContextState;
    const { setCurrentUser, currentUser } = useContext(UserContext) as UserContextState;

    const [loading, setLoading] = useState<boolean>(true);

    const getAccounts = async () => {
        try {
            console.log(currentUser.userId)
            const { data } = await axInst.post<Account[]>(
                "/accounts/account",
                { userId: currentUser.userId },
            )
            // console.log(accounts, "accounts");
            // console.log(currentUser, "before setting currentuser account");
            setCurrentUser({ ...currentUser, accounts: data });
            console.log(currentUser, "after setting currentuser account");
            setLoading(false);
            return;
        } catch (e) {
        }
    }

    useEffect(() => {
        getAccounts();
    }, []);

    if (loading) {
        return <Container />
    }


    return (
        <Container>
            {currentUser.accounts.map(a => {
                return (
                    <Link to={`/${a.type}`} onClick={() => setCurrentAccount(a)}>
                        <div>{a.type}</div>
                        <div>{a.accountId}</div>
                        <div>{a.balance}</div>
                    </Link>
                );
            })}
            <CreateAccount />
        </Container>
    );
};

export default Home;
