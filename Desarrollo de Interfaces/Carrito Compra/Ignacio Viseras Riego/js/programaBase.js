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
  if (
    !precioArticluo.value.match(filtroPrecio1) &&
    !precioArticluo.value.match(filtroPrecio2)
  ) {
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

window.onload = function () {
  inicializarVariables();
  inicializarEventos();
};
