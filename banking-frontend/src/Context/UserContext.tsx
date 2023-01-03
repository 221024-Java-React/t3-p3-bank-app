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
    firstLogin: true,
};

const UserProvider: React.FC<ProviderProps> = ({ children }) => {
    const [currentUser, setCurrentUser] = useState<User>(initUser);
    const [firstLogin, setFirstLogin] = useState<boolean>(false);
    const navigate = useNavigate();

    const loginUser = async (email: string, password: string) => {
        try {
            const thisUser = await axInst.post("/users/login", { email, password });
            console.log(thisUser)

        } catch (e) {
            console.log(e);
        }
    };

    const resetPassword = async (email: string, password: string) => {
        try {
            const thisUser: User = await axInst.post("/users/login_reset", {
                email,
                password,
            });

            setCurrentUser(thisUser);
        } catch (e) {
            console.log(e);
        }
    };

    const authenticateUser = async (email: string, token: string) => {
        try {
            const thisUser: User = await axInst.post("/users/login_Auth", {
                email,
                token,
            });
            setCurrentUser(thisUser);
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
                resetPassword,
                authenticateUser,
                logoutUser,
                getBankAccounts,
                firstLogin,
            }}
        >
            {children}
        </UserContext.Provider>
    );
};

export default UserProvider;
