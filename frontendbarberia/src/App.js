import './App.css';
import {Route,Routes,HashRouter} from "react-router-dom";
import Login from './componentes/Login/Login';
import Menu from './componentes/Menu/Menu';
import ListarClientes from './componentes/Cliente/ListarClientes';


function App() {
  return (
    <>
    

      <HashRouter>
        <Routes>
          <Route exact path='/' element={<Login/>} />
          <Route exact path='/menu' element={<Menu/>} />
          <Route exact path='/clientes' element={<ListarClientes/>} />   
        </Routes>
      </HashRouter>
   
   
   </>
    
    
  );
}

export default App;
