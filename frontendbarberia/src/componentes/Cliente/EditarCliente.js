import Menu from "../Menu/Menu";
import axios from "axios";
import { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import swal from "sweetalert";
import { API } from "../config/ApiUrl";

const URL=API('actualizar');
const URLF=API('list/'); //Endpoint de buscar cliente por id

const EditarCliente = () => {
    const [nombreCliente, setnombreCliente] = useState("");
    const [apellidoCliente, setapellido_cliente] = useState("");
    const [username, setusername] = useState("");
    const [password, setpassword] = useState("");
    const [regresar, setregresar] = useState(0);
    const {id}=useParams();
    let roles = document.getElementsByClassName("role");

    const navigate = useNavigate();

    useEffect(()=>{ 
        buscarCliente();
      },[]);

      useEffect(()=>{
        if(regresar!==0){
          navigate('/clientes');
        }
      },[regresar]);

    const buscarCliente=async()=>{
        const cliente = await axios({
            method:"GET",
            url:URLF+id
        })
        setnombreCliente(cliente.data.nombre_cliente);
        setapellido_cliente(cliente.data.apellido_cliente);
        setusername(cliente.data.username);
        setpassword("");
        for (const rol of cliente.data.roles) {
            for (const check of roles) {
                if(rol.nombre.replaceAll("ROLE_","").substring(0,3)===check.value.toUpperCase().substring(0,3)){
                    check.checked=true;
                }
                
            }
            
        }
    }

    const guardar = async () => {
        
        let listaRoles = [];
        for (const rol of roles) {
            if(rol.checked){
            listaRoles.push(rol.value);
        }
        }
        try {
            const insertarCliente = await axios({
                method:"PUT",
                url: URL,
                data:{
                    id:id,
                    nombre_cliente:nombreCliente,
                    apellido_cliente:apellidoCliente,
                    username:username,
                    password:password,
                    roles:listaRoles,
                },
                // headers:{
                //     user:sessionStorage.getItem("user"),
                //     key:sessionStorage.getItem("key")
                // }
            });
            console.log(insertarCliente.data);
            swal("Actualizado",insertarCliente.data.message,"success").then((value)=>{
                setregresar(1);
                
  
              });
        } catch (error) {
            console.log(error);
            
        }


      }



    return ( <>
    <Menu/>
    
    <center><h2>Editar Cliente</h2></center> 
    <div className="container col-6">
        <form onSubmit={guardar}>
            <div className="mb-3">
                <label className="form-label">Nombre de cliente</label>
                <input className="form-control" type="text" 
                value={nombreCliente} onChange={(e)=>setnombreCliente(e.target.value)}></input>
            </div>
            <div className="mb-3">
                <label className="form-label">Apellido de cliente</label>
                <input className="form-control" type="text" 
                value={apellidoCliente} onChange={(e)=>setapellido_cliente(e.target.value)}></input>
            </div>
            <div className="mb-3">
                <label className="form-label">Usuario de cliente</label>
                <input className="form-control" type="text" 
                value={username} onChange={(e)=>setusername(e.target.value)} ></input>
            </div>
            <div className="mb-3">
                <label className="form-label">Contraseña de cliente</label>
                <input className="form-control" type="password" value={password} onChange={(e)=>setpassword(e.target.value)}></input>
            </div>

            <div className="mb-3">
                <input className="form-check-input role" type="checkbox" value="user"/>{" "}
                <label className="form-check-label">User</label>{" "}

                <input className="form-check-input role" type="checkbox" value="admin"/>{" "}
                <label className="form-check-label">Admin</label>{" "}

                <input className="form-check-input role" type="checkbox" value="barber"/>{" "}
                <label className="form-check-label">Barber</label>{" "}
            </div>

            <button type="submit" className="btn btn-outline-dark">Guardar</button>{" "}
            <Link className="btn btn-outline-dark" to="/clientes">Regresar</Link>
            
        </form>
    </div>
    </> );
}
 
export default EditarCliente;