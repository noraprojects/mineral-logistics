import './PiggyBank.scss'
import {Account} from "../model/Account.ts"
import {OwnerBadge} from "./OwnerBadge";
import {useState} from "react";

interface PiggyBankProps {
  account: Account

}

export function PiggyBank({account}: PiggyBankProps){
    const [showFields, setShowFields] = useState(true)
    return (
        <div className="piggy-bank">
            <OwnerBadge owner={account.owner} onClick={() => setShowFields(!showFields)}/>
            <div className="fields">
        <div className="owner">{account.owner}</div>
            <div className="balance">{account.balance}</div>
            </div>
        </div>
    )
}