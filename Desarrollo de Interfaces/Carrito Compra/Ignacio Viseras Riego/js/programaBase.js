var nombreArticulo;
var precioArticluo;
var cantidad;
var menError;
var botonArticulo;


function validarFormulario(){
    filtroNombre = /^\D+$/;
    if (!nombreArticulo == "") {
        if (!filtroNombre.test(nombreArticulo)) {
            menError.textContent = "tienes que poner un nombre de producto correcto";
            return false;
        }
    }else{
        menError.textContent = "Campo por rellenar";
        return false;
    }

    if (!precioArticluo == "") {
        /*if (!filtroNombre.test(precioArticluo)) {
            menError.textContent = "tienes que poner un nombre de producto correcto";
            return false;
        }*/
    }else{
        menError.textContent = "Campo por rellenar";
        return false;
    }
}

function añadir(){
    var dinero;
    if (validarFormulario != true){
        nombreArticulo = nombreArticulo + ", " + nombreArticulo;
        console.log(nombreArticulo);
        dinero += dinero * cantidad;
        console.log(dinero);
    }
}

function inicializarVariables(){
    nombreArticulo = document.getElementById("nombreProducto").value;
    precioArticluo = document.getElementById("nombreProducto").value;
    cantidad = document.getElementById("nombreProducto").value;
    menError = document.getElementById("mensajeError");
    botonArticulo = document.getElementById("añadirProductos");
}

function inicializarEventos(){
    document.addEventListener("click", añadir);
}

window.onload = function(){
    inicializarVariables();
    inicializarEventos();
}
