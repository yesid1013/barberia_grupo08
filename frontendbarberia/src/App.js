import './App.css';
import {Route,Routes,HashRouter} from "react-router-dom";
import Login from './componentes/Login/Login';
import Menu from './componentes/Menu/Menu';
import ListarClientes from './componentes/Cliente/ListarClientes';
import CrearCliente from './componentes/Cliente/CrearCliente';
import EditarCliente from './componentes/Cliente/EditarCliente';


function App() {
  return (
    <>
    

      <HashRouter>
        <Routes>
          <Route exact path='/' element={<Login/>} />
          <Route exact path='/menu' element={<Menu/>} />
          <Route exact path='/clientes' element={<ListarClientes/>} />
          <Route exact path='/crearCliente' element={<CrearCliente/>} />
          <Route exact path='/editarCliente/:id' element={<EditarCliente/>} />     
        </Routes>
      </HashRouter>
   
   
   </>
    
    
  );
}

export default App;
