import {useState} from "react" //Alamcenar informacion y modificar
import Hijo from "./Hijo";
const Ejemplo = () => {
    const [numeroA, setnumeroA] = useState(0);
    const [numeroB, setnumeroB] = useState(0);
    const [suma, setsuma] = useState(0);

    const Sumar=() =>{
        setsuma(parseInt(numeroA)+parseInt(numeroB));
    };
    
    return ( 
    <>
    <Hijo numeroA={numeroA} numeroB={numeroB} suma={suma} setnumeroA={setnumeroA} setnumeroB={setnumeroB} Sumar={Sumar}/>
    
    
    
    
    
    
    </> );
}
 
export default Ejemplo;