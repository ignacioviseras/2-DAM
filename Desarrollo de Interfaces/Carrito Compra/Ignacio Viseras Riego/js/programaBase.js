var nombreProducto;
var precioArticluo;
var cantidad;
var total = 0;
var recibirLista = "";
var listado;
var menError;
var menErrorPrecio;
var botonArticulo;
var Tarjeta = {};

/**validacion para los campos del formulario */
function validarFormulario() {
  /** ^ indica que el patrón debe iniciar con los caracteres dentro de los corchetes
      [A-Z] indica que los caracteres admitidos son letras del alfabeto
      + indica que los caracteres dentro de los corchetes se pueden repetir
      $ indica que el patrón finaliza con los caracteres que están dentro de los corchetes.
      i indica que validaremos letras mayúsculas y minúsculas (case-insensitive) 
  */
  filtroNombre = /^[A-Z]+$/i;
  /**esto acepta numeros enteros o numeros con 2 decimales si pones 2.
   * lo da por mal acepta tanto , como .
   */
  filtroPrecio = /^[0-9]+?([.,\,][0-9]{2})?$/;
  if (nombreProducto.value.trim() == "") {
    menError.textContent = "Campo por rellenar";
    return false;
  }
  menError.textContent = "";
  if (!nombreProducto.value.match(filtroNombre)) {
    menError.textContent = "tienes que poner un nombre de producto correcto";
    return false;
  }
  if (precioArticluo.value.trim() == "") {
    menErrorPrecio.textContent = "Campo por rellenar";
    return false;
  }
  menErrorPrecio.textContent = "";
  if (precioArticluo.value.trim() <= 0) {
    menErrorPrecio.textContent = "Tiene que se un numero positivo";
    return false;
  }
  if (!precioArticluo.value.match(filtroPrecio)) {
    menErrorPrecio.textContent = "introduce bn el numero 2 o 2.15";
    return false;
  }
  if (cantidad.value.trim() == "") {
    menErrorCantidad.textContent = "Campo por rellenar";
    return false;
  }
  menErrorCantidad.textContent = "";
  if (cantidad.value.trim() <= 0) {
    menErrorCantidad.textContent = "Tiene que se un numero positivo";
    return false;
  }
  return true;
}

/** aqui guardamos los valores del formulario
 * en caso de que cumpla la validacion*/
 function guardar() {
  if (validarFormulario() != false) {
    recibirLista += nombreProducto.value + ", ";
    listado = recibirLista.substring(0, recibirLista.length - 2);
    total += precioArticluo.value * cantidad.value;
    document.getElementById("listaCarri").value = listado;
    document.getElementById("precioCarri").value = total.toFixed(2);
  }
}

/* validacion tarjeta */
function validacionTarjeta() {
  filtroNumeroTarjeta = /[0-9]{16}/;
  filtroNombre = /[a-zA-Z]{0,} [a-zA-Z]{0,} [a-zA-Z]{0,}/;
  filtroCaducidad = /[0-9]{2}\/[0-9]{2}/;
  filtroCVC = /[0-9]{3}/
  if (numeroTarjeta.value.trim() == "") {
    errorTarjeta.textContent = "Campo por rellenar";
    return false;
  }
  if (!numeroTarjeta.value.match(filtroNumeroTarjeta)) {
    errorTarjeta.textContent = "Numero de Tarjeta Erroneo";
    return false;
  }
  if (nombreTitular.value.trim() == "") {
    errorTitular.textContent = "Campo por rellenar";
    return false;
  }
  if (!nombreTitular.value.match(filtroNombre)) {
    errorTitular.textContent = "Tienes que introducir 3 nombres sin caracteres especiales";
    return false;
  }
  if (caducidad.value.trim() == "") {
    errorCaducidad.textContent = "Campo por rellenar";
    return false;
  }
  if (!caducidad.value.match(filtroCaducidad)) {
    errorCaducidad.textContent = "Formato de caducidad xx/xx";
    return false;
  }
  if (cvc.value.trim() == "") {
    errorCVC.textContent = "Campo por rellenar";
    return false;
  }
  if (!cvc.value.match(filtroCaducidad)) {
    errorCVC.textContent = "Formato de CVC xxx";
    return false;
  }
  return true;
}

/*recibir datos tarjeta*/
/* function guardarTarjeta(){
  if (validacionTarjeta() != false) {
    Tarjeta += {"numeroTarjeta.value" "filtroNombre.value"}
  }
} */

/** Aparicion campos del select */
function ocultarTarjeta() {
  const tipoPago = document.getElementsByName("pago")[0].value;
  let datosTarjeta = document.getElementById("datosTarjeta");
  let payBox = document.getElementById("payBox");

  if (tipoPago === 'tarjeta') {
    payBox.style.display = 'block';
    payBox.style.height= '316px';
    payBox.style.top= '160px';
    payBox.style.left= '110px';
    datosTarjeta.style.display = 'block';
    importeT.style.display = 'none';
  } else{
    payBox.style.display = 'block';
    payBox.style.height= '165px';
    payBox.style.top= '200px';
    payBox.style.left= '120px';
    importeT.style.display = 'block';
    datosTarjeta.style.display = 'none';
    document.getElementById("importe").value = total.toFixed(2) + " €";
  }
}

/** borra todo el formulario tanto campos
 * para rellenar como de resultado*/
function resetForm(){
  document.getElementById("div-Articulos").reset();
}

/** funcion para aceptar los terminos los cuales no nos dejara
 * poner en caso de no rellenar los campos del form */
function terminos(){
  if (document.getElementById("acepta").checked == false)
    document.getElementById("imprimir").disabled = true;
  if (listado != undefined && total != 0) {
    document.getElementById("imprimir").disabled = false;
  }else{
    document.getElementById("acepta").value = off;
    alert("Tienes que introducir elementos en el carrito y sus precios")
  }
}

/**aqui se ejecuta el boton imprimir 
 * q inicialmente tiene q estar ""oculto""" */
function imprimir(){
  if (document.getElementById("acepta").checked == true) {
    alert("Articulos en el carrito " + listado + ". Precio " + total.toFixed(2) + "€ " + "Forma de pago: "+ document.getElementsByName("pago")[0].value)
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
  menErrorCantidad = document.getElementById("mensajeErrorCantidad");
  botonArticulo = document.getElementById("añadirProductos");
  errorTarjeta = document.getElementById("errorTarjeta");
  errorTitular = document.getElementById("errorTitular");
  errorCaducidad = document.getElementById("errorCaducidad");
  errorCVC = document.getElementById("errorCVC");
}

function inicializarEventos(){
  document.getElementById("añadirProductos").addEventListener("click", guardar);
  document.getElementById("acepta").addEventListener("click", terminos);
  document.getElementById("imprimir").addEventListener("click", imprimir);
  document.getElementById("reset").addEventListener("click", resetForm);
  /*con el blur hacemos q la validacion se cumpla antes de fulsar el boton de añadir */
  nombreProducto.addEventListener("blur", validarFormulario);
  precioArticluo.addEventListener("blur", validarFormulario);
  cantidad.addEventListener("blur", validarFormulario);
}

window.onload = function (){
  inicializarVariables();
  inicializarEventos();
}