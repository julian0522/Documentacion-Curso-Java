# Servicios y Lógica de Negocio con Spring

La **Capa de Servicio** (*Service Layer*) es un componente fundamental en la arquitectura de una aplicación, especialmente cuando se sigue el patrón de diseño **Modelo-Vista-Controlador (MVC)** o arquitecturas en capas. En el contexto de Spring Boot, es la capa que se ubica **entre la capa de presentación (Controladores)** y la **capa de persistencia (Repositorios)**.

## 1. ¿Por Qué una Capa de Servicio? Introducción y Contexto

### El Problema: Lógica de Negocio en el Controlador

En el patrón Model-View-Controller (MVC), los controladores se encargan de recibir las peticiones del usuario, interactuar con el modelo y seleccionar la vista adecuada. Son la puerta de entrada a nuestra aplicación.

Sin embargo, si ponemos toda la lógica de negocio (reglas, cálculos, validaciones complejas, coordinación de acciones) directamente en los controladores, estos se vuelven:

* **Voluminosos y difíciles de leer:** Acumulan demasiadas responsabilidades.
* **Difíciles de mantener:** Un cambio en la lógica de negocio afecta directamente a la capa de presentación.
* **Difíciles de probar:** Probar la lógica de negocio requiere levantar todo el contexto web del controlador.
* **Poco reutilizables:** La misma lógica no puede ser fácilmente invocada desde otras partes de la aplicación (ej. una tarea programada, una API interna).

### La Solución: El Patrón Service Layer

El patrón Service Layer (Capa de Servicio) introduce una capa intermedia entre la capa de presentación (controladores) y la capa de acceso a datos (repositorios). Su propósito principal es encapsular la lógica de negocio.

**El Service Layer es donde reside la "inteligencia" de tu aplicación.**

**Beneficios Clave:**

* **Separación de Responsabilidades:** Los controladores se enfocan solo en manejar peticiones y respuestas HTTP. Los servicios se enfocan en *qué* hacer con los datos y *cómo* realizar las operaciones de negocio.
* **Mayor Cohesión:** Cada clase (controlador, servicio, repositorio) tiene una responsabilidad clara y única.
* **Menor Acoplamiento:** Los controladores dependen de interfaces de servicio, no de implementaciones concretas o lógica detallada. Esto facilita cambiar implementaciones o probar componentes de forma aislada.
* **Reutilización:** La lógica de negocio en los servicios puede ser invocada por múltiples controladores u otros componentes de la aplicación.
* **Mantenibilidad:** Los cambios en la lógica de negocio se localizan principalmente en la capa de servicio, reduciendo el riesgo de efectos secundarios inesperados en otras partes.
* **Testabilidad:** Los servicios pueden ser probados fácilmente de forma unitaria, sin necesidad de configurar un entorno web o de base de datos completo.

#### ✨ Funcionalidad Clave

Un servicio en Spring Boot es el lugar donde se implementan las **reglas de negocio** que definen cómo la aplicación gestiona, procesa y transforma los datos.

1.  **Orquestación de Datos:** Combina datos de múltiples fuentes (varios repositorios) para formar una única respuesta compleja.
2.  **Validación de Negocio:** Aplica reglas específicas antes de guardar o modificar datos (ej: verificar que el saldo de una cuenta sea positivo antes de una transferencia).
3.  **Transaccionalidad:** Define los límites de las transacciones (generalmente a nivel de método), asegurando que un conjunto de operaciones de base de datos se ejecute de forma atómica.
4.  **Transformación/Conversión:** Realiza la conversión entre los **objetos de transferencia de datos (DTOs)** utilizados en la capa web y las **entidades** utilizadas en la capa de persistencia.

## 2. Inversión de Dependencias con Spring

### Inversión de Control (IoC) e Inyección de Dependencias (DI)

Spring se basa fuertemente en los principios de Inversión de Control (IoC) y su implementación más común, la Inyección de Dependencias (DI).

* **IoC:** En lugar de que un objeto cree o gestione directamente sus dependencias, un contenedor (el contenedor de Spring) se encarga de crearlas y proporcionárselas al objeto cuando las necesita. Se "invierte" el control sobre la gestión de dependencias.
* **DI:** Es el mecanismo específico para lograr IoC. Las dependencias (otros objetos que una clase necesita para funcionar) son "inyectadas" en la clase, generalmente a través de constructores, métodos `setter` o campos.

El **Contenedor de IoC de Spring** es el corazón del framework. Es responsable de:

1. Instanciar los beans (los objetos gestionados por Spring).
2. Configurar los beans.
3. Gestionar el ciclo de vida de los beans.
4. Inyectar las dependencias entre beans.

### Anotaciones Clave: `@Service` y `@Autowired`

Spring proporciona anotaciones para simplificar la configuración de IoC y DI:

* `@Service`: La anotación `@Service` es una especialización de `@Component`.
  * Le indica al **Contenedor de Inversión de Control (IoC)** de Spring que esta clase es un **Bean** que debe ser gestionado por Spring y que representa un servicio de lógica de negocio.
  * Spring escaneará esta clase y creará una única instancia (por defecto, un **singleton**) para ser inyectada en otras clases (como los Controladores).

    ```java
    @Service
    public class MyBusinessService {
        // Lógica de negocio aquí
    }
    ```

* `@Autowired`: Esta anotación se utiliza para **inyectar dependencias**. Spring buscará un bean compatible en su contenedor y lo asignará automáticamente al campo, constructor o método `setter` anotado.

    ```java
    @Service
    public class AnotherService {

        private final MyBusinessService myBusinessService;

        @Autowired // Inyección por constructor (método recomendado)
        public AnotherService(MyBusinessService myBusinessService) {
            this.myBusinessService = myBusinessService;
        }

        public void performAction() {
            myBusinessService.doSomething();
        }
    }
    ```

    La inyección por constructor (`@Autowired` en el constructor) es generalmente preferible ya que asegura que el objeto tenga todas sus dependencias al ser creado y facilita la prueba unitaria.

    La inversión de control es un principio de diseño donde el control del flujo de ejecución del programa se invierte. En lugar de que el código de la aplicación controle cuándo y cómo se crean los objetos, este control se delega a un framework o contenedor externo.
    ### Concepto Fundamental
    En la programación tradicional, cuando una clase necesita una dependencia, ella misma crea la instancia usando la palabra clave new. Con IoC, el control de la creación de objetos se transfiere al contenedor de Spring, que es responsable de crear, configurar y gestionar el ciclo de vida de los objetos.
### Funcionamiento en Spring Boot
Spring Boot utiliza un contenedor de IoC que escanea las clases anotadas con estereotipos como @Component, @Service, @Repository y @Controller. Estos componentes son registrados como beans y el contenedor se encarga de inyectarlos donde sean necesarios.

```java
@Configuration
public class AppConfig {
    
    // Definición manual de un bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

@Service
public class AutenticacionService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    // Spring inyecta tanto el repositorio como el encoder
    public AutenticacionService(UsuarioRepository usuarioRepository,
                               PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public boolean autenticar(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new CredencialesInvalidasException(
                "Credenciales inválidas"
            ));
        
        return passwordEncoder.matches(password, usuario.getPasswordHash());
    }
}
```

## 3. Separación por Capas: Profundizando en el Patrón Service Layer

Como mencionamos, el patrón Service Layer es una de las capas en la arquitectura de aplicaciones Spring:

* **Capa de Presentación:** Maneja la interacción con el usuario (controladores REST, controladores MVC para vistas, etc.). Su trabajo es recibir la entrada, validar datos básicos, pasar la solicitud a la capa de servicio y devolver una respuesta. **(Ej: Clases con `@RestController` o `@Controller`)**
* **Capa de Servicio (Business Logic):** Contiene las reglas de negocio, coordina las operaciones de acceso a datos si es necesario, realiza validaciones complejas y define las transacciones. No interactúa directamente con la base de datos o fuentes de datos externas; delega esta tarea a la capa de acceso a datos. **(Ej: Clases con `@Service`)**
* **Capa de Acceso a Datos (Data Access Object - DAO / Repository):** Es responsable de la comunicación directa con la fuente de datos (bases de datos, servicios externos, etc.). Proporciona métodos para realizar operaciones CRUD básicas sobre las entidades (guardar, leer, actualizar, eliminar). **(Ej: Interfaces que extienden `JpaRepository` o clases con `@Repository`)**

![Separación Capas](assets/SeparacionCapas.png)

El Service Layer actúa como el **orquestador**. Un método en un servicio podría:

1. Recibir datos de un controlador.
2. Validar esos datos según reglas de negocio.
3. Invocar uno o varios métodos de diferentes repositorios para obtener o guardar información.
4. Realizar cálculos o transformaciones complejas con los datos.
5. Manejar la lógica transaccional.
6. Devolver un resultado al controlador.

**Cómo te ayuda Cursor AI aquí:**

* **Navegación y Búsqueda:** Utiliza las capacidades de navegación y búsqueda semántica de **Cursor AI** para moverte rápidamente entre controladores, servicios y repositorios en tu proyecto, ayudándote a visualizar la estructura en capas.
* **Diagramas Conceptuales (Potencial):** Aunque no es una función nativa de diagramación, a veces puedes pedir a **Cursor AI** en el chat que te describa la interacción entre capas para un escenario específico, ayudando a solidificar tu comprensión.

## 4. Otras Anotaciones de Componentes de Spring

Además de `@Service`, Spring ofrece otras anotaciones estereotipo y de configuración importantes:

* `@Component`: Esta es la anotación estereotipo genérica. Cualquier clase anotada con `@Component` es candidata a ser gestionada como un bean por el contenedor de Spring. `@Service`, `@Repository` y `@Controller` son especializaciones de `@Component` que añaden semántica (significado) a la clase.

    ```java
    @Component
    public class UtilityClass {
        // Métodos de utilidad general
    }
    ```

    Aunque puedes usar `@Component` para servicios, es mejor usar `@Service` porque comunica más claramente el rol de la clase.

* `@Repository`: Anotación estereotipo para clases que actúan como **repositorios de datos** o DAOs (Data Access Objects). Indica que la clase tiene el rol de interactuar con la base de datos. Spring puede aplicar funcionalidades especiales a estas clases (como traducción automática de excepciones de base de datos).

    ```java
    @Repository
    public interface ProductRepository extends JpaRepository<Product, Long> {
        // Métodos para acceder a datos de productos
    }
    ```

* `@Controller`: Anotación estereotipo para clases que actúan como **controladores en la capa de presentación**, manejando peticiones web.

    ```java
    @Controller // Para MVC tradicional que devuelve vistas
    public class HomeController {
        // Maneja peticiones web
    }

    @RestController // Combinación de @Controller y @ResponseBody para APIs REST
    public class ProductController {
       // Maneja peticiones REST y devuelve datos directamente
    }
    ```

* `@Configuration` y `@Bean`: Estas anotaciones se utilizan para definir beans de Spring usando configuración basada en Java en lugar de escaneo de componentes.
  * `@Configuration`: Indica que una clase declara uno o más métodos `@Bean`. Spring procesará esta clase para generar beans.
  * `@Bean`: Se usa en un método dentro de una clase `@Configuration`. El método debe retornar un objeto que Spring registrará como un bean en su contenedor. El nombre del bean por defecto será el nombre del método.

    ```java
    @Configuration
    public class AppConfig {

        @Bean // Define un bean llamado 'myCustomBean'
        public MyCustomClass myCustomBean() {
            return new MyCustomClass();
        }

        @Bean // Define otro bean llamado 'anotherDependency'
        public AnotherDependency anotherDependency() {
            // Aquí puedes configurar la dependencia si es necesario
            return new AnotherDependency();
        }

        // Puedes inyectar otros beans de configuración en métodos @Bean
        @Bean
        public ServiceWithDependency myServiceWithDependency(@Autowired AnotherDependency anotherDependency) {
             return new ServiceWithDependency(anotherDependency);
        }
    }
    ```

    Usas `@Configuration` y `@Bean` principalmente cuando necesitas crear beans de clases de terceros (que no puedes anotar con `@Component`), o cuando necesitas lógica compleja para crear o configurar un bean.

## 5. Implementando el Servicio LibroService

Ahora, vamos a aplicar estos conceptos creando un servicio real para gestionar libros.

Nuestro `LibroService` encapsulará la lógica de negocio relacionada con los libros. Para simplificar, usaremos una `List` en memoria para almacenar los datos, simulando una fuente de datos temporal.

Primero, definiremos una clase simple `Libro`:

```java
public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    // Constructor, getters y setters
}
```

Luego, crearemos la interfaz `LibroService` (opcional, pero recomendado para desacoplamiento y testabilidad):

```java
import java.util.List;

public interface LibroService {
    List<Libro> listarLibros();
    Libro buscarPorTitulo(String titulo);
    void agregarLibro(Libro libro);
    void eliminarLibro(String titulo); // Simplificamos buscando por título
}
```

Y la implementación de nuestro servicio, anotada con `@Service` para que Spring la gestione:

```java
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // Para manejar el resultado de la búsqueda

@Service
public class LibroServiceImpl implements LibroService {

    private final List<Libro> listaLibros = new ArrayList<>(); // Nuestra "base de datos" en memoria

    @Override
    public List<Libro> listarLibros() {
        // Lógica para listar
        return new ArrayList<>(listaLibros); // Devolver una copia para evitar modificaciones externas
    }

    @Override
    public Libro buscarPorTitulo(String titulo) {
        // Lógica para buscar
        Optional<Libro> found = listaLibros.stream()
                                          .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
                                          .findFirst();
        return found.orElse(null); // Devuelve el libro si lo encuentra, null si no
    }

    @Override
    public void agregarLibro(Libro libro) {
        // Lógica para agregar
        if (libro != null && buscarPorTitulo(libro.getTitulo()) == null) { // Simple validación: no duplicados por título
            listaLibros.add(libro);
        }
    }

    @Override
    public void eliminarLibro(String titulo) {
        // Lógica para eliminar
        listaLibros.removeIf(libro -> libro.getTitulo().equalsIgnoreCase(titulo));
    }
}
```

Finalmente, inyectaremos este servicio en un controlador simple para poder invocar sus métodos desde peticiones HTTP:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // O @Controller con @ResponseBody
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> getAllLibros() {
        return libroService.listarLibros();
    }

    @GetMapping("/{titulo}")
    public Libro getLibroByTitulo(@PathVariable String titulo) {
        return libroService.buscarPorTitulo(titulo);
    }

    @PostMapping
    public void addLibro(@RequestBody Libro libro) {
        libroService.agregarLibro(libro);
    }

    @DeleteMapping("/{titulo}")
    public void deleteLibro(@PathVariable String titulo) {
        libroService.eliminarLibro(titulo);
    }
}
```

## Anotacion @Transactional (Debe ir en el servicio)

¡La anotación `@Transactional` es una de las características más importantes y potentes de Spring Boot para la capa de servicio\!

Sirve para gestionar las **transacciones de base de datos** de forma declarativa, es decir, sin necesidad de escribir código manual para iniciar, confirmar o revertir una transacción.

-----

### ⚙️ ¿Para Qué Sirve la Anotación `@Transactional`?

La anotación `@Transactional` en Spring Boot (y Spring Framework) se utiliza para asegurar la **integridad, consistencia y atomicidad** de un conjunto de operaciones de base de datos.

Su propósito principal es:

1.  **Definir un Límite Transaccional:** Marca el inicio y el fin de una unidad de trabajo que debe ejecutarse completamente o no ejecutarse en absoluto (el principio de **Atomicidad**).
2.  **Gestionar el `Commit` o `Rollback`:** Spring se encarga automáticamente de:
      * Hacer **`commit`** (confirmar) los cambios en la base de datos si el método se ejecuta sin lanzar ninguna excepción no marcada (`RuntimeException` o sus subclases).
      * Hacer **`rollback`** (revertir o deshacer) todos los cambios realizados si el método lanza una excepción no marcada.

#### Ejemplo de Uso

Se aplica típicamente a nivel de **clase** (afectando a todos los métodos) o, más comúnmente, a nivel de **método** dentro de la Capa de Servicio:

```java
@Service
public class AccountService {

    // ... Repositorios inyectados

    // Si ocurre un error en save(entityA) o save(entityB),
    // ambos cambios se revertirán, manteniendo la consistencia.
    @Transactional
    public void transferMoney(Long accountAId, Long accountBId, BigDecimal amount) {
        // 1. Lógica de negocio para restar dinero de la Cuenta A
        Account accountA = accountRepository.findById(accountAId).orElseThrow();
        accountA.setBalance(accountA.getBalance().subtract(amount));
        accountRepository.save(accountA); // Operación 1

        // Aquí podría ocurrir una excepción (ej: cuenta B no existe)

        // 2. Lógica de negocio para sumar dinero a la Cuenta B
        Account accountB = accountRepository.findById(accountBId).orElseThrow();
        accountB.setBalance(accountB.getBalance().add(amount));
        accountRepository.save(accountB); // Operación 2
    }
}
```

-----

### 🔑 Los Principios ACID

La anotación `@Transactional` ayuda a implementar los principios **ACID** (Atomicidad, Consistencia, Aislamiento y Durabilidad), que son el pilar de las bases de datos relacionales:

1.  **A**tomicidad: Garantiza que todas las operaciones dentro de la transacción se completen con éxito. Si una falla, todas las demás se deshacen.
2.  **C**onsistencia: Asegura que la transacción lleve la base de datos de un estado válido a otro.
3.  **I**solamiento (*Isolation*): Define cómo las operaciones de una transacción son ocultadas de otras transacciones concurrentes (gestionado por el atributo `isolation`).
4.  **D**urabilidad: Una vez que una transacción es confirmada (`commit`), sus cambios son permanentes y sobreviven a cualquier fallo del sistema.

-----

### 📄 Atributos Comunes

La anotación `@Transactional` acepta varios atributos que permiten personalizar el comportamiento de la transacción:

| Atributo | Descripción |
| :--- | :--- |
| **`propagation`** | Define cómo debe comportarse la transacción si ya existe una transacción en curso. El más común es **`REQUIRED`** (usar la transacción actual o crear una nueva). |
| **`isolation`** | Especifica el nivel de aislamiento de la transacción, controlando la visibilidad de los cambios a otras transacciones concurrentes (ej: `READ_COMMITTED`). |
| **`timeout`** | Define el tiempo máximo (en segundos) que la transacción puede ejecutarse antes de que Spring lance una excepción y haga un *rollback*. |
| **`readOnly`** | Un *hint* de optimización. Si se establece en `true`, la transacción solo leerá datos y el *backend* puede aplicar optimizaciones. |
| **`rollbackFor`** | Permite especificar qué tipos de excepciones deben forzar un **`rollback`**, incluyendo aquellas que por defecto Spring ignora (como las excepciones verificadas). |
