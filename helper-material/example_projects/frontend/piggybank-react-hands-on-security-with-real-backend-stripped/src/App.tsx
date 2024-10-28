import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom"
import {PiggybankList} from "./components/PiggybankList.tsx"
import {QueryClient, QueryClientProvider} from "@tanstack/react-query"
import SecurityContextProvider from "./context/SecurityContextProvider.tsx"
import {RouteGuard} from "./components/RouteGuard.tsx"
import {useContext} from "react"
import SecurityContext from "./context/SecurityContext.ts"

const queryClient = new QueryClient()

function Header() {
    const {logout, loggedInUser} = useContext(SecurityContext)
    return (
        <div>
            <div>Hello {loggedInUser}</div>
            <button onClick={logout}>Logout</button>
        </div>)
}

function App() {
    return (
        <QueryClientProvider client={queryClient}>
            <SecurityContextProvider>
                <BrowserRouter>
                    <Header/>
                    <Routes>
                        <Route path="/piggybanks" element={<RouteGuard><PiggybankList/></RouteGuard>}/>
                        <Route path="/" element={<Navigate to="/piggybanks"/>}/>
                    </Routes>
                </BrowserRouter>
            </SecurityContextProvider>
        </QueryClientProvider>
    )
}

export default App
