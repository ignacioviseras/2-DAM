
//Declaracion de variables globales
var nombre;
var precio;
var unidades;
var articulos={};
var precioT;
var resultado;
var suma={};
var nombreArticulos = '';
var precioTotal = 0;


//FUNCIONES--------------------------------------------------------

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

    resultado=Object.values(articulos).reduce((a, b) => a + b, 0);
<<<<<<< HEAD
        document.getElementById("precioT").innerHTML=resultado;  

        
=======
        document.getElementById("precioT").innerHTML=resultado; 
        document.getElementById("importe").innerHTML=resultado;      
>>>>>>> c727f5a39763a7f8d0df8b31edb7242b74196911
        
}
  
/*------------------------------ Control de aparición de tarjeta o efectivo ----------------------*/

function ocultarTarjeta() {
    const tipoPago = document.getElementsByName("pago")[0].value
    let datosTarjeta = document.getElementById("datosTarjeta");

    if (tipoPago === 'Tarjeta') {
        datosTarjeta.style.display = 'block';
        importeT.style.display = 'none';
    } else{
        datosTarjeta.style.display = 'none';
        importeT.style.display = 'block';
        
    }
<<<<<<< HEAD
=======
}
>>>>>>> c727f5a39763a7f8d0df8b31edb7242b74196911

/*-------------------------------Reseteo de formulario---------------------------------*/
    
function ocultarTarjeta() {
    const tipoPago = document.getElementsByName("pago")[0].value
    let datosTarjeta = document.getElementById("datosTarjeta");
    
    if (tipoPago === 'Tarjeta') {
        datosTarjeta.style.display = 'block';
        importeT.style.display = 'none';
    } else{
        datosTarjeta.style.display = 'none';
        importeT.style.display = 'block';
    }
}

function limpiarFormulario() {
    document.getElementById("form").reset();
    document.getElementById("importe").value = "";
    document.getElementById("precioT").value = "";
    document.getElementById("articulos").value = "";
}

/*-------------------------------Sumatorio de efectivo---------------------------------*/
    
function sumatorioEfectivo() {
    suma= Object.values(articulos).reduce((a, b) => a + b, 0);
    document.getElementById("importe").innerHTML=suma;
     
}

/*-------------------------------Bloqueo imprimir---------------------------------*/
    
function bloqueoImprimir() {
   
    document.getElementById("imprimir").enabled=true;
}

function desbloqueoImprimir(){
            
    document.getElementById("imprimir").disabled=true;
}

function controlImprimir(){
<<<<<<< HEAD
    var nombreArticulos = ''
    var precioTotal = 0
    if(document.getElementById("acepto").checked==true){
        document.getElementById("imprimir").disabled=false;
        for (const val of Object.keys(articulos)) {
            nombreArticulos += val + ', '
        }
        for (const val of Object.values(articulos)) {
            precioTotal += val
        }
        nombreArticulos = nombreArticulos.slice(0, -2)
        console.log(document.getElementsByName("pago")[0].value)
        alert("Los articulos de mi carro son : " + nombreArticulos + " y el precio total es: "+ precioTotal + " € "+ " Forma de pago: " + document.getElementsByName("pago")[0].value)
=======
    
    if(document.getElementById("acepto").checked==true){
        document.getElementById("imprimir").disabled=false;
        for (const val of Object.keys(articulos)) {
            nombreArticulos += val + ', ';
        }
        for (const val of Object.values(articulos)) {
            precioTotal += val;
        }
        nombreArticulos = nombreArticulos.slice(0, -2);
        console.log(document.getElementsByName("pago")[0].value);
        alert("Los articulos de mi carro son : " + nombreArticulos + " y el precio total es: "+ precioTotal + " € "+ " Forma de pago: " + document.getElementsByName("pago")[0].value);
>>>>>>> c727f5a39763a7f8d0df8b31edb7242b74196911

    } else{
        document.getElementById("imprimir").disabled=true;

    }
} 

/*--------------------------------Carga de ventana---------------------------------*/

window.onload = function() {

      
    document.getElementById("botonArticulos").addEventListener("click", guardarArticulos);
<<<<<<< HEAD
//    document.getElementById("tarjeta").addEventListener("click", mostrarT);
    document.getElementById("efectivo").addEventListener("click", sumatorioEfectivo);
=======
>>>>>>> c727f5a39763a7f8d0df8b31edb7242b74196911
    document.getElementById("acepto").addEventListener("click", controlImprimir);
    document.getElementById("botonArticulos").addEventListener("click", Validar);
    document.getElementById("restablecer").addEventListener("click", limpiarFormulario);
}


/*-----------------------------------Validación JS-------------------- */

function Validar(){

    //validamos el nombre solo con letras y que admita compuestos

    if (nombre.trim().match(/^[a-zA-Z]+$/)==null) {
        alert('[ERROR]--Introduce el nombre correctamente.');
        return false;
    }
 
}
