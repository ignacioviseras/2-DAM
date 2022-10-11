var nombreProducto;
var precioArticluo;
var cantidad;
var total = 0;
var recibirLista = "";
var listado;
var menError;
var menErrorPrecio;
var botonArticulo;

/**validacion para los campos del formulario */
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

/* validacion tarjeta */

/** aqui guardamos los valores del formulario
 * en caso de que cumpla la validacion*/
function guardar() {
  if (validarFormulario() != false) {
    recibirLista += nombreProducto.value + ", ";
    listado = recibirLista.substring(0, recibirLista.length - 2);
    total += precioArticluo.value * cantidad.value;
    document.getElementById("listaCarri").value = listado;
    document.getElementById("precioCarri").value = total;
  }
}

/** Aparicion campos del select */
function ocultarTarjeta() {
  const tipoPago = document.getElementsByName("pago")[0].value;
  let datosTarjeta = document.getElementById("datosTarjeta");

  if (tipoPago === 'tarjeta') {
      datosTarjeta.style.display = 'block';
      importeT.style.display = 'none';
  } else{
      datosTarjeta.style.display = 'none';
      importeT.style.display = 'block';
      document.getElementById("importe").value = total;
  }
}
/** borra todo el formulario tanto campos
 * para rellenar como de resultado*/
function resetForm(){
  document.getElementById("div-Articulos").reset();
}

/* checkbox */
/* imprimir */
function imprimir(){
  if (document.getElementById("acepta").checked == true) {
    document.getElementById("imprimir").disabled = false;
    alert("asdasd" + listado + "asd asd" + total + "€" + "Forma de pago"+ document.getElementById(""))
  } else {
    document.getElementById("imprimir").disabled=true;
  }
}

function inicializarVariables(){
    nombreProducto = document.getElementById("nombreProducto");
    precioArticluo = document.getElementById("precioArticluo"); 
    cantidad = document.getElementById("cantidad");
    menError = document.getElementById("mensajeError");
    menErrorPrecio = document.getElementById("mensajeErrorPrecio");
    botonArticulo = document.getElementById("añadirProductos");

}

function inicializarEventos(){
    document.getElementById("añadirProductos").addEventListener("click", guardar);
    document.getElementById("reset").addEventListener("click", resetForm);
}

window.onload = function (){
  inicializarVariables();
  inicializarEventos();
}