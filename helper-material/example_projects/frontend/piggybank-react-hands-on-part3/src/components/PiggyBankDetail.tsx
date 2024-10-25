import {useParams} from "react-router-dom"
import {useAccount, useAddDonation} from "../hooks/useAccounts.ts"
import {PiggyBank} from "./PiggyBank.tsx"
import {Box} from "@mui/material"
import {DonationsList} from "./DonationsList.tsx"
import {DonationForm} from "./DonationForm.tsx"
import {DonationFormData, NewDonation} from "../model/Donation.ts"

const tmpHardCodedSponsor = {
    id: "4",
    name: "Grandma",
    profilePic: "https://img.freepik.com/free-vector/cheerful-grandmother-cartoon-portrait_1308-164977.jpg?uid=R81303125&ga=GA1.1.684872613.1713387229&semt=ais_hybrid-rr-similar"

}

export function PiggyBankDetail() {
    const {id} = useParams()
    const {account, isLoading: isLoadingAccount, isError} = useAccount(id!)
    const {addDonation, isPending: addDonationPending, isError: addDonationError} = useAddDonation(id!)

    if (isLoadingAccount || addDonationPending) {
        return <div>Working...</div>
    }

    if (isError || addDonationError || !account) {
        return <div>Error!</div>
    }

    function handleAddDonation(donationFormData: DonationFormData) {
        if (account) {
            const newDonation: NewDonation = {
                ...donationFormData,
                timestamp: new Date(),
                accountId: account.id,
                sponsor: tmpHardCodedSponsor
            }
            addDonation(newDonation)
        }
    }

    return (
        <Box
            sx={{
                mt: 5,
                display: 'flex',
                flexWrap: 'wrap',
                justifyContent: 'center',
                gap: 2
            }}
        >
            <PiggyBank account={account}/>
            <DonationForm onSubmit={handleAddDonation}/>
            <DonationsList account={account}/>
        </Box>
    )
}
