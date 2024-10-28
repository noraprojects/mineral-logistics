import axios from 'axios'
import {Piggybank} from "../model/Piggybank.ts"

const BACKEND_URL: string = import.meta.env.VITE_BACKEND_URL

export async function getPiggybanks() {
    const {data: piggybanks} = await axios.get<Piggybank[]>(`${BACKEND_URL}/piggybanks`)
    return piggybanks
}


