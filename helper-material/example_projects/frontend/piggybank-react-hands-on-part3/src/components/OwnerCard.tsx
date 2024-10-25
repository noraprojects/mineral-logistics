import {Card, CardActionArea, CardContent, CardMedia, Typography} from "@mui/material"
import {Account} from "../model/Account.ts"

interface OwnerCardProps {
    account: Account
}

export function OwnerCard({account}: OwnerCardProps) {
    return (
        <Card sx={{width: 200}} variant="outlined">
            <CardActionArea>
                <CardMedia component="img" sx={{height: 280, objectFit: "cover", p: 1, objectPosition: "top"}}
                           image={account.owner.profilePic}
                           alt="piggybank owner"/>
                <CardContent>
                    <Typography gutterBottom variant="h4" component="div" sx={{textAlign: 'center'}}>
                        {account.owner.name}
                    </Typography>
                </CardContent>
            </CardActionArea>
        </Card>
    )
}