
# 📘 **Documentación Completa sobre Expresiones Lambda en Java**

## 🧩 1. ¿Qué son las expresiones Lambda en Java?

Las **expresiones lambda** fueron introducidas en **Java 8 (2014)** y representan una forma **concisa y funcional** de escribir código.
Permiten **definir métodos anónimos** (sin nombre) y tratarlos como **valores**, lo que hace que el código sea más **legible, expresivo y corto**.

En esencia, una **expresión lambda** es una **función que se puede pasar como argumento o almacenar en una variable**.

---

## 🧠 2. ¿Por qué existen las expresiones Lambda?

Antes de Java 8, las interfaces funcionales (interfaces con un solo método abstracto) se implementaban mediante **clases anónimas**, lo cual era **verboso** y **difícil de leer**.

Por ejemplo, antes de Java 8:

```java
List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro");

Collections.sort(nombres, new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
});
```

Con una **expresión lambda**, el mismo código se simplifica enormemente:

```java
List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro");

Collections.sort(nombres, (a, b) -> a.compareTo(b));
```

---

## 🧱 3. Estructura de una expresión Lambda

### Sintaxis general:

```java
(parámetros) -> expresión
```

o

```java
(parámetros) -> { bloque de código }
```

### Ejemplos:

| Forma                | Ejemplo                                        | Descripción                               |
| -------------------- | ---------------------------------------------- | ----------------------------------------- |
| Lambda simple        | `(x) -> x * 2`                                 | Devuelve el doble de `x`.                 |
| Múltiples parámetros | `(x, y) -> x + y`                              | Suma `x` e `y`.                           |
| Sin parámetros       | `() -> System.out.println("Hola")`             | No recibe parámetros, imprime un mensaje. |
| Bloque de código     | `(x, y) -> { int suma = x + y; return suma; }` | Contiene varias líneas de código.         |

---

## 🔩 4. Tipos funcionales compatibles

Las lambdas **siempre** se asocian con una **interfaz funcional**, es decir, una interfaz que tiene **exactamente un método abstracto**.

Ejemplo de interfaz funcional:

```java
@FunctionalInterface
interface Operacion {
    int ejecutar(int a, int b);
}
```

Uso con lambda:

```java
Operacion suma = (a, b) -> a + b;
System.out.println(suma.ejecutar(5, 3)); // 8
```

El compilador infiere automáticamente que `(a, b)` corresponde al método `ejecutar(int a, int b)`.

---

## 🧰 5. Interfaces funcionales más comunes en Java (paquete `java.util.function`)

| Interfaz              | Parámetros | Retorno | Ejemplo de uso                 |
| --------------------- | ---------- | ------- | ------------------------------ |
| **Predicate<T>**      | T          | boolean | `(x) -> x > 0`                 |
| **Function<T, R>**    | T          | R       | `(x) -> x.toString()`          |
| **Consumer<T>**       | T          | void    | `(x) -> System.out.println(x)` |
| **Supplier<T>**       | —          | T       | `() -> Math.random()`          |
| **UnaryOperator<T>**  | T          | T       | `(x) -> x * x`                 |
| **BinaryOperator<T>** | (T, T)     | T       | `(a, b) -> a + b`              |

Ejemplo práctico:

```java
Predicate<Integer> esPar = x -> x % 2 == 0;
System.out.println(esPar.test(4)); // true
```

---

## ⚙️ 6. Uso de lambdas con Streams (una de sus aplicaciones más comunes)

Las **lambdas** se usan masivamente con la API de **Streams** introducida también en Java 8.

Ejemplo práctico:

```java
List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "Sofía");

nombres.stream()
       .filter(n -> n.startsWith("P"))
       .map(String::toUpperCase)
       .forEach(System.out::println);
```

Salida:

```
PEDRO
```

---

## 🧩 7. Referencias a métodos y constructores

Las referencias a métodos son una forma más simplificada de escribir expresiones lambda cuando la lógica de la función puede delegarse directamente a un método existente. Se utilizan con la sintaxis `Class::method`.
Las lambdas también permiten usar **referencias a métodos o constructores** como una forma aún más compacta.

### Formas:

| Tipo                                                   | Sintaxis                 | Ejemplo               |
| ------------------------------------------------------ | ------------------------ | --------------------- |
| Referencia a método estático                           | `Clase::métodoEstatico`  | `Math::max`           |
| Referencia a método de instancia                       | `objeto::método`         | `System.out::println` |
| Referencia a método de instancia de un tipo arbitrario | `Clase::métodoInstancia` | `String::toUpperCase` |
| Referencia a constructor                               | `Clase::new`             | `ArrayList::new`      |

Ejemplo:

```java
List<String> nombres = Arrays.asList("Ana", "Pedro", "Juan");
nombres.forEach(System.out::println);
```

---

## 🧩 8. Inferencia de tipos

El compilador **infere automáticamente** los tipos de los parámetros, por lo que no siempre es necesario especificarlos:

```java
// Con tipo explícito
(int x, int y) -> x + y

// Con inferencia de tipo
(x, y) -> x + y
```

---

## 🧩 9. Captura de variables (Closures)

Las lambdas pueden **usar variables externas**, pero estas deben ser **efectivamente finales** (no modificarse después de su uso).

Ejemplo:

```java
int factor = 2;
Function<Integer, Integer> multiplicar = x -> x * factor;
System.out.println(multiplicar.apply(5)); // 10
```

> ⚠️ Si intentas cambiar `factor` después, el compilador mostrará un error.

---

## 🧠 10. Ventajas de las expresiones Lambda

* Código más **limpio y conciso**.
* **Facilitan la programación funcional** en Java.
* **Integración natural con Streams** y APIs modernas.
* Menos clases anónimas innecesarias.
* Mejor **legibilidad y mantenibilidad** del código.

---

## ⚠️ 11. Limitaciones y consideraciones

* No se puede acceder a variables **no finales** externas.
* No se puede sobrecargar una lambda directamente (solo a través de interfaces funcionales distintas).
* Dificultad de depuración en casos complejos.
* El código puede ser menos claro para principiantes.

---

## 🧾 12. Ejemplo completo

```java
import java.util.*;
import java.util.function.*;

public class EjemploLambda {
    public static void main(String[] args) {
        // Ejemplo 1: Function
        Function<Integer, Integer> cuadrado = x -> x * x;
        System.out.println(cuadrado.apply(4)); // 16

        // Ejemplo 2: Predicate
        Predicate<String> empiezaConA = s -> s.startsWith("A");
        System.out.println(empiezaConA.test("Ana")); // true

        // Ejemplo 3: Consumer
        Consumer<String> imprimir = System.out::println;
        imprimir.accept("Hola desde una lambda!");

        // Ejemplo 4: Stream + Lambda
        List<String> nombres = Arrays.asList("Ana", "Pedro", "Juan", "Sofía");
        nombres.stream()
               .filter(n -> n.length() > 3)
               .map(String::toUpperCase)
               .forEach(System.out::println);
    }
}
```

---

## 🧮 13. Cómo se ven en tiempo de ejecución

En tiempo de ejecución, las lambdas no crean clases anónimas como antes; en su lugar, el compilador usa una técnica llamada **invokedynamic**, que genera de forma eficiente la implementación del método funcional.

Esto mejora el **rendimiento** y reduce la **carga de memoria**.

---

## 🧭 14. Cuándo usar lambdas

Usa expresiones lambda cuando:

* Implementas **interfaces funcionales** (como `Runnable`, `Comparator`, `Consumer`, etc.).
* Trabajas con **Streams** o **colecciones**.
* Necesitas **callbacks** o **acciones simples**.
* Quieres **evitar clases anónimas verbosas**.

Evita lambdas cuando:

* El código dentro de la lambda sea muy extenso o complejo (usa una clase separada mejor).
* Se necesite manejar múltiples métodos (ya no sería una interfaz funcional).
