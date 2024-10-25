import './App.css'
import {PiggyBank} from "./components/PiggyBank.tsx"
import {Account} from "./model/Account";

// this will come from a REST API in the future
const account: Account = {
  id:'1',
  balance: 100,
  owner: {
      id: '1',
      name: 'Bobby',
      image: "https://img.freepik.com/free-vector/smiling-young-man-glasses_1308-174373.jpg?size=626&ext=jpg&ga=GA1.1.2008272138.1728172800&semt=ais_hybrid"
  }
}
interface PiggyBankProps {
    account: Account
}


function App() {
    return (
    <PiggyBank account={account}/>
)
}
export default App