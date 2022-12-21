import React, { useState } from "react";
import { Account, AccountContextState } from "../Interfaces/Account";
import { ProviderProps } from "../Interfaces/ProviderProps";
import { initUser } from "./UserContext";

export const Context = React.createContext<AccountContextState | null>(null);

const initAccount = {
  accountId: "",
  type: "",
  user: initUser,
  balance: 0,
  transactions: [],
};

const AccountProvider: React.FC<ProviderProps> = ({ children }) => {
  const [currentAccount, setCurrentAccount] = useState<Account>(initAccount);

  return (
    <Context.Provider value={{ currentAccount, setCurrentAccount }}>
      {children}
    </Context.Provider>
  );
};

export default AccountProvider;
