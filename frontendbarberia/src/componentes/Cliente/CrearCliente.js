import Menu from "../Menu/Menu";
import {useState,useEffect} from "react";
import { Link, useNavigate } from "react-router-dom";
import swal from "sweetalert";
import axios from "axios";
import { API } from "../config/ApiUrl";

const URL =API('crear');
const CrearCliente = () => {
    const [nombreCliente, setnombreCliente] = useState("");
    const [apellidoCliente, setapellido_cliente] = useState("");
    const [username, setusername] = useState("");
    const [password, setpassword] = useState("");
    const [regresar, setregresar] = useState(0);
    


    const navigate = useNavigate();

    

      useEffect(()=>{
        if(sessionStorage.getItem("key")==null){
          swal("Acceso no autoriazdo","Debe digitar credenciales","error"); 
          navigate('/');
        }
      },[]);

      useEffect(()=>{
        if(regresar!==0){
          navigate('/clientes');
        }
      },[regresar]);

      const guardar = async () => {
        let roles = document.getElementsByClassName("role");
        let listaRoles = [];
        for (const rol of roles) {
            if(rol.checked){
            listaRoles.push(rol.value);
        }
        }
        try {
            const insertarCliente = await axios({
                method:"POST",
                url: URL,
                data:{
                    nombre_cliente:nombreCliente,
                    apellido_cliente:apellidoCliente,
                    username:username,
                    password:password,
                    roles:listaRoles,
                },
                headers:{
                    user:sessionStorage.getItem("user"),
                    key:sessionStorage.getItem("key")
                }
            });
            console.log(insertarCliente.data);
            swal("Guardado",insertarCliente.data.message,"success").then((value)=>{
                setregresar(1);
  
              });
        } catch (error) {
            //console.log(error.response.data.errors);
            swal("Error de datos",JSON.parse(error.request.response).errors[0].message,"error")
            //console.log(JSON.parse(error.request.response).errors[0].message);
            
        }


      }


    return ( <>
    <Menu/>
    <center><h2>Crear cliente</h2></center>
    <div className="container col-6 ">
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
 
export default CrearCliente;