# Principios SOLID y su Aplicación Práctica en Java

## ¿Qué son los Principios SOLID y Por Qué Son Importantes?

SOLID es un acrónimo que representa cinco principios fundamentales para el diseño de software:

1. **S** - Single Responsibility Principle (Principio de Responsabilidad Única)
2. **O** - Open/Closed Principle (Principio Abierto/Cerrado)
3. **L** - Liskov Substitution Principle (Principio de Sustitución de Liskov)
4. **I** - Interface Segregation Principle (Principio de Segregación de Interfaz)
5. **D** - Dependency Inversion Principle (Principio de Inversión de Dependencia)

Seguir estos principios te ayuda a evitar los "malos olores" del código (code smells) y construir software con las siguientes cualidades::

- **Mantenible:** Más fácil de corregir y actualizar.
- **Escalable:** Permite añadir nuevas características con menos esfuerzo.
- **Flexible:** Adaptable a cambios futuros.
- **Comprensible:** Diseño claro y fácil de entender.
- **Testable:** Facilita la escritura de pruebas unitarias.

## Principios SOLID en Detalle

## 1. Single Responsibility Principle (SRP)

**Definición:**

Una clase debe tener **una, y solo una, razón para cambiar**. Esto significa que debe tener una **única responsabilidad bien definida**.

**¿Por qué es importante?**

Cuando una clase tiene múltiples responsabilidades, un cambio en una de esas responsabilidades puede afectar (y potencialmente romper) las otras. Esto hace que la clase sea difícil de modificar, probar y reutilizar.

**Ejemplo de Violación:**

Imagina una clase `ReportGenerator` que se encarga tanto de generar el contenido de un informe como de guardarlo en un archivo y enviarlo por email.

```java
public class BadReportGenerator {
    public String generateReportData() {
        // Lógica compleja para generar datos del informe
        System.out.println("Generando datos del informe...");
        // Generar datos del informe
        return "Datos del Informe";
    }

    public void saveToFile(String reportData, String filename) {
        // Lógica para guardar el informe en un archivo
        System.out.println("Guardando informe en " + filename);
        // ... código para escribir en archivo
        // Guardar en archivo
    }

    public void sendEmail(String reportData, String emailAddress) {
        // Lógica para enviar el informe por email
        System.out.println("Enviando informe por email a " + emailAddress);
        // ... código para enviar email
        // Enviar por email
    }
}
```

Esta clase tiene 3 razones para cambiar:

1. Si cambia la lógica de generación de datos.
2. Si cambia la forma de guardar archivos.
3. Si cambia la forma de enviar emails.

**Refactorización:**

Dividimos la clase `BadReportGenerator` en tres clases con responsabilidades únicas:

```java
public class ReportDataGenerator {
    public String generateReportData() {
        System.out.println("Generando datos del informe...");
        return "Datos del Informe";
    }
}

public class ReportSaver {
    public void saveToFile(String reportData, String filename) {
        System.out.println("Guardando informe en " + filename);
        // Guardar en archivo
    }
}

public class ReportEmailSender {
    public void sendEmail(String reportData, String emailAddress) {
        System.out.println("Enviando informe por email a " + emailAddress);
        // Enviar por email
    }
}
```

Una clase de más alto nivel (o un servicio en Spring) puede coordinar estas responsabilidades

```java
public class ReportProcessor {
    private ReportDataGenerator dataGenerator = new ReportDataGenerator();
    private ReportSaver saver = new ReportSaver();
    private ReportEmailSender emailSender = new ReportEmailSender();

    public void processAndSendReport(String filename, String emailAddress) {
        String reportData = dataGenerator.generateReportData();
        saver.saveToFile(reportData, filename);
        emailSender.sendEmail(reportData, emailAddress);
        // Enviar por email
    }
}
```

Ahora, cada clase tiene solo una razón para cambiar. Si la lógica de guardar archivos cambia, solo modificas `ReportSaver`.

## 2. Open/Closed Principle (OCP)

**Definición:**

Las entidades de software (clases, módulos, funciones, etc.) deben estar **abiertas para extensión, pero cerradas para modificación**. Esto significa que puedes añadir nueva funcionalidad sin cambiar el código existente de las clases que ya funcionan y están probadas.

**¿Por qué es importante?**

Cuando tienes que modificar código existente cada vez que añades una nueva característica, corres el riesgo de introducir errores en funcionalidades que antes funcionaban (código "frágil"). OCP promueve el uso de abstracciones (interfaces, clases abstractas) para permitir la extensión a través de nuevas implementaciones.

## El problema: El uso excesivo de `if` o `switch`

Cuando ves una estructura que pregunta constantemente "¿Qué tipo de objeto eres?" para decidir qué hacer, probablemente estás violando el OCP.

### ❌ Mal diseño (Violando el OCP)

Imagina que tienes un sistema de descuentos. Cada vez que quieres agregar un nuevo tipo de descuento, tienes que modificar la clase original.

```java
public class CalculadorDescuento {
    public double aplicarDescuento(Object producto, double precio) {
        if (producto instanceof Ropa) {
            return precio * 0.8; // 20% descuento
        } else if (producto instanceof Alimento) {
            return precio * 0.9; // 10% descuento
        }
        // Si mañana queremos "Electronica", ¡tenemos que modificar esta clase!
        return precio;
    }
}

```

**Problema:** Cada vez que el negocio inventa un nuevo descuento, abrimos `CalculadorDescuento.java`, lo modificamos, lo recompilamos y volvemos a testear todo.

---

## La solución: Abstracción e Interfaces

Para cumplir con el OCP en Java, utilizamos **interfaces** o **clases abstractas**. En lugar de que una clase controle todos los casos, definimos un contrato.

### ✅ Buen diseño (Aplicando OCP)

Primero, creamos una interfaz para el comportamiento que queremos extender:

```java
public interface Descuento {
    double aplicar(double precio);
}

```

Ahora, cada vez que necesitemos un descuento nuevo, **creamos una clase nueva** en lugar de modificar la lógica central:

```java
public class DescuentoRopa implements Descuento {
    public double aplicar(double precio) { return precio * 0.8; }
}

public class DescuentoAlimento implements Descuento {
    public double aplicar(double precio) { return precio * 0.9; }
}

```

Finalmente, nuestra clase principal queda **cerrada a modificaciones** pero **abierta a extensiones**:

```java
public class ProcesadorDePrecios {
    public double procesar(double precio, Descuento descuento) {
        // No importa cuántos tipos de descuento existan en el futuro,
        // este código nunca tendrá que cambiar.
        return descuento.aplicar(precio);
    }
}

```

---

## Ventajas de aplicar OCP

1. **Estabilidad:** El código existente no se toca, por lo que no rompes lo que ya funciona.
2. **Escalabilidad:** Añadir una nueva función es tan simple como crear un nuevo archivo `.java`.
3. **Desacoplamiento:** El sistema no depende de implementaciones específicas, sino de abstracciones.

### Comparativa rápida

| Concepto | Violando OCP | Cumpliendo OCP |
| --- | --- | --- |
| **Añadir lógica** | Editando archivos existentes. | Creando archivos nuevos. |
| **Riesgo** | Alto (puedes romper funciones viejas). | Bajo (aislamiento total). |
| **Herramienta** | `if / else`, `switch`. | `interface`, `abstract class`, Polimorfismo. |


**Ejemplo de Violación:**

Imagina una clase que calcula el área total de diferentes formas geométricas.

```java
public class BadAreaCalculator {
    public double calculateTotalArea(List<Object> shapes) {
        double totalArea = 0;
        for (Object shape : shapes) {
            if (shape instanceof Rectangle) {
                totalArea += ((Rectangle) shape).getArea();
            } else if (shape instanceof Circle) {
                totalArea += ((Circle) shape).getArea();
            }
        }
        return totalArea;
    }
}
```

Si añades una nueva forma (ej. Triángulo), tendrás que modificar este método.

**Refactorización:**

Introducimos una abstracción (`Shape` interfaz) y hacemos que las formas la implementen. El calculador trabaja con la abstracción.

```java
public interface Shape {
    double calculateArea();
}

public class Rectangle implements Shape {
    @Override
    public double calculateArea() {
        return width * height;
    }
}

public class Circle implements Shape {
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

public class GoodAreaCalculator {
    public double calculateTotalArea(List<Shape> shapes) {
        return shapes.stream().mapToDouble(Shape::calculateArea).sum();
    }
}
```

Ahora, el calculador trabaja con la abstracción `Shape`:

- Está cerrado a modificación (no necesita cambiar si se añade una nueva forma)
- Está abierto a extensión (puedes añadir `Triangle` implementando `Shape` sin tocar GoodAreaCalculator)

Para añadir Triángulo:

```java
public class Triangle implements Shape {
    @Override
    public double calculateArea() {
        return 0.5 * base * height; 
    }
}
```

No necesitas modificar `GoodAreaCalculator`, solo usas `Triangle` como cualquier otra `Shape`.

## 3. Liskov Substitution Principle (LSP)

**Definición:**

Las subclases deben ser sustituibles por sus clases base sin alterar el comportamiento esperado.

> Si `S` es un subtipo de `T`, entonces los objetos de tipo `T` en un programa pueden ser sustituidos por objetos de tipo `S` sin alterar ninguna de las propiedades deseables de ese programa (como la corrección o el rendimiento). En términos simples: **una subclase debe poder sustituir a su clase base sin romper el código que usa la clase base**.

**¿Por qué es importante?**

Si una subclase no cumple el "contrato" de su clase base (explícito o implícito), el código que espera trabajar con la clase base de repente se encuentra con comportamientos inesperados (excepciones, resultados incorrectos, etc.) cuando recibe una instancia de la subclase. Esto rompe la fiabilidad y hace que el código sea impredecible.

**Ejemplo de Violación:**

Un ejemplo clásico (aunque a veces debatido) es el de `Square` heredando de `Rectangle`.

```java
public class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public int getArea() { return width * height; }
}

public class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}
```

Un cuadrado "es un" rectángulo, pero cambiar el ancho también cambia el alto.

Esto viola la expectativa común de un rectángulo donde cambiar ancho o alto es independiente.

Código cliente que espera trabajar con un `Rectangle`

```java
public class AreaCalculator {
    public void calculateAndPrintArea(Rectangle r) {
        r.setWidth(5);
        r.setHeight(10); // Esperamos que el área sea 5 * 10 = 50
        System.out.println("Área esperada: 50, Área real: " + r.getArea());
    }
}
```

Si usamos esto:

```java
AreaCalculator calculator = new AreaCalculator();
calculator.calculateAndPrintArea(new Rectangle()); // OK, imprime 50
calculator.calculateAndPrintArea(new Square());    // ¡Problema! El área será 10 * 10 = 100 porque setHeight(10) también cambió el ancho.
```

El `Square` no se pudo sustituir por el `Rectangle` sin alterar el resultado esperado del cliente.

**Solución:**

## La solución: Abstraer el comportamiento común

Para cumplir con el LSP, no debemos heredar de clases concretas que cambian el comportamiento base. Es mejor usar interfaces o clases abstractas que definan solo lo que es común.

### ✅ Buen diseño (Aplicando LSP)

```java
public interface Forma {
    int calcularArea();
}

public class Rectangulo implements Forma {
    private int ancho, alto;
    // getters, setters...
    public int calcularArea() { return ancho * alto; }
}

public class Cuadrado implements Forma {
    private int lado;
    // getter, setter...
    public int calcularArea() { return lado * lado; }
}

```

Ahora, cualquier función que use `Forma` sabe que solo puede pedir el área, y no asume nada sobre cómo se comportan los lados internamente.

---

## ¿Cómo saber si estás violando el LSP?

Busca estas "señales de alerta" en tu código Java:

1. **Métodos vacíos o con excepciones:** Si heredas de una clase pero dejas un método vacío o lanzas una `UnsupportedOperationException` porque "esa función no aplica a esta hija", estás violando el LSP.
2. **Validaciones con `instanceof`:** Si tienes un código que pregunta: `if (objeto instanceof Perro) { ... }`, significa que el padre no es lo suficientemente genérico para ser sustituido.

> **Regla de oro:** Una clase hija debe honrar el "contrato" de la clase padre. No puede exigir más ni dar menos de lo que el padre prometió.

A menudo, las violaciones de LSP sugieren que la relación de herencia ("es un") no es tan simple como parece, o que la abstracción inicial no captura completamente el comportamiento. Una solución podría ser no usar herencia directa si rompe el contrato, o asegurar que el contrato de la clase base es lo suficientemente amplio. En el ejemplo, podríamos evitar la herencia y tener clases `Rectangle` y `Square` separadas, o ambas implementando una interfaz común Shape con solo un método `getArea()` (como en el ejemplo de OCP), donde no hay métodos `setWidth`/`setHeight` que causen el problema.

## 4. Interface Segregation Principle (ISP)

**Definición:**

Los clientes **no deben ser forzados a depender de interfaces que no usan**. Es mejor tener muchas interfaces pequeñas y específicas que una interfaz grande y "gorda".

En términos prácticos: es mejor tener **muchas interfaces específicas** que una sola **interfaz general** (interfaz "gorda" o "contaminada").

**¿Por qué es importante?**

Cuando una interfaz es demasiado grande, las clases que la implementan se ven obligadas a proporcionar implementaciones (a veces vacías o sin sentido) para métodos que no necesitan. Esto hace que el código sea menos claro, más difícil de mantener y que los clientes que solo necesitan una pequeña parte de la funcionalidad dependan de una interfaz llena de métodos irrelevantes para ellos.

**Ejemplo de Violación:**

```java
interface BadRobot {
    void run();
    void walk();
    void fly(); // No todos los robots pueden volar
    void drill(); // No todos los robots pueden taladrar
    void cook(); // No todos los robots pueden cocinar
    void serve(); // No todos los robots pueden servir
}

// Un robot de servicio solo necesita algunos métodos
class ServiceRobot implements BadRobot {
    @Override public void run() { /* ... */ }
    @Override public void walk() { /* ... */ }
    @Override public void fly() { /* No aplica, implementación vacía o excepción */ }
    @Override public void drill() { /* No aplica */ }
    @Override public void cook() { /* No aplica */ }
    @Override public void serve() { /* ... */ } // Este es su propósito
}

// Un robot de construcción solo necesita otros métodos
class ConstructionRobot implements BadRobot {
    @Override public void run() { /* ... */ }
    @Override public void walk() { /* ... */ }
    @Override public void fly() { /* No aplica */ }
    @Override public void drill() { /* ... */ } // Este es su propósito
    @Override public void cook() { /* No aplica */ }
    @Override public void serve() { /* No aplica */ }
}
```

**Refactorización:**

Dividimos la interfaz grande en interfaces más pequeñas y específicas basadas en las necesidades de los "clientes" (las clases que implementarán estas interfaces).

```java
interface Movable { // Habilidad de moverse
    void run();
    void walk();
}

interface Flying { // Habilidad de volar
    void fly();
}

interface Drilling { // Habilidad de taladrar
    void drill();
}

interface Cooking { // Habilidad de cocinar
    void cook();
}

interface Serving { // Habilidad de servir
    void serve();
}

// Ahora las clases implementan solo las interfaces que necesitan
class GoodServiceRobot implements Movable, Serving { // Implementa solo lo que usa
    @Override public void run() { /* ... */ }
    @Override public void walk() { /* ... */ }
    @Override public void serve() { /* ... */ }
}

class GoodConstructionRobot implements Movable, Drilling { // Implementa solo lo que usa
    @Override public void run() { /* ... */ }
    @Override public void walk() { /* ... */ }
    @Override public void drill() { /* ... */ }
}
```

#### Beneficios del ISP

1. **Desacoplamiento:** Si cambias la interfaz `Alimentable`, la clase `Robot` no se ve afectada en absoluto. No tiene que recompilarse ni volver a testearse.
2. **Claridad:** Al leer la definición de la clase `Robot`, sabes exactamente qué capacidades tiene sin métodos "basura".
3. **Mantenibilidad:** Evitas los `UnsupportedOperationException` que suelen ser una señal clara de que el diseño está roto.

> **Diferencia clave con SRP:** Mientras que el SRP (Responsabilidad Única) se fija en lo que hace una **clase**, el ISP se fija en lo que expone una **interfaz** hacia el cliente.

## 5. Dependency Inversion Principle (DIP)

**Definición:**

1. Los módulos de **alto nivel** (clases que orquestan otras clases) no deben depender de módulos de **bajo nivel** (clases que realizan operaciones detalladas/utilitarias). Ambos deben depender de **abstracciones** (interfaces o clases abstractas).
2. Las **abstracciones** no deben depender de los **detalles** (implementaciones concretas). Los **detalles** deben depender de las **abstracciones**.

En la práctica, esto significa que tus clases deben depender de `interfaces` o `clases abstractas` en lugar de clases concretas.

**¿Por qué es importante?**

Las dependencias directas a clases concretas hacen que el código sea rígido y difícil de cambiar. Si un módulo de alto nivel depende directamente de un módulo de bajo nivel concreto, cambiar el módulo de bajo nivel requiere modificar el módulo de alto nivel. DIP, combinado con DI, invierte esta dependencia: el módulo de alto nivel define la abstracción que necesita, y las implementaciones concretas de bajo nivel se ajustan a esa abstracción.

**Ejemplo de Violación:**

Imagina una clase `OrderProcessor` (módulo de alto nivel) que necesita enviar notificaciones usando una implementación concreta de un servicio de email (`EmailNotificationService`, módulo de bajo nivel).

```java
// Módulo de bajo nivel concreto
public class EmailNotificationService {
    public void sendEmail(String recipient, String message) {
        System.out.println("Enviando email a " + recipient + ": " + message);
    }
}

// Módulo de alto nivel que depende DIRECTAMENTE del módulo de bajo nivel concreto
public class BadOrderProcessor {
    private EmailNotificationService notificationService = new EmailNotificationService(); // Dependencia directa a la implementación concreta

    public void processOrder(String customer, String orderDetails) {
        // ... lógica de procesamiento de orden ...
        notificationService.sendEmail(customer, "Your order " + orderDetails + " has been processed.");
    }
}

```

Si quieres cambiar a `SMSNotificationService`, debes modificar `BadOrderProcessor`

**Refactorización:**

Introducimos una abstracción (interfaz `NotificationService`) de la que ambos módulos dependerán.

```java
public interface NotificationService {
    void send(String recipient, String message);
}

// Módulos de bajo nivel concretos que implementan la abstracción (dependen de ella)
public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Enviando email a " + recipient + ": " + message);
    }
}

public class SmsNotificationService implements NotificationService {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Enviando SMS a " + recipient + ": " + message);
    }
}

// Módulo de alto nivel que depende de la abstracción (NotificationService)
public class GoodOrderProcessor {
    private final NotificationService notificationService;

    public GoodOrderProcessor(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void processOrder(String customer, String orderDetails) {
        // ... lógica de procesamiento de orden ...
        notificationService.send(customer, "Your order " + orderDetails + " has been processed."); // Llama al método de la abstracción
    }
}
```

Ahora puedes inyectar `EmailNotificationService` o `SmsNotificationService` sin modificar `GoodOrderProcessor`

Al hacer esta implementacion surge un inconveniente y es que dos o mas clases implementan la misma interfaz, al momento de inyectar la dependencia de esta interfaz en Otra clase o componente de alto nivel, el compilador no logra identificar que implementacion concreta se utilizara de todas las que tiene la interfaz, es por ello que surge lo siguiente:

Esta es la duda más común cuando se empieza con Spring Boot. Si tienes dos clases (`EmailService` y `SMSService`) que implementan la misma interfaz, Spring entrará en pánico porque no sabrá cuál elegir, lanzando una excepción de tipo `NoUniqueBeanDefinitionException`.

Para identificar o decidir cuál se está llamando, tienes varias herramientas:

---

### 1. Desambiguación (Cómo decides tú)

Como desarrollador, debes darle una pista a Spring para que sepa qué "pieza" encajar en el constructor.

* **Usando `@Primary`:** Si pones esta anotación sobre `EmailService`, Spring siempre elegirá esa por defecto cuando alguien pida un `ServicioMensajeria`.
* **Usando `@Qualifier`:** Es la forma más explícita. Le pones un "nombre" a la implementación.

```java
// En la implementación
@Service
@Qualifier("email")
public class EmailService implements ServicioMensajeria { ... }

// En el Notificador
@Autowired
public Notificador(@Qualifier("email") ServicioMensajeria servicio) {
    this.servicio = servicio;
}

```

Aquí ya no hay duda: sabes que es el de Email porque lo has pedido por su "nombre".

---

### 2. Identificación en tiempo de ejecución (Logs)

Si heredas un proyecto y no sabes qué se está inyectando, la forma más rápida es imprimir la clase en el constructor o usar un log:

```java
@Autowired
public Notificador(ServicioMensajeria servicio) {
    this.servicio = servicio;
    // Esto imprimirá el nombre real de la clase (EmailService o SMSService)
    System.out.println("Servicio inyectado: " + servicio.getClass().getSimpleName());
}

```

---

### 3. La "Magia" del IDE (IntelliJ IDEA)

Si usas IntelliJ IDEA (versión Ultimate es mejor para esto, pero la Community también ayuda), el IDE te pone iconos en el margen izquierdo:

* Verás un icono de una **hoja verde** o un **frijol (bean)** al lado del constructor.
* Al hacer clic, el IDE te mostrará una lista de los Beans que coinciden.
* Si solo hay uno activo, te llevará directamente a la implementación que Spring está usando.

---

### 4. Uso de Perfiles (`@Profile`)

A veces, la implementación depende de **dónde** estás ejecutando el código. Es muy común usar esto para no gastar dinero enviando SMS reales en desarrollo:

```java
@Service
@Profile("prod") // Solo se activa si el perfil es 'prod'
public class SMSService implements ServicioMensajeria { ... }

@Service
@Profile("dev") // Se activa en desarrollo
public class MockMensajeria implements ServicioMensajeria { ... }

```

En este caso, para saber cuál se está llamando, solo tienes que mirar tu archivo `application.properties` y ver qué perfil tienes activo: `spring.profiles.active=dev`.

---

### En resumen

Para identificarlo:

1. **Mira las anotaciones:** Busca `@Primary` o `@Qualifier`.
2. **Mira los Perfiles:** Revisa qué implementación está activa según el entorno.
3. **Usa el Debugger:** Pon un punto de interrupción y observa el nombre de la instancia.


## Aplicación Práctica de SOLID en Spring Boot

Los principios SOLID encajan perfectamente con la arquitectura por capas de Spring Boot (Controlador -> Servicio -> Repositorio) y el uso intensivo de la Inyección de Dependencias:

- **SRP**: La separación natural en Controladores (web), Servicios (negocio) y Repositorios (datos) es una aplicación directa del SRP. Cada capa tiene una responsabilidad principal.
- **OCP y DIP**: El uso común de interfaces para Servicios y Repositorios, combinado con `@Autowired`, permite que tus clases de alto nivel (Controlador -> Servicio; Servicio -> Repositorio) dependan de abstracciones en lugar de implementaciones concretas. Esto hace que sea fácil cambiar la implementación subyacente (ej. cambiar de una lista en memoria a una base de datos, o cambiar la lógica de negocio específica en un servicio) sin modificar las clases que la utilizan.
- **LSP**: Al implementar interfaces de servicio o repositorio (como `JpaRepository`), debes asegurarte de que tus implementaciones cumplen el contrato definido en la interfaz, respetando LSP.
- **ISP**: Las interfaces de repositorio en Spring Data JPA (como `CrudRepository` o `JpaRepository`) son generalmente específicas para una entidad, siguiendo el ISP. Puedes crear tus propias interfaces de servicio más pequeñas si una se vuelve demasiado grande, aplicando ISP.

### Al diseñar tu aplicación Spring Boot, piensa en SOLID desde el principio

1. Identifica las responsabilidades principales y crea clases (`@Controller`, `@Service`, `@Repository`) para cada una (SRP).
2. Define interfaces para tus servicios y repositorios para permitir la extensión y aplicar DIP.
3. Usa `@Autowired` o el uso de constructores para inyectar estas interfaces en lugar de implementaciones concretas (DIP).
4. Asegúrate de que tus implementaciones cumplen los contratos de las interfaces (LSP).
5. Si una interfaz de servicio se vuelve demasiado grande, considera dividirla (ISP).

## Conclusión

Los principios SOLID son una brújula para navegar la complejidad del desarrollo de software.

No son reglas rígidas, sino guías para tomar mejores decisiones de diseño que resulten en código más robusto y flexible.

Aplicarlos requiere práctica y pensamiento crítico, pero el esfuerzo vale la pena a largo plazo.

---

¡Ahora, **aplica estos principios** en tu próximo ejercicio práctico!






































