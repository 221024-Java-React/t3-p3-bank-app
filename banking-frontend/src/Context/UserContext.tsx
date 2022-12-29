import React, { useState } from "react";
import { User, UserContextState } from "../Interfaces/User";
import { ProviderProps } from "../Interfaces/ProviderProps";
import { axInst } from "../Util/axInstance";
import { Account } from "../Interfaces/Account";
import { useNavigate } from "react-router";

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

    const loginUser = (user: User) => {
        setCurrentUser(user);
    };

    const logoutUser = async () => {
        setCurrentUser(initUser);

        await axInst.put("/users/logout", { email: currentUser.email })

        navigate("/");
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
                logoutUser,
                getBankAccounts,
            }}
        >
            {children}
        </UserContext.Provider>
    );
};

export default UserProvider;
