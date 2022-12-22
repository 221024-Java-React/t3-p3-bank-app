import React, { useContext, useEffect } from "react";
import styled, { ThemeProvider } from "styled-components";
import { Link } from "react-router-dom";
import { AccountContext } from "../../../Context/AccountContext";
import { Context } from "../../../Context/UserContext";
import { AccountContextState } from "../../../Interfaces/Account";
import { Account } from "../../../Interfaces/Account";
import { axInst } from "../../../Util/axInstance";
import { UserContextState } from "../../../Interfaces/User";

const Container = styled.div``;

const Home: React.FC = () => {
  const { setCurrentAccount } = useContext(
    AccountContext
  ) as AccountContextState;
  const { currentUser } = useContext(Context) as UserContextState;

  const getAccountInfo = async () => {
    // axios call to get current user
    // const res = await axInst.get("/");
    // grab their account info from that returned user object
  };

  useEffect(() => {}, []);

  return (
    <Container>
      <Link to="" onClick={() => setCurrentAccount(checkingAccount)}>
        Checking
      </Link>
      <Link to="" onClick={() => setCurrentAccount(savingsAccount)}>
        Savings
      </Link>
    </Container>
  );
};

export default Home;
