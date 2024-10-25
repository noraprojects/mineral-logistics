import './App.css'
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom"
import {PiggyBankList} from "./components/PiggyBankList.tsx"
import {PiggyBankDetail} from "./components/PiggyBankDetail.tsx"
import axios from "axios"
import {QueryClient, QueryClientProvider} from "@tanstack/react-query"
import {CssBaseline, ThemeProvider} from "@mui/material"
import {theme} from "./theme/theme.ts"

axios.defaults.baseURL = "http://localhost:3001"
const queryClient = new QueryClient()


function App() {
    return (
        <QueryClientProvider client={queryClient}>
            <ThemeProvider theme={theme}>
                <CssBaseline/>
                <BrowserRouter>
                    <Routes>
                        <Route path="/piggybanks/:id" element={<PiggyBankDetail/>}/>
                        <Route path="/piggybanks" element={<PiggyBankList/>}/>
                        <Route path="/" element={<Navigate to="/piggybanks"/>}/>
                    </Routes>
                </BrowserRouter>
            </ThemeProvider>
        </QueryClientProvider>
    )
}

export default App
