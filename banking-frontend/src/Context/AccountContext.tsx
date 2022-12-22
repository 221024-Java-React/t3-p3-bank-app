import React, { useState } from "react";
import { Account, AccountContextState } from "../Interfaces/Account";
import { ProviderProps } from "../Interfaces/ProviderProps";
import { initUser } from "./UserContext";

export const AccountContext = React.createContext<AccountContextState | null>(
  null
);

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
    <AccountContext.Provider value={{ currentAccount, setCurrentAccount }}>
      {children}
    </AccountContext.Provider>
  );
};

export default AccountProvider;
