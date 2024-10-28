import {useQuery} from '@tanstack/react-query'
import {getPiggybanks} from "../services/backend.ts"


export function usePiggybanks() {
    const {isLoading, isError, data: piggybanks} = useQuery({queryKey: ['piggybanks'], queryFn: () => getPiggybanks()})
    return {
        isLoading,
        isError,
        piggybanks,
    }
}


