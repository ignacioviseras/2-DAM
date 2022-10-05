
//Declaracion de variables globales
var nombre;
var precio;
var unidades;
var articulos={};
var precioT;
var resultado;
var acepto;


function guardarArticulos (){
    nombre= document.getElementById("nombre").value;
    precio= document.getElementById("precio").value;
    unidades= document.getElementById("unidades").value;
    acepto= document.getElementById("acepto").value;
   
    precioT= precio*unidades;
    if (articulos[nombre]== null){
        articulos[nombre]=precioT;
        document.getElementById("articulos").innerHTML += nombre+ ", ";
    }else{
        articulos[nombre]+= precioT;
    }

    resultado=Object.values(articulos).join();
        document.getElementById("precioT").innerHTML=resultado;  
        
}


 
    let datosTarjeta = document.getElementById("datosTarjeta");
    const mostrar = (datosTarjeta) => {
        datosTarjeta.style.display = 'block';
    }
    const ocultar = (datosTarjeta) => {
        datosTarjeta.style.display = 'none';
    }

function mostrarT(datosTarjeta) { 
    let tarjeta = document.getElementById(datosTarjeta);
    window.getComputedStyle(tarjeta).display !== 'none';
        mostrar(tarjeta);
}

function ocultarT(datosTarjeta) { 
    let tarjeta = document.getElementById(datosTarjeta);
    window.getComputedStyle(tarjeta).display == 'block'; 
        ocultar(tarjeta);
}

    
function limpiarFormulario() {
    document.getElementById("form").reset();
}



window.onload = function() {

      
    document.getElementById("botonArticulos").addEventListener("click", guardarArticulos);
    document.getElementById("tarjeta").addEventListener("click", mostrarT);
    document.getElementById("efectivo").addEventListener("click", ocultarT);
    document.getElementById("restablecer").addEventListener("click", limpiarFormulario);

    
}





/*------------------Validación JS-------------------- */

