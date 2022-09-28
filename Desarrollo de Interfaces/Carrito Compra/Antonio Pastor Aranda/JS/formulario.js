
//Declaracion de variables globales
var articulos;
var form= document.getElementById("form");
var nombre;
var precio;
var unidades;

function guardarArticulos (){
    articulos[nombre]= document.getElementById("nombre").value;
    articulos[precio]= document.getElementById("precio").value;
    articulos[unidades]= document.getElementById("unidades").value;
}


window.onload = function() {

    
     botonArticulos= addEventListener("click", guardarArticulos);

    
    
}

console.log (articulos);


/*------------------Validación JS-------------------- */


function validate() {
    if( document.form.nombre.value == "" ) {
       alert( "por favor, Introduce un nombre!" );
       document.form.nombre.focus() ;
       return false;
    }
   
 }