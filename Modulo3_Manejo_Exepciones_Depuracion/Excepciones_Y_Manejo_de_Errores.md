# Manejo de errores y excepciones en Java
Los términos error y excepción se utilizan para manejar problemas que ocurren durante la ejecución de un programa, pero tienen significados y usos diferentes.

![Errores y Excepciones en Java](https://jarroba.com/wp-content/uploads/2014/10/Exception_jarroba_diagramClass.png)

---

## ¿Qué entendemos como errores?

Son _**problemas graves**_ que generalmente indican que la JVM (Java Virtual Machine) **no puede continuar ejecutando el programa**. Estos problemas suelen ser externos al control del programa y es poco probable que se puedan recuperar de ellos.

### Características principales
- No se espera que sean manejados o capturados.
- Son condiciones críticas, como problemas de memoria (`OutOfMemoryError`), fallos en el hardware, errores de inicialización del sistema, entre otros.
- Derivan de la clase `Error`, que es una subclase de `Throwable`.

---

## ¿Qué entendemos como excepciones?

Son **condiciones anómalas** que ocurren durante la ejecución de un programa y que un programa **puede manejar** y de las cuales **puede recuperarse**.

Las excepciones en Java son eventos anormales que ocurren durante la ejecución o compilacion de un programa y que interrumpen el flujo normal de instrucciones. Se utilizan para manejar errores o condiciones inesperadas de forma controlada, evitando que el programa se detenga abruptamente.

### Características principales:
- Se espera que se manejen usando bloques `try-catch`.
- Pueden ser **verificadas** (checked) o **no verificadas** (unchecked).
- Derivan de la clase `Exception`, que también es una subclase de `Throwable`.

![Jerarquía de Excepciones](https://cdn.javarush.com/images/article/f23906db-7512-4047-985d-8ddb6bbc99c9/1024.webp)

### Excepciones Verificadas (_Checked_)
- Son excepciones que deben ser declaradas en el método que las puede lanzar o deben ser capturadas.
- Ejemplo: `IOException`, `SQLException`.

### Excepciones No Verificadas (_Unchecked_)
- Son excepciones que no requieren ser declaradas ni capturadas obligatoriamente.
- Ejemplo: `NullPointerException`, `ArrayIndexOutOfBoundsException`.

![Tipos de excepciones](https://cdn.javarush.com/images/article/2e4a84d4-3d29-41a2-b6f9-32ae87e9ee96/1024.webp)

---

## Por qué es importante el manejo de excepciones en el desarrollo profesional

- Al manejar excepciones, los desarrolladores pueden crear aplicaciones más robustas que no se bloqueen ante eventos inesperados.
- Facilita el mantenimiento del código, ya que permite identificar y manejar errores de manera organizada.
- Ayuda a prevenir problemas de seguridad al manejar adecuadamente las excepciones y evitar que se propaguen.
- Mejora la experiencia del usuario al proporcionar mensajes de error claros y evitar bloqueos inesperados.

```java
public static void main(String arg[]) {
    int [] array = new int[20];
    array[-3] = 24;
}
```

---

## Lanzar excepciones

Puedes lanzar excepciones de manera explícita utilizando la palabra clave `throw`. Esto es útil cuando deseas indicar que ha ocurrido una condición excepcional en tu código que debe ser manejada por otro bloque de código.

```java
throw new Exception("Mensaje de la excepción");
```

-----

## Estructura `try-catch`

La estructura `try-catch` se utiliza para capturar y manejar excepciones que pueden ocurrir durante la ejecución de un bloque de código.
La sintaxis básica es:

1.  **Bloque `try`**: Contiene el código que podría lanzar una excepción.
2.  **Bloque `catch`**: Captura y maneja la excepción lanzada. Puedes tener múltiples bloques catch para manejar diferentes tipos de excepciones.

<!-- end list -->

```java
try {
    // Código que puede generar una excepción
} catch (TipoDeExcepcion e) {
    // Código para manejar la excepción
}
```

### **Ejemplo**

```java
try {
    var array = new int[5];
    array[10] = 1;
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Índice fuera de los límites del array!");
}
```

### Bloque `finally`

El bloque `finally` es *opcional* y se utiliza para ejecutar código que debe ejecutarse siempre, independientemente de si se produjo una excepción o no.
Es especialmente útil para liberar recursos como conexiones de base de datos o archivos abiertos.

```java
try {
    // Código que puede generar una excepción
} catch (TipoDeExcepcion e) {
    // Código para manejar la excepción
} finally {
    // Código que se ejecuta siempre
}
```
-----

## Estructura `try-with-resources`

El concepto de **`try-with-resources`** en Java es una característica muy útil introducida en Java 7 que facilita la gestión de recursos, como archivos, conexiones de base de datos y otros tipos de flujos de entrada/salida (E/S).

Permite declarar uno o más recursos que se cerrarán automáticamente al finalizar el bloque `try`. Un **recurso** es cualquier objeto que implemente la interfaz `java.lang.AutoCloseable`, lo cual incluye la interfaz `java.io.Closeable`.

```java
try (var recurso = new Recurso()) {
    // Código que utiliza el recurso
} catch (TipoDeExcepcion e) {
    // Manejo de excepciones
}
```

```java
try (var r1 = new Recurso1();
     var r2 = new Recurso2()) {
    // Código que utiliza r1 y r2
} catch (TipoDeExcepcion e) {
    // Manejo de excepciones
}
```

-----

### Ventajas de `try-with-resources`

  * **Gestión Automática de Recursos**: Los recursos se cierran automáticamente al final del bloque `try`, sin importar si se produjo una excepción o no.
  * **Código Más Limpio y Legible**: Elimina la necesidad de escribir bloques `finally` para cerrar recursos manualmente.
  * **Reducción de Errores**: Minimiza el riesgo de fugas de recursos (como archivos abiertos o conexiones de base de datos).

El uso de `try-with-resources` simplifica la gestión de recursos y garantiza que sean cerrados de manera adecuada, mejorando la legibilidad y robustez del código. Esta característica es especialmente útil para trabajar con flujos de E/S y otros recursos que necesitan ser cerrados explícitamente.

-----

#### Ejemplo

```java
import java.util.Scanner;

public class EjemploTryWithResourcesScanner {
    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            System.out.print("Por favor, introduce tu nombre: ");
            var nombre = scanner.nextLine();
            System.out.print("Por favor, introduce tu edad: ");
            var edad = scanner.nextInt();
            System.out.printf("Hola, %s. Tienes %d años.%n", nombre, edad);
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
```
-----

## Distintas opciones de manejo de excepciones en el bloque `catch`

El bloque **`catch`** en Java es extremadamente flexible y permite manejar las excepciones de varias maneras.

### 1\. Capturar una excepción específica

```java
try {
    // Código que puede generar una excepción
} catch (IOException e) {
    // Manejo específico para IOException
}
```

### 2\. Capturar múltiples excepciones independientes

```java
try {
    // Código que puede generar múltiples excepciones
} catch (ArrayIndexOutOfBoundsException e) {
    // Manejo específico para ArrayIndexOutOfBoundsException
} catch (NumberFormatException e) {
    // Manejo específico para NumberFormatException
}
```

### 3\. Capturar múltiples excepciones

Desde Java 7, puedes capturar múltiples excepciones en un solo bloque `catch` usando el operador de tubería o pipe (`|`).

```java
try {
    // Código que puede generar varias excepciones
} catch (IOException | SQLException e) {
    // Manejo común para IOException y SQLException
}
```

### 4\. Capturar subclases de excepciones

```java
try {
    // Código que puede generar una excepción
} catch (Exception e) {
    // Manejo para cualquier excepción que derive de Exception
}
```

### 5\. Capturar y proveer una respuesta alternativa

```java
try { 
    int resultado = Integer.parseInt("abc"); // Esto genera NumberFormatException
} catch (NumberFormatException e) {
    int resultado = 0; // Valor por defecto
    System.out.println("Valor no válido, usando 0 como predeterminado.");
}
```

### 6\. Capturar y encadenar excepciones

```java
try {
    // Código que puede generar una excepción
} catch (IOException e) {
    throw new RuntimeException("Error al procesar archivo", e);
}
```

### 7\. Re-lanzar la misma excepción

```java
try {
    // Código que puede generar una excepción
} catch (IOException e) {
    System.out.println("Ocurrió un error, re-lanzando excepción."); 
    throw e; 
}
```

### 8\. Capturar y mostrar los detalles de la excepción

El método **`printStackTrace()`** pertenece a la clase `Throwable`, que es la superclase de todas las excepciones y errores en Java. Este método se utiliza para imprimir la *traza de la pila* (stack trace) de la excepción en el flujo de error estándar.

El propósito principal de `printStackTrace()` es proporcionar una representación detallada del estado del programa en el momento en que se lanzó la excepción, mostrando la secuencia exacta de llamadas de método que llevaron a la excepción. Esto es muy útil para depurar y localizar el origen del problema.

```java
try {
    // Código que puede generar una excepción
} catch (IOException e) {
    e.printStackTrace();
}
```

La salida de `printStackTrace()` podría verse algo así:

```java
java.lang.Exception: Esta es una excepción de ejemplo
    at EjemploPrintStackTrace.metodoQueLanzaExcepcion(EjemploPrintStackTrace.java:10)
    at EjemploPrintStackTrace.main(EjemploPrintStackTrace.java:5)
```

  * **Tipo de Excepción**: `java.lang.Exception`
  * **Mensaje**: "Esta es una excepción de ejemplo"
  * **Pila de Llamadas**: Lista de llamadas de método que llevaron a la excepción, incluyendo los nombres de los métodos, el nombre del archivo fuente y el número de línea.

-----

# Creación de excepciones personalizadas

Es una práctica útil cuando necesitas manejar situaciones específicas que no están cubiertas por las excepciones estándar de Java.

### ¿Por qué crear nuevas excepciones?

  * **Claridad**: Permite describir con mayor precisión el tipo de error que ha ocurrido.
  * **Mantenimiento**: Facilita el mantenimiento del código al proporcionar excepciones específicas para diferentes condiciones de error.
  * **Reutilización**: Las excepciones personalizadas pueden reutilizarse en diferentes partes de la aplicación, lo que mejora la consistencia.

-----

## Pasos para crear excepciones personalizadas

### **1\. Definir la clase de excepción personalizada**

Para crear una excepción personalizada, debes definir una nueva clase que extienda **`Exception`** o **`RuntimeException`**, dependiendo de si deseas que la excepción sea verificada (checked) o no verificada (unchecked).

**Ejemplo de excepción verificada (*checked*)**

```java
public class MiExcepcionVerificada extends Exception {
    // Constructor con un mensaje de error
    public MiExcepcionVerificada(String mensaje) {
        super(mensaje);
    }
    
    // Constructor con un mensaje de error y una causa
    public MiExcepcionVerificada(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
```

**Ejemplo de excepción no verificada (*unchecked*)**

```java
public class MiExcepcionNoVerificada extends RuntimeException {
    // Constructor con un mensaje de error
    public MiExcepcionNoVerificada(String mensaje) {
        super(mensaje);
    }
    
    // Constructor con un mensaje de error y una causa
    public MiExcepcionNoVerificada(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
```

### **2\. Lanzar la excepción personalizada**

Para lanzar tu excepción personalizada, utiliza la palabra clave **`throw`** en el código donde desees que se genere la excepción.

```java
public class VerificadorDeEdad {
    public static void verificarEdad(int edad) throws MiExcepcionVerificada {
        if (edad < 18) {
            throw new MiExcepcionVerificada("La edad debe ser mayor o igual a 18.");
        }
    }

    public static void main(String[] args) {
        try {
            verificarEdad(16); // Esto lanzará MiExcepcionVerificada
        } catch (MiExcepcionVerificada e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### **3\. Capturar y manejar la excepción**

```java
public class EjemploManejo {
    public static void main(String[] args) {
        try {
            metodoQueLanzaExcepcionPersonalizada();
        } catch (MiExcepcionVerificada e) {
            System.out.println("Capturada MiExcepcionVerificada: " + e.getMessage());
        }
    }

    public static void metodoQueLanzaExcepcionPersonalizada() throws MiExcepcionVerificada {
        throw new MiExcepcionVerificada("Ocurrió un error personalizado.");
    }
}
```

-----

## Manejo de excepciones verificadas (*checked*)

Manejar excepciones verificadas en los métodos de Java tiene varias implicaciones que afectan tanto la estructura del código como la forma en que se diseñan y manejan los errores.

### 1\. Declaración obligatoria de excepciones

Cuando un método puede lanzar una excepción verificada, debe declarar esta posibilidad en su firma utilizando la cláusula **`throws`**. Esto obliga a los desarrolladores a pensar y documentar qué excepciones pueden ocurrir, lo que puede mejorar la claridad y la documentación del código.

```java
public void leerArchivo(String nombreArchivo) throws IOException {
    // Código que puede lanzar IOException
}
```

### 2\. Manejo obligatorio de excepciones

Los métodos que llaman a otros métodos que lanzan excepciones verificadas deben manejar estas excepciones, ya sea capturándolas con bloques `try-catch` o declarando que también las lanzan. Esto garantiza que las excepciones no se pasen por alto y que se manejen de manera adecuada en algún nivel del código.

En java, la declaracion de excepciones en la firma de un metodo (es decir, usando throws) solo es obligatoria para las excepciones verificadas *(checked exceptions)*, no para las no verificadas.

#### ¿Puedo declarar una excepción no verificada en la firma de un metodo?

Si, puedes, pero no es obligatorio. es util si quieres documentar que un metodo puede lanzar una excepcion.

```java
public void procesarArchivo(String nombreArchivo) {
    try {
        leerArchivo(nombreArchivo); // Manejo con try-catch
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

```java
public void procesarArchivo(String nombreArchivo) throws IOException {
    leerArchivo(nombreArchivo); // Propagación de la excepción
}
```

### 3\. Propagación de excepciones

Si un método no puede manejar una excepción, puede propagarla declarando la excepción en su propia firma utilizando **`throws`**. Facilita la delegación del manejo de excepciones a niveles superiores de la aplicación, permitiendo que los métodos de nivel superior decidan cómo manejar las excepciones.

```java
public void procesarArchivo(String nombreArchivo) throws IOException {
    leerArchivo(nombreArchivo);
}
```

### 4\. Complejidad del código

El manejo de excepciones verificadas puede aumentar la complejidad del código, ya que los desarrolladores deben asegurarse de capturar o propagar todas las excepciones verificadas. Esto puede resultar en bloques de código más extensos y complicados. Si bien esto puede parecer una carga adicional, también puede conducir a un código más robusto y manejable a largo plazo.

```java
public void procesarArchivo(String nombreArchivo) {
    try {
        leerArchivo(nombreArchivo);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

### 5\. Documentación y claridad

Las excepciones verificadas obligan a los desarrolladores a documentar explícitamente qué excepciones pueden ser lanzadas por un método, mejorando la claridad y la documentación del código. Esto proporciona a los usuarios del método una mejor comprensión de los posibles problemas que pueden enfrentar y cómo manejarlos.

```java
/**
 * Lee el contenido de un archivo.
 * @param nombreArchivo El nombre del archivo a leer.
 * @throws IOException Si ocurre un error de E/S.
 */
public void leerArchivo(String nombreArchivo) throws IOException {
    // Código que puede lanzar IOException
}
```

-----

## Buenas prácticas para manejar excepciones

1.  **Evita capturar `Exception` o `Throwable` directamente**: En lugar de capturar todas las excepciones generales, captura excepciones específicas que esperas que ocurran.
2.  **Usa mensajes claros y descriptivos**: Asegúrate de que los mensajes de error proporcionen suficiente información para diagnosticar el problema.
    ```java
    throw new IllegalArgumentException("La cantidad no puede ser negativa: ");
    ```
3.  **Evita capturar y silenciar excepciones**: Capturar una excepción y no hacer nada con ella puede dificultar la depuración.
    ```java
    try {
        // Código que puede lanzar una Exception
    } catch (Exception e) { }
    ```
4.  **Libera recursos en el bloque `finally`**: Utiliza el bloque `finally` para asegurarte de que los recursos se liberen correctamente, incluso si ocurre una excepción.
5.  **Utiliza `try-with-resources` para cerrar automáticamente los recursos**: Esto es especialmente útil para manejar flujos de E/S y otros recursos que deben cerrarse.
6.  **Documenta las excepciones lanzadas por tus métodos**: Utiliza JavaDoc para describir qué excepciones puede lanzar un método y en qué condiciones.
    ```java
    /**
     * Lee el contenido de un archivo.
     * @param nombreArchivo El nombre del archivo a leer.
     * @throws IOException Si ocurre un error de E/S.
     */
    public void leerArchivo(String nombreArchivo) throws IOException {
        // Implementación
    }
    ```
7.  **No utilices excepciones para control de flujo**: Las excepciones deben usarse para situaciones excepcionales, no para el flujo normal del programa.
    ```java
    try {
        // Código que siempre lanza una excepción para el control de flujo
    } catch (SpecificException e) {
        // Manejo de flujo normal
    }
    ```
8.  **Captura excepciones y lanza otras más descriptivas**: Si necesitas lanzar una excepción diferente, captura la original y lánzala como causa.
    ```java
    try {
        // Código que puede lanzar una excepción
    } catch (IOException e) {
        throw new CustomException("Error al procesar el archivo", e);
    }
    ```
9.  **Establece y sigue un esquema claro para manejar excepciones**: Define una política de manejo de excepciones y asegúrate de que todos los desarrolladores del proyecto la sigan.
10. **Revisa y refactoriza regularmente el código para mejorar el manejo de excepciones**: Asegúrate de que el manejo de excepciones sea eficiente y adecuado para el contexto del proyecto.

# Propagación de Excepciones
---

## 🔹 1. ¿Qué es la propagación de excepciones?

Cuando en Java ocurre una excepción (por ejemplo, `NullPointerException`, `IOException`, etc.), se crea un **objeto de excepción** y se "lanza" (`throw`).
A partir de ese momento, Java busca **un bloque `catch` adecuado** en la pila de llamadas de métodos (**call stack**).

* Si el método actual tiene un `try-catch` que la maneje → se captura ahí.
* Si no la maneja → la excepción **se propaga hacia el método que lo llamó**.
* Si sigue sin ser manejada → continúa subiendo hasta el **main()**.
* Si llega al `main()` y no se maneja → el programa termina con un **error en tiempo de ejecución** y se imprime el stack trace.

---

## 🔹 2. Excepciones verificadas (checked exceptions)

Ejemplos: `IOException`, `SQLException`.

* El **compilador obliga** a que sean manejadas con `try-catch` o declaradas con `throws` en la firma del método.
* Si un método puede lanzar una excepción verificada y no la manejas ni la declaras → error de compilación.

📌 Ejemplo:

```java
import java.io.*;

public class EjemploChecked {
    public static void metodoA() throws IOException {
        metodoB();
    }

    public static void metodoB() throws IOException {
        throw new IOException("Error de IO");
    }

    public static void main(String[] args) {
        try {
            metodoA(); // Obligatorio manejarla o declararla
        } catch (IOException e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
    }
}
```

➡ Aquí la excepción **se propaga de `metodoB()` → `metodoA()` → `main()`** hasta que es capturada.

---

## 🔹 3. Excepciones no verificadas (unchecked exceptions)

Ejemplos: `NullPointerException`, `ArithmeticException`.

* **Heredan de `RuntimeException`.**
* El compilador **NO obliga** a manejarlas ni a declararlas con `throws`.
* Si no se manejan, se propagan igual que las checked, pero sin generar error de compilación.

📌 Ejemplo:

```java
public class EjemploUnchecked {
    public static void metodoA() {
        metodoB();
    }

    public static void metodoB() {
        int x = 10 / 0; // Lanza ArithmeticException
    }

    public static void main(String[] args) {
        try {
            metodoA(); // No obliga a try-catch, pero si no se maneja el programa se rompe
        } catch (ArithmeticException e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
    }
}
```

➡ La excepción se propaga de **`metodoB()` → `metodoA()` → `main()`**, hasta que es atrapada.

---

## 🔹 4. Diferencias principales en la propagación

| Característica                         | Checked (verificadas)         | Unchecked (no verificadas)                    |
| -------------------------------------- | ----------------------------- | --------------------------------------------- |
| Compilador obliga a manejar o declarar | ✅ Sí                          | ❌ No                                          |
| Propagación en runtime                 | ✅ Igual                       | ✅ Igual                                       |
| Ejemplos                               | `IOException`, `SQLException` | `NullPointerException`, `ArithmeticException` |

------

# 🔹 ¿Qué son las **Suppressed Exceptions** en Java?

Las **suppressed exceptions** (excepciones suprimidas) son excepciones que ocurren **mientras otra excepción ya estaba siendo lanzada**, pero que no pueden propagarse directamente porque ya hay una excepción principal en curso.

Java introdujo este mecanismo a partir de **Java 7**, principalmente con el uso del **try-with-resources**.

Cuando usas `try-with-resources`, los recursos (por ejemplo, archivos, streams, conexiones) se cierran automáticamente al salir del bloque. Si ocurre una excepción dentro del bloque `try` y, al mismo tiempo, otra excepción al cerrar el recurso (en el `close()`), Java no puede lanzar ambas al mismo tiempo.

Entonces:

* La **excepción principal** es la que ocurrió en el `try`.
* La otra (la del `close()`) se **suprime** y se guarda como **suppressed exception** dentro de la principal.

---

## 🔹 ¿Para qué sirven?

* Evitan que se **pierda información** sobre errores secundarios que ocurren al cerrar recursos.
* Permiten al desarrollador **diagnosticar mejor** problemas, ya que una excepción de cierre podría estar ocultando el error real.
* Mantienen el flujo de ejecución **más claro**, mostrando una excepción principal, pero sin descartar las otras.

---

## 🔹 ¿Cómo se usan?

1. **Cuando se lanzan automáticamente (try-with-resources):**

```java
import java.io.*;

public class EjemploSuppressed {
    public static void main(String[] args) {
        try (
            BufferedReader br = new BufferedReader(new FileReader("archivo.txt"));
        ) {
            // Si ocurre un error aquí...
            String linea = br.readLine();
            System.out.println(linea);

        } catch (IOException e) {
            System.out.println("Excepción principal: " + e);

            // Ver todas las excepciones suprimidas
            for (Throwable sup : e.getSuppressed()) {
                System.out.println("Suppressed: " + sup);
            }
        }
    }
}
```

👉 Si ocurre un error al leer y luego otro al cerrar el `BufferedReader`, el segundo aparecerá como **suppressed**.

---

2. **Agregando excepciones suprimidas manualmente:**

Java permite agregar una excepción como suprimida a otra usando:

```java
try {
    throw new Exception("Principal");
} catch (Exception e) {
    Exception extra = new Exception("Suprimida");
    e.addSuppressed(extra);

    e.printStackTrace(); // mostrará ambas
}
```

---

3. **Obteniendo las excepciones suprimidas:**

Se recuperan con:

```java
Throwable[] suprimidas = e.getSuppressed();
```

## 🔹 Ejemplo de **Suppressed Exceptions**
```java
public class Pedido {
    private Inventario inventario;

    public Pedido(Inventario inventario) {
        this.inventario = inventario;
    }

    public void procesarPedido(String rutaArchivo) throws PedidoException {
        // Inicializamos una lista donde se van a guardar las excepciones que ocurran al procesar cada línea del archivo
        // Esto es para no detenerse en el primer error, sino acumularlos y reportarlos todos despues
        List<Exception> errores = new ArrayList<>();

        // try-with-resources para cerrar automáticamente el recurso
        try (BufferedReader br = Files.newBufferedReader(Paths.get(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    // CSV esperado: idProducto,cantidad
                    String[] partes = linea.split(",");
                    int idProducto = Integer.parseInt(partes[0]);
                    int cantidad = Integer.parseInt(partes[1]);

                    Producto producto = inventario.buscarProducto(idProducto);
                    producto.reducirStock(cantidad);

                    System.out.println("Pedido procesado: " + cantidad + " x " + producto.getNombre());

                    //Si ocurre cualquier excepción al procesar esa línea, en lugar de detener el programa, 
                    //se guarda un PedidoException en la lista errores.
                } catch (NumberFormatException e) {
                    errores.add(new PedidoException("Error al convertir datos numéricos en: " + linea, e));
                } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
                    errores.add(new PedidoException("Error en el pedido: " + linea, e) );
                }
            }
        } catch (IOException e) {
            throw new PedidoException("Error al leer archivo de pedidos", e);
        }

        /*
         * Si la lista errores no está vacía, significa que hubo pedidos inválidos.
         * Se crea un PedidoException general con el mensaje "Se encontraron errores al procesar pedidos".
         * Se agregan todas las excepciones individuales como suppressed exceptions (addSuppressed).
         * Finalmente, se lanza este PedidoException
         * 
         ! Suppressed exceptions = excepciones adicionales que acompañan a una excepción principal.
         ! Se usan para no perder información cuando ocurren varios errores.
         ! Se acceden con getSuppressed().
         ? por eso usamos excepciones suprimidas las cuales son las excepciones que puede lanzar la lectura del archivo y esas excepcioens
         ? las guardamos en una excepcion principal que es la que al final se propaga para no perder la informcaion ni el origen de los errores
         ? se tomo este enfoque para que el programa no se pare cuando salte la primer excepcion si no que procese todo el archivo y regrese todos
         ? los posibles errorres encontrados para luego guardarlos en un log y asi dejar trazabilidad de los mismos
         */
        if (!errores.isEmpty()) {
            // puedes lanzar una sola excepción con todas dentro
            PedidoException pe = new PedidoException("Se encontraron errores al procesar pedidos", null);
            for (Exception e : errores) {
                pe.addSuppressed(e); // ✅ añade todas como "suppressed"
            }
            throw pe;
        }
    }
}
```

---

✅ En resumen:
Las **suppressed exceptions** en Java son excepciones adicionales que ocurren durante otra excepción (usualmente en `try-with-resources`). No se pierden, sino que se guardan dentro de la excepción principal, lo que ayuda a depurar y entender mejor los errores.

---


# Lista de Reproduccion donde se explican las excepciones en Java

https://www.youtube.com/watch?v=kGzwPunAOxk&list=PLTd5ehIj0goNuDBQuP5uy8dP-3V3h1m0V

------

# Ejercicio

## Sistema de Gestión de Clientes y Pedidos

Desarrolla un sistema de gestión de clientes y pedidos que permita a los usuarios registrar clientes, crear pedidos y buscar pedidos. El sistema debe manejar adecuadamente las excepciones relacionadas con la gestión de clientes y pedidos utilizando excepciones personalizadas tanto verificadas (checked) como no verificadas (unchecked).

## Requisitos:

1.  Crea una excepción personalizada llamada **`ClienteNoEncontradoException`** que extienda **`RuntimeException`**. Esta excepción debe ser lanzada cuando se intente buscar un cliente que no exista en el sistema.
2.  Crea una excepción personalizada llamada **`PedidoInvalidoException`** que extienda **`Exception`**. Esta excepción debe ser lanzada cuando se intente crear un pedido con información inválida (por ejemplo, cantidad negativa o cliente inexistente).
3.  Crea una clase llamada **`GestionClientes`** que contenga métodos para registrar y buscar clientes.
      * Método **`registrarCliente(String id, String nombre)`** que registre un cliente en el sistema.
      * Método **`buscarCliente(String id)`** que lance `ClienteNoEncontradoException` si el cliente no existe.
4.  Crea una clase llamada **`GestionPedidos`** que contenga métodos para crear y buscar pedidos.
      * Método **`crearPedido(String clienteId, String producto, int cantidad)`** que lance `PedidoInvalidoException` si la información del pedido es inválida.
      * Método **`buscarPedido(String clienteId, String producto)`** que lance `ClienteNoEncontradoException` si el cliente no existe.
5.  Utiliza bloques **`try-catch`** para manejar las excepciones lanzadas por los métodos de `GestionClientes` y `GestionPedidos`. Proporciona mensajes de error adecuados para el usuario.
6.  Crea una clase principal que permita al usuario interactuar con el sistema de gestión de clientes y pedidos, y maneje las excepciones adecuadamente.

-----

## Solución

```java
public class ClienteNoEncontradoException extends RuntimeException {
    public ClienteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
```

```java
public class PedidoInvalidoException extends Exception {
    public PedidoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
```

```java
import java.util.HashMap;
import java.util.Map;

public class GestionClientes {
    private Map<String, String> clientes = new HashMap<>();

    public void registrarCliente(String id, String nombre) {
        clientes.put(id, nombre);
    }

    public String buscarCliente(String id) {
        if (!clientes.containsKey(id)) {
            throw new ClienteNoEncontradoException("El cliente con ID " + id + " no existe.");
        }
        return clientes.get(id);
    }
}
```

```java
import java.util.HashMap;
import java.util.Map;

public class GestionPedidos {
    private Map<String, Map<String, Integer>> pedidos = new HashMap<>();
    private GestionClientes gestionClientes;

    public GestionPedidos(GestionClientes gestionClientes){
        this.gestionClientes = gestionClientes;
    }

    public void crearPedido(String clienteId, String producto, int cantidad) throws PedidoInvalidoException {
        if (cantidad <= 0) {
            throw new PedidoInvalidoException("La cantidad debe ser positiva.");
        }

        try {
            gestionClientes.buscarCliente(clienteId);
        } catch (ClienteNoEncontradoException e) {
            throw new PedidoInvalidoException("El cliente con ID " + clienteId + " no existe.");
        }

        var pedidosCliente = pedidos.get(clienteId);
        if(pedidosCliente == null) {
            pedidosCliente = new HashMap<>();
            pedidos.put(clienteId, pedidosCliente);
        }

        pedidosCliente.put(producto, cantidad);
    }

    public int buscarPedido(String clienteId, String producto) {
        if (!pedidos.containsKey(clienteId)) {
            throw new ClienteNoEncontradoException("El cliente con ID " + clienteId + " no existe.");
        }
        return pedidos.get(clienteId).getOrDefault(producto, 0);
    }
}
```

```java
public class SistemaGestion {
    public static void main(String[] args) {
        var gestionClientes = new GestionClientes();
        var gestionPedidos = new GestionPedidos(gestionClientes);

        try {
            gestionClientes.registrarCliente("C001", "Juan Perez");
            gestionPedidos.crearPedido("C001", "Laptop", 2);
            System.out.println("Cantidad de Laptops para C001: " + gestionPedidos.buscarPedido("C001", "Laptop"));
            gestionPedidos.crearPedido("C002", "Monitor", 1);
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (PedidoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            gestionClientes.buscarCliente("C002");
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

# Ejemplo 2

* Excepciones **verificadas (checked)** y **no verificadas (unchecked)**
* Uso de **try-catch-finally**
* **Propagación de excepciones** con `throws`
* **Excepciones personalizadas**
* **Encadenamiento de excepciones**
* **Envoltorio (wrapping)** de excepciones

Vamos a hacerlo con un ejemplo de un **sistema de biblioteca** 🏛️📚.

---

## Ejemplo Completo

```java
import java.io.IOException;

// Clase de excepción personalizada (checked)
class LibroNoEncontradoException extends Exception {
    public LibroNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public LibroNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa); // encadenamiento de excepciones
    }
}

// Otra excepción personalizada (unchecked)
class OperacionInvalidaException extends RuntimeException {
    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}

// Clase que representa un libro
class Libro {
    private String titulo;

    public Libro(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}

// Clase Biblioteca
class Biblioteca {

    private Libro[] libros = {
        new Libro("El Quijote"),
        new Libro("Cien Años de Soledad"),
        new Libro("Clean Code")
    };

    // Método que lanza una excepción checked
    public Libro buscarLibro(String titulo) throws LibroNoEncontradoException {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        throw new LibroNoEncontradoException("Libro '" + titulo + "' no encontrado");
    }

    // Método que lanza una excepción no verificada (unchecked)
    public void prestarLibro(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new OperacionInvalidaException("El título del libro no puede estar vacío");
        }
        System.out.println("Libro '" + titulo + "' prestado correctamente.");
    }

    // Método que encapsula otra excepción (wrapping)
    public void cargarDatos() throws LibroNoEncontradoException {
        try {
            // Simulamos error de IO
            throw new IOException("Error al leer archivo de datos");
        } catch (IOException e) {
            // Encadenamos la IOException dentro de nuestra excepción personalizada
            throw new LibroNoEncontradoException("No se pudo cargar la lista de libros", e);
        }
    }
}

public class ManejoExcepcionesDemo {
    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        // 1. Manejo de excepciones checked con try-catch-finally
        try {
            Libro libro = biblioteca.buscarLibro("Harry Potter");
            System.out.println("Se encontró el libro: " + libro.getTitulo());
        } catch (LibroNoEncontradoException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Bloque finally ejecutado (siempre se ejecuta).");
        }

        // 2. Excepción no verificada (unchecked)
        try {
            biblioteca.prestarLibro(""); // causa excepción
        } catch (OperacionInvalidaException e) {
            System.err.println("Excepción no verificada: " + e.getMessage());
        }

        // 3. Encadenamiento de excepciones (cause)
        try {
            biblioteca.cargarDatos();
        } catch (LibroNoEncontradoException e) {
            System.err.println("Excepción encadenada: " + e.getMessage());
            System.err.println("Causa original: " + e.getCause());
        }

        // 4. Propagación de excepciones
        try {
            metodoPropagaExcepcion();
        } catch (Exception e) {
            System.err.println("Excepción propagada hasta main: " + e.getMessage());
        }

        System.out.println("Programa finalizado correctamente ✅");
    }

    // Método que propaga excepción
    public static void metodoPropagaExcepcion() throws Exception {
        throw new Exception("Error propagado desde metodoPropagaExcepcion");
    }
}
```

---

## 🔍 Explicación paso a paso

1. **Excepciones personalizadas**

   * `LibroNoEncontradoException` → checked (obliga a manejarla con try-catch o `throws`).
   * `OperacionInvalidaException` → unchecked (extiende `RuntimeException`, no obliga a capturarla).

2. **Checked vs Unchecked**

   * `buscarLibro` lanza una checked.
   * `prestarLibro` lanza una unchecked.

3. **Encadenamiento de excepciones**

   * En `cargarDatos`, se lanza un `IOException`, y lo envolvemos en un `LibroNoEncontradoException` con `throw new LibroNoEncontradoException("...", e);`.

4. **Wrapping (envoltorio)**

   * Mismo caso anterior: atrapamos una excepción y la convertimos en otra más específica para nuestra aplicación.

5. **Propagación**

   * `metodoPropagaExcepcion()` lanza una excepción y no la maneja. Se propaga hasta `main`.

6. **finally**

   * Siempre se ejecuta, incluso si hay excepción.

---

👉 Con este ejemplo se cubre todo el panorama de excepciones en Java.

