import {usePiggybanks} from "../hooks/usePiggybanks.ts"

export function PiggybankList() {
    const {piggybanks, isLoading, isError} = usePiggybanks()

    if (isLoading) {
        return <div>Loading...</div>
    }

    if (isError || !piggybanks) {
        return <div>Error!</div>
    }

    return (
        <div
            style={{
                display: 'flex',
                flexDirection: 'row',
                flexWrap: 'wrap',
                justifyContent: 'center',
                gap: 2
            }}
        >
            {piggybanks.map(
                (piggybank) =>
                    <div style={{maxWidth: '30rem', border: '1px solid black', padding: '1rem'}}
                         key={piggybank.id}>{piggybank.balance}</div>
            )}
        </div>
    )
}

