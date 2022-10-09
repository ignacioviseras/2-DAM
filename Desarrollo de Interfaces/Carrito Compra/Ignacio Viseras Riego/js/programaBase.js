var nombreProducto;
var precioArticluo;
var cantidad;
var total = 0;
var recibirLista = "";
var listado;
var menError;
var menErrorPrecio;
var botonArticulo;

function validarFormulario() {
  filtroNombre = /^\D+$/;
  filtroPrecio1 = /^[0-9]/;
  filtroPrecio2 = /^[0-9]+.[0-9]{2}/;
  if (nombreProducto.value.trim() == "") {
    menError.textContent = "Campo por rellenar";
    return false;
  }
  if (!nombreProducto.value.match(filtroNombre)) {
    menError.textContent = "tienes que poner un nombre de producto correcto";
    return false;
  }
  if (precioArticluo.value.trim() == "") {
    menErrorPrecio.textContent = "Campo por rellenar";
    return false;
  }
  if (!precioArticluo.value.match(filtroPrecio1) && !precioArticluo.value.match(filtroPrecio2)) {
    menErrorPrecio.textContent = "introduce bn el numero";
    return false;
  }
  return true;
}

function anadir() {
  if (validarFormulario() != false) {
    guardar();
  }
}

function guardar() {
  recibirLista += nombreProducto.value + ", ";
  listado = recibirLista.substring(0, recibirLista.length - 2);
  total += precioArticluo.value * cantidad.value;
  console.log(listado);
  console.log(total);
  document.getElementById("listaCarri").value = listado;
  document.getElementById("precioCarri").value = total;
}


function inicializarVariables() {
  nombreProducto = document.getElementById("nombreProducto");
  precioArticluo = document.getElementById("precioArticluo");
  cantidad = document.getElementById("cantidad");
  menError = document.getElementById("mensajeError");
  menErrorPrecio = document.getElementById("mensajeErrorPrecio");
  botonArticulo = document.getElementById("añadirProductos");
}

function inicializarEventos() {
  document.getElementById("añadirProductos").addEventListener("click", anadir);
}
function valorSuma(){
    document.getElementById("importe").value = listado;
}

/*antonio*/
let datosTarjeta = document.getElementById("datosTarjeta");
const mostrar = (datosTarjeta) => {
    datosTarjeta.style.display = 'block';
    importeT.style.display = 'none';
};
const ocultar = (datosTarjeta) => {
    datosTarjeta.style.display = 'none';
    importeT.style.display = 'block';
};

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
/* let opcion = document.getElementById("opcionTarjeta");
const mostrarC = (opcion) => {
    opcionTarjeta.style.display = 'block';
    opcionEfectivo.style.display = 'none';
}

const ocultarC = (opcion) => {
    opcionEfectivo.style.display = 'block';
    opcionTarjeta.style.display = 'none';
}

function mostrar(opcion){
    let marcado = document.getElementById(opcionTarjeta);
    window.getComputedStyle(marcado).display !== 'none';
        mostrarC(marcado);
}

function ocultar(opcion){
    let marcado = document.getElementById(opcionTarjeta);
    window.getComputedStyle(marcado).display == 'block';
        ocultarC(marcado);
}  */

function inicializarVariables(){
    nombreProducto = document.getElementById("nombreProducto");
    precioArticluo = document.getElementById("precioArticluo"); 
    cantidad = document.getElementById("cantidad");
    menError = document.getElementById("mensajeError");
    menErrorPrecio = document.getElementById("mensajeErrorPrecio");
    botonArticulo = document.getElementById("añadirProductos");

}

function inicializarEventos(){
    document.getElementById("añadirProductos").addEventListener("click", anadir);
    document.getElementById("tarjeta").addEventListener("click", mostrarT);
    document.getElementById("efectivo").addEventListener("click", valorSuma);
}

window.onload = function (){
  inicializarVariables();
  inicializarEventos();
}