import {Stack, Typography} from "@mui/material"
import {Account} from "../model/Account.ts"
import {DonationCard} from "./DonationCard.tsx"

interface DonationsListProps {
    account: Account
}

export function DonationsList({account}: DonationsListProps) {
    return (
        <Stack>
            <Typography variant="h5" sx={{fontWeight: 700}}>Your donations to {account.owner.name}</Typography>
            <Stack spacing={2} sx={{mt: 1}}>
                {account.donations
                    .sort((a, b) => new Date(b.timestamp).getTime() - new Date(a.timestamp).getTime())
                    .map((donation) => (
                        <DonationCard key={donation.id} donation={donation}/>
                    ))}
            </Stack>
        </Stack>
    )
}
