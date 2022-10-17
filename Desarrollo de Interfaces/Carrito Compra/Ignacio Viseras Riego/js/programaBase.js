var nombreProducto;
var precioArticluo;
var cantidad;
var total = 0;
var recibirLista = '';
var listado;
var menError;
var menErrorPrecio;
var botonArticulo;

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
  if (!nombreProducto.value.match(filtroNombre)) {
    menError.textContent = "tienes que poner un nombre de producto correcto";
    return false;
  }
  if (precioArticluo.value.trim() == "") {
    menErrorPrecio.textContent = "Campo por rellenar";
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
  if (cantidad.value.trim() <= 0) {
    menErrorCantidad.textContent = "Tiene que ser un numero positivo";
    return false;
  }
  return true;

 function validarFormulario(){
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
        menErrorPrecio.textContent = "Campo por rellenar"
        return false;
    }
    if (!(precioArticluo.value.match(filtroPrecio1)) && !(precioArticluo.value.match(filtroPrecio2))) {
        menErrorPrecio.textContent = "introduce bn el numero";
        return false;
    }
    return true;
}

function anadir(){
    if(validarFormulario() != false){
        guardar();
    }
}

function guardar(){
    recibirLista += nombreProducto.value + ", ";
    listado = recibirLista.substring(0, recibirLista.length-2);
    total += precioArticluo.value * cantidad.value;
    console.log(listado);
    console.log(total);
    document.getElementById("listaCarri").value = listado;
    document.getElementById("precioCarri").value = total.toFixed(2);
  }
}

/* validacion tarjeta */
function validacionTarjeta() {
  filtroNumeroTarjeta = /[0-9]{16}/;
  filtroNombre = /[a-zA-Z]{0,} [a-zA-Z]{0,} [a-zA-Z]{0,}/;
  filtroCaducidad = /[0-9]{2}\/[0-9]{2}/;
  filtroCVC = /[0-9]{0,}/
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

/** funcion para aceptar los terminos los cuales no nos dejara
 * poner en caso de no rellenar los campos del form */
function terminos(){
  if (document.getElementById("acepta").checked == false)
    document.getElementById("imprimir").disabled = true;
  if (listado != undefined && total != 0) {
    document.getElementById("imprimir").disabled = false;
  }else{
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
    document.getElementById("precioCarri").value = total;
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
    document.getElementById("añadirProductos").addEventListener("click", anadir);
}

window.onload = function(){
    inicializarVariables();
    inicializarEventos();
}
