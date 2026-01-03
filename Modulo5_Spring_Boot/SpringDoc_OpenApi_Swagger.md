# **Springdoc OpenAPI** (Swagger)

**Springdoc OpenAPI** es la biblioteca estándar para aplicaciones Spring Boot que automatiza la generación de documentación de API siguiendo la especificación **OpenAPI 3**. Sustituyó a la antigua librería *Springfox* (Swagger 2), la cual quedó obsoleta.

---

## 1. ¿Qué es y para qué sirve?

* **¿Qué es?** Es una librería que examina tus controladores, modelos y configuraciones de Spring en tiempo de ejecución para generar un archivo JSON/YAML que describe tu API.
* **¿Para qué sirve?** 1.  **Swagger UI:** Genera una interfaz visual interactiva donde puedes probar los endpoints sin usar Postman.
2.  **Contrato de API:** Genera el archivo `openapi.json` que sirve como contrato para que otros equipos (Frontend o Mobile) sepan cómo consumir tu servicio.
3.  **Client Generation:** Permite generar automáticamente el código del cliente en otros lenguajes.

---

## 2. Configuración (Maven)

Para Spring Boot 3, solo necesitas añadir una dependencia:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>

```

Por defecto, tras arrancar la app, la documentación estará en:

* **Interfaz Visual:** `http://localhost:8080/swagger-ui/index.html`
* **JSON de OpenAPI:** `http://localhost:8080/v3/api-docs`

---

## 3. Anotaciones de OpenAPI 3

A diferencia de Swagger 2 (que usaba `@Api`, `@ApiOperation`), OpenAPI 3 utiliza el paquete `io.swagger.v3.oas.annotations`.

### Para el Controlador y Endpoints

* **`@Tag`**: Agrupa y nombra los controladores (ej: "Usuarios", "Ventas").
* **`@Operation`**: Describe el propósito del endpoint y el método HTTP.
* **`@Parameter`**: Describe un parámetro de entrada (`@PathVariable`, `@RequestParam`).
* **`@RequestBody`**: Describe el cuerpo de la petición esperado. Pueden describir su propósito, si es requerido y referenciar el esquema del modelo (@Schema).

### Para las Respuestas

* **`@ApiResponse`**: Define el código de estado (200, 404, 500) y su descripción.
* **`@ApiResponses`**: Contenedor para múltiples respuestas.

### Para el Modelo (DTOs)

* **`@Schema`**: Describe un objeto o una propiedad (permite poner ejemplos, descripciones y marcar si es obligatorio).

---

## 4. Ejemplo Práctico de Implementación

### Modelo Documentado

```java
@Schema(description = "Representa un usuario en el sistema")
public class UsuarioDTO {

    @Schema(example = "1", description = "ID único del usuario")
    private Long id;

    @Schema(example = "Juan Perez", required = true)
    private String nombre;
    
    // Getters y Setters...
}

```

### Controlador Documentado

```java
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Gestión de usuarios y perfiles")
public class UsuarioController {

    @Operation(summary = "Obtener usuario por ID", description = "Busca un usuario en la base de datos y devuelve su perfil")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "El usuario no existe")
    })
    @GetMapping("/{id}")
    public UsuarioDTO obtener(@Parameter(description = "ID del usuario a buscar") @PathVariable Long id) {
        return new UsuarioDTO(id, "Juan");
    }
}

```

---

## 5. Configuración Personalizada (Bean de Configuración)

Si quieres cambiar el título, la versión o los términos de servicio, crea una clase de configuración:

```java
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Mi Empresa")
                .version("1.0")
                .description("Documentación detallada de los servicios de la app")
                .contact(new Contact().name("Soporte").email("soporte@empresa.com"))
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}

```

---

## 6. Integración con Bean Validation (`javax.validation`)

Una de las mejores características de **Springdoc** es que lee tus anotaciones de validación automáticamente.

* Si pones `@NotNull` en un campo, Swagger marcará ese campo con un asterisco rojo y dirá que es **required**.
* Si pones `@Size(min=3, max=10)`, Swagger mostrará las restricciones de longitud en la UI.

---

## 7. Propiedades Útiles en `application.properties`

Puedes personalizar las rutas de acceso:

```properties
# Cambiar la ruta de la UI
springdoc.swagger-ui.path=/docs
# Cambiar la ruta del JSON
springdoc.api-docs.path=/api-definitions
# Ordenar los endpoints alfabéticamente
springdoc.swagger-ui.operationsSorter=alpha

```

## 8. Segundo Ejemplo de su uso
```java
package com.example.apicursos.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

// ...

@Tag(name = "Cursos", description = "API para la gestión de cursos académicos")
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Operation(summary = "Obtener un curso por su ID", 
            description = "Retorna la información detallada de un curso específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso encontrado exitosamente",
                  content = @Content(schema = @Schema(implementation = Curso.class))),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado - El ID proporcionado no existe"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida - Por ejemplo, si el ID no es numérico")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(
        @Parameter(description = "ID del curso a buscar")
        @PathVariable Long id) {
            // Lógica del controlador
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo curso")
    public Curso createCurso(
            @RequestBody(description = "Datos del nuevo curso a crear", required = true, 
                        content = @Content(schema = @Schema(implementation = Curso.class))) 
            @Valid @org.springframework.web.bind.annotation.RequestBody Curso curso) { 
        // Lógica del controlador
    }

    // ... otros métodos del controlador ...
}
```

```java
package com.example.apicursos.model;

import io.swagger.v3.oas.annotations.media.Schema; 

@Entity
@Table(name = "cursos")
@Schema(description = "Representa un curso académico")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único generado automáticamente", example = "101")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    @Schema(description = "Nombre completo del curso", example = "Programación Avanzada en Java")
    private String nombre;

    @NotBlank
    @Size(min = 2, max = 10)
    @Schema(description = "Código único del curso", example = "PROG401") 
    private String codigo;

    // ... otras propiedades con @Schema ...

    // ... Constructores, Getters, Setters, toString ...
}
```


## 9. Documentacion de Seguridad en Swagger

Para configurar seguridad en **Springdoc OpenAPI**, especialmente con **JWT (JSON Web Token)**, necesitas definir cómo Swagger debe enviar el token en las peticiones. Si no haces esto, aunque tengas el endpoint documentado, siempre recibirás un error `401 Unauthorized` al intentar probarlo desde la interfaz.

Aquí tienes la guía para implementar seguridad y proteger tus endpoints en la documentación.

---

### 9.1. Configuración de Seguridad Global (JWT)

Debes registrar un componente `SecurityScheme` en tu clase de configuración. Esto añadirá el botón **"Authorize"** (el candado) en la parte superior de la interfaz de Swagger.

```java
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        
        return new OpenAPI()
            .info(new Info()
                .title("API de Usuarios")
                .version("1.0"))
            // 1. Definir el esquema de seguridad
            .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
            .components(new Components()
                .addSecuritySchemes(securitySchemeName,
                    new SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
    }
}

```

---

### 9.2. Protección selectiva de Endpoints

Si no quieres que **todos** los endpoints requieran el candado (por ejemplo, el de `login` o `registro`), puedes usar la anotación `@SecurityRequirement` a nivel de método.

### Ejemplo: Un endpoint protegido y uno público

```java
@RestController
@RequestMapping("/api")
public class AuthController {

    @Operation(summary = "Login público", description = "No requiere token")
    @PostMapping("/login")
    public String login() {
        return "Token generado...";
    }

    @Operation(summary = "Obtener perfil", description = "Requiere un token JWT válido")
    @SecurityRequirement(name = "bearerAuth") // Nombre definido en el Bean anterior
    @GetMapping("/perfil")
    public String getPerfil() {
        return "Datos privados";
    }
}

```

---

### 9.3. Manejo de Roles y Permisos en la Documentación

Puedes enriquecer la documentación para indicar qué **Roles** se necesitan para ejecutar un endpoint. Aunque OpenAPI no lee automáticamente `@PreAuthorize`, puedes añadirlo manualmente a la descripción:

```java
@Operation(
    summary = "Eliminar usuario",
    description = "Solo accesible por usuarios con rol **ROLE_ADMIN**"
)
@DeleteMapping("/{id}")
public void eliminar(@PathVariable Long id) { ... }

```

---

### 9.4. Flujo de Trabajo en Swagger UI

Una vez configurado lo anterior, el proceso para probar la API es:

1. Abres `swagger-ui/index.html`.
2. Buscas el endpoint de `/login` y lo ejecutas para obtener tu token.
3. Copias el token.
4. Haces clic en el botón superior **Authorize**.
5. Pegas el token (Swagger añadirá automáticamente la palabra "Bearer " antes).
6. Ahora todos los endpoints protegidos enviarán el Header `Authorization: Bearer <tu_token>`.

---

### 9.5. Bonus: Filtrar contenido por seguridad

Si quieres que Swagger **oculte** ciertos endpoints dependiendo de alguna condición (ej. no mostrar los endpoints internos en producción), puedes usar grupos en `application.properties`:

```properties
# Definir un grupo para la API pública
springdoc.group-config[0].group=publica
springdoc.group-config[0].paths-to-match=/api/public/**

# Definir un grupo para la API de administración
springdoc.group-config[1].group=admin
springdoc.group-config[1].paths-to-match=/api/admin/**

```