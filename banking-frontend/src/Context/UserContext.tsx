import React, { useState } from 'react'
import { User, UserContextState } from '../Types/User';

interface ProviderProps {
    children: React.ReactNode;
}

export const Context = React.createContext<UserContextState | null>(null);

const UserProvider: React.FC<ProviderProps> = ({ children }) => {

    const [currentUser, setCurrentUser] = useState<User>({
        firstName: '',
        lastName: '',
        email: '',
        address: '',
        phoneNumber: '',
        password: '',
    });
    const [users, setUsers] = useState<User[]>([]);
    const [loggedIn, setLoggedIn] = useState<boolean>(false);

    const registerUser = (user: User) => {
        const newUser: User = {
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            address: user.address,
            phoneNumber: user.phoneNumber,
            password: user.password,
        }

        setUsers([...users, newUser]);
    }

    const loginUser = (user: User) => {
        setLoggedIn(true);
        setCurrentUser(user);
    }


    return (
        <Context.Provider value={{ registerUser, loginUser, currentUser, loggedIn }}>
            {children}
        </Context.Provider>
    )
}

export default UserProvider
