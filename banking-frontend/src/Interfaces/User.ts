import { Account } from "./Account";

export interface User {
    userId: string;
    type: string;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    address: string;
    phoneNumber: string;
    accounts: Account[];
}

export interface UserContextState {
    currentUser: User;
    setCurrentUser: (user: User) => void;
    loginUser: (user: User) => void;
    logoutUser: () => void;
}
