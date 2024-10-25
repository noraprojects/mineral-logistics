import axios from 'axios'
import {Account} from "../model/Account.ts"
import {Donation, NewDonation} from "../model/Donation.ts"

export async function getAccounts() {
    const {data: accounts} = await axios.get<Account[]>("/accounts")
    return accounts
}

export async function getAccountsByOwner(ownerName: string) {
    const {data: boards} = await axios.get<Account[]>(`/accounts?owner.name_like=${ownerName}`)
    return boards
}

export async function getAccount(id: string) {
    const {data: account} = await axios.get<Account>(`/accounts/${id}?_embed=donations`)
    return account
}

export async function addDonation(donation: NewDonation) {
    const {data: newDonation} = await axios.post<Donation>('/donations', donation)
    return newDonation
}

