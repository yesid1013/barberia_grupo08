const Hijo = ({numeroA,numeroB,setnumeroA,setnumeroB,suma,Sumar}) => {
    return ( <>
    <div className="container col-3">
    <div>
        Sumando a <input type="number" className="form-control"
        value={numeroA}
        onChange={(e)=>setnumeroA(e.target.value)}
        
        ></input>
    </div>
    <br/>
    <div>
        Sumando b <input type="number" className="form-control"
        value={numeroB}
        onChange={(e)=>setnumeroB(e.target.value)}
        ></input>
    </div>
    <br/>
    <div>
        Sumando a <button onClick={Sumar}> Sumar</button>
    </div>
    <br/>
    <div>
        Resultado<input type="text" className="form-control" value={suma}></input>
    </div>

    </div>
    
    
    </> );
}
 
export default Hijo;