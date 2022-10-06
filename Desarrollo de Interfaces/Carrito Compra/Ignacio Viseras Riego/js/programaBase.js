var nombreProducto;
var precioArticluo;
var cantidad;
var total;
var recibirLista = '';
var listado;
var menError;
var botonArticulo;


 function validarFormulario(){
    filtroNombre = /^\D+$/;
    filtroPrecio1 = /^[0-9]/;
    filtroPrecio2 = /^[0-9]+.[0-9]{2}/;
    /* if (!nombreProducto == "") {
        if (!filtroNombre.test(nombreProducto)) {
            menError.textContent = "tienes que poner un nombre de producto correcto";
            return false;
        }
    }else{
        menError.textContent = "Campo por rellenar";
        return false;
    } */
    if (nombreProducto.value.trim() == "") {
        menError.textContent = "Campo por rellenar";
        return false;
    }
    if (!nombreProducto.value.match(filtroNombre)) {
        menError.textContent = "tienes que poner un nombre de producto correcto";
        return false;
    }
   /*if (!precioArticluo == "") {
        if (!filtroPrecio1.test(precioArticluo) || filtroPrecio2.test(precioArticluo)) {
            menError.textContent = "Ejemplo: 2.45";
            return false;
        }
    }else{
        menError.textContent = "Campo por rellenar";
        return false;
    }*/
    return true;
}

function anadir(){
    if(validarFormulario() != false){
        console.log(nombreProducto);
        guardar();
       /*  console.log(precioArticluo); */
    }
}

function guardar(){
    recibirLista += nombreProducto.value + ", ";
    listado = recibirLista.substring(0, recibirLista.length-2)
    total += precioArticluo * cantidad;
}

function inicializarVariables(){
    nombreProducto = document.getElementById("nombreProducto");
    precioArticluo = document.getElementById("precioArticluo").value; 
    cantidad = document.getElementById("cantidad").value;
    menError = document.getElementById("mensajeError");
    botonArticulo = document.getElementById("añadirProductos");
}

function inicializarEventos(){
    document.getElementById("añadirProductos").addEventListener("click", anadir);
}

window.onload = function(){
    console.log("hola");
    inicializarVariables();
    console.log("como estamosss");
    inicializarEventos();

}
