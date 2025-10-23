
# 📘 **Interfaces Funcionales en Java**

Una **interfaz funcional** en **Java** es una **interfaz que tiene exactamente un único método abstracto** (aunque puede tener otros métodos *default* o *static*).

Estas interfaces representan **un solo comportamiento** o **una acción**, y son la **base de las expresiones lambda** introducidas en **Java 8**.

---

## 🧠 1. Características principales

| Característica                            | Descripción                                                                                                                |
| ----------------------------------------- | -------------------------------------------------------------------------------------------------------------------------- |
| **Un único método abstracto**             | Es el requisito principal. Solo puede haber **uno**, aunque puede tener métodos `default`, `static` o `private`.           |
| **Anotación `@FunctionalInterface`**      | Es opcional, pero **recomendada**. Garantiza que la interfaz cumpla con la restricción de tener un único método abstracto. |
| **Compatibilidad con expresiones lambda** | Las interfaces funcionales permiten pasar **funciones como parámetros**, haciendo el código más conciso.                   |
| **Soporte desde Java 8**                  | Fueron introducidas junto con las **lambda expressions** y la **API de Streams**.                                          |

---

## 🧱 2. Estructura básica

```java
@FunctionalInterface
public interface Operacion {
    int ejecutar(int a, int b);
}
```

### 🔹 Explicación:

* `@FunctionalInterface` → indica que la interfaz está diseñada para tener un solo método abstracto.
* `ejecutar()` → el método abstracto que debe implementarse.
* Esta interfaz puede implementarse usando **una clase anónima**, **una lambda**, o una **referencia a método**.

---

## ⚙️ 3. Ejemplo básico de uso

### ✅ Sin lambda (forma tradicional):

```java
Operacion suma = new Operacion() {
    @Override
    public int ejecutar(int a, int b) {
        return a + b;
    }
};
System.out.println(suma.ejecutar(5, 3)); // 8
```

### ✅ Con expresión lambda (forma moderna):

```java
Operacion suma = (a, b) -> a + b;
System.out.println(suma.ejecutar(5, 3)); // 8
```

👉 **Nota:** Gracias a que `Operacion` tiene un único método abstracto, el compilador puede inferir el tipo de los parámetros y del retorno.

---

## 🧮 4. Ejemplo con métodos `default` y `static`

```java
@FunctionalInterface
interface Calculadora {
    int operar(int a, int b);

    default void mostrarOperacion(String op) {
        System.out.println("Ejecutando: " + op);
    }

    static void version() {
        System.out.println("Calculadora v1.0");
    }
}
```

➡️ **No rompe la regla** de una interfaz funcional, ya que solo `operar()` es abstracto.

---

## 🧭 5. Ventajas de las interfaces funcionales

✅ Código más **conciso y legible** (gracias a las lambdas).
✅ Fomentan el **paradigma funcional** dentro de Java.
✅ Facilitan la programación **reactiva y concurrente**.
✅ Permiten pasar **comportamientos como argumentos**.
✅ Base de las **Streams API** (map, filter, reduce, etc.).

---

## ⚠️ 6. Reglas importantes

1. **Solo puede haber un método abstracto.**
   Si se define más de uno, el compilador lanzará error si se usa `@FunctionalInterface`.

2. **Los métodos `default`, `static` o `private` no cuentan como abstractos.**

3. **Métodos heredados** (como `equals()`, `hashCode()`, `toString()`) **no cuentan** como abstractos.

4. Si una interfaz hereda de otra que ya tiene un método abstracto, **debe mantener la unicidad del método abstracto**.

---

## 🧾 7. Buenas prácticas

✅ **Usa `@FunctionalInterface`** siempre que diseñes una interfaz con un único método.
✅ **Evita agregar nuevos métodos abstractos** a una interfaz funcional ya publicada (rompe compatibilidad).
✅ **Usa las interfaces predefinidas** en `java.util.function` cuando sea posible.
✅ **Nombrar bien** la interfaz y su método abstracto (ej. `Operacion`, `calcular`, `transformar`, etc.).



# ☕️ **Interfaces funcionales más usadas en Java (con lambdas y Streams)**

Desde **Java 8**, el paquete `java.util.function` incluye un conjunto estándar de **interfaces funcionales** que cubren la mayoría de los patrones de uso comunes.

Estas interfaces permiten pasar **comportamientos** como parámetros a métodos, por ejemplo, cuando usas `filter()`, `map()`, `forEach()`, `reduce()`, etc., en Streams.

---

## 🧩 1. `Predicate<T>`

### 📘 Definición:

Representa una **función que toma un argumento y devuelve un booleano**.
Se usa para **evaluar condiciones o filtros**.

```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```

### 🧠 Uso típico:

* En **Streams**, se usa con `filter()` para filtrar elementos según una condición.

### 💡 Ejemplo:

```java
List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6);

numeros.stream()
       .filter(n -> n % 2 == 0)  // ← Predicate<Integer>
       .forEach(System.out::println);
```

📤 **Salida:**

```
2
4
6
```

---

## 🧮 2. `Function<T, R>`

### 📘 Definición:

Representa una **función que recibe un argumento y devuelve un resultado**.

```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```

### 🧠 Uso típico:

* En **Streams**, se usa con `map()` para transformar los elementos de un tipo a otro.

### 💡 Ejemplo:

```java
List<String> palabras = List.of("java", "lambda", "stream");

List<Integer> longitudes = palabras.stream()
                                   .map(s -> s.length())  // ← Function<String, Integer>
                                   .toList();

System.out.println(longitudes);
```

📤 **Salida:**

```
[4, 6, 6]
```

---

## 🧾 3. `Consumer<T>`

### 📘 Definición:

Representa una **operación que acepta un argumento y no devuelve ningún resultado**.

```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```

### 🧠 Uso típico:

* En **Streams**, se usa con `forEach()` para realizar una acción sobre cada elemento.

### 💡 Ejemplo:

```java
List<String> nombres = List.of("Ana", "Luis", "Carlos");

nombres.stream()
       .forEach(n -> System.out.println("Hola " + n));  // ← Consumer<String>
```

📤 **Salida:**

```
Hola Ana
Hola Luis
Hola Carlos
```

---

## 🧰 4. `Supplier<T>`

### 📘 Definición:

Representa una **función sin argumentos que devuelve un resultado**.
Se usa para **proveer o generar valores**.

```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

### 💡 Ejemplo:

```java
Supplier<Double> aleatorio = () -> Math.random();

System.out.println(aleatorio.get()); // 0.1356...
```

👉 En Streams, a veces se usa para generar flujos infinitos con `Stream.generate()`:

```java
Stream.generate(() -> Math.random())
      .limit(3)
      .forEach(System.out::println);
```

---

## ⚙️ 5. `UnaryOperator<T>`

### 📘 Definición:

Es una **Function** especial que **toma y devuelve el mismo tipo**.

```java
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {}
```

### 💡 Ejemplo:

```java
List<Integer> numeros = List.of(1, 2, 3);

numeros.stream()
       .map(x -> x * x)  // ← UnaryOperator<Integer>
       .forEach(System.out::println);
```

📤 **Salida:**

```
1
4
9
```

---

## ⚙️ 6. `BinaryOperator<T>`

### 📘 Definición:

Una **Function** especial que **recibe dos argumentos del mismo tipo y devuelve un resultado del mismo tipo**.
Muy usada con el método `reduce()`.

```java
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T> {}
```

### 💡 Ejemplo:

```java
List<Integer> numeros = List.of(1, 2, 3, 4, 5);

int suma = numeros.stream()
                  .reduce(0, (a, b) -> a + b);  // ← BinaryOperator<Integer>

System.out.println(suma); // 15
```

---

## 🔗 7. `BiFunction<T, U, R>`

### 📘 Definición:

Recibe **dos argumentos** y devuelve **un resultado**.

```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}
```

### 💡 Ejemplo:

```java
BiFunction<Integer, Integer, String> sumarTexto = (a, b) -> "Resultado: " + (a + b);
System.out.println(sumarTexto.apply(3, 4)); // "Resultado: 7"
```

---

## 🔗 8. `BiConsumer<T, U>`

### 📘 Definición:

Recibe **dos argumentos** y **no devuelve nada**.

```java
@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
}
```

### 💡 Ejemplo:

```java
Map<String, Integer> edades = Map.of("Ana", 20, "Luis", 25);

edades.forEach((nombre, edad) -> System.out.println(nombre + " tiene " + edad + " años."));
```

📤 **Salida:**

```
Ana tiene 20 años.
Luis tiene 25 años.
```

---

## 🧠 9. `BiPredicate<T, U>`

### 📘 Definición:

Recibe **dos argumentos** y devuelve **un booleano**.

```java
@FunctionalInterface
public interface BiPredicate<T, U> {
    boolean test(T t, U u);
}
```

### 💡 Ejemplo:

```java
BiPredicate<String, Integer> longitudMayor = (texto, n) -> texto.length() > n;
System.out.println(longitudMayor.test("Hola", 3)); // true
```

---

# 🌊 **Cómo se usan en Streams**

| Método de Stream | Interfaz funcional usada              | Descripción                                      |
| ---------------- | ------------------------------------- | ------------------------------------------------ |
| `filter()`       | `Predicate<T>`                        | Filtra elementos según una condición             |
| `map()`          | `Function<T, R>`                      | Transforma los elementos                         |
| `forEach()`      | `Consumer<T>`                         | Ejecuta una acción sobre cada elemento           |
| `reduce()`       | `BinaryOperator<T>`                   | Combina los elementos para producir un resultado |
| `flatMap()`      | `Function<T, Stream<R>>`              | Convierte cada elemento en un Stream y los une   |
| `sorted()`       | `Comparator<T>` *(también funcional)* | Ordena los elementos                             |
| `collect()`      | `Collector<T, A, R>` *(más avanzada)* | Acumula resultados en una colección u objeto     |

---

## 💡 Ejemplo integrador completo

```java
import java.util.*;
import java.util.stream.*;

public class EjemploStreams {
    public static void main(String[] args) {
        List<String> nombres = List.of("Ana", "Luis", "Pedro", "Lucía", "Alba");

        List<String> resultado = nombres.stream()
                .filter(n -> n.startsWith("L"))           // Predicate
                .map(String::toUpperCase)                 // Function
                .sorted()                                 // Comparator (funcional)
                .toList();

        resultado.forEach(System.out::println);           // Consumer
    }
}
```

📤 **Salida:**

```
LUIS
LUCÍA
```

---

# 🧾 **Resumen visual**

| Tipo de interfaz      | Argumentos | Retorno   | Uso común                            |
| --------------------- | ---------- | --------- | ------------------------------------ |
| `Predicate<T>`        | 1          | `boolean` | Filtrar                              |
| `Function<T, R>`      | 1          | `R`       | Transformar                          |
| `Consumer<T>`         | 1          | `void`    | Iterar o ejecutar acción             |
| `Supplier<T>`         | 0          | `T`       | Generar valores                      |
| `UnaryOperator<T>`    | 1          | `T`       | Transformar en el mismo tipo         |
| `BinaryOperator<T>`   | 2          | `T`       | Reducir / combinar                   |
| `BiFunction<T, U, R>` | 2          | `R`       | Transformar dos valores              |
| `BiConsumer<T, U>`    | 2          | `void`    | Iterar en pares (como `Map.forEach`) |

---

## 🚀 Conclusión

Las **interfaces funcionales predefinidas** son el **motor de las expresiones lambda y Streams** en Java.
Gracias a ellas puedes escribir código **más conciso, expresivo y funcional**, eliminando clases anónimas innecesarias.

* `Predicate` → **filtrar**
* `Function` → **transformar**
* `Consumer` → **accionar**
* `Supplier` → **proveer**
* `Operator` → **reducir o modificar**

---

