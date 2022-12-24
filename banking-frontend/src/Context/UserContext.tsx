import React, { useState } from "react";
import { User, UserContextState } from "../Interfaces/User";
import { ProviderProps } from "../Interfaces/ProviderProps";

export const UserContext = React.createContext<UserContextState | null>(null);

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
    const [loggedIn, setLoggedIn] = useState<boolean>(false);

    const loginUser = (user: User) => {
        setLoggedIn(true);
        setCurrentUser(user);
    };
    const logoutUser = () => {
        setLoggedIn(false);
        setCurrentUser(initUser);
    };

    return (
        <UserContext.Provider
            value={{
                currentUser,
                loggedIn,
                setCurrentUser,
                loginUser,
                logoutUser,
            }}
        >
            {children}
        </UserContext.Provider>
    );
};

export default UserProvider;
