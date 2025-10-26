
# 🧩 **Documentación completa sobre las Listas en Java**

## 📘 1. ¿Qué es una lista en Java?

Una **lista (`List`)** en Java es una **colección ordenada de elementos** que puede contener **elementos duplicados**.
Pertenece al **framework de colecciones (`Java Collections Framework`)** y se encuentra en el paquete `java.util`.

👉 Es una **interfaz genérica**, lo que significa que puedes definir el tipo de dato que almacenará:

```java
List<String> nombres = new ArrayList<>();
List<Integer> edades = new LinkedList<>();
```

![alt text](image.png)

---

## 🏗️ 2. ¿Dónde se encuentra?

Está definida así:

```java
package java.util;

public interface List<E> extends Collection<E> {
    // métodos...
}
```

🔹 `E` representa el tipo de dato genérico (por ejemplo, `String`, `Integer`, etc.).

---

## 🧠 3. Principales características

| Característica         | Descripción                                                          |
| ---------------------- | -------------------------------------------------------------------- |
| **Ordenada**           | Los elementos tienen un orden (índice).                              |
| **Indexada**           | Puedes acceder a los elementos por posición (índice empezando en 0). |
| **Permite duplicados** | Puedes agregar elementos repetidos.                                  |
| **Mutable**            | Puedes agregar, eliminar y modificar elementos.                      |
| **Genérica**           | Puedes especificar el tipo de datos que almacena.                    |

---

## 🧰 4. Clases que implementan `List`

| Implementación   | Descripción                                                                       |
| ---------------- | --------------------------------------------------------------------------------- |
| **`ArrayList`**  | Basada en un arreglo dinámico. Rápida para acceder, lenta para insertar/eliminar. |
| **`LinkedList`** | Basada en nodos enlazados. Rápida para insertar/eliminar, más lenta para acceder. |
| **`Vector`**     | Similar a `ArrayList` pero sincronizado (obsoleto en la mayoría de los casos).    |
| **`Stack`**      | Hereda de `Vector` y sigue el principio LIFO (Last In, First Out).                |

---

## 🧩 5. Cómo se crean

### 🪶 Opción 1: Usando `ArrayList`

```java
import java.util.ArrayList;
import java.util.List;

public class EjemploArrayList {
    public static void main(String[] args) {
        List<String> frutas = new ArrayList<>();

        frutas.add("Manzana");
        frutas.add("Banano");
        frutas.add("Pera");

        System.out.println(frutas);
    }
}
```

**Salida:**

```
[Manzana, Banano, Pera]
```

---

### 🪶 Opción 2: Usando `LinkedList`

```java
import java.util.LinkedList;
import java.util.List;

public class EjemploLinkedList {
    public static void main(String[] args) {
        List<Integer> numeros = new LinkedList<>();

        numeros.add(10);
        numeros.add(20);
        numeros.add(30);

        System.out.println(numeros);
    }
}
```

---

## 🧮 6. Operaciones más comunes con Listas

| Método                | Descripción                         | Ejemplo                     |
| --------------------- | ----------------------------------- | --------------------------- |
| `add(E e)`            | Agrega un elemento al final.        | `lista.add("Java");`        |
| `add(int index, E e)` | Inserta en una posición específica. | `lista.add(1, "Python");`   |
| `get(int index)`      | Obtiene un elemento.                | `lista.get(0);`             |
| `set(int index, E e)` | Reemplaza un elemento.              | `lista.set(0, "C#");`       |
| `remove(int index)`   | Elimina por índice.                 | `lista.remove(2);`          |
| `remove(Object o)`    | Elimina por valor.                  | `lista.remove("Java");`     |
| `size()`              | Devuelve el tamaño.                 | `lista.size();`             |
| `contains(Object o)`  | Verifica si existe un elemento.     | `lista.contains("Python");` |
| `clear()`             | Elimina todos los elementos.        | `lista.clear();`            |
| `isEmpty()`           | Verifica si está vacía.             | `lista.isEmpty();`          |

### 🔍 **1. Buscar elementos en una lista**

| Método                                | Descripción                                                                  | Ejemplo                                                  |
| ------------------------------------- | ---------------------------------------------------------------------------- | -------------------------------------------------------- |
| `contains(Object o)`                  | Verifica si la lista contiene un elemento.                                   | `lista.contains("Hola")`                                 |
| `get(int index)`                      | Devuelve el elemento en una posición específica.                             | `lista.get(2)`                                           |
| `indexOf(Object o)`                   | Devuelve el índice de la primera aparición del elemento (o `-1` si no está). | `lista.indexOf("Hola")`                                  |
| `lastIndexOf(Object o)`               | Devuelve el índice de la última aparición del elemento.                      | `lista.lastIndexOf("Hola")`                              |
| `isEmpty()`                           | Verifica si la lista está vacía.                                             | `lista.isEmpty()`                                        |
| `size()`                              | Devuelve la cantidad de elementos de la lista.                               | `lista.size()`                                           |
| `subList(int fromIndex, int toIndex)` | Devuelve una porción de la lista.                                            | `lista.subList(2, 5)`                                    |
| `stream().filter(...)` *(Java 8+)*    | Permite buscar con condiciones personalizadas.                               | `lista.stream().filter(x -> x.startsWith("A")).toList()` |

---

### 🗑️ **2. Eliminar elementos de una lista**

| Método                                              | Descripción                                                      | Ejemplo                               |
| --------------------------------------------------- | ---------------------------------------------------------------- | ------------------------------------- |
| `remove(int index)`                                 | Elimina el elemento en la posición indicada.                     | `lista.remove(0)`                     |
| `remove(Object o)`                                  | Elimina la **primera** ocurrencia del objeto especificado.       | `lista.remove("Hola")`                |
| `removeAll(Collection<?> c)`                        | Elimina todos los elementos que estén en la colección dada.      | `lista.removeAll(otraLista)`          |
| `clear()`                                           | Elimina **todos** los elementos de la lista.                     | `lista.clear()`                       |
| `retainAll(Collection<?> c)`                        | Conserva solo los elementos que están también en otra colección. | `lista.retainAll(listaFiltrada)`      |
| `removeIf(Predicate<? super E> filter)` *(Java 8+)* | Elimina los elementos que cumplan una condición.                 | `lista.removeIf(x -> x.length() < 3)` |

---

### ✏️ **3. Actualizar o modificar elementos**

| Método                                              | Descripción                                         | Ejemplo                                 |
| --------------------------------------------------- | --------------------------------------------------- | --------------------------------------- |
| `set(int index, E element)`                         | Reemplaza el elemento en la posición dada.          | `lista.set(1, "Nuevo valor")`           |
| `add(int index, E element)`                         | Inserta un elemento en una posición específica.     | `lista.add(2, "Insertado")`             |
| `add(E element)`                                    | Agrega un elemento al final.                        | `lista.add("Nuevo")`                    |
| `replaceAll(UnaryOperator<E> operator)` *(Java 8+)* | Modifica todos los elementos aplicando una función. | `lista.replaceAll(String::toUpperCase)` |

---

### 💡 **Consejos útiles**

* Para **recorrer y buscar condicionalmente**, usa `for`, `for-each` o **Streams** (`filter`, `map`, `findFirst`, etc.).
* Para **eliminar mientras recorres**, usa `Iterator` o `removeIf` (evita `ConcurrentModificationException`).
* Para **actualizar masivamente**, `replaceAll()` es ideal en Java 8+.

---

## 🔁 7. Recorrer una lista

### 🟢 Con `for` tradicional

```java
for (int i = 0; i < frutas.size(); i++) {
    System.out.println(frutas.get(i));
}
```

### 🟢 Con `for-each`

```java
for (String fruta : frutas) {
    System.out.println(fruta);
}
```

### 🟢 Con `forEach` (lambda)

```java
frutas.forEach(fruta -> System.out.println(fruta));
```

---

## ⚙️ 8. Ejemplo completo de uso

```java
import java.util.ArrayList;
import java.util.List;

public class ListaEjemplo {
    public static void main(String[] args) {
        List<String> animales = new ArrayList<>();

        // Agregar elementos
        animales.add("Perro");
        animales.add("Gato");
        animales.add("Loro");

        // Mostrar lista
        System.out.println("Animales: " + animales);

        // Acceder por índice
        System.out.println("Primer animal: " + animales.get(0));

        // Reemplazar
        animales.set(1, "Tigre");

        // Eliminar
        animales.remove("Loro");

        // Recorrer
        for (String a : animales) {
            System.out.println(a);
        }

        // Tamaño
        System.out.println("Total de animales: " + animales.size());
    }
}
```

**Salida:**

```
Animales: [Perro, Gato, Loro]
Primer animal: Perro
Perro
Tigre
Total de animales: 2
```

---

## 🧱 9. Diferencia entre `ArrayList` y `LinkedList`

| Aspecto               | `ArrayList`                     | `LinkedList`                     |
| --------------------- | ------------------------------- | -------------------------------- |
| Estructura            | Basado en arreglo dinámico      | Basado en nodos enlazados        |
| Acceso por índice     | Muy rápido                      | Más lento                        |
| Inserción/eliminación | Lento (porque reordena índices) | Rápido (solo cambia referencias) |
| Memoria               | Más compacta                    | Usa más memoria (referencias)    |

✅ Usa `ArrayList` para lecturas frecuentes.
✅ Usa `LinkedList` si insertas o eliminas muchos elementos intermedios.

---

## 🧠 10. Uso avanzado: Tipos genéricos

Puedes definir listas de cualquier tipo:

```java
List<Double> precios = new ArrayList<>();
List<Empleado> empleados = new ArrayList<>();
```

También puedes usar interfaces o clases personalizadas:

```java
class Persona {
    String nombre;
    Persona(String nombre) { this.nombre = nombre; }
}

List<Persona> personas = new ArrayList<>();
personas.add(new Persona("Julian"));
```

---

## 🧹 11. Métodos útiles de utilidades (`Collections`)

```java
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class EjemploCollections {
    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>();
        numeros.add(5);
        numeros.add(2);
        numeros.add(9);

        Collections.sort(numeros);          // Ordenar
        Collections.reverse(numeros);       // Invertir
        int max = Collections.max(numeros); // Máximo
        int min = Collections.min(numeros); // Mínimo

        System.out.println("Ordenados: " + numeros);
        System.out.println("Máximo: " + max + ", Mínimo: " + min);
    }
}
```

---

## 💼 12. Cuándo usar una lista en Java

Usa una `List` cuando:

* Necesitas **mantener el orden** de inserción.
* Necesitas **acceder a los elementos por índice**.
* Necesitas **permitir duplicados**.
* Vas a **modificar la colección con frecuencia** (agregar o quitar).

---

## 🔒 13. Versiones inmutables (Java 9+)

A partir de **Java 9**, puedes crear listas **inmutables**:

```java
List<String> dias = List.of("Lunes", "Martes", "Miércoles");
```

⚠️ No puedes agregar ni eliminar elementos, de lo contrario lanzará `UnsupportedOperationException`.

---

## 🧭 14. Ejemplo visual (conceptualmente)

Imagina esta lista:

```
List<String> lista = new ArrayList<>();

lista.add("A");
lista.add("B");
lista.add("C");
```

Visualmente sería:

| Índice | Valor |
| ------ | ----- |
| 0      | "A"   |
| 1      | "B"   |
| 2      | "C"   |

---

## Conclusión

Las **listas en Java** son una herramienta esencial para manejar colecciones dinámicas de datos.
Son **flexibles, potentes y ampliamente usadas** en casi cualquier aplicación Java, desde proyectos pequeños hasta sistemas empresariales.