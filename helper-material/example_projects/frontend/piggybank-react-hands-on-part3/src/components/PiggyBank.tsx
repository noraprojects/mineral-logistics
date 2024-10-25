import './PiggyBank.scss'
import {Account} from "../model/Account.ts"
import {OwnerBadge} from "./OwnerBadge.tsx"
import {useLocalStorage} from "@uidotdev/usehooks"

interface PiggyBankProps {
    account: Account
}

export function PiggyBank({account}: PiggyBankProps) {
    const [showFields, setShowFields] = useLocalStorage("show-fields", true)
    return (
        <div className="piggy-bank">
            <OwnerBadge owner={account.owner} onClick={() => setShowFields(!showFields)}/>
            {showFields && <div className="fields">
                <div className="owner">{account.owner.name}</div>
                <div className="balance">{account.balance}<span className="currency">€</span></div>
            </div>}
        </div>
    )
}

