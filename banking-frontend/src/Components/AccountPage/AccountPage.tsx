import React, { useContext } from "react";
import styled from "styled-components";
import { AccountContextState } from "../../Interfaces/Account";
import { Context } from "../../Context/AccountContext";

const Container = styled.div``;

const AccountPage: React.FC = () => {
  const { currentAccount } = useContext(Context) as AccountContextState;

  return (
    <Container>
      <h1>{currentAccount.type}</h1>
      <table>
        <tr>
          <th scope="column">Date</th>
          <th scope="column">Amount</th>
          <th scope="column">Type</th>
        </tr>
        {currentAccount.transactions.map(t => {
          return (
            <tr>
              <td>{t.date}</td>
              <td>{t.amount}</td>
              <td>{t.type}</td>
            </tr>
          );
        })}
      </table>
    </Container>
  );
};

export default AccountPage;
