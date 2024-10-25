import {Owner} from "./Owner.ts"
import {Donation} from "./Donation.ts"

export type Account = {
    id: string
    balance: number
    owner: Owner
    donations: Donation[]
}
