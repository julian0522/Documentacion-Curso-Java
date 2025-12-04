# Capa Presentacion (Controller)
¡Claro\! La clase **Controller** en Java con Spring Boot es fundamental para construir APIs RESTful, ya que actúa como el punto de entrada para manejar las peticiones HTTP.

Aquí tienes una documentación completa de sus capacidades:

-----

## 🚀 Conceptos Fundamentales del Controller

En Spring Boot, la clase que maneja las peticiones web se marca típicamente con una de estas anotaciones:

  * **`@Controller`**: Es la anotación base. Se usa para cualquier clase que funcione como controlador, incluyendo aquellas que devuelven vistas (como en aplicaciones MVC tradicionales).
  * **`@RestController`**: Es la anotación más común para APIs RESTful. Es una anotación de conveniencia que combina **`@Controller`** y **`@ResponseBody`**. El uso de `@ResponseBody` en el nivel de clase (implícito con `@RestController`) significa que el valor de retorno de los métodos del controlador debe enlazarse directamente al cuerpo de la respuesta HTTP, generalmente como JSON o XML.
  * **`@RequestMapping`**: Se usa a nivel de clase para definir la **ruta base** para todos los *endpoints* dentro de ese controlador.

**Ejemplo de Clase Controller:**

```java
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    // Los métodos para manejar peticiones irán aquí
}
```

-----

## 🌐 Manejo de Peticiones HTTP (HTTP Methods)

Dentro del `Controller`, los métodos individuales se mapean a combinaciones específicas de **ruta** y **método HTTP** (GET, POST, PUT, DELETE, etc.) usando anotaciones especializadas:

| Anotación | Método HTTP | Propósito Común |
| :--- | :--- | :--- |
| **`@GetMapping`** | **GET** | Obtener un recurso o una colección de recursos. |
| **`@PostMapping`** | **POST** | Crear un nuevo recurso. |
| **`@PutMapping`** | **PUT** | Actualizar un recurso existente (reemplazo completo). |
| **`@PatchMapping`** | **PATCH** | Actualizar parcialmente un recurso existente. |
| **`@DeleteMapping`** | **DELETE** | Eliminar un recurso. |

**Ejemplos de Mapeo de Métodos:**

```java
// GET /api/v1/usuarios
@GetMapping 
public List<Usuario> obtenerTodos() { ... }

// POST /api/v1/usuarios
@PostMapping
public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario nuevoUsuario) { ... }

// GET /api/v1/usuarios/{id}
@GetMapping("/{id}") 
public Usuario obtenerPorId(@PathVariable Long id) { ... }
```

-----

## 📥 Recepción de Parámetros en el Controller

Puedes recibir datos de la petición HTTP de tres maneras principales, utilizando anotaciones como parámetros en los métodos del controlador:

### 1\. Parámetros de Ruta (**Path Variables**)

La anotación `@PathVariable` (Variable de Ruta) es utilizada en Spring para extraer valores de las **variables de plantilla URI** (Uniform Resource Identifier). En términos más sencillos, te permite tomar una parte de la URL que actúa como un **identificador único** y convertirla en un parámetro de un método Java dentro de tu controlador.

El uso principal de `@PathVariable` es implementar el concepto de **identificación de recursos** en el diseño RESTful.

1.  **Identificación de Recursos:** Si quieres obtener o manipular un único recurso (por ejemplo, un usuario específico, un producto, una orden), utilizas su ID directamente en la ruta, en lugar de usar parámetros de consulta (`?id=123`).
      * **URL RESTful (con `@PathVariable`):** `/productos/5` (Mejor)
      * **URL No RESTful (con `@RequestParam`):** `/productos?id=5`
2.  **URLs Semánticas:** Hace que las URLs sean más intuitivas y fáciles de leer, indicando claramente que la operación se realiza sobre el recurso especificado por ese valor.

  * **Anotación:** **`@PathVariable`**
  * **Uso:** `GET /api/v1/usuarios/123`

  La interfaz de la anotación `@PathVariable` en Spring MVC (que Spring Boot utiliza) define las siguientes propiedades:

| Propiedad | Tipo | Propósito | Valor Predeterminado |
| :--- | :--- | :--- | :--- |
| **`value`** (o **`name`**) | `String` | Define el nombre de la variable de plantilla URI (dentro de las llaves `{}`) que se debe extraer. | Vacío (asume el nombre del parámetro del método). |
| **`required`** | `boolean` | Indica si la variable de ruta es obligatoria. | `true` |
| **`defaultValue`** | `String` | Proporciona un valor predeterminado si la variable de ruta no está presente. | Vacío (no aplica un valor predeterminado por defecto). |

<!-- end list -->

```java
@GetMapping("/productos/{id}") // 1. Plantilla: {id}
public Producto obtenerProductoPorId(@PathVariable Long id) { // 2. Inyección: id
    // Spring inyecta automáticamente el valor de {id} en el parámetro 'id'
    return productoService.buscarPorId(id);
}
```

### 2\. Parámetros de Consulta (**Query Parameters**)

La anotación `@RequestParam` (Parámetro de Petición) se usa en Spring Boot para extraer los valores de los **parámetros de consulta** (*Query Parameters*) de una petición HTTP.

Los parámetros de consulta son pares clave-valor que se añaden al final de la URL, después del signo de interrogación (`?`), y se separan entre sí por el símbolo de ampersand (`&`).

**Estructura de la URL con Query Parameters:**

```
[URL_base]?[clave1]=[valor1]&[clave2]=[valor2]
```

**Ejemplo Práctico:**

`GET /api/productos?categoria=electronica&stockMinimo=10`

  * **Anotación:** **`@RequestParam`**
  * **Uso:** `GET /api/v1/usuarios?rol=admin&page=1`

  A diferencia de `@PathVariable`, que se usa para **identificar** un recurso único, `@RequestParam` se usa para **modificar** o **filtrar** la lista de recursos devueltos.

1.  **Filtrado y Búsqueda:** Permite al cliente refinar los resultados de una colección (ej: buscar productos por color, precio, o categoría).
2.  **Paginación:** Es el mecanismo estándar para manejar `page` (página) y `size` (tamaño) en las APIs.
3.  **Datos Opcionales/Adicionales:** Se usa para pasar datos que no son esenciales para identificar el recurso, como un orden de clasificación (`sortBy`).

-----

| Escenario | Propiedad de `@RequestParam` | Resultado si falta |
| :--- | :--- | :--- |
| **Obligatorio** | `required = true` (por defecto) | Error **400 Bad Request** |
| **Opcional (con `null`)** | `required = false` | El parámetro Java será **`null`** |
| **Opcional (con valor fijo)** | `defaultValue = "valor"` | El parámetro Java será el **valor predeterminado** |

-----

#### 📌 Diferencia Clave: `@RequestParam` vs. `@PathVariable`

| Característica | `@RequestParam` (Query Parameter) | `@PathVariable` (Path Variable) |
| :--- | :--- | :--- |
| **Ubicación en URL** | Después de `?` y usa `&` para separar. | En el segmento de la ruta, dentro de la estructura de la URL. |
| **Sintaxis en URL** | `/recursos?id=5` | `/recursos/5` |
| **Uso Principal** | Filtrar, buscar, paginar, ordenar, datos opcionales. | Identificar un recurso único, construir URLs semánticas. |
| **Obligatoriedad** | Puede ser **opcional** (`required=false`). | Siempre **obligatorio** (si falta, es 404 Not Found). |

<!-- end list -->

```java
@GetMapping
public List<Usuario> buscarUsuarios(
    @RequestParam(required = false) String rol, // El parámetro no es obligatorio
    @RequestParam(defaultValue = "0") int page) { // Si no se envía, usa 0
    
    return usuarioService.findFiltered(rol, page);
}
```

### 3\. Cuerpo de la Petición (**Request Body**)

La anotación `@RequestBody` se usa para indicarle a Spring Boot que el parámetro de un método del controlador debe ser **enlazado (bound)** al **cuerpo (body)** de la petición HTTP entrante.

En el contexto de APIs RESTful, el cuerpo de la petición generalmente contiene datos en formato **JSON** o, a veces, XML.

El propósito fundamental de `@RequestBody` es permitir que el cliente envíe un objeto de datos complejo para que sea procesado por la aplicación.

1.  **Creación de Recursos (POST):** Se utiliza para enviar los datos de un nuevo recurso (ej: un nuevo usuario) que debe ser persistido.
2.  **Actualización de Recursos (PUT/PATCH):** Se usa para enviar los datos que deben reemplazar o modificar un recurso existente.
3.  **Manejo de Estructuras Complejas:** Es la forma en que Spring recibe y deserializa estructuras de datos jerárquicas (objetos, listas de objetos, etc.) que no caben en parámetros de ruta o de consulta.

  * **Anotación:** **`@RequestBody`**
  * **Uso:** Envía un JSON completo en el cuerpo. Spring Boot lo convierte automáticamente a un objeto Java (`Usuario` en este caso).

  Por defecto, la presencia del cuerpo de la petición es **obligatoria**. Si un cliente realiza una petición POST/PUT a un *endpoint* con `@RequestBody` pero envía un cuerpo vacío o sin datos, Spring intentará deserializarlo.

  * Si el cuerpo está **completamente vacío**, Spring devolverá un error **400 Bad Request** (`HttpMessageNotReadableException`).
  * Si el cuerpo está presente pero mal formado (ej: JSON mal estructurado), también causará un error **400 Bad Request**.

<!-- end list -->

```java
public class NuevoUsuarioRequest {
    private String nombre;
    private String email;
    // Getters y Setters...
}

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(
        @RequestBody NuevoUsuarioRequest request) { // <--- Aquí se usa @RequestBody
        
        // El objeto 'request' ya tiene los datos deserializados del JSON.
        Usuario nuevo = usuarioService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
}
```

### 4\. Cabeceras HTTP (**Headers**)

La anotación `@RequestHeader` se utiliza en Spring Boot para extraer el valor de una **cabecera (Header)** HTTP específica de la petición entrante y enlazarlo como parámetro de un método de controlador.

Las cabeceras HTTP son pares clave-valor que contienen metadatos sobre la petición misma, el cliente o la información de seguridad.

**Ejemplo de Cabeceras en una Petición:**

```
GET /api/recursos HTTP/1.1
Host: api.ejemplo.com
Authorization: Bearer <TOKEN_JWT_AQUI>
Accept-Language: es
Content-Type: application/json
```
El uso de `@RequestHeader` está enfocado en acceder a información que no forma parte del recurso ni de los parámetros de consulta, sino que es contextual a la comunicación.

1.  **Autenticación y Autorización:** Es el uso más común. Permite acceder a la cabecera `Authorization` para obtener tokens de seguridad (JWT, Bearer, etc.).
2.  **Negociación de Contenido:** Acceder a cabeceras como `Accept` (qué tipo de respuesta espera el cliente) o `Content-Type` (qué formato tiene el cuerpo de la petición).
3.  **Localización:** Obtener la cabecera `Accept-Language` para saber qué idioma prefiere el cliente.
4.  **Información de Sesión:** Acceder a cabeceras de `Cookie` o IDs de seguimiento.


  * **Anotación:** **`@RequestHeader`**
  * **Uso:** Para leer una cabecera específica.

<!-- end list -->

```java
@GetMapping("/perfil")
public Usuario verPerfil(
    @RequestHeader(name = "X-Tenant-Id", required = false) Integer tenantId) { 
    
    // Si la cabecera 'X-Tenant-Id' no se envía, tenantId será null.
    if (tenantId != null) {
        return usuarioService.findByTenant(tenantId);
    }
    return usuarioService.findDefault();
}
```

#### 📚 Otros Usos Avanzados de Cabeceras

Además de extraer una sola cabecera, puedes acceder a todas ellas de varias maneras:

##### A. Obtener Todas las Cabeceras en un `Map`

Puedes obtener todas las cabeceras como un `Map<String, String>` o `Map<String, List<String>>` si usas la anotación sin especificar una clave:

```java
@GetMapping("/metadata")
public ResponseEntity<Map<String, String>> obtenerMetadatos(
    @RequestHeader Map<String, String> headers) {
    
    // El mapa 'headers' contendrá todas las cabeceras de la petición.
    return ResponseEntity.ok(headers);
}
```

##### B. Usar `HttpHeaders`

Puedes inyectar la clase `HttpHeaders` de Spring directamente, la cual proporciona un acceso más orientado a objetos y utilidades para trabajar con las cabeceras.

```java
@GetMapping("/info")
public String obtenerInfo(HttpHeaders headers) {
    // Acceso directo a métodos útiles de HttpHeaders
    List<String> userAgents = headers.get("User-Agent");
    return "User Agent: " + (userAgents != null ? userAgents.get(0) : "N/A");
}
```

-----

## 📤 Envío de Datos y Control de la Respuesta

El valor de retorno de un método en un `@RestController` se serializa automáticamente al cuerpo de la respuesta HTTP. Para un control más fino, se usa la clase **`ResponseEntity`**.

  * **Retorno Simple:** Devuelve un objeto Java (Spring lo convierte a JSON/XML).

<!-- end list -->

```java
@GetMapping("/{id}")
public Usuario obtener() {
    return new Usuario("Alice"); // Devuelve 200 OK con el objeto Usuario en el body
}
```

  * **Retorno con `ResponseEntity`:** Permite controlar el **código de estado HTTP** y las **cabeceras**.

<!-- end list -->

```java
@PostMapping
public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario u) {
    Usuario guardado = usuarioService.save(u);
    // Devuelve un código 201 Created y una cabecera "Location"
    return ResponseEntity
            .created(URI.create("/api/v1/usuarios/" + guardado.getId())) 
            .body(guardado);
}
```

-----

## 🛠️ Otras Capacidades Esenciales del Controller

Además del manejo básico de peticiones, la clase `Controller` es crucial para:

-----

## 🛑 1. Manejo de Excepciones Avanzado

El manejo de excepciones en un *Controller* asegura que tu API responda con códigos de estado HTTP y cuerpos de respuesta significativos en lugar de exponer rastros de pila (stack traces) internos.

### 1.1. Manejo Específico con `@ExceptionHandler`

Como viste, `@ExceptionHandler` a nivel de método captura una excepción *dentro de la misma clase* del controlador.

  * **Propósito:** Es útil para manejar errores específicos de ese recurso o para prototipos rápidos.
  * **Combinación con `@ResponseStatus`:** La anotación `@ResponseStatus` (colocada en el método o directamente en la clase de la excepción personalizada) define el **código de estado HTTP** que debe devolver el servidor. Por ejemplo, `HttpStatus.NOT_FOUND` resulta en un código **404**.

### 1.2. Manejo Global con `@ControllerAdvice`

La mejor práctica es centralizar el manejo de errores. Esto se logra con la anotación **`@ControllerAdvice`** (o su versión REST: **`@RestControllerAdvice`**).

  * **Funcionamiento:** Una clase marcada con `@ControllerAdvice` actúa como un interceptor para manejar excepciones lanzadas desde **cualquier *Controller*** de la aplicación.
  * **Sintaxis:** Dentro de la clase `@ControllerAdvice`, se usan métodos anotados con `@ExceptionHandler` para capturar excepciones globalmente.

<!-- end list -->

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Código 404
    public ErrorResponse handleNotFound(RecursoNoEncontradoException ex) {
        // Formato de error estandarizado para toda la API
        return new ErrorResponse(ex.getMessage(), LocalDateTime.now());
    }
    
    // Captura errores de validación de Spring automáticamente
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Código 400
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // ... Lógica para extraer mensajes de error de validación
    }
}
```

-----

## ✅ 2. Validación de Datos (Bean Validation)

Spring Boot utiliza la especificación **Jakarta Bean Validation** (JSR 380) para validar objetos.

### 2.1. El Rol de `@Valid` y `@Validated`

Ambas anotaciones se usan para **activar** la validación en un objeto:

  * **`@Valid` (Jakarta Validation):** Es el estándar. Ejecuta la validación de las restricciones (ej: `@NotNull`, `@Size`) definidas en el objeto DTO.
  * **`@Validated` (Spring):** Es una extensión de Spring. Se usa para aplicar la validación a nivel de clase o para usar **grupos de validación** (validar solo un subconjunto de reglas para una operación específica, como solo validar el nombre en una actualización parcial).

### 2.2. Definición de Restricciones en el Modelo (DTO)

Las restricciones se colocan directamente en los campos del objeto de petición (DTO) que recibe `@RequestBody`.

```java
public class UsuarioDTO {

    // El nombre no puede ser nulo ni vacío
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    // El email debe tener formato de correo
    @Email(message = "Formato de email incorrecto")
    @NotBlank(message = "El email es obligatorio")
    private String email; 

    // La edad debe ser al menos 18
    @Min(value = 18, message = "Debe ser mayor de edad")
    private int edad; 

    // ... Getters y Setters
}

// Uso en el Controller
@PostMapping
public ResponseEntity<Void> crearUsuario(@Valid @RequestBody UsuarioDTO dto) {
    // Si falla, se lanza MethodArgumentNotValidException (manejar con @ControllerAdvice)
    // ...
}
```

-----

## ⚙️ 3. Inyección de Dependencias (DI)

La inyección de dependencias es la forma en que el `Controller` obtiene acceso a la lógica de negocio (Servicios) y a la capa de datos (Repositorios) sin tener que instanciarlos manualmente.

### 3.1. Inyección por Constructor (Método Preferido)

Es el estándar recomendado por Spring. Si una clase solo tiene un constructor, Spring asume implícitamente la inyección y **omite** la necesidad de la anotación `@Autowired`.

```java
private final UsuarioService usuarioService;
private final LogService logService; // Puede inyectar múltiples beans

// 💡 Spring inyecta automáticamente los beans aquí
public UsuarioController(UsuarioService usuarioService, LogService logService) {
    this.usuarioService = usuarioService;
    this.logService = logService;
}
```

  * **Ventajas:**
      * Los campos son **`final`** (inmutables), lo que garantiza que la dependencia no cambie después de la inicialización.
      * Hace que las dependencias sean **explícitas** y facilita las pruebas unitarias.

### 3.2. Inyección de Campos (Método Menos Recomendado)

Usar `@Autowired` directamente sobre el campo, aunque es más conciso, no permite que el campo sea `final`.

```java
// Menos recomendado
@Autowired
private UsuarioService usuarioService; 
```

-----

## 🍪 4. Configuración de Sesión y Cookies

Aunque las APIs RESTful son generalmente *stateless* (sin estado), a veces necesitas interactuar con mecanismos de estado para casos de uso específicos como carritos de compra o personalización.

### 4.1. `@SessionAttribute`

Esta anotación te permite leer y escribir atributos que están ligados a la sesión HTTP del usuario.

  * **Uso:** Útil en APIs que aún mantienen alguna capa de estado, o cuando se integra con una aplicación web tradicional.

<!-- end list -->

```java
@GetMapping("/contador")
public Integer incrementarContador(@SessionAttribute(name = "conteo", required = false) Integer conteo) {
    if (conteo == null) {
        conteo = 0;
    }
    // 💡 Para guardar el valor, se usa el objeto `Model` o la clase `SessionStatus` 
    // junto con el `request.getSession()`, ya que @SessionAttribute es solo para leer y enlazar.
    // En REST puro, este uso es raro y se prefiere JWTs o cookies.
    return conteo + 1;
}
```

### 4.2. `@CookieValue`

Esta anotación extrae un valor de una *cookie* HTTP enviada por el cliente.

  * **Uso:** Común para leer tokens de sesión almacenados en *cookies* o IDs de seguimiento.

<!-- end list -->

```java
@GetMapping("/bienvenida")
public String saludar(@CookieValue(name = "user_pref", defaultValue = "default") String preferenciaUsuario) {
    // preferenceUser contendrá el valor de la cookie llamada "user_pref"
    if (preferenciaUsuario.equals("dark")) {
        return "Bienvenido al tema oscuro";
    }
    return "Bienvenido al tema " + preferenciaUsuario;
}
```

## Recibir parametros como formulario

¡Claro\! En Spring Boot, la forma más común y directa de recibir parámetros enviados desde un formulario HTML (`Content-Type: application/x-www-form-urlencoded` o `multipart/form-data`) es utilizando la anotación **`@ModelAttribute`**.

Esta anotación funciona de manera similar al concepto de `[FromForm]` en .NET Core, permitiendo que Spring mapee automáticamente los campos del formulario HTTP a un objeto Java.

Aquí te explico cómo funciona y cuáles son los dos escenarios principales.

-----

## 📝 1. Uso de `@ModelAttribute` para Datos de Formulario

El `@ModelAttribute` (o, a menudo, ninguna anotación en absoluto, ya que Spring lo infiere) le indica a Spring que debe buscar los datos en los **parámetros de la petición** (ya sea en la URL para GET, o en el cuerpo codificado para POST) y poblar un objeto *Java Bean* (DTO).

### Mecanismo:

1.  **Envío del Cliente:** El formulario HTML envía datos con nombres específicos (ej: `name="usuario"` y `name="password"`).
2.  **Mapeo:** Spring busca un constructor sin argumentos en la clase **DTO** y luego utiliza los *setters* de esa clase para asignar los valores recibidos de la petición.

### Ejemplo de Implementación

Supongamos que tienes un formulario de registro:

#### 1\. Clase DTO (Data Transfer Object)

Esta clase contiene los campos exactos que esperas recibir del formulario.

```java
public class RegistroForm {
    private String nombreUsuario;
    private String email;
    private String password;
    
    // 💡 IMPORTANTE: Debes tener getters y setters para que Spring pueda mapear los datos.
    // También es necesario un constructor vacío (por defecto si no defines otro).
    // ... Getters y Setters
}
```

#### 2\. Método del Controller

En tu método de manejo de peticiones (`@PostMapping`):

```java
@PostMapping("/registrar")
public String registrarUsuario(@ModelAttribute RegistroForm form) {
    // Los campos de 'form' están automáticamente llenos con los datos del formulario.
    System.out.println("Usuario: " + form.getNombreUsuario());
    
    usuarioService.crear(form);
    
    return "redirect:/registro-exitoso";
}
```

> **Nota:** Cuando usas `@ModelAttribute`, Spring asume automáticamente que estás trabajando con datos de tipo `application/x-www-form-urlencoded` o `multipart/form-data`.

-----

## 📂 2. Manejo de Subida de Archivos (`multipart/form-data`)

Si tu formulario incluye la subida de archivos (el equivalente al `FromForm` con `IFormFile` en .NET), necesitas que el formulario use la codificación `multipart/form-data`. Spring maneja esto usando la clase **`MultipartFile`**.

### Implementación para Archivos

Para recibir un archivo, cambias el tipo de dato del parámetro:

```java
@PostMapping("/subir-foto")
public String subirArchivo(
    @RequestParam("nombre") String nombre, // Otros campos de texto
    @RequestParam("archivo") MultipartFile archivo // El campo del archivo
) {
    if (!archivo.isEmpty()) {
        try {
            byte[] bytes = archivo.getBytes();
            // Lógica para guardar el archivo en disco o en la base de datos
            System.out.println("Archivo recibido: " + archivo.getOriginalFilename());
        } catch (IOException e) {
            // Manejo de error
        }
    }
    return "Archivo subido con éxito";
}
```

  * **`@RequestParam` vs. `@ModelAttribute`:** Para archivos individuales (`MultipartFile`), se suele usar **`@RequestParam`** para acceder directamente al campo del archivo.

### Combinando Datos y Archivos

Si quieres recibir el DTO del formulario y el archivo en la misma petición:

```java
public class PerfilForm {
    private String nombre;
    private String biografia;
    // Getters y Setters...
}

@PostMapping(value = "/perfil", consumes = {"multipart/form-data"})
public String actualizarPerfil(
    @ModelAttribute PerfilForm datos, // Datos de texto/números
    @RequestParam("foto") MultipartFile foto // El archivo
) {
    // ... procesar 'datos' y 'foto'
    return "Perfil actualizado";
}
```

-----

## 💡 Comparación con `@RequestBody`

Es importante distinguir `@ModelAttribute` de `@RequestBody`:

| Característica | `@ModelAttribute` (Formulario) | `@RequestBody` (REST JSON) |
| :--- | :--- | :--- |
| **Fuente de Datos** | Parámetros de la URL o cuerpo codificado (`key1=val1&key2=val2`). | Cuerpo crudo (raw body), generalmente JSON o XML. |
| **`Content-Type`** | `application/x-www-form-urlencoded` o `multipart/form-data`. | `application/json` o `application/xml`. |
| **Mapeo** | Utiliza *setters* del DTO para asignar valores por nombre de campo. | Utiliza **Message Converters** (ej: Jackson) para deserializar la estructura JSON/XML. |
| **Uso Principal** | Formularios web tradicionales, subida de archivos, o `GET` con muchos parámetros. | APIs RESTful para crear o actualizar recursos. |

Para resumir, si tu cliente envía datos como un formulario, usa **`@ModelAttribute`** para los datos de texto y **`@RequestParam MultipartFile`** para los archivos.