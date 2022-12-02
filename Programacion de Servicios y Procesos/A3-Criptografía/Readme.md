# A3 Criptografía

## ¿Qué es la criptografía?

---

Para comprender un poco más la criptografía podemos definirla como la técnica o disciplina que permite escribir apelando a un código oculto.

Dentro de esta técnica vamos a aclarar unos conceptos como son:

- **Criptograma**→ Se considera al mensaje secreto. Mensaje en clave o cifrado.
- **Encriptar**→ Proceso en el que volvemos ilegible una información. También lo podemos definir como cifrar.
- **Desencriptar**→ Proceso que revierte el mensaje o información a un formato legible. También podemos definirlo como Descifrar.

La criptografía la podemos dividir en 3 grandes grupos. 

1. **Simétrica**→ Técnica que utiliza una sola clave para descifrar y cifrar la información. 
2. **Asimétrica**→ Técnica que utiliza una clave para cifrar y otra para descifrar. (encontrando una clave publica y una privada)
3. **Mixta**→ Es considerada una mezcla entre ambas.

El tipo más utilizado de cifrado es el mixto un ejemplo de este seria el protocolo HTTPS.

## ¿Qué es el hash?

---

El Hash es una función de resumen, es capaz de crear un algoritmo para una determinada información de entrada y que devuelve un código único como salida que resume dicha información.

Una vez obtenido el resumen de salida nunca obtendremos el mensaje original. Pero para la misma información de entrada, si podremos obtener el mismo resumen hash (siempre que se utilice el mismo algoritmo)

Tipos de algoritmos hash:

- MD5: Obsoleto actualmente
- SHA: Se utiliza actualmente por resistencia a las colisiones. (ocurrencia de dos mensajes que generan la misma cadena de salida de resumen).

Como en cualquier otro proyecto dividimos en tres paquetes.

# 🗃️beans

### 📔Users.java

---

El primero que tendremos será el Beans donde dentro de este se encontrará las clases con sus atributos, getter and setters.

```java
public class Users implements Serializable{
  private static final long serialVersionUID = 1L;
  String name, password;
	
  public Users(String name, String password){
    this.name = name;
    this.password = password;
  }
```

Tendremos que tener en cuenta que la clase deberá ser implementada por serializable aplicándole un id estático.

# 🗃️daos

### 📔IntUsersDao.java

---

Como podemos observar hemos  creado tres métodos para poder devolver la lista de usuarios que hemos generado en el Impl, un método para buscar por nombre y por último uno para obtener la contraseña mediante el nombre.

```java
public interface IntUsersDao {

  public List<Users> userList();
  public String findByName(String name);
  public String getCon(String name);

}
```

### 📔UsersDaoImpl.java

---

En esta clase necesitamos una crear una lista de usuarios, para eso en nuestro constructor la creamos y luego cargamos todos los usuarios en esa lista.

```java
public class UsersDaoImpl implements IntUsersDao{

  private ArrayList<Users> list;
	
  public UsersDaoImpl() {
    list = new ArrayList<Users>();
    loadingUsersList();
  }
```

Cada usuario tiene 2 datos:

1. **Nombre**→ Es el nombre del usuario
2. **Contraseña**→ Es el valor hash de la contraseña del usuario

Al guardar solo el valor hash de la contraseña lo que estamos haciendo es hacer esta ilegible de tal manera que en el caso de que alguien acceda a nuestra base de datos no podrá sacar las contraseñas de usuario.

```java
private void loadingUsersList(){
  list.add(new Users("Paco","�N����'Ry:�LUNS�쬃��J�O���h�j(A��=�Z)�Vba�O���S��ڕ��"));//asd
  list.add(new Users("Juan","%/��5�O�c�٠K�<:Vv|�ր��{mvu�'Pw}��v�AX��2���SH�\bU�n5�FiN�E&"));//gas
  list.add(new Users("Manolo","r��W>.��l#��qP$�������$(���θ�/���`��k���\b'zQ@��ĉ���I��"));//zx
}
```

Este método devuelve la lista de usuarios.

```java
@Override
public List<Users> userList() {
  return list;
}
```

En este método realizamos una búsqueda de usuario por nombre y nos devolverá su nombre.

```java
@Override
public String findByName(String name) {
  for (Users user : list) {
    if (user.getName().equals(name)) {
      return user.getName();
    }
  }
  return null;
}
```

En este método le pasamos el nombre de un usuario y obtenemos la contraseña de este.

⚠️Las contraseñas que están guardadas en realidad es el valor hash de la contraseña⚠️

```java
public String getCon(String name) {
  for (Users user : list) {
    if (user.getName().equals(name)) {
      return user.getPassword();
    }
  }
  return null;
}
```

# 🗃️controller

### 📔Main.java

---

Aquí será donde se ejecutara el programa.

1. Tenemos un bucle while para que sólo pueda haber 3 intentos de introducir la contraseña como se requiere en el enunciado.
    - En caso de haber gastado esos intentos nos sacará del programa.
    
    ```java
    intento++;
    System.out.println("Contraseña erronea intento nº "+ intento +"/3");
    ```
    
2. Solicitamos un nombre de usuario y una contraseña
    - A la contraseña que nos pasan le sacaremos el hash Sha-512 una vez obtenido lo tendremos que pasar a String.
    
    ```java
      public static void main(String[] args) throws NoSuchAlgorithmException {
      int intento = 0;
      while(intento < 3) {//1
        System.out.println("----introduce un nombre----");
        String nombre = nextLine();
    		
        MessageDigest md = MessageDigest.getInstance("SHA-512");//2
        System.out.println("----introduce una contraseña----");
        String passwd = nextLine();//2
        byte[] passwdByte = passwd.getBytes();//2
        md.update(passwdByte);//2
        byte[] hashPasswd = md.digest();//2
        String strHashPw = new String(hashPasswd);//2
    ```
    

1. En el if comprobamos que la contraseña escrita sea la misma que la contraseña que tiene el usuario
    - ⚠️Hay que recalcar que aunque sean String los 2 datos son el hash de un texto⚠️
    - De ser así accederemos a las opciones que nos permitirá cifrar y descifrar el contenido.
    
    ```java
    if(strHashPw.equals(udao.getCon(nombre))) {
      try {
        //creamos un generador de claves
        KeyGenerator genKey = KeyGenerator.getInstance("AES");
        //creamos la clave
        SecretKey key = genKey.generateKey();
        //el cifrador nos permitira cifrar y descifrar
        Cipher cipher = Cipher.getInstance("AES");
    		
        Boolean salir = false;
        String EncryptedMessage = "";
        String DecryptedMessage;
        String message;
        byte[] bytesMessage;
        byte[] bytesDecryptedMessage = new byte [0];
        byte[] bytesEncryptedMessage = new byte [0];
        int flag = 0;
    		
        System.out.println("======Bienvenido "+ udao.findByName(nombre) +"======");
    ```
    
2. Accederemos a un bucle en el que solicitaremos la opción que se desea ejecutar.
    
    ```java
    while(salir == false) {
      System.out.println("===========Menu==========");
      System.out.println("1. Encriptar frase" + "\n" 
                        +"2. Desencriptar frase" + "\n" 
                        +"3. Salir del programa" + "\n");
    
      String opcion = nextLine();
    ```
    
    1. Opción 1 → Cifrar
        - En esta opción solicitaremos una frase una vez nos la proporcionen tendremos que hacer los siguientes pasos.
            1. Obtener el numero de bytes del String ya que el Chiper solo trabaja con bytes.
            2. Le decimos al cifrador que queremos hacer la acción de **descifrar** Cipher.ENCRYPT_MODE y que la clave que usaremos será la de la variable key
            3. Lo ciframos
            4. Lo pasamos de byte a String
        - ⚠️ Pasamos el valor de flag a 1 esto nos será útil para la opción de descifrar.
        
        ```java
        if(opcion.equals("1")) {
        //----Cifrar----
          System.out.println("******Introduce una frase******");
          message = nextLine();
        
          bytesMessage = message.getBytes();//1
          cipher.init(Cipher.ENCRYPT_MODE, key);//2
          bytesEncryptedMessage = cipher.doFinal(bytesMessage);//3
          EncryptedMessage = new String(bytesEncryptedMessage);//4
        	
          System.out.println("mensaje sin cifrar-> " + message);
          System.out.println("mensaje cifrado-> " + EncryptedMessage);
          flag = 1;//⚠️
        }
        ```
        
    2. Opción 2 → Descifrar
        - En esta opción descifraremos la frase que anteriormente fue cifrada.
            1. Antes de nada comprobaremos que hay un mensaje cifrado así nos evitamos que rompa el programa.
            2. Le de decimos al cifrador que queremos hacer la acción de **descifrar** Cipher.DECRYPT_MODE y que la clave que usaremos será la de la variable key.
            3. Lo desciframos utilizando los bytes del texto cifrado.
            4. Lo pasamos a String.
            
            ```java
            if(opcion.equals("2")) {
            //----Descifrar----
              if(flag != 0) {//1
                cipher.init(Cipher.DECRYPT_MODE, key);//2
                bytesDecryptedMessage = cipher.doFinal(bytesEncryptedMessage);//3
                DecryptedMessage = new String(bytesDecryptedMessage);//4
            		
            		
                System.out.println("mensaje cifrado-> " + EncryptedMessage);
                System.out.println("mensaje descifrado-> " + DecryptedMessage);
              }else {
                System.out.println("---Tienes que encriptar una frase antes---");
              }
            }
            ```
            
    3. Opción 3 → Salir
        - Esta opción nos permitirá salir de la ejecución del menú y entrara al acceso de usuarios.
        
        ```java
        if(opcion.equals("3")) {
          System.out.println("******Saliendo del programa******");
          salir = true;
        }
        ```
        
3. En caso de tener algún error en la ejecución del programa nos sacara los errores gracias al catch.
    
    ```java
    catch (GeneralSecurityException e) {
        System.out.println("Error->  " + e.getMessage());
        e.printStackTrace();
    ```
