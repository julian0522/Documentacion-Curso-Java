
# 📘 **Documentación completa sobre los `Map` en Java**

## 🧩 1. ¿Qué es un `Map` en Java?

En Java, `Map` es una **interfaz** que pertenece al paquete `java.util`.
Su propósito es **almacenar pares clave-valor (key-value pairs)**, donde:

* Cada **clave (key)** es **única**.
* Cada **valor (value)** puede repetirse.
* Una clave sirve para **acceder rápidamente a su valor asociado**.

📦 Paquete:

```java
import java.util.Map;
```

📄 Definición (simplificada):

```java
public interface Map<K, V>
```

* `K` → tipo de la **clave**
* `V` → tipo del **valor**

---

## 🧠 2. ¿Para qué sirve un `Map`?

Los `Map` se utilizan cuando se necesita **asociar datos entre sí**, de forma similar a un **diccionario** o una **tabla hash**:

| Clave     | Valor    |
| --------- | -------- |
| "España"  | "Madrid" |
| "Francia" | "París"  |
| "Italia"  | "Roma"   |

Ejemplo de uso:

```java
Map<String, String> capitales = new HashMap<>();
capitales.put("España", "Madrid");
capitales.put("Francia", "París");

System.out.println(capitales.get("Francia")); // París
```

---

## 🧰 3. Métodos principales del `Map`

| Método                        | Descripción                                       |
| ----------------------------- | ------------------------------------------------- |
| `put(K key, V value)`         | Inserta o reemplaza un valor asociado a una clave |
| `get(Object key)`             | Devuelve el valor asociado a la clave             |
| `remove(Object key)`          | Elimina el par asociado a la clave                |
| `containsKey(Object key)`     | Devuelve `true` si el mapa contiene la clave      |
| `containsValue(Object value)` | Devuelve `true` si el mapa contiene el valor      |
| `size()`                      | Devuelve el número de pares clave-valor           |
| `isEmpty()`                   | Indica si el mapa está vacío                      |
| `keySet()`                    | Devuelve un `Set` con todas las claves            |
| `values()`                    | Devuelve una colección con todos los valores      |
| `entrySet()`                  | Devuelve un conjunto de objetos `Map.Entry<K,V>`  |

Ejemplo:

```java
for (Map.Entry<String, String> entrada : capitales.entrySet()) {
    System.out.println("País: " + entrada.getKey() + ", Capital: " + entrada.getValue());
}
```

---

## 🌍 4. Aplicaciones en el mundo real

Los `Map` son fundamentales en muchos contextos:

| Aplicación                   | Ejemplo                              |
| ---------------------------- | ------------------------------------ |
| 📚 Diccionarios              | Palabra → Definición                 |
| 💳 Bases de datos en memoria | ID → Registro de usuario             |
| 🌐 Servidores web            | URL → Controlador asociado           |
| 🎮 Videojuegos               | Nombre de jugador → Puntaje          |
| 🧾 Cachés                    | Clave → Objeto previamente calculado |
| 🧭 Configuraciones           | Nombre de parámetro → Valor          |

---

## 🧱 5. Implementaciones principales de `Map`

Java ofrece varias clases que implementan `Map`, cada una con **características diferentes**.

---

## 🔹 5.1. `HashMap`

📦 Paquete:

```java
import java.util.HashMap;
```

* Basado en **tablas hash**.
* **No garantiza orden** de los elementos.
* Permite **una clave `null`** y múltiples valores `null`.
* **Muy rápido** en operaciones de inserción, búsqueda y eliminación.

📈 Complejidad promedio:

| Operación   | Complejidad |
| ----------- | ----------- |
| Inserción   | O(1)        |
| Búsqueda    | O(1)        |
| Eliminación | O(1)        |

Ejemplo:

```java
Map<String, Integer> edades = new HashMap<>();
edades.put("Ana", 25);
edades.put("Luis", 30);
System.out.println(edades.get("Luis")); // 30
```

**Ventajas:**

* Alta eficiencia.
* Ideal para grandes volúmenes de datos.
* Permite claves y valores `null`.

**Desventajas:**

* No conserva orden.
* No es sincronizado (no es seguro en entornos multi-hilo).

**¿Cuándo usarlo?**
- Cuando necesitas velocidad y **no te importa el orden** de los elementos.
- Ideal para búsquedas rápidas por clave.

---

## 🔹 5.2. `LinkedHashMap`

📦 Paquete:

```java
import java.util.LinkedHashMap;
```

* Mantiene el **orden de inserción** de los elementos.
* Internamente combina **tabla hash + lista doblemente enlazada**.
* Permite **una clave `null`**.

Ejemplo:

```java
Map<String, Integer> puntuaciones = new LinkedHashMap<>();
puntuaciones.put("Carlos", 80);
puntuaciones.put("Lucía", 95);
System.out.println(puntuaciones); // {Carlos=80, Lucía=95}
```

**Ventajas:**

* Mantiene el orden de inserción.
* Ideal para mostrar datos en el orden agregado.
* Puede configurarse para eliminar los elementos menos usados (LRU Cache).

**Desventajas:**

* Ligeramente más lento que `HashMap`.
* Mayor consumo de memoria.

**¿Cuándo usarlo?**
- Cuando quieres **predecibilidad en el orden** en que agregaste los elementos.
- Ideal para estructuras tipo historial, listas ordenadas, cachés simples.

---

### 🔹 5.3. `TreeMap`

📦 Paquete:

```java
import java.util.TreeMap;
```

* Implementación basada en un **árbol rojo-negro (Red-Black Tree)**.
* Mantiene los elementos **ordenados según las claves**.
* No permite claves `null`.

Ejemplo:

```java
Map<String, Integer> notas = new TreeMap<>();
notas.put("Pedro", 85);
notas.put("Ana", 90);
System.out.println(notas); // {Ana=90, Pedro=85}
```

**Ventajas:**

* Mantiene las claves ordenadas.
* Permite subrangos (`subMap`, `headMap`, `tailMap`).

**Desventajas:**

* Más lento que `HashMap` (operaciones O(log n)).
* No permite claves `null`.

**¿Cuándo usarlo?**
- Cuando necesitas **ordenar automáticamente las claves**.
- Útil para reportes, ranking, o cualquier caso donde el orden alfabético o numérico importa.

---

### 🔹 5.5. `ConcurrentHashMap`

📦 Paquete:

```java
import java.util.concurrent.ConcurrentHashMap;
```

* Diseñada para **concurrencia** (entornos multi-hilo).
* Permite acceso simultáneo eficiente.
* No permite claves o valores `null`.

Ejemplo:

```java
Map<String, Integer> inventario = new ConcurrentHashMap<>();
inventario.put("Manzana", 10);
inventario.put("Pera", 20);
```

**Ventajas:**

* Alto rendimiento en entornos concurrentes.
* Evita bloqueos totales (usa segmentación interna).

**Desventajas:**

* Consumo de memoria mayor.
* No garantiza orden.

---

## ⚖️ 6. Comparativa general

| Implementación        | Orden         | Claves nulas | Thread-safe | Complejidad promedio | Ideal para            |
| --------------------- | ------------- | ------------ | ----------- | -------------------- | --------------------- |
| **HashMap**           | No            | ✅ Sí         | ❌ No        | O(1)                 | General, rápido       |
| **LinkedHashMap**     | Inserción     | ✅ Sí         | ❌ No        | O(1)                 | Mantener orden        |
| **TreeMap**           | Orden natural | ❌ No         | ❌ No        | O(log n)             | Datos ordenados       |
| **ConcurrentHashMap** | No            | ❌ No         | ✅ Sí        | O(1)                 | Entornos concurrentes |

---

## 🧪 7. Ejemplo práctico combinado

```java
import java.util.*;

public class EjemploMap {
    public static void main(String[] args) {
        Map<String, Integer> inventario = new LinkedHashMap<>();

        inventario.put("Manzanas", 50);
        inventario.put("Peras", 30);
        inventario.put("Bananas", 70);

        // Acceder a un valor
        System.out.println("Stock de Peras: " + inventario.get("Peras"));

        // Iterar
        for (String fruta : inventario.keySet()) {
            System.out.println(fruta + ": " + inventario.get(fruta));
        }

        // Comprobar existencia
        if (inventario.containsKey("Bananas")) {
            System.out.println("Tenemos bananas en stock.");
        }
    }
}
```

---

## 🚀 8. Buenas prácticas con Maps

✅ Usa `HashMap` para rendimiento general.
✅ Usa `LinkedHashMap` si necesitas mantener orden.
✅ Usa `TreeMap` si requieres datos ordenados automáticamente.
✅ Usa `ConcurrentHashMap` para multihilo.
❌ Evita `Hashtable` en código nuevo.
✅ Usa `Map.of(...)` (Java 9+) para mapas inmutables rápidos:

```java
Map<String, String> paises = Map.of(
    "México", "Ciudad de México",
    "Argentina", "Buenos Aires"
);
```

---

##  ¿Cuándo NO usar `Map`?

- Si necesitas **valores duplicados** sin clave, usa una `List`.
- Si necesitas simplemente saber si un valor está presente o no, usa un `Set`.
---
# Metodos Usados en los maps
## 🧩 **HashMap<K, V>**

Mapa sin orden, que asocia **claves únicas** con valores.
Utiliza una **tabla hash** para almacenar los datos, lo que permite un acceso promedio en **O(1)**.
---

### 🔹 **Métodos principales**

| Método                            | Descripción                                                  | Retorno                  |
| --------------------------------- | ------------------------------------------------------------ | ------------------------ |
| **`put(K key, V value)`**         | Inserta o reemplaza el valor asociado a una clave.           | Valor anterior o `null`  |
| **`get(Object key)`**             | Obtiene el valor asociado a una clave o `null` si no existe. | Valor o `null`           |
| **`remove(Object key)`**          | Elimina la entrada correspondiente a la clave.               | Valor eliminado o `null` |
| **`containsKey(Object key)`**     | Verifica si existe una entrada con la clave dada.            | `boolean`                |
| **`containsValue(Object value)`** | Verifica si el mapa contiene el valor dado.                  | `boolean`                |
| **`keySet()`**                    | Devuelve un conjunto (`Set<K>`) con todas las claves.        | `Set<K>`                 |
| **`values()`**                    | Devuelve una colección de todos los valores.                 | `Collection<V>`          |
| **`entrySet()`**                  | Devuelve un conjunto de entradas (`Map.Entry<K,V>`).         | `Set<Map.Entry<K,V>>`    |

---

### 🔹 **Métodos avanzados (Java 8+)**

| Método                                                | Descripción                                                          | Retorno                 |
| ----------------------------------------------------- | -------------------------------------------------------------------- | ----------------------- |
| **`getOrDefault(Object key, V defaultValue)`**        | Devuelve el valor o uno por defecto si no existe.                    | `V`                     |
| **`putIfAbsent(K key, V value)`**                     | Inserta solo si la clave no tiene valor.                             | Valor previo o `null`   |
| **`replace(K key, V value)`**                         | Reemplaza el valor si la clave existe.                               | Valor anterior o `null` |
| **`replace(K key, V oldValue, V newValue)`**          | Reemplaza solo si el valor actual coincide.                          | `boolean`               |
| **`remove(Object key, Object value)`**                | Elimina solo si clave y valor coinciden.                             | `boolean`               |
| **`compute(K key, BiFunction<K,V,V> remap)`**         | Recalcula el valor para la clave (puede eliminar si retorna `null`). | `V`                     |
| **`computeIfAbsent(K key, Function<K,V> func)`**      | Calcula e inserta un valor solo si la clave no existe.               | `V`                     |
| **`computeIfPresent(K key, BiFunction<K,V,V> func)`** | Recalcula el valor solo si la clave existe.                          | `V`                     |
| **`merge(K key, V value, BiFunction<V,V,V> func)`**   | Combina valores nuevos con los existentes.                           | `V`                     |
| **`forEach(BiConsumer<K,V> action)`**                 | Itera sobre todas las entradas del mapa.                             | `void`                  |
| **`replaceAll(BiFunction<K,V,V> func)`**              | Reemplaza todos los valores aplicando una función.                   | `void`                  |

---

### 🔹 **Ejemplo**

```java
HashMap<String, String> mapa = new HashMap<>();
mapa.put("CO", "Colombia");
mapa.put("MX", "México");
mapa.putIfAbsent("AR", "Argentina");

System.out.println(mapa.get("CO")); // Colombia
System.out.println(mapa.getOrDefault("CL", "Desconocido")); // Desconocido
```
---

## 🌳 7. **TreeMap<K, V>**

Mapa **ordenado por clave**, que implementa la interfaz `NavigableMap<K,V>`.
Internamente usa un **árbol rojo-negro (Red-Black Tree)**, garantizando orden y operaciones en **O(log n)**.

### 🔹 **Métodos principales de TreeMap**

| Método                           | Descripción                                            | Retorno             |
| -------------------------------- | ------------------------------------------------------ | ------------------- |
| **`firstKey()`**                 | Devuelve la primera clave en el orden.                 | `K`                 |
| **`lastKey()`**                  | Devuelve la última clave.                              | `K`                 |
| **`headMap(K toKey)`**           | Submapa con claves menores que `toKey`.                | `SortedMap<K,V>`    |
| **`tailMap(K fromKey)`**         | Submapa con claves mayores o iguales a `fromKey`.      | `SortedMap<K,V>`    |
| **`subMap(K fromKey, K toKey)`** | Submapa entre dos claves.                              | `SortedMap<K,V>`    |
| **`ceilingKey(K key)`**          | Devuelve la menor clave ≥ `key`.                       | `K`                 |
| **`floorKey(K key)`**            | Devuelve la mayor clave ≤ `key`.                       | `K`                 |
| **`higherKey(K key)`**           | Devuelve la menor clave estrictamente mayor que `key`. | `K`                 |
| **`lowerKey(K key)`**            | Devuelve la mayor clave estrictamente menor que `key`. | `K`                 |
| **`descendingMap()`**            | Devuelve una vista en orden inverso.                   | `NavigableMap<K,V>` |
| **`pollFirstEntry()`**           | Elimina y devuelve la primera entrada.                 | `Map.Entry<K,V>`    |
| **`pollLastEntry()`**            | Elimina y devuelve la última entrada.                  | `Map.Entry<K,V>`    |

---

### 🔹 **Ejemplo**

```java
TreeMap<Integer, String> ordenado = new TreeMap<>();
ordenado.put(2, "B");
ordenado.put(1, "A");
ordenado.put(3, "C");

System.out.println(ordenado); // {1=A, 2=B, 3=C}
System.out.println(ordenado.firstKey()); // 1
System.out.println(ordenado.ceilingKey(2)); // 2
```
---

## 🔗 8. **LinkedHashMap<K, V>**

Mapa que **mantiene el orden de inserción** o **de acceso** (según configuración).
Internamente combina una **tabla hash** con una **lista doblemente enlazada**.

### 🔹 **Métodos principales**

Mismos que `HashMap`, con las siguientes particularidades adicionales:

| Método                                                                          | Descripción                                                                                                         | Retorno   |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- | --------- |
| **`LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)`** | Constructor que define si el orden será por **inserción (`false`)** o **acceso (`true`)**.                          | —         |
| **`protected boolean removeEldestEntry(Map.Entry<K,V> eldest)`**                | Método que puede sobrescribirse para eliminar automáticamente el elemento más antiguo (por ejemplo, en cachés LRU). | `boolean` |

---

### 🔹 **Ejemplo**

```java
LinkedHashMap<String, Integer> ordenadoMapa = new LinkedHashMap<>();
ordenadoMapa.put("Uno", 1);
ordenadoMapa.put("Dos", 2);
ordenadoMapa.put("Tres", 3);
System.out.println(ordenadoMapa); // {Uno=1, Dos=2, Tres=3}
```

**Con orden de acceso:**

```java
LinkedHashMap<String, Integer> lru = new LinkedHashMap<>(16, 0.75f, true);
lru.put("A", 1);
lru.put("B", 2);
lru.get("A"); // "A" se mueve al final
System.out.println(lru); // {B=2, A=1}
```

