import axios from "axios"; //Libreria que nos permite consumir el backend
import {useState,useEffect} from "react";
import { Link, useNavigate } from "react-router-dom";
import swal from "sweetalert";
import Menu from "../Menu/Menu";
import { API } from "../config/ApiUrl";

const URL=API('listar');
const URLE=API('eliminar/');


const ListarClientes = () => {
    const navigate = useNavigate()

      useEffect(()=>{
        try {
            let tipoUsuario = "user";
            if(sessionStorage.getItem("key")==null){
                swal("Acceso no autoriazdo","Debe digitar credenciales","error"); 
                navigate('/');
              }
            //   JSON.parse(sessionStorage.getItem("roles")).forEach(element => {//Identificar si el usuario es admin
            //     if(element.nombre==="ROLE_ADMIN"){
            //       tipoUsuario="admin";
            //     }
            //   });
            // if(tipoUsuario==="user"){
            //     swal("Acceso no autoriazdo","Debe ser administrador","error"); 
            //     navigate('/menu');
            // }
              
            
        } catch (error) {
            
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
            if(error.request.status==401){
                swal("Acceso no autoriazdo","No tiene credenciales válidas","error"); 
                navigate('/menu');
            }

            
            
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
    <div className="container ">
    <Link className="btn btn-outline-success" to={'/crearCliente'}><i className="fa-solid fa-user-plus"></i></Link><br/><br/>
        <table className="table table-striped table-dark">
        <thead >
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