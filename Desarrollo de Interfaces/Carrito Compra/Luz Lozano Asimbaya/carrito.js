<<<<<<< Updated upstream
=======
let articulos;
let articulo;
let precio;
let unidades;
let total = 0;
let formaPago;
let pagoTarjeta;
let pagoEfectivo;
let importe;
let validarNombre = /^[a-zA-Z\s]{2,254}?$/; // /^[a-zA-ZÑñÁáÉéÍíÓóÚúÜü\s]+$/
let validarPrecio = /^\d+([,.]\d{1,2})?$/; // /^isNaN(valor)$/

function añadirArticulos() {
  if (articulos.value == "") {
    articulos.value = nombreArticulo.value;
  } else {
    articulos.value = articulos.value.concat(",", nombreArticulo.value);
  }
  importe = precioArticulo.value * unidades.value;
  total += importe;
  precioTotal.value = total;
  nombreArticulo.value = "";
  precioArticulo.value = "";
  unidades.value = 1;
}

function validarArticulos() {
  var errores = 0;
  if (!validarNombre.test(nombreArticulo.value)) {
    nombreError.innerHTML = "El artículo no es válido";
    errores++;
  } else {
    nombreError.innerHTML = "";
  }
  if (!validarPrecio.test(precioArticulo.value)) {
    precioError.innerHTML = "El precio no es válido";
    errores++;
  } else {
    precioError = "";
  }
  if (errores == 0) {
    añadirArticulos();
  }
}
/*
function pago() {
  if ((document.getElementById("formaPago").value = "seleccione")) {
    document.getElementById("pagoTarjeta").style.display = "none";
  } else {
    document.getElementById("pagoTarjeta").style.display = "block";
  }
}*/

function initVariables() {
  formaPago = document.getElementById("formaPago");
  pagoTarjeta = document.getElementById("pagoTarjeta");
  pagoEfectivo = document.getElementById("pagoEfectivo");
  nombreArticulo = document.getElementById("nombreArticulo");
  articulos = document.getElementById("articulos");
  precioArticulo = document.getElementById("precioArticulo");
  unidades = document.getElementById("unidades");
  precioTotal = document.getElementById("precioTotal");
  boton = document.getElementById("boton");
  nombreError = document.getElementById("nombreError");
  precioError = document.getElementById("precioError");

  pagoEfectivo.style.display = "none";
  pagoTarjeta.style.display = "none";
}

function initEventos() {
  //formaPago.addEventListener("change", pago());
  boton.addEventListener("click", añadirArticulos());
}
window.onload = function () {
  initVariables();
  initEventos();
};
>>>>>>> Stashed changes
