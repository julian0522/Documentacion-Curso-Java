
# 🧩 1. ¿Cómo funciona la Web?

## ✔️ Arquitectura Cliente-Servidor

La Web funciona bajo un modelo **Cliente ←→ Servidor**.

* **Cliente** → normalmente un navegador, una app móvil o una aplicación que hace peticiones.
* **Servidor** → donde vive tu aplicación (por ejemplo, la app creada con Spring Boot).

Ese cliente se comunica con un servidor, mediante un protocolo que se llama `HTTP` o protocolo de tranferencia de hipertexto

**Proceso general:**

1. El cliente envía una petición (request).
2. El servidor recibe la petición.
3. El servidor procesa: lógica, base de datos, reglas de negocio.
4. El servidor envía una respuesta (response).
5. El cliente la interpreta (HTML, JSON, archivos, etc.)

---

# 🌐 2. ¿Qué es HTTP?

**HTTP (HyperText Transfer Protocol)** es el protocolo que define cómo se comunican los clientes y los servidores en la web.

Las peticiones HTTP son mensajes que un cliente (como un navegador) envía a un servidor web para solicitar un recurso o realizar una acción. Estas peticiones siguen el protocolo HTTP y constan de un método (como GET o POST), una URL, encabezados y opcionalmente un cuerpo de mensaje. El servidor responde con un estado que indica el resultado de la solicitud.  

Es un protocolo **sin estado (stateless)** → cada petición es independiente.

Una petición HTTP tiene:

```
GET /ruta HTTP/1.1
Host: www.google.com
User-Agent: Chrome
Content-Type: application/json

{ "ejemplo": "dato" }
```

## ✔️ Componentes de una petición HTTP:

1. **Método HTTP:** Indica la operación que el cliente quiere realizar (ej. GET para obtener datos, POST para enviar datos).
2. **URL:** La dirección del recurso que se está solicitando. 
3. **Encabezados HTTP:** Metadatos opcionales que proporcionan información adicional sobre la petición o el cliente.
4. **Cuerpo de mensaje:** Contiene los datos que se envían al servidor, utilizado comúnmente en métodos como POST. 

---

# 🔒 3. HTTP vs HTTPS

**La diferencia principal es la seguridad:** HTTPS (Hypertext Transfer Protocol Secure) cifra los datos para protegerlos durante la transmisión, mientras que HTTP (Hypertext Transfer Protocol) los transmite en texto plano, haciéndolos vulnerables a la interceptación. HTTPS utiliza SSL/TLS para encriptar la comunicación, verificar la autenticidad del sitio web y mantener la privacidad de los datos sensibles, como información de pago o personal. 

| HTTP                          | HTTPS                               |
| ----------------------------- | ----------------------------------- |
| *No* cifrado                  | Cifrado (TLS/SSL)                   |
| Vulnerable                    | Seguro                              |
| Puerto 80                     | Puerto 443                          |
| No sirve para datos sensibles | Obligatorio para login, pagos, APIs |

HTTPS garantiza:

* Confidencialidad
* Integridad
* Autenticidad

Hoy en día, **casi todo debe ser HTTPS**.

---

# 🧭 4. ¿Qué es una URL y cuál es su estructura?

Una URL `(sigla en inglés de Uniform Resource Locator o Localizador Uniforme de Recursos)` es una referencia a un recurso web que especifica su ubicación en una red informática y un mecanismo para recuperarlo. Es esencialmente la dirección que escribes en tu navegador para acceder a una página web, una imagen, un video o cualquier otro recurso en internet.

### Ejemplo:

```
https://mi-sitio.com:8080/productos/listar?categoria=ropa&page=2#comentarios
```

## 🏗️ Partes de una URL

Una URL típica se compone de varias partes, cada una con un propósito específico. Aunque hay variaciones y algunas partes son opcionales, la estructura general es la siguiente:

**`esquema://subdominio.dominio.tld:puerto/ruta/recurso?parámetros#fragmento`**

Aquí tienes el desglose de sus componentes principales:

### 1. Esquema (Protocolo)

* **Ejemplo:** `https://`
* Indica el **protocolo** que debe usar el navegador para acceder al recurso.
* Los más comunes son **`http`** (Hypertext Transfer Protocol) y **`https`** (Secure HTTP), que cifra la comunicación para hacerla más segura. Otros ejemplos son `ftp` (File Transfer Protocol) o `mailto`.

### 2. Autoridad (Dominio y Puerto)

#### a. Subdominio
* **Ejemplo:** `www.` (a menudo se omite hoy en día) o `blog.`
* Una división del dominio principal, utilizada para organizar y segmentar el contenido (por ejemplo, tener una sección de blog separada del sitio principal).

#### b. Dominio
* **Ejemplo:** `google`
* Es el **nombre único y legible** del sitio web o servidor.

#### c. TLD (Dominio de Nivel Superior)
* **Ejemplo:** `.com`, `.org`, `.edu`, `.mx`
* La parte final del dominio que clasifica el tipo de entidad (comercial, organización, educativo) o su ubicación geográfica (código de país).

#### d. Puerto (Opcional)
* **Ejemplo:** `:8080`
* Especifica el **número de puerto** a través del cual el protocolo debe conectarse al servidor. Por defecto, HTTP usa el puerto **80** e HTTPS usa el puerto **443**, por lo que generalmente se omite en la URL.

### 3. Ruta (Path)

* **Ejemplo:** `/ayuda/productos/`
* Especifica la **ubicación exacta** del recurso dentro del servidor web, de forma similar a la estructura de carpetas de un disco duro.

### 4. Recurso/Archivo (Opcional)

* **Ejemplo:** `terminos.html`
* El **nombre del archivo** específico al que se desea acceder. Si se omite, el servidor generalmente entrega un archivo predeterminado (como `index.html`).

### 5. Parámetros de Consulta (Query Parameters) (Opcional)

* **Ejemplo:** `?id=123&orden=asc`
* Comienzan con un **signo de interrogación (`?`)** y son pares de **`clave=valor`** separados por el símbolo **`&`**. Se utilizan para pasar datos dinámicos al servidor, como criterios de búsqueda o identificadores de productos.

### 6. Fragmento (Anchor) (Opcional)

* **Ejemplo:** `#seccion-contacto`
* Comienza con un **signo de almohadilla (`#`)** y se utiliza para **apuntar a una sección específica** (un "anclaje") dentro del recurso. El navegador usa esta información, pero **no** se envía al servidor.

---

### Partes:

| Parte        | Ejemplo                  | Significado                   |
| ------------ | ------------------------ | ----------------------------- |
| Protocolo    | `https://`               | Cómo conectarse               |
| Dominio      | `mi-sitio.com`           | Dirección del servidor        |
| Puerto       | `:8080`                  | Opcional (HTTP=80, HTTPS=443) |
| Ruta         | `/productos/listar`      | Recurso solicitado            |
| Query Params | `?categoria=ropa&page=2` | Parámetros                    |
| Fragmento    | `#comentarios`           | Marcador interno              |

---

# 🔥 5. ¿Qué es una API?

Una **API** (sigla en inglés de **Application Programming Interface** o **Interfaz de Programación de Aplicaciones**) es un **conjunto de reglas y protocolos** que permite que diferentes aplicaciones de software se comuniquen entre sí, intercambiando datos o solicitando funcionalidades.

En términos sencillos, la API actúa como un **mensajero** o un **contrato de servicio** entre dos sistemas:

* **El Cliente (Aplicación Solicitante):** La aplicación o software que necesita una funcionalidad o datos (por ejemplo, una aplicación móvil que necesita saber el clima).
* **El Servidor (Aplicación Proveedora):** La aplicación o sistema que posee la información o la funcionalidad (por ejemplo, el servidor de una empresa meteorológica).
* **La API:** La interfaz que recibe la solicitud del cliente, la comunica al servidor, espera la respuesta y se la devuelve al cliente.


---

## 🛠️ Usos principales de una API

Las APIs son fundamentales en el desarrollo de software moderno y facilitan una serie de funciones clave:

* **Integración de Aplicaciones:** Permiten conectar nuevas aplicaciones con sistemas de software ya existentes. Esto acelera el desarrollo, ya que el desarrollador no necesita construir la funcionalidad desde cero, sino que aprovecha el código existente a través de la API.
    * *Ejemplo:* Un sitio web de comercio electrónico utiliza una API de PayPal para procesar pagos sin tener que desarrollar su propio sistema de seguridad y manejo de tarjetas.
* **Distribución de Contenido:** Facilitan que las empresas distribuyan su información y servicios a través de diferentes plataformas (web, iOS, Android).
    * *Ejemplo:* La API de Google Maps permite que cualquier sitio web o aplicación muestre mapas, datos de tráfico y direcciones.
* **Innovación Abierta:** Las APIs públicas o de socios permiten que desarrolladores externos creen nuevas aplicaciones y servicios que se integran con el sistema de la empresa.
    * *Ejemplo:* Una red social abre su API para que otras aplicaciones puedan publicar contenido automáticamente o acceder a datos de perfil (con permiso del usuario).
* **Automatización:** Se utilizan para automatizar tareas repetitivas al permitir que un sistema se comunique directamente con otro para realizar una acción.
    * *Ejemplo:* Un sistema de gestión de inventario utiliza una API de una empresa de envío para solicitar la recogida de un paquete automáticamente después de una venta.

---

## 🌐 Tipos comunes de APIs Web

En el contexto de internet, la mayoría de las APIs son **APIs Web** que utilizan el protocolo **HTTP** para la comunicación (solicitudes y respuestas):

| Tipo de API | Descripción | Uso Común |
| :--- | :--- | :--- |
| **API REST** | El tipo más común hoy en día. Utiliza verbos HTTP estándar (`GET`, `POST`, `PUT`, `DELETE`) para manipular "recursos" (datos) y suele devolver los datos en formato **JSON** o **XML**. | Sitios web, aplicaciones móviles y cualquier servicio moderno que necesite compartir datos. |
| **API SOAP** | Un protocolo más antiguo y estricto. Utiliza el lenguaje XML para el intercambio de datos y se basa en un conjunto de reglas muy definidas. | Aplicaciones empresariales más antiguas o entornos que requieren un alto nivel de seguridad y transaccionalidad. |
| **APIs Privadas** | Diseñadas para ser utilizadas **internamente** dentro de una misma empresa, para conectar diferentes departamentos o sistemas. | Conectar la aplicación de inventario con el sistema de contabilidad de la misma empresa. |
| **APIs Públicas/Abiertas** | Puestas a disposición del público en general (a menudo de forma gratuita) para que cualquier desarrollador pueda integrarlas. | APIs de clima, APIs de redes sociales, APIs de bancos de imágenes. |

Ejemplo:

```
GET /api/usuarios      → obtiene usuarios
POST /api/usuarios     → crea un usuario
PUT /api/usuarios/5    → actualiza el usuario 5
DELETE /api/usuarios/5 → borra el usuario 5
```

Las APIs generalmente responden en formato **JSON**.

Spring Boot facilita muchísimo la creación de APIs REST.

---

# ⛑️ 6. Métodos HTTP (los más importantes)

Los métodos HTTP (también conocidos como verbos HTTP) son una parte fundamental del protocolo HTTP. Indican la acción deseada a realizar sobre el recurso identificado por la URL.

Son esenciales para las APIs REST, donde cada método se mapea a una operación CRUD (Crear, Leer, Actualizar, Borrar) para gestionar los datos.

## 🌐 Métodos HTTP Principales

Aquí están los métodos HTTP más comunes y utilizados, con su propósito principal:

| Método | Propósito Principal | Equivalente CRUD | Descripción |
| :--- | :--- | :--- | :--- |
| **`GET`** | **Obtener/Recuperar** datos. | **R**ead (Leer) | Solicita una representación del recurso especificado. Las solicitudes `GET` solo deben recuperar datos y no deben tener efectos secundarios en el servidor. |
| **`POST`** | **Crear** un nuevo recurso. | **C**reate (Crear) | Envía datos a un recurso para que sean procesados, a menudo resultando en la creación de un nuevo recurso. Se utiliza para enviar formularios. |
| **`PUT`** | **Actualizar/Reemplazar** completamente un recurso. | **U**pdate (Actualizar) | Reemplaza todas las representaciones actuales del recurso de destino con la carga útil (payload) de la solicitud. Si el recurso no existe, puede crearlo. |
| **`DELETE`** | **Eliminar** un recurso. | **D**elete (Borrar) | Elimina el recurso especificado en la URL. |

---

## ⚙️ Métodos HTTP Secundarios (Auxiliares)

Además de los cuatro verbos principales, existen otros métodos que cumplen funciones específicas:

* **`PATCH`**:
    * **Propósito:** **Aplicar modificaciones parciales** a un recurso.
    * **Diferencia con `PUT`:** A diferencia de `PUT`, que reemplaza el recurso completo, `PATCH` solo envía los datos que se desean cambiar, manteniendo el resto del recurso intacto.
* **`HEAD`**:
    * **Propósito:** Solicitar la **información de la cabecera** del recurso.
    * **Uso:** Es idéntico a `GET`, pero el servidor no devuelve el cuerpo de la respuesta, solo las cabeceras (metadata). Es útil para verificar la existencia de un recurso o para obtener metadatos (como la fecha de la última modificación) sin descargar el contenido.
* **`OPTIONS`**:
    * **Propósito:** Describir las **opciones de comunicación** disponibles para el recurso.
    * **Uso:** Se utiliza para determinar qué métodos HTTP (ej. `GET`, `POST`, `DELETE`) están permitidos en un determinado recurso. Esto es crucial en la seguridad de las APIs web (**CORS** - Cross-Origin Resource Sharing).
* **`CONNECT`**:
    * **Propósito:** Establecer un **túnel** al servidor identificado por el recurso.
    * **Uso:** Se usa principalmente para la comunicación cifrada **HTTPS** a través de servidores proxy.
* **`TRACE`**:
    * **Propósito:** Realizar una prueba de **bucle de diagnóstico** ("loop-back") del mensaje a lo largo de la ruta hacia el servidor.
    * **Uso:** Se utiliza para fines de depuración y diagnóstico para ver exactamente lo que el servidor final recibió de la solicitud.

---

# 🎨 7. Códigos de respuesta HTTP

| Categoría | Rango                | Significado |
| --------- | -------------------- | ----------- |
| 1xx       | Informativos         | Poco usados |
| 2xx       | Éxito                | ✔️          |
| 3xx       | Redirecciones        | ↪️          |
| 4xx       | Errores del cliente  | ❌           |
| 5xx       | Errores del servidor | 💥          |

### ✅ 2xx: Éxito (Todo está bien)

Estos códigos confirman que la petición se completó con éxito.

| Código | Nombre | Descripción | Uso Común |
| :---: | :--- | :--- | :--- |
| **200** | **OK** | La petición fue exitosa y la respuesta contiene el recurso solicitado. | **Peticiones `GET` exitosas.** Es la respuesta estándar para cargar una página o dato. |
| **201** | **Created** | La petición ha tenido éxito y, como resultado, se ha creado un nuevo recurso. | **Peticiones `POST` exitosas** (cuando se crea una nueva entidad, ej: un usuario, un artículo). |
| **204** | **No Content** | La petición ha tenido éxito, pero no hay contenido que devolver en el cuerpo de la respuesta. | **Peticiones `PUT`, `DELETE`, o `POST` exitosas** donde no es necesario devolver un cuerpo de respuesta (ej: borrar un archivo, actualizar un campo sin necesidad de confirmación). |

### ➡️ 3xx: Redirección (El recurso se ha movido)

Estos códigos indican que el cliente necesita realizar una acción adicional (típicamente ir a otra URL) para completar la petición. Son clave para la optimización SEO.

| Código | Nombre | Descripción | Uso Común |
| :---: | :--- | :--- | :--- |
| **301** | **Moved Permanently** | El recurso solicitado ha sido asignado a una nueva URL **permanentemente**. | Para redirecciones permanentes a nivel de servidor (muy importante para SEO, ya que transfiere la autoridad del enlace). |
| **302** | **Found** | El recurso solicitado se encuentra **temporalmente** bajo una URL diferente. | Para redirecciones temporales (ej: durante un mantenimiento corto). |
| **304** | **Not Modified** | Indica al navegador que el recurso no ha cambiado desde la última vez que lo solicitó. | Usado en caché. Ahorra ancho de banda, indicando que el cliente puede usar la versión guardada. |

### ⚠️ 4xx: Error del Cliente (El cliente se equivocó)

Son los errores más comunes que indican que la petición del cliente no fue correcta, ya sea por una URL errónea o por falta de autenticación.

| Código | Nombre | Descripción | Uso Común |
| :---: | :--- | :--- | :--- |
| **400** | **Bad Request** | El servidor no pudo procesar la petición debido a un error de sintaxis del cliente. | **Errores de validación de formulario o API** (ej: faltan campos obligatorios, el formato de un dato es incorrecto). |
| **401** | **Unauthorized** | La petición requiere autenticación. | Cuando se intenta acceder a un recurso **protegido** sin proporcionar credenciales (token, contraseña). |
| **403** | **Forbidden** | El servidor se niega a autorizar la petición, aunque la sintaxis sea válida. | El cliente **tiene credenciales**, pero no el **permiso** para acceder a ese recurso específico (ej: un usuario normal intenta acceder a la página de administrador). |
| **404** | **Not Found** | El servidor no ha encontrado nada que coincida con la URL solicitada. | **El error más común.** Ocurre cuando se pide una página, imagen o archivo que no existe en la ruta especificada. |
| **405** | **Method Not Allowed** | El método especificado en la petición (ej: `POST`) no está permitido para el recurso. | Se intenta hacer un `DELETE` a una URL que solo acepta `GET`. |
| **429** | **Too Many Requests** | El usuario ha enviado demasiadas peticiones en un período de tiempo dado. | Aplicación de límites de tasa (*rate limiting*) para prevenir abuso o sobrecarga. |

### ❌ 5xx: Error del Servidor (El servidor falló)

Estos códigos indican que la petición era válida, pero el servidor no pudo completarla debido a un problema interno.

| Código | Nombre | Descripción | Uso Común |
| :---: | :--- | :--- | :--- |
| **500** | **Internal Server Error** | El servidor encontró una condición inesperada que le impidió completar la petición. | **Error genérico** cuando el código del servidor lanza una excepción o falla inesperadamente. |
| **502** | **Bad Gateway** | El servidor, actuando como *gateway* o *proxy*, recibió una respuesta no válida de un servidor de origen. | Común en configuraciones con *proxies* inversos (ej: NGINX o Apache) cuando el servidor de aplicaciones subyacente falla. |
| **503** | **Service Unavailable** | El servidor no está disponible temporalmente. | Mantenimiento programado o sobrecarga extrema del servidor. |

---

# 🧱 8. JSON (formato estándar para APIs)

JSON significa JavaScript Object Notation (Notación de Objetos de JavaScript). Es un formato de texto ligero y legible por humanos diseñado para el intercambio de datos.

JSON es el formato de datos estándar de facto para la comunicación entre el cliente (navegador, aplicación móvil) y el servidor, especialmente en los servicios web conocidos como APIs REST (Representational State Transfer).

Spring Boot maneja JSON de manera eficiente y automática, principalmente gracias a una librería llamada Jackson.

Spring Boot no maneja JSON por sí mismo, sino que delega esta tarea a la librería Jackson, que es la biblioteca de serialización y deserialización predeterminada.

Ejemplo:

```json
{
  "id": 5,
  "nombre": "Juan",
  "activo": true
}
```

---

# 🚦 9. ¿Qué es REST?

**REST** significa **Representational State Transfer** (Transferencia de Estado Representacional).

No es un protocolo ni un *software*; es un **estilo arquitectónico** o un conjunto de directrices para diseñar sistemas de comunicación que sean escalables, eficientes y fáciles de usar, especialmente en el contexto de la web distribuida (APIs).

Una **API** (Application Programming Interface) que sigue estos principios se llama **API RESTful** o **Servicio Web RESTful**.

---

## 🎯 Principios Clave de REST

El objetivo de REST es permitir que los sistemas se comuniquen usando el protocolo HTTP de la manera más estándar y uniforme posible, basándose en seis restricciones arquitectónicas. Las más importantes son:

### 1. Cliente-Servidor (Client-Server)
El cliente y el servidor deben estar **separados**.

* El **cliente** se encarga de la interfaz de usuario y la experiencia.
* El **servidor** se encarga del almacenamiento y procesamiento de datos.

Esta separación permite que cada parte evolucione de forma independiente sin afectar a la otra, mejorando la portabilidad y la escalabilidad.

### 2. Sin Estado (Stateless)
Cada petición del cliente al servidor debe contener **toda la información necesaria** para que el servidor la entienda y la complete.

* El servidor **no guarda ninguna información** sobre las peticiones anteriores del cliente.
* Si el cliente necesita mantener una sesión (ej: estar logueado), debe enviar la información de autenticación (como *tokens*) en **cada petición**.

Esto simplifica el diseño del servidor, mejora la fiabilidad y facilita la escalabilidad horizontal.

### 3. Cacheable (Cacheable)
Las respuestas del servidor deben indicar si el recurso solicitado es **cacheable** o no.

* Si un cliente puede almacenar la respuesta en caché, se puede reutilizar esa respuesta para peticiones futuras, reduciendo la latencia y la carga del servidor. (Esto se relaciona directamente con el código HTTP **304 Not Modified** que vimos antes).

### 4. Interfaz Uniforme (Uniform Interface)
Este es el principio central que define la naturaleza de REST. Para lograr una interacción simple y desacoplada, el sistema debe adherirse a cuatro reglas:

* **Identificación de Recursos:** Los recursos individuales (ej: un usuario, un producto) se identifican mediante **URIs** (Uniform Resource Identifiers).
    * *Ejemplo:* `/api/usuarios/123`
* **Manipulación de Recursos a Través de Representaciones:** El cliente recibe representaciones del estado del recurso (generalmente en **JSON** o XML). Al modificar esta representación y enviarla de vuelta, el servidor actualiza el estado del recurso.
* **Mensajes Autodescriptivos:** Cada mensaje de respuesta debe contener suficiente información para describir cómo procesar el mensaje.
* **HATEOAS (Hypermedia as the Engine of Application State):** El recurso devuelto debe contener enlaces a otros recursos relacionados, guiando al cliente sobre qué acciones puede realizar a continuación. (Este principio es a menudo omitido o aplicado de forma parcial en muchas APIs "RESTful").

Ejemplo RESTful:

```
GET /users
POST /users
GET /users/10
PUT /users/10
DELETE /users/10
```

-----

## 📐 Reglas para Diseñar APIs RESTful con Verbos

Para asegurar que tu API sea verdaderamente RESTful (adherente a los principios de REST), sigue estas reglas:

### 1\. Nombres de Recursos: Siempre Sustantivos en Plural

Los *endpoints* (rutas) deben referirse a los **recursos**, no a las acciones. Usa sustantivos en **plural**. La acción ya la define el verbo HTTP.

| Incorrecto | Correcto (RESTful) |
| :--- | :--- |
| `GET /getProductos` | **`GET /productos`** |
| `POST /crearUsuario` | **`POST /usuarios`** |

### 2\. Uso de `GET` (Lectura)

  * **Idempotencia y Seguridad:** `GET` debe ser **idempotente** (repetir la petición no cambia el resultado) y **seguro** (no debe tener efectos secundarios en el servidor).
  * **Filtrado/Búsqueda:** Utiliza **parámetros de consulta** (`?clave=valor`) para filtrar, ordenar o paginar las colecciones.
      * *Ejemplo:* `GET /api/productos?categoria=electronica&ordenarPor=precio`

### 3\. Uso de `POST` (Creación)

  * **Creación:** Úsalo exclusivamente para crear nuevos recursos.
  * **Respuesta:** Tras un `POST` exitoso, el servidor debe responder con el código de estado **`201 Created`** e incluir la URI del nuevo recurso creado en el encabezado `Location`.

### 4\. Uso de `PUT` vs. `PATCH` (Actualización)

Esta es la diferencia más importante a entender:

  * **`PUT` (Reemplazo Total):** Si quieres cambiar el nombre de un producto, debes enviar **TODO** el objeto de producto (nombre, precio, stock, descripción, etc.). Si omites un campo, se considerará que ese campo debe ser vaciado o reemplazado con un valor nulo.
      * *Uso:* Cambios importantes o cuando el cliente siempre envía el objeto completo.
  * **`PATCH` (Actualización Parcial):** Si quieres cambiar solo el nombre de un producto, solo envías el campo `nombre`. El resto de los campos del recurso permanecen intactos.
      * *Uso:* Pequeñas modificaciones para ahorrar ancho de banda y evitar errores de reemplazo.

### 5\. Uso de `DELETE` (Eliminación)

  * **Respuesta:** Tras una eliminación exitosa, responde con **`204 No Content`** si no vas a devolver nada, o **`200 OK`** si confirmas la eliminación devolviendo un mensaje.

-----

## 🛑 Cuándo NO Usar los Verbos Estándar

A veces, una operación no encaja en CRUD (por ejemplo, una operación de negocio compleja como "enviar correo" o "cerrar sesión"). En estos casos, puedes considerar:

1.  **Modelar como Recurso:** Si es posible, trata la operación como un recurso secundario.
      * *Ejemplo:* Para "cerrar una orden", usa `PUT /api/ordenes/123/estado` con el valor `cerrada`.
2.  **Operación en POST:** Si realmente es una **acción** que no devuelve un recurso, puedes usar `POST` con un nombre de recurso que represente la acción (aunque es menos RESTful).
      * *Ejemplo:* `POST /api/ordenes/123/enviar-notificacion`

---

---

## 🏗️ Concepto del Sub-Recurso

Un **Sub-Recurso** es un recurso que existe solo en el contexto de otro recurso principal (o *padre*).

La URL refleja esta relación de contención usando el **`slash (/)`** para anidar las entidades.

### Estructura General

La sintaxis sigue el patrón:

$$\text{/api/\{recurso\_padre\_plural\}/\{id\_padre\}/\{sub\_recurso\_plural\}}$$

| Elemento | Ejemplo | Propósito |
| :--- | :--- | :--- |
| **Recurso Padre** | `/clientes` | La colección principal de entidades. |
| **ID del Padre** | `/clientes/456` | Identifica a un cliente específico. |
| **Sub-Recurso** | `/clientes/456/pedidos` | Identifica la colección de **pedidos** que pertenecen *solo* al cliente `456`. |

---

## 📝 Aplicaciones del Principio del Sub-Recurso

El uso de sub-recursos tiene dos aplicaciones principales en el diseño REST:

### 1. Relaciones de Colección (Uno a Muchos)

Se usa para acceder a colecciones de entidades que son propiedad de un recurso principal.

| Verbo | Ruta RESTful | Acción |
| :--- | :--- | :--- |
| **`GET`** | `/clientes/456/pedidos` | Obtener **todos los pedidos** del cliente `456`. |
| **`POST`** | `/clientes/456/pedidos` | **Crear un nuevo pedido** y asignárselo automáticamente al cliente `456`. |
| **`GET`** | `/clientes/456/pedidos/101` | Obtener un **pedido específico** (`101`) del cliente `456`. |

Esto garantiza la **coherencia de datos**, ya que la URL te obliga a pensar en el contexto del padre.

### 2. Manipulación de Propiedades Específicas (Transiciones de Estado)

Como vimos en el ejemplo anterior, se utiliza para apuntar y manipular una **propiedad específica** del recurso padre, especialmente aquellas que representan un **estado** o un campo único.

| Verbo | Ruta RESTful | Cuerpo (Body) | Acción |
| :--- | :--- | :--- | :--- |
| **`GET`** | `/pedidos/123/estado` | *(vacío)* | Obtener el estado actual del pedido `123`. |
| **`PUT`** | `/pedidos/123/estado` | `"enviado"` | **Reemplazar** el estado del pedido `123` con "enviado". |

En este caso, `/estado` no es una colección, sino un punto de acceso directo para modificar una propiedad clave del recurso principal `/pedidos/123`, manteniendo la URL libre de verbos de acción.

## 🌟 Beneficios del Diseño con Sub-Recursos

1.  **Claridad y Semántica:** La URL es más descriptiva y refleja las relaciones reales de tu modelo de datos (Ej: un pedido no existe sin un cliente que lo haya hecho).
2.  **Organización:** Ayuda a estructurar grandes APIs de forma jerárquica y lógica.
3.  **Seguridad y Permisos:** Facilita la aplicación de reglas de negocio y seguridad. Por ejemplo, podrías permitir que solo un usuario *Administrador* pueda acceder a `/clientes/456/facturas`, mientras que el cliente `456` solo puede ver sus propias `/clientes/456/pedidos`.

En esencia, el Principio del Sub-Recurso asegura que tu API hable el mismo lenguaje que tu modelo de negocio, usando URLs como la herramienta para expresar las **relaciones jerárquicas** entre tus datos.

# 🧳 10. Sesiones, Cookies, Tokens

¡Claro! Los conceptos de **sesiones**, **tokens** y **cookies** son fundamentales para la gestión de la identidad y el estado en el protocolo HTTP, que, como vimos, es inherentemente sin estado (*stateless*). Permiten que los sitios web recuerden quién eres a pesar de que HTTP no lo haga por defecto.

Aquí tienes un desglose de cada concepto y cómo se relacionan:

---

## 🍪 1. Cookies (Galletas)

Las **cookies** son pequeños archivos de texto que los **sitios web envían y almacenan** en el **navegador del usuario** (el cliente).

### ¿Qué son?
* Son el **mecanismo principal** para que los sitios web recuerden información de estado.
* Siempre se envían con la **petición HTTP** de vuelta al servidor (siempre que la cookie aplique al dominio y ruta de la petición).

### Uso Principal
* **Identificación de Sesión:** El uso más crucial es almacenar un **ID de sesión** único o un **token de autenticación** para que el servidor sepa qué usuario está haciendo la petición.
* **Personalización:** Recordar preferencias de idioma, temas o configuraciones de usuario.
* **Seguimiento:** Rastrear la actividad del usuario para análisis o publicidad.

### Tipos Comunes
* **De Sesión:** Expiran cuando se cierra el navegador.
* **Persistentes:** Tienen una fecha de expiración establecida y permanecen en el disco duro del usuario.

---

## 👤 2. Sesiones (Sessions)

Una **sesión** es un **estado lógico y temporal** que se crea en el **servidor** para mantener el seguimiento de un usuario específico a lo largo de múltiples peticiones.

### ¿Qué son?
* Cuando un usuario inicia sesión, el servidor crea una entrada en su memoria o base de datos que contiene toda la información de la sesión (ej: "Usuario ID: 123, Logueado: Sí, Carrito: [Item A]").
* El servidor genera un **ID de sesión** único (ej: `ASDF123XYZ`) para identificar esta entrada.

### Funcionamiento
1.  El usuario se autentica (envía usuario y contraseña).
2.  El **servidor** crea la sesión y su ID único.
3.  El **servidor** envía este ID de sesión al **cliente** dentro de una **cookie**.
4.  En peticiones futuras, el **cliente** envía la **cookie** con el ID de sesión.
5.  El **servidor** busca el ID en su almacenamiento de sesiones para recuperar el estado del usuario.

### Limitaciones
Depende del servidor para almacenar el estado, lo que puede ser un desafío para la escalabilidad (si tienes muchos servidores, necesitan compartir la información de la sesión).

---

## 🔑 3. Tokens (Tokens de Autenticación)

Un **token** es una cadena de caracteres cifrada que se utiliza para **verificar la identidad** de un usuario sin necesidad de consultar el estado de la sesión en el servidor en cada petición. El tipo más popular es el **JSON Web Token (JWT)**.

### ¿Qué son?
* Son una alternativa moderna a las sesiones tradicionales, especialmente populares en las **APIs RESTful**.
* Contienen toda la información de autenticación del usuario, codificada y firmada digitalmente (para verificar su integridad).
* Son **autocontenidos** (no requieren que el servidor busque información en una base de datos de sesiones). 

### Funcionamiento (Típico con JWT)
1.  El usuario se autentica.
2.  El **servidor** genera un JWT que incluye el ID del usuario y otros datos (el *payload*).
3.  El **servidor** envía el **token** al **cliente** (a menudo dentro de una cookie o en el encabezado `Authorization`).
4.  En peticiones futuras, el **cliente** envía el **token**.
5.  El **servidor** solo necesita **verificar la firma** del token para confirmar la identidad del usuario y sus permisos, **sin hacer una consulta a la base de datos** para buscar el estado de la sesión.

### Relación y Dónde se Usan
| Concepto | Dónde Vive | Uso en HTTP |
| :--- | :--- | :--- |
| **Cookie** | Cliente (navegador) | Mecanismo de transporte, almacena el ID de Sesión o el Token. |
| **Sesión** | Servidor (Memoria/DB) | Almacenamiento del estado del usuario (tradicional). |
| **Token (JWT)** | Se genera en el Servidor, se almacena en el Cliente. | Acreditación de identidad **autocontenida** (moderna). |

En resumen:

* **Cookies** son el **vehículo** que se mueve entre el cliente y el servidor.
* **Sesiones** son el **estado del usuario** almacenado en el servidor (el enfoque tradicional).
* **Tokens** son el **pase de acceso** seguro y autocontenido que se almacena en el cliente y puede reemplazar la necesidad de la sesión tradicional en el servidor.
