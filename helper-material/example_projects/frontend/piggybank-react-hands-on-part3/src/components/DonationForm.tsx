import {Button, Paper, Stack, TextField} from "@mui/material"
import {DonationFormData} from "../model/Donation.ts"
import {useState} from "react"

interface DonationFormProps {
    onSubmit: (donationFormData: DonationFormData) => void;
}

export function DonationForm({onSubmit}: DonationFormProps) {
    const [donationFormData, setDonationFormData] = useState<DonationFormData>({
        amount: 0,
        shortMessage: '',
        longMessage: '',
    })

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target
        setDonationFormData({...donationFormData, [name]: value})
    }


    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault()
        onSubmit(donationFormData)
    }

    return (
        <Paper variant="outlined" sx={{p: 3, width: '25rem'}}>
            <form onSubmit={handleSubmit}>
                <Stack spacing={2}>
                    <TextField
                        name="amount"
                        type="number"
                        value={donationFormData.amount}
                        onChange={handleChange}
                        label="Donation Amount (€)"
                        variant="outlined"
                        slotProps={{
                            htmlInput: {
                                min: 1
                            }
                        }}
                    />
                    <TextField
                        name="shortMessage"
                        value={donationFormData.shortMessage}
                        onChange={handleChange}
                        required
                        label="Short Message"
                        variant="outlined"
                    />
                    <TextField
                        name="longMessage"
                        value={donationFormData.longMessage}
                        onChange={handleChange}
                        label="Long Message"
                        variant="outlined"
                        multiline
                    />
                    <Button variant="contained" color="primary" type="submit">Donate</Button>
                </Stack>
            </form>
        </Paper>
    )
}
