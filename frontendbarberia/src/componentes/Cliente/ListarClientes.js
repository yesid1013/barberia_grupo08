import axios from "axios"; //Libreria que nos permite consumir el backend
import {useState,useEffect} from "react";
import { Link, useNavigate } from "react-router-dom";
import swal from "sweetalert";
import Menu from "../Menu/Menu";
const URL="http://localhost:8081/api/v1/cliente/listar";
const URLE= "http://localhost:8081/api/v1/cliente/eliminar/";


const ListarClientes = () => {
    const navigate = useNavigate()

      useEffect(()=>{
        if(sessionStorage.getItem("key")==null){
          swal("Acceso no autoriazdo","Debe digitar credenciales","error"); 
          navigate('/');
        }
      },[]);

      
    const [clientes, setclientes] = useState([]);

    useEffect(()=>{ //Cuando se carga la pagina me llama al metodo getclientes
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

    const eliminarCliente= async(id)=>{
        swal({
            title: "Eliminar registro",
            text: "¿Está seguro de eliminar el registro?",
            icon: "warning",
            buttons: true,
            dangerMode: true
        }).then(async (willDelete)=> {
            if (willDelete) {
                try {
                    const eliminar = await axios({
                        method:"DELETE",
                        url:URLE+id
                    })
                    swal("Eliminado",eliminar.data.message,"success"); 
                    getClientes();
                } catch (error) {
                    swal("Ah ocurrido un error",JSON.parse(error.request.response).message,"error"); 
                    
                }

                
            } else {
                swal("El registro no se borró");
                
            }
        });
        
    };


    return ( <>
    <Menu/>
    <div className="container">
    <Link className="btn btn-outline-success" to={'/crearCliente'}><i className="fa-solid fa-user-plus"></i></Link>
        <table className="table">
        <thead className="table-primary">
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Usuario</th>
                <th>Acciones</th>


            </tr>
        </thead>
        <tbody>
            {clientes.map((cliente)=>(
                <tr key={cliente.id}>
                <td>{cliente.nombre_cliente}</td>
                <td>{cliente.apellido_cliente}</td>
                <td>{cliente.username}</td>
                <td><Link className="btn btn-outline-info" to={'/editarCliente/'+cliente.id}><i className="fa-solid fa-user-pen"></i></Link>{" "}<Link className="btn btn-outline-danger" onClick={()=>eliminarCliente(cliente.id)}><i className="fa-solid fa-user-slash"></i></Link></td>

            </tr>

            ))}
            
        </tbody>
        </table>

    </div>
    
    </> );
}
 
export default ListarClientes;