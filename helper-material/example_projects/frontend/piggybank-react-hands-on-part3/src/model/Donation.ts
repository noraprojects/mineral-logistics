export type Donation = {
    id: string
    amount: number
    timestamp: Date
    accountId: string
    shortMessage: string
    longMessage: string
    sponsor: {
        id: string
        name: string
        profilePic: string
    }
}
export type NewDonation = Omit<Donation, 'id'>
export type DonationFormData = Omit<NewDonation, 'accountId' | 'timestamp' | 'sponsor'>

