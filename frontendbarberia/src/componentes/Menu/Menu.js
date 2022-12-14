import { Link, useNavigate } from "react-router-dom";
import swal from "sweetalert";
import { useEffect } from "react";
const Menu = () => {
    let tipoUsuario = "user";
    const navigate = useNavigate();
    try {
      JSON.parse(sessionStorage.getItem("roles")).forEach(element => {//Identificar si el usuario es admin
        if(element.nombre==="ROLE_ADMIN"){
          tipoUsuario="admin";
        }
      });
    
      
    } catch (error) {
      
    }

      useEffect(()=>{
        if(sessionStorage.getItem("key")==null){
          swal("Acceso no autoriazdo","Debe digitar credenciales","error"); 
          navigate('/');
        }
      },[]);

      const cerrarSesion = () =>{
        sessionStorage.clear();
        navigate('/');
      }

    return ( <><nav className="navbar navbar-expand-lg navbar-light bg-light">
    <div className="container-fluid">
      {tipoUsuario==="admin" ? ( //Si es usuario admin le muestra la opcion de clientes
        <Link className="navbar-brand" to="/clientes">Clientes</Link>
      ):(
        ""
      )}
      
      <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav">
          <li className="nav-item">
            <a className="nav-link active" aria-current="page" href="#">Barberos</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#">Citas</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" onClick={cerrarSesion}>Cerrar sesión</a>
          </li>
          
        </ul>
      </div>
    </div>
  </nav></> );
}
 
export default Menu;