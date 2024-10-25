import {Link} from "react-router-dom"
import {OwnerCard} from "./OwnerCard.tsx"
import {Box, Stack, TextField} from "@mui/material"
import {useState} from "react"
import {useAccountsByOwnerClientSide} from "../hooks/useAccounts.ts"

export function PiggyBankList() {
    const [ownerFilter, setOwnerFilter] = useState<string>('')
    const {accounts, isLoading, isError} = useAccountsByOwnerClientSide(ownerFilter)
    //const {accounts, isLoading, isError} = useAccountsByOwnerServerSide(ownerFilter)

    if (isLoading) {
        return <div>Loading...</div>
    }

    if (isError || !accounts) {
        return <div>Error!</div>
    }

    return (
        <Stack sx={{alignItems: 'center', mt: 5}}>
            <TextField value={ownerFilter} sx={{width: '50%'}}
                       onChange={(e) => setOwnerFilter(e.target.value)}
                       label="Filter by name"/>
            <Box
                sx={{
                    mt: 5,
                    display: 'flex',
                    flexWrap: 'wrap',
                    justifyContent: 'center',
                    gap: 2
                }}
            >
                {accounts.map(
                    (account) =>
                        <Link to={`/piggybanks/${account.id}`} key={account.id} style={{textDecoration: 'none'}}>
                            <OwnerCard account={account}/>
                        </Link>
                )}
            </Box>
        </Stack>
    )
}

