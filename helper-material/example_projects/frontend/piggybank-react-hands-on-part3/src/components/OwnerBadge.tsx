import './OwnerBadge.scss'
import {Owner} from "../model/Owner.ts"

interface OwnerBadgeProps {
    owner: Owner
    onClick: () => void
}

export function OwnerBadge({owner, onClick}: OwnerBadgeProps) {
    return (
        <div className="owner-badge" onClick={onClick}>
            <img src={owner.profilePic} alt={owner.name}/>
        </div>
    )
}

