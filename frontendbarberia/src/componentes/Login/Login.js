import axios from "axios"; //Libreria que nos permite consumir el backend
import {useState} from "react";
import { useNavigate } from "react-router-dom";
import swal from "sweetalert";
const URL="http://localhost:8081/api/v1/cliente/login";


const Login = () => {
    const [user, setuser] = useState("");
    const [pwd, setpwd] = useState("");
    const navigate = useNavigate()

    const ingresar= async(e)=>{ //async porque estoy trabajando el front en el puerto 3000 y debo hacer una peticion en otro puerto
        e.preventDefault(); //No recargue la pagina
        try {
            const login = await axios({ //Le enviamos lo que necesita el endpoint en el backend
                method:"POST",
                url:URL,
                headers:{
                    user:user,
                    pwd:pwd
                }
            })
            swal("Acceso exitoso","Bienvenido "+login.data.user,"success")
            .then((value)=>{//Cuando le de ok a la ventana se guardara la sessionstorage con el usuario y la llave
              sessionStorage.setItem("user",login.data.user);
              sessionStorage.setItem("key",login.data.key);
              navigate("/menu");

            });
             //cuando las credenciales sean correctas me lleve a esa ruta
            //Dentro del try me mandara lo que manddel endpoint si son correctas las credenciales y en el catch el error que mande el endpoint si son incorrectas
        } catch (error) {
            swal("Acceso denegado",JSON.parse(error.request.response).message,"error"); 
            console.log(JSON.parse(error.request.response).message);
        }

    }


    return ( 
    <>
    <div className="container col-3">
      <h1>Login Barberia</h1>

    <form onSubmit={ingresar}>
  <div className="form-outline mb-4">
    <input
     value={user} 
     onChange={(e)=>setuser(e.target.value)} 
     type="text"  className="form-control" />
    <label className="form-label" >Usuario</label>
  </div>

  <div className="form-outline mb-4">
    <input 
    value={pwd}
    onChange={(e)=>setpwd(e.target.value)} 
    type="password"  className="form-control" />
    <label className="form-label" >Contraseña</label>
  </div>

  <div className="row mb-4">
    <div className="col d-flex justify-content-center">
      <div className="form-check">
        <input className="form-check-input" type="checkbox" value=""  checked />
        <label className="form-check-label" > Remember me </label>
      </div>
    </div>

    <div className="col">
      <a href="#!">Forgot password?</a>
    </div>
  </div>

  <button type="submit" className="btn btn-primary btn-block mb-4">Sign in</button>

  
  <div className="text-center">
    <p>Not a member? <a href="#!">Register</a></p>
    <p>or sign up with:</p>
    <button type="button" className="btn btn-link btn-floating mx-1">
      <i className="fab fa-facebook-f"></i>
    </button>

    <button type="button" className="btn btn-link btn-floating mx-1">
      <i className="fab fa-google"></i>
    </button>

    <button type="button" className="btn btn-link btn-floating mx-1">
      <i className="fab fa-twitter"></i>
    </button>

    <button type="button" className="btn btn-link btn-floating mx-1">
      <i className="fab fa-github"></i>
    </button>
  </div>
</form>

    </div>
        
        

        
    
    </>);
}
 
export default Login;