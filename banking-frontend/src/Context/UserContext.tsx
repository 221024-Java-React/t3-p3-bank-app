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

    const loginUser = (user: User) => {
        setCurrentUser(user);
    };

    const logoutUser = () => {
        setCurrentUser(initUser);
    };

    return (
        <UserContext.Provider
            value={{
                currentUser,
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
