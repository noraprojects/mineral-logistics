import {Avatar, Box, Paper, Stack, Typography} from "@mui/material"
import {Donation} from "../model/Donation.ts"
import {AmountDisplay} from "./AmountDisplay.tsx"
import dayjs from "dayjs"
import relativeTime from "dayjs/plugin/relativeTime"
import {gray} from "../theme/colors.ts"

dayjs.extend(relativeTime)

interface DonationCardProps {
    donation: Donation;
}

export function DonationCard({donation}: DonationCardProps) {
    const relativeTimeAgo = dayjs(donation.timestamp).fromNow()

    return (
        <Paper variant="outlined" sx={{p: 2, maxWidth: '45rem'}}>
            <Stack direction="row" spacing={4} divider={
                <Box
                    component="hr"
                    sx={(theme) => ({
                        border: `1px solid ${theme.palette.divider}`
                    })}
                />
            }>
                <AmountDisplay amount={donation.amount} currency="€"/>
                <Stack sx={{width: '100%'}}>
                    <Stack direction="row" spacing={1} sx={{justifyContent: 'space-between'}}>
                        <Stack>
                            <Typography variant="body2" sx={{mb: -0.7, color: gray[500]}}>
                                {relativeTimeAgo} {/* Display relative time */}
                            </Typography>
                            <Typography variant="h5" sx={{color: 'text.primary'}}>
                                {donation.shortMessage}
                            </Typography>
                        </Stack>
                        <Avatar
                            alt="Donator"
                            src={donation.sponsor.profilePic}
                            sx={{width: '2.8rem', height: '2.8rem'}}
                        />
                    </Stack>
                    <Stack>
                        <Typography variant="body1" sx={{color: 'text.secondary'}}>
                            {donation.longMessage}
                        </Typography>
                    </Stack>
                </Stack>
            </Stack>
        </Paper>
    )
}
