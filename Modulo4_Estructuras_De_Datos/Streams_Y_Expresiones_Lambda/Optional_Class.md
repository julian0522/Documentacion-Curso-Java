
# ☕️ **Clase `Optional` en Java**

## 📘 1. ¿Qué es `Optional`?

`Optional<T>` es una **clase contenedora genérica** introducida en **Java 8** en el paquete `java.util`.
Su propósito es **evitar el uso excesivo de valores `null`** y **prevenir errores `NullPointerException` (NPE)**.

Un `Optional<T>` puede contener:

* un valor **presente** (`non-null`), o
* un valor **ausente** (`empty`).

De esta forma, el desarrollador **debe manejar explícitamente la ausencia de valor**, en lugar de asumir que el valor siempre está presente.

---

## 🧱 2. Declaración de la clase

```java
public final class Optional<T> {
    // Métodos principales
    public static <T> Optional<T> of(T value)
    public static <T> Optional<T> ofNullable(T value)
    public static <T> Optional<T> empty()
    public T get()
    public boolean isPresent()
    public boolean isEmpty()
    public void ifPresent(Consumer<? super T> action)
    public T orElse(T other)
    public T orElseGet(Supplier<? extends T> supplier)
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier)
    public <U> Optional<U> map(Function<? super T, ? extends U> mapper)
    public <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper)
    public Optional<T> filter(Predicate<? super T> predicate)
}
```

---

## 🌱 3. Creación de objetos `Optional`

Hay tres formas principales de crear un `Optional`:

### 🔹 `Optional.of(value)`

Crea un `Optional` **que contiene un valor no nulo**.
Si pasas `null`, lanza una excepción `NullPointerException`.

```java
Optional<String> opt = Optional.of("Java");
```

### 🔹 `Optional.ofNullable(value)`

Crea un `Optional` que **puede contener o no un valor**.
Si el valor es `null`, crea un `Optional.empty()`.

```java
Optional<String> opt = Optional.ofNullable(posibleNull);
```

### 🔹 `Optional.empty()`

Crea un `Optional` **vacío**, es decir, sin valor presente.

```java
Optional<String> optVacio = Optional.empty();
```

---

## 🔍 4. Verificar la presencia de un valor

### 🔹 `isPresent()`

Devuelve `true` si el `Optional` contiene un valor, `false` si está vacío.

```java
if (opt.isPresent()) {
    System.out.println("Valor: " + opt.get());
}
```

### 🔹 `isEmpty()` (desde Java 11)

Devuelve `true` si el `Optional` **no tiene valor**.

```java
if (opt.isEmpty()) {
    System.out.println("No hay valor");
}
```

### 🔹 `ifPresent(Consumer<T>)`

Ejecuta una acción si hay valor presente (sin necesidad de `if`).

```java
opt.ifPresent(valor -> System.out.println("Valor: " + valor));
```

---

## 📦 5. Obtener el valor de un `Optional`

### ⚠️ `get()`

Devuelve el valor si está presente, o lanza `NoSuchElementException` si está vacío.
👉 **No recomendado** en la mayoría de casos, ya que contradice la idea de `Optional`.

```java
String valor = opt.get(); // Peligroso si está vacío
```

---

### ✅ Métodos seguros para obtener el valor

#### 🔹 `orElse(valorDefecto)`

Devuelve el valor si está presente, o el valor por defecto si no lo está.

```java
String resultado = opt.orElse("Sin valor");
```

#### 🔹 `orElseGet(Supplier<T>)`

Devuelve el valor si está presente, o genera uno con un *Supplier* si no lo está.
(A diferencia de `orElse`, el *Supplier* solo se ejecuta si está vacío).

```java
String resultado = opt.orElseGet(() -> "Generado por Supplier");
```

#### 🔹 `orElseThrow(Supplier<Exception>)`

Lanza una excepción personalizada si el `Optional` está vacío.

```java
String valor = opt.orElseThrow(() -> new IllegalStateException("Falta el valor"));
```

---

## 🧮 6. Transformaciones con `Optional`

### 🔹 `map(Function<T, R>)`

Transforma el valor contenido si está presente, devolviendo un nuevo `Optional` con el resultado.
Si está vacío, el resultado también será vacío.

```java
Optional<String> nombre = Optional.of("Java");
Optional<Integer> longitud = nombre.map(s -> s.length());

System.out.println(longitud.get()); // 4
```

---

### 🔹 `flatMap(Function<T, Optional<R>>)`

Similar a `map()`, pero la función devuelve otro `Optional`.
Evita el anidamiento de `Optional<Optional<T>>`.

```java
Optional<String> texto = Optional.of("123");
Optional<Integer> numero = texto.flatMap(t -> convertirAEntero(t));

public static Optional<Integer> convertirAEntero(String s) {
    try {
        return Optional.of(Integer.parseInt(s));
    } catch (NumberFormatException e) {
        return Optional.empty();
    }
}
```

---

### 🔹 `filter(Predicate<T>)`

Filtra el valor si cumple una condición; si no la cumple, devuelve `Optional.empty()`.

```java
Optional<Integer> numero = Optional.of(10);
Optional<Integer> resultado = numero.filter(n -> n > 5);

System.out.println(resultado.isPresent()); // true
```

---

## ⚙️ 7. Combinando métodos (`map`, `filter`, etc.)

El poder de `Optional` está en **encadenar transformaciones de forma funcional**, evitando condicionales explícitos.

```java
Optional<String> nombre = Optional.of("Java");

nombre.filter(s -> s.length() > 3)
      .map(String::toUpperCase)
      .ifPresent(System.out::println);
```

📤 **Salida:**

```
JAVA
```

---

## 🧠 8. Ejemplo completo práctico

```java
public class OptionalEjemplo {
    public static void main(String[] args) {
        Optional<String> nombre = Optional.ofNullable(obtenerNombre());

        String resultado = nombre
                .filter(n -> n.startsWith("J"))
                .map(String::toUpperCase)
                .orElse("Desconocido");

        System.out.println(resultado);
    }

    static String obtenerNombre() {
        return "java";
    }
}
```

📤 **Salida:**

```
JAVA
```

---

## 🔄 9. Relación con **Streams**

`Optional` y **Streams** comparten una filosofía funcional similar:

* Ambos **evitan los `null`**.
* Ambos usan **interfaces funcionales** (`Predicate`, `Function`, `Consumer`, etc.).
* Ambos pueden **encadenar operaciones** (`map`, `filter`, `flatMap`).

### 🔹 Ejemplo: convertir Stream a Optional

```java
List<String> nombres = List.of("Ana", "Luis", "Carlos");

Optional<String> resultado = nombres.stream()
                                   .filter(n -> n.startsWith("L"))
                                   .findFirst(); // devuelve Optional<String>

resultado.ifPresent(System.out::println); // Luis
```

### 🔹 Ejemplo: usar Optional dentro de un Stream

```java
List<Optional<String>> lista = List.of(
    Optional.of("Java"),
    Optional.empty(),
    Optional.of("Python")
);

List<String> valores = lista.stream()
                            .flatMap(opt -> opt.stream()) // convierte Optional a Stream
                            .toList();

System.out.println(valores); // [Java, Python]
```

👉 Desde **Java 9**, `Optional` tiene el método `stream()`, que devuelve:

* un `Stream` con un solo elemento si hay valor, o
* un `Stream` vacío si no lo hay.

Esto lo hace **fácil de integrar con las operaciones de Stream**.

---

## 📊 10. Beneficios de usar `Optional`

✅ Evita errores de **NullPointerException**.
✅ Hace el código más **seguro y expresivo**.
✅ Fomenta un estilo **funcional y declarativo**.
✅ Se integra naturalmente con **Streams y lambdas**.
✅ Obliga al desarrollador a **pensar en la ausencia de valores**.

---

## ⚠️ 11. Malos usos comunes

🚫 No se debe usar `Optional`:

* Como campo en una entidad (por ejemplo, en una clase POJO o modelo de datos).
* Como parámetro de un método (mejor usar `@Nullable` o sobrecarga).
* Para almacenar colecciones (ya que una lista vacía es mejor que un `Optional<List<T>>`).

✅ Debe usarse **solo como valor de retorno** cuando algo **puede o no estar presente**.

---

## 🧾 12. Resumen general

| Categoría                   | Método                                     | Descripción                           |
| --------------------------- | ------------------------------------------ | ------------------------------------- |
| **Creación**                | `of()`, `ofNullable()`, `empty()`          | Crea instancias de Optional           |
| **Verificación**            | `isPresent()`, `isEmpty()`, `ifPresent()`  | Comprueba presencia de valor          |
| **Obtención segura**        | `orElse()`, `orElseGet()`, `orElseThrow()` | Obtiene valor con seguridad           |
| **Transformación**          | `map()`, `flatMap()`, `filter()`           | Aplica funciones y condiciones        |
| **Integración con Streams** | `stream()`                                 | Permite combinar Optional con Streams |
| **Manejo clásico**          | `get()`                                    | Evitar, puede lanzar excepción        |

---

## 🚀 13. Conclusión

La clase `Optional`:

* Es una **herramienta poderosa para manejar valores nulos de forma explícita**.
* Fomenta un código **seguro, limpio y declarativo**.
* Está **íntimamente relacionada con Streams** porque ambas comparten el mismo estilo funcional (`map`, `filter`, `flatMap`, `ifPresent`).

En resumen, `Optional` es el **equivalente a un “contenedor de valor seguro”**, útil cuando algo **puede o no existir**, y reemplaza el patrón tradicional de:

```java
if (obj != null) {
    ...
}
```

con un código más **fluido y elegante**.
