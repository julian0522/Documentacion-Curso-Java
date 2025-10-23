
# ☕ **Documentación completa sobre los Streams en Java**

## 📘 1. ¿Qué es un *Stream* en Java?

Un **Stream** en Java (introducido en **Java 8**, dentro del paquete `java.util.stream`) es una **secuencia de elementos** que se pueden procesar de forma **funcional y declarativa**.

Los Streams **no almacenan datos**, sino que **operan sobre fuentes de datos** (como listas, conjuntos, arrays o incluso archivos) para transformarlos o filtrarlos mediante **operaciones encadenadas**.

👉 En resumen:

> **Stream = flujo de datos + operaciones funcionales**

---

## 🧩 2. Características principales

| Característica                          | Descripción                                                                             |
| --------------------------------------- | --------------------------------------------------------------------------------------- |
| **Inmutables**                          | Los Streams no modifican la fuente original de datos.                                   |
| **Declarativos**                        | Se describen las transformaciones, no el *cómo* hacerlo.                                |
| **Laziness (evaluación diferida)**      | Las operaciones intermedias no se ejecutan hasta que se invoque una operación terminal. |
| **Pueden ser secuenciales o paralelos** | Permiten procesamiento en paralelo con facilidad (`parallelStream()`).                  |
| **Se consumen una sola vez**            | Un Stream no puede reutilizarse después de una operación terminal.                      |

---

## 🧱 3. Estructura básica de un Stream

Todo uso de Streams sigue el mismo patrón:

```java
fuenteDeDatos.stream()         // 1. Fuente
             .filter(...)      // 2. Operaciones intermedias
             .map(...)         // 2. Operaciones intermedias
             .collect(...);    // 3. Operación terminal
```

📌 **Tres fases:**

1. **Creación** → obtener un Stream a partir de una fuente.
2. **Transformación** → aplicar operaciones intermedias.
3. **Terminación** → producir un resultado.

---

## 🌱 4. Cómo crear Streams

### 🔹 Desde una colección

```java
List<String> lista = List.of("Java", "Python", "C++");
Stream<String> stream = lista.stream();
```

### 🔹 Desde un array

```java
String[] array = {"A", "B", "C"};
Stream<String> stream = Arrays.stream(array);
```

### 🔹 Desde valores directos

```java
Stream<Integer> numeros = Stream.of(1, 2, 3, 4, 5);
```

### 🔹 Desde un archivo (usando `Files.lines()`)

```java
try (Stream<String> lineas = Files.lines(Path.of("archivo.txt"))) {
    lineas.forEach(System.out::println);
}
```

### 🔹 Desde una secuencia infinita

```java
Stream<Integer> infinitos = Stream.iterate(1, n -> n + 1); // 1, 2, 3, ...
Stream<Double> aleatorios = Stream.generate(Math::random);
```

---

## ⚙️ 5. **Etapas del ciclo de vida de un Stream**

Un Stream **no funciona como un bucle tradicional**, sino como una **tubería (pipeline)** de operaciones.
Su ciclo se divide en **tres fases**:

| Etapa                                | Tipo                     | Qué hace                                                                                                |
| ------------------------------------ | ------------------------ | ------------------------------------------------------------------------------------------------------- |
| **1️⃣ Creación**                     | Fuente → Stream          | Se crea el flujo de datos desde una colección, arreglo o generador.                                     |
| **2️⃣ Transformación (Intermedias)** | Stream → Stream          | Se definen operaciones que transforman o filtran los datos. Son *perezosas* (no ejecutan nada todavía). |
| **3️⃣ Operación terminal**           | Stream → Valor/colección | Dispara el procesamiento y devuelve un resultado.                                                       |

---

### 🔁  **Flujo de ejecución (pipeline)**

El ciclo de ejecución de un Stream se comporta así:

1. **Creas el Stream** (aún no se ejecuta nada).
2. **Encadenas operaciones intermedias** (`filter`, `map`, `sorted`, `peek`, etc.).
   Estas operaciones **no se ejecutan todavía** — el Stream **solo construye un plan de trabajo**.
3. **Llamas a una operación terminal** (`forEach`, `collect`, `count`, `reduce`, etc.).
   En ese momento:

   * El Stream se **abre**.
   * Java **itera internamente** sobre los datos fuente.
   * Aplica las operaciones intermedias **en cadena y por elemento**.
   * **Genera el resultado**.
4. El Stream se **cierra automáticamente** (no se puede reutilizar).

---

### ⚙️ **Ejemplo paso a paso**

```java
List<Integer> numeros = List.of(1, 2, 3, 4, 5);

long total = numeros.stream()
    .filter(n -> {
        System.out.println("Filtrando " + n);
        return n % 2 == 0;
    })
    .map(n -> {
        System.out.println("Multiplicando " + n);
        return n * 10;
    })
    .count();

System.out.println("Total: " + total);
```

---

### 🧠 ¿Qué pasa internamente?

1️⃣ `numeros.stream()`
→ Se crea el Stream (nada se ejecuta).

2️⃣ `.filter(...)`
→ Se agrega al pipeline, pero no se ejecuta aún.

3️⃣ `.map(...)`
→ Se agrega al pipeline, sigue esperando.

4️⃣ `.count()`
→ **¡Se activa el pipeline!**
El Stream empieza a recorrer la lista **uno por uno**:

| Elemento | Acción                                   | Resultado   |
| -------- | ---------------------------------------- | ----------- |
| 1        | `Filtrando 1` → no pasa el filtro        | se descarta |
| 2        | `Filtrando 2` → pasa → `Multiplicando 2` | cuenta +1   |
| 3        | `Filtrando 3` → no pasa                  | se descarta |
| 4        | `Filtrando 4` → pasa → `Multiplicando 4` | cuenta +1   |
| 5        | `Filtrando 5` → no pasa                  | se descarta |

📤 **Salida:**

```
Filtrando 1
Filtrando 2
Multiplicando 2
Filtrando 3
Filtrando 4
Multiplicando 4
Filtrando 5
Total: 2
```

---

### 🧠 **Punto clave: Ejecución *perezosa* (Lazy Evaluation)**

👉 Las operaciones intermedias **no se ejecutan inmediatamente**.
Solo cuando llega una **operación terminal**, el Stream **procesa cada elemento uno a uno a través de todas las etapas**.

Visualízalo así:

```
Colección -> filter() -> map() -> collect()
```

No se procesa toda la lista con `filter` y luego toda con `map`.
En realidad, se procesa **elemento por elemento** a través de la cadena completa.

🔁 Es decir:

```
Elemento 1 -> filter -> map -> (si pasa) collect
Elemento 2 -> filter -> map -> (si pasa) collect
...
```

---

## ⚙️ 6. Operaciones en Streams

Las operaciones se dividen en **intermedias** y **terminales**.

---

## 🔄 7. Operaciones intermedias

Son **lazy** (perezosas): no se ejecutan hasta que se invoque una operación terminal.
Cada una **devuelve un nuevo Stream**.

| Operación                         | Descripción                            | Ejemplo                     |
| --------------------------------- | -------------------------------------- | --------------------------- |
| `filter(Predicate)`               | Filtra elementos según una condición   | `filter(n -> n > 5)`        |
| `map(Function)`                   | Transforma los elementos               | `map(String::toUpperCase)`  |
| `flatMap(Function)`               | Descompone estructuras anidadas        | `flatMap(List::stream)`     |
| `distinct()`                      | Elimina duplicados                     | `distinct()`                |
| `sorted()` / `sorted(Comparator)` | Ordena los elementos                   | `sorted()`                  |
| `limit(n)`                        | Limita la cantidad de elementos        | `limit(5)`                  |
| `skip(n)`                         | Omite los primeros `n` elementos       | `skip(2)`                   |
| `peek(Consumer)`                  | Permite ver elementos sin modificarlos | `peek(System.out::println)` |

### 💡 Ejemplo:

```java
List<String> nombres = List.of("Ana", "Pedro", "Lucía", "Luis", "Ana");

nombres.stream()
       .filter(n -> n.startsWith("L"))
       .distinct()
       .map(String::toUpperCase)
       .sorted()
       .forEach(System.out::println);
```

📤 **Salida:**

```
LUIS
LUCÍA
```

---

## 🏁 8. Operaciones terminales

| **Método**                              | **Tipo de Retorno** | **Descripción**                                                        | **Ejemplo**                                                |
| --------------------------------------- | ------------------- | ---------------------------------------------------------------------- | ---------------------------------------------------------- |
| `forEach(Consumer<T>)`                  | `void`              | Ejecuta una acción sobre cada elemento del Stream.                     | `stream.forEach(System.out::println);`                     |
| `forEachOrdered(Consumer<T>)`           | `void`              | Igual que `forEach()`, pero mantiene el orden del Stream secuencial.   | `stream.forEachOrdered(System.out::println);`              |
| `toArray()`                             | `Object[]` / `T[]`  | Convierte el Stream en un arreglo.                                     | `String[] arr = stream.toArray(String[]::new);`            |
| `reduce(BinaryOperator<T>)`             | `Optional<T>`       | Combina todos los elementos usando una operación binaria (acumulador). | `Optional<Integer> sum = stream.reduce(Integer::sum);`     |
| `reduce(T identity, BinaryOperator<T>)` | `T`                 | Igual que el anterior, pero con un valor inicial.                      | `int suma = stream.reduce(0, Integer::sum);`               |
| `count()`                               | `long`              | Devuelve la cantidad de elementos en el Stream.                        | `long total = stream.count();`                             |
| `min(Comparator<T>)`                    | `Optional<T>`       | Devuelve el elemento mínimo según el comparador.                       | `Optional<Integer> min = stream.min(Integer::compareTo);`  |
| `max(Comparator<T>)`                    | `Optional<T>`       | Devuelve el elemento máximo según el comparador.                       | `Optional<Integer> max = stream.max(Integer::compareTo);`  |
| `findFirst()`                           | `Optional<T>`       | Devuelve el primer elemento del Stream (si existe).                    | `Optional<String> first = stream.findFirst();`             |
| `findAny()`                             | `Optional<T>`       | Devuelve cualquier elemento (útil en Streams paralelos).               | `Optional<String> any = stream.findAny();`                 |
| `anyMatch(Predicate<T>)`                | `boolean`           | Devuelve `true` si algún elemento cumple la condición.                 | `boolean alguno = stream.anyMatch(s -> s.length() > 3);`   |
| `allMatch(Predicate<T>)`                | `boolean`           | Devuelve `true` si todos los elementos cumplen la condición.           | `boolean todos = stream.allMatch(s -> s.startsWith("J"));` |
| `noneMatch(Predicate<T>)`               | `boolean`           | Devuelve `true` si ningún elemento cumple la condición.                | `boolean ninguno = stream.noneMatch(String::isEmpty);`     |

---

## 🧱 **Operaciones Terminales con `Collectors`**

La clase **`Collectors`** (en el paquete `java.util.stream`) proporciona métodos estáticos que devuelven objetos **Collector**, los cuales se utilizan con el método terminal **`collect()`** para acumular, transformar, agrupar o resumir los datos de un Stream.

### 🔹 **1. Recolección básica**

| **Collector**                              | **Tipo de Retorno** | **Descripción**                                             | **Ejemplo**                                                                                         |
| ------------------------------------------ | ------------------- | ----------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `Collectors.toList()`                      | `List<T>`           | Recolecta los elementos en una lista (mantiene duplicados). | `List<String> list = stream.collect(Collectors.toList());`                                          |
| `Collectors.toSet()`                       | `Set<T>`            | Recolecta los elementos en un conjunto (sin duplicados).    | `Set<String> set = stream.collect(Collectors.toSet());`                                             |
| `Collectors.toMap(keyMapper, valueMapper)` | `Map<K,V>`          | Recolecta los elementos en un mapa.                         | `Map<Integer, String> map = stream.collect(Collectors.toMap(String::length, Function.identity()));` |
| `Collectors.joining()`                     | `String`            | Concatena los elementos en una cadena.                      | `String s = stream.collect(Collectors.joining(", "));`                                              |

---

### 🔹 **2. Agrupamiento y partición**

| **Collector**                             | **Tipo de Retorno**     | **Descripción**                                                      | **Ejemplo**                                                                                          |
| ----------------------------------------- | ----------------------- | -------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------- |
| `Collectors.groupingBy(classifier)`       | `Map<K, List<T>>`       | Agrupa los elementos según una función clasificadora.                | `Map<Integer, List<String>> grouped = stream.collect(Collectors.groupingBy(String::length));`        |
| `Collectors.partitioningBy(Predicate<T>)` | `Map<Boolean, List<T>>` | Divide los elementos en dos grupos (true/false) según una condición. | `Map<Boolean, List<String>> parts = stream.collect(Collectors.partitioningBy(s -> s.length() > 3));` |

---

### 🔹 **3. Estadísticas y resumen**

| **Collector**                                       | **Tipo de Retorno**       | **Descripción**                                           | **Ejemplo**                                                                                      |
| --------------------------------------------------- | ------------------------- | --------------------------------------------------------- | ------------------------------------------------------------------------------------------------ |
| `Collectors.counting()`                             | `Long`                    | Cuenta los elementos del Stream.                          | `long count = stream.collect(Collectors.counting());`                                            |
| `Collectors.summingInt(ToIntFunction<T>)`           | `Integer`                 | Suma los valores enteros.                                 | `int sum = stream.collect(Collectors.summingInt(Integer::intValue));`                            |
| `Collectors.summingDouble(ToDoubleFunction<T>)`     | `Double`                  | Suma los valores `double`.                                | `double sum = stream.collect(Collectors.summingDouble(Double::valueOf));`                        |
| `Collectors.averagingInt(ToIntFunction<T>)`         | `Double`                  | Calcula el promedio de los valores enteros.               | `double avg = stream.collect(Collectors.averagingInt(Integer::intValue));`                       |
| `Collectors.summarizingInt(ToIntFunction<T>)`       | `IntSummaryStatistics`    | Devuelve estadísticas (conteo, suma, min, max, promedio). | `IntSummaryStatistics stats = stream.collect(Collectors.summarizingInt(Integer::intValue));`     |
| `Collectors.summarizingDouble(ToDoubleFunction<T>)` | `DoubleSummaryStatistics` | Igual que el anterior, pero para `double`.                | `DoubleSummaryStatistics stats = stream.collect(Collectors.summarizingDouble(Double::valueOf));` |

---

### 🔹 **4. Reducción y transformación avanzada**

| **Collector**                                        | **Tipo de Retorno**    | **Descripción**                                                | **Ejemplo**                                                                                                                     |
| ---------------------------------------------------- | ---------------------- | -------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- |
| `Collectors.reducing(BinaryOperator<T>)`             | `Optional<T>`          | Reduce los elementos usando una función de acumulación.        | `Optional<Integer> sum = stream.collect(Collectors.reducing(Integer::sum));`                                                    |
| `Collectors.mapping(mapper, downstreamCollector)`    | depende del downstream | Aplica una función de mapeo y luego recoge con otro collector. | `Set<Integer> set = stream.collect(Collectors.mapping(String::length, Collectors.toSet()));`                                    |
| `Collectors.collectingAndThen(downstream, finisher)` | tipo del finisher      | Aplica un Collector y luego una función de post-procesamiento. | `List<String> unmodifiable = stream.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));` |

---

## 🧠 **5. Ejemplo completo de uso de Collectors**

```java
import java.util.*;
import java.util.stream.*;

public class EjemploCollectors {
    public static void main(String[] args) {
        List<String> nombres = List.of("Ana", "Pedro", "Lucía", "Luis", "Ana");

        // 1. Recolectar en lista
        List<String> lista = nombres.stream()
                                    .collect(Collectors.toList());

        // 2. Agrupar por longitud
        Map<Integer, List<String>> agrupados = nombres.stream()
                                    .collect(Collectors.groupingBy(String::length));

        // 3. Particionar por longitud > 3
        Map<Boolean, List<String>> particion = nombres.stream()
                                    .collect(Collectors.partitioningBy(s -> s.length() > 3));

        // 4. Estadísticas
        IntSummaryStatistics stats = nombres.stream()
                                    .collect(Collectors.summarizingInt(String::length));

        // 5. Concatenar nombres
        String concatenados = nombres.stream()
                                    .collect(Collectors.joining(", "));

        System.out.println("Lista: " + lista);
        System.out.println("Agrupados: " + agrupados);
        System.out.println("Partición: " + particion);
        System.out.println("Estadísticas: " + stats);
        System.out.println("Concatenados: " + concatenados);
    }
}
```

📤 **Salida:**

```
Lista: [Ana, Pedro, Lucía, Luis, Ana]
Agrupados: {3=[Ana, Ana], 4=[Luis], 5=[Pedro, Lucía]}
Partición: {false=[Ana, Ana], true=[Pedro, Lucía, Luis]}
Estadísticas: IntSummaryStatistics{count=5, sum=20, min=3, average=4.0, max=5}
Concatenados: Ana, Pedro, Lucía, Luis, Ana
```

## 🧮 9. Ejemplo completo de procesamiento

```java
List<Integer> numeros = List.of(5, 8, 3, 2, 8, 10, 4);

int suma = numeros.stream()
                  .filter(n -> n % 2 == 0)  // pares
                  .distinct()                // sin duplicados
                  .sorted()                  // ordenados
                  .peek(System.out::println) // debug
                  .reduce(0, Integer::sum);  // suma total

System.out.println("Suma total: " + suma);
```

📤 **Salida:**

```
2
4
8
10
Suma total: 24
```

---

## 📦 10. `collect()` y la clase `Collectors`

La operación `collect()` **recolecta los resultados** de un Stream en una estructura final, normalmente usando la clase `Collectors`.

### 🔹 Ejemplo: convertir a lista

```java
List<String> listaMayus = nombres.stream()
                                 .map(String::toUpperCase)
                                 .collect(Collectors.toList());
```

### 🔹 Ejemplo: convertir a conjunto

```java
Set<String> conjunto = nombres.stream()
                              .collect(Collectors.toSet());
```

### 🔹 Ejemplo: concatenar en una cadena

```java
String resultado = nombres.stream()
                          .collect(Collectors.joining(", "));
```

📤 **Salida:**

```
Ana, Pedro, Lucía, Luis, Ana
```

### 🔹 Ejemplo: agrupamiento (groupingBy)

```java
Map<Character, List<String>> porInicial =
    nombres.stream()
           .collect(Collectors.groupingBy(n -> n.charAt(0)));
```

📤 **Salida:**

```
{A=[Ana, Ana], P=[Pedro], L=[Lucía, Luis]}
```

---

## ⚙️ 11. `reduce()` — reducción manual

El método `reduce()` combina todos los elementos de un Stream usando una función acumuladora.

```java
List<Integer> nums = List.of(1, 2, 3, 4);

int producto = nums.stream()
                   .reduce(1, (a, b) -> a * b);

System.out.println(producto); // 24
```

---

## 🔄 12. Stream paralelo

Los Streams pueden ejecutarse en paralelo para aprovechar varios núcleos del procesador.

### 🔹 Ejemplo:

```java
List<Integer> numeros = IntStream.rangeClosed(1, 10).boxed().toList();

int suma = numeros.parallelStream()
                  .mapToInt(Integer::intValue)
                  .sum();

System.out.println("Suma: " + suma);
```

📌 **Nota:**

* `parallelStream()` divide el procesamiento en varios hilos automáticamente.
* Es útil para tareas **grandes y CPU-bound**, pero no para listas pequeñas.

---

## 🧠 13. Streams primitivos

Para evitar *autoboxing*, existen variantes especializadas:

| Clase Stream   | Tipo de datos | Ejemplo de creación                   |
| -------------- | ------------- | ------------------------------------- |
| `IntStream`    | `int`         | `IntStream.range(1, 10)`              |
| `LongStream`   | `long`        | `LongStream.of(1L, 2L, 3L)`           |
| `DoubleStream` | `double`      | `DoubleStream.generate(Math::random)` |

### 🔹 Ejemplo:

```java
int suma = IntStream.rangeClosed(1, 5)
                    .sum();
System.out.println(suma); // 15
```

---

## 🧩 14. Relación entre **Streams**, **Lambdas** y **Optional**

| Concepto     | Rol principal                                                               | Ejemplo de interacción                |
| ------------ | --------------------------------------------------------------------------- | ------------------------------------- |
| **Lambda**   | Define la acción que el Stream debe aplicar                                 | `.filter(n -> n > 5)`                 |
| **Stream**   | Ejecuta las acciones sobre los datos                                        | `.map(String::toUpperCase)`           |
| **Optional** | Representa el resultado de ciertas operaciones que pueden no devolver valor | `.findFirst()` devuelve `Optional<T>` |

### 💡 Ejemplo combinado:

```java
List<String> nombres = List.of("Ana", "Luis", "Pedro");

Optional<String> primero = nombres.stream()
                                  .filter(n -> n.startsWith("L"))
                                  .findFirst();

primero.ifPresent(System.out::println); // Luis
```

---

## ⚠️ 15. Buenas prácticas y errores comunes

✅ **Usa Streams para transformar y filtrar datos**, no para modificar estructuras existentes.
✅ **Evita usar `peek()`** salvo para depuración.
✅ **No reutilices un Stream** (se consumen una sola vez).
✅ **Prefiere operaciones puras** (sin efectos secundarios).
✅ **No abuses de Streams paralelos** (pueden ser costosos si los datos son pocos).
✅ **Combina con `Optional` y lambdas** para un código más expresivo.

---

## 🧾 16. Resumen visual

| Etapa          | Ejemplo                         | Descripción     |
| -------------- | ------------------------------- | --------------- |
| **Creación**   | `list.stream()`                 | Fuente de datos |
| **Intermedia** | `.filter(x -> x > 10)`          | Transformación  |
| **Intermedia** | `.map(String::toUpperCase)`     | Transformación  |
| **Terminal**   | `.collect(Collectors.toList())` | Resultado final |

---

## 🚀 17. Conclusión

Los **Streams en Java** son una herramienta **potente, funcional y declarativa** para procesar colecciones y datos.

Permiten escribir código más:

* 🔹 **Conciso**
* 🔹 **Legible**
* 🔹 **Libre de errores comunes (como NPE)**
* 🔹 **Orientado a funciones y expresiones lambda**

En esencia:

> **Stream = datos + funciones + operaciones encadenadas**

y es la base del estilo **funcional moderno en Java**.