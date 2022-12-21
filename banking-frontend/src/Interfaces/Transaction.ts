import { Account } from "./Account";

export interface Transaction {
  transactionId: number;
  type: string;
  account: Account;
  message: string;
  amount: number;
  date: string;
}
