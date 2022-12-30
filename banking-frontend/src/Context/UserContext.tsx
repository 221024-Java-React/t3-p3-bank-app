import React, { useState } from "react";
import { useNavigate } from "react-router";
import { User, UserContextState } from "../Interfaces/User";
import { ProviderProps } from "../Interfaces/ProviderProps";
import { axInst } from "../Util/axInstance";
import { Account } from "../Interfaces/Account";

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
    const navigate = useNavigate();

    const loginUser = async (email: string, password: string) => {
        try {
            const { data: loggedInUser } = await axInst.post("/users/login", { email, password });
            setCurrentUser(loggedInUser);
        } catch (e) {
            console.log(e);
        }
    };

    const authenticateUser = async (email: string, passcode: string) => {
        try {
            await axInst.post("/users/login_Auth", {
                email,
                passcode,
            });
        } catch (e) {
            console.log(e);
        }
    };

    const logoutUser = async () => {
        const { email } = currentUser;

        try {
            await axInst.put("/users/logout", { email });
            setCurrentUser(initUser);
            navigate("/");
        } catch (e) {
            console.log(e);
        }
    };

    const getBankAccounts = async () => {
        const { userId } = currentUser;

        try {
            const { data: accounts } = await axInst.post<Promise<Account[] | undefined>>(
                "/accounts/account",
                {
                    userId,
                }
            );

            return accounts;
        } catch (e) {
            console.log(e);
        }
    };

    return (
        <UserContext.Provider
            value={{
                currentUser,
                setCurrentUser,
                loginUser,
                authenticateUser,
                logoutUser,
                getBankAccounts,
            }}
        >
            {children}
        </UserContext.Provider>
    );
};

export default UserProvider;
