import axios from "axios"; //Libreria que nos permite consumir el backend
import {useState,useEffect} from "react";
import { useNavigate } from "react-router-dom";
import swal from "sweetalert";
const URL="http://localhost:8081/api/v1/cliente/listar";



const ListarClientes = () => {
    const [clientes, setclientes] = useState([]);

    useEffect(()=>{
        getClientes();
      },[])
    const getClientes= async()=>{ 

        try {
            const login = await axios({ 
                method:"GET",
                url:URL,
                headers:{
                    user:sessionStorage.getItem("user"),
                    key:sessionStorage.getItem("key")
                }
            })
            setclientes(login.data)
            
        } catch (error) {
            
        }

    }


    return ( <>
    <table className="table">
        <thead className="table-primary">
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Usuario</th>


            </tr>
        </thead>
        <tbody>
            {clientes.map((cliente)=>(
                <tr>
                <td>{cliente.nombre_cliente}</td>
                <td>{cliente.apellido_cliente}</td>
                <td>{cliente.username}</td>

            </tr>

            ))}
            
        </tbody>
    </table>
    </> );
}
 
export default ListarClientes;