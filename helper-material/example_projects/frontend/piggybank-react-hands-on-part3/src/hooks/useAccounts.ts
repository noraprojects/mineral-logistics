import {useMutation, useQuery, useQueryClient} from '@tanstack/react-query'
import {addDonation, getAccount, getAccounts, getAccountsByOwner} from "../services/dataService.ts"
import {NewDonation} from "../model/Donation.ts"

export function useAccountsByOwnerClientSide(ownerName: string) {
    const {isLoading, isError, data: accounts} = useQuery({queryKey: ['accounts'], queryFn: () => getAccounts()})
    const filteredAccounts = accounts?.filter((account) => account.owner.name.includes(ownerName))

    return {
        isLoading,
        isError,
        accounts: filteredAccounts,
    }
}

export function useAccountsByOwnerServerSide(ownerName: string) {
    const {isLoading, isError, data: accounts} = useQuery({
        queryKey: ['accounts', ownerName],
        queryFn: () => getAccountsByOwner(ownerName)
    })

    return {
        isLoading,
        isError,
        accounts,
    }
}

export function useAccount(id: string) {
    const {isLoading, isError, data: account} = useQuery({
        queryKey: ['account', id],
        queryFn: () => getAccount(id),
    })
    return {
        isLoading,
        isError,
        account,
    }
}


export function useAddDonation(accountId: string) {
    const queryClient = useQueryClient()
    // the queryClient is used to invalidate the account cache with key ['account', accountId] after a new donation is succesfully added
    // this results in a refetch of the account
    // please note that this will NOT give us an updated account BALANCE since json-server can not 'calculate' something!
    const {
        mutate,
        isPending,
        isError,
    } = useMutation(
        {
            mutationFn: (donation: NewDonation) => addDonation(donation),
            onSuccess: () => queryClient.invalidateQueries({queryKey: ['account', accountId]}),
        })

    return {
        isPending,
        isError,
        addDonation: mutate,
    }
}

