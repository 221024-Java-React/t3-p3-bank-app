import React, { useState } from "react";
import { User, UserContextState } from "../Interfaces/User";
import { ProviderProps } from "../Interfaces/ProviderProps";

export const Context = React.createContext<UserContextState | null>(null);

export const initUser = {
  userId: "",
  type: "",
  firstName: "",
  lastName: "",
  email: "",
  password: "",
  address: "",
  phoneNumber: "",
  accounts: [],
};

const UserProvider: React.FC<ProviderProps> = ({ children }) => {
  const [currentUser, setCurrentUser] = useState<User>(initUser);
  const [users, setUsers] = useState<User[]>([]);
  const [loggedIn, setLoggedIn] = useState<boolean>(false);

  // const registerUser = (user: User) => {
  //   const newUser = {
  //     userId: user.userId,
  //     firstName: user.firstName,
  //     lastName: user.lastName,
  //     email: user.email,
  //     address: user.address,
  //     phoneNumber: user.phoneNumber,
  //     password: user.password,
  //   };
  // };

  const updateCurrentUser = (user: User) => {
    setCurrentUser(user);
  };

  const loginUser = (user: User) => {
    setLoggedIn(true);
    setCurrentUser(user);
  };
  const logoutUser = () => {
    setLoggedIn(false);
    setCurrentUser(initUser);
  };

  return (
    <Context.Provider
      value={{
        // registerUser,
        loginUser,
        currentUser,
        loggedIn,
        updateCurrentUser,
        logoutUser,
      }}
    >
      {children}
    </Context.Provider>
  );
};

export default UserProvider;
