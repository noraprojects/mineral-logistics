import {Owner} from "./Owner.ts";

export interface Account {
    id: string
    balance: number
    owner: Owner
}