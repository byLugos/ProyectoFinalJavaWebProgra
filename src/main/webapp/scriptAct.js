
document.getElementById('btnGuardar').addEventListener('click',function (){
    const tipoAct = document.getElementById('tipoAct');
    const selected = tipoAct.options[tipoAct.selectedIndex].value
    const nombreAct = document.getElementById('nameAct').value
    const descripAct = document.getElementById('descripcion').value
    const ponderado = document.getElementById('ponderado').value
    const fecha = document.getElementById('fecha').value
    //const subId = document.getElementById('descripcion').value

    const xhr = new XMLHttpRequest()
    xhr.open("POST","ServletRoom",true)

    xhr.onreadystatechange = ()=>{

        if( xhr.readyState === 4 && xhr.status === 200 ){

            const resp = JSON.parse( xhr.responseText)

            if(resp.state){
                alert("Hecho, se inserto")
            }else{
                alert("No se ha insertado")
            }

        }

    }

    const data = `selected=${selected}&nombreAct=${nombreAct}&descripAct=${descripAct}&ponderado=${ponderado}&fecha=${fecha}`;

    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhr.send( data )

})