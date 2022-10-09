//Declaracion de variables globales
var nombre;
var precio;
var unidades;
var articulos = {};
var precioT;
var resultado;
var suma = {};
var disabled;
var enabled;
var texto = "";
var Tarjeta = {};
var numeroTarjeta;
var nombreTitular;
var caducidad;
var cvc;

function guardarArticulos() {
  nombre = document.getElementById("nombre").value;
  precio = document.getElementById("precio").value;
  unidades = document.getElementById("unidades").value;
  acepto = document.getElementById("acepto").value;

  precioT = precio * unidades;
  if (articulos[nombre] == null) {
    articulos[nombre] = precioT;
    document.getElementById("articulos").innerHTML += nombre + ", ";
  } else {
    articulos[nombre] += precioT;
  }

  resultado = Object.values(articulos).join();
  document.getElementById("precioT").innerHTML = resultado;
}

/*------------------------------ Control de aparición de tarjeta o efectivo ----------------------*/

let datosTarjeta = document.getElementById("datosTarjeta");
const mostrar = (datosTarjeta) => {
  datosTarjeta.style.display = "block";
  importeT.style.display = "none";
};
const ocultar = (datosTarjeta) => {
  datosTarjeta.style.display = "none";
  importeT.style.display = "block";
};

function mostrarT(datosTarjeta) {
  let tarjeta = document.getElementById(datosTarjeta);
  window.getComputedStyle(tarjeta).display !== "none";
  mostrar(tarjeta);
}

function ocultarT(datosTarjeta) {
  let tarjeta = document.getElementById(datosTarjeta);
  window.getComputedStyle(tarjeta).display == "block";
  ocultar(tarjeta);
}

/*-------------------------------Reseteo de formulario---------------------------------*/

function limpiarFormulario() {
  document.getElementById("form").reset();
  document.getElementById("importe").value = "";
  document.getElementById("precioT").value = "";
  document.getElementById("articulos").value = "";
}

/*-------------------------------Sumatorio de efectivo---------------------------------*/

function sumatorioEfectivo() {
  suma = Object.values(articulos).reduce((a, b) => a + b, 0);
  document.getElementById("importe").innerHTML = suma;
}

/*-------------------------------Bloqueo imprimir---------------------------------*/

function bloqueoImprimir() {
  document.getElementById("imprimir").enabled = true;
}

function desbloqueoImprimir() {
  document.getElementById("imprimir").disabled = true;
}

function controlImprimir() {
  if (document.getElementById("acepto").checked == true) {
    document.getElementById("imprimir").disabled = false;
  } else {
    document.getElementById("imprimir").disabled = true;
  }
}

/*--------------------------------Carga de ventana---------------------------------*/

window.onload = function () {
  document
    .getElementById("botonArticulos")
    .addEventListener("click", guardarArticulos);
  document.getElementById("tarjeta").addEventListener("click", mostrarT);
  document
    .getElementById("efectivo")
    .addEventListener("click", sumatorioEfectivo);
  document.getElementById("acepto").addEventListener("click", controlImprimir);
  document.getElementById("botonArticulos").addEventListener("click", Validar);
  document
    .getElementById("restablecer")
    .addEventListener("click", limpiarFormulario);
};

/*-----------------------------------Validación JS-------------------- */

function Validar() {
  //validamos el nombre solo con letras y que admita compuestos

  if (nombre.trim().match(/^[a-zA-Z]+$/) == null) {
    alert("[ERROR]--Introduce el nombre correctamente.");
    return false;
  }
}
