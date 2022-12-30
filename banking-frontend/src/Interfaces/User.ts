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
    firstLogin: boolean;
}

export interface UserContextState {
    currentUser: User;
    setCurrentUser: (user: User) => void;
    loginUser: (email: string, password: string) => Promise<User | undefined | void>;
    resetPassword: (email: string, password: string) => Promise<User | undefined | void>;
    authenticateUser: (email: string, passcode: string) => Promise<User | undefined | void>;
    logoutUser: () => void;
    getBankAccounts: () => Promise<Account[] | undefined>;
}
