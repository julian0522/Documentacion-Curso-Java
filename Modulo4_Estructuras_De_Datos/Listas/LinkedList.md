

## 🧩 ¿Qué es una LinkedList en Java?

En Java, una **LinkedList** es una **estructura de datos dinámica** que pertenece al paquete `java.util`.
Representa una **lista doblemente enlazada** donde **cada elemento (nodo)** contiene:

* El **dato** (valor almacenado)
* Una **referencia al nodo anterior**
* Una **referencia al nodo siguiente**

👉 Se declara así:

```java
import java.util.LinkedList;

LinkedList<String> lista = new LinkedList<>();
```

---

## ⚙️ ¿Cómo funciona internamente?

Una `LinkedList` está formada por **nodos conectados entre sí**.
Cada nodo sabe cuál es su **anterior** y su **siguiente**.

Ejemplo visual de una lista con tres elementos:

```
null ← [A] ↔ [B] ↔ [C] → null
```

* El primer nodo no tiene anterior (`null`).
* El último nodo no tiene siguiente (`null`).
* Puedes recorrerla en ambas direcciones (de principio a fin o viceversa).

---

## 💡 Operaciones comunes

| Operación                        | Ejemplo                     | Descripción                             |
| -------------------------------- | --------------------------- | --------------------------------------- |
| `add(E e)`                       | `lista.add("Hola");`        | Agrega al final                         |
| `addFirst(E e)`                  | `lista.addFirst("Inicio");` | Agrega al principio                     |
| `addLast(E e)`                   | `lista.addLast("Fin");`     | Agrega al final                         |
| `get(int index)`                 | `lista.get(1);`             | Obtiene el elemento en la posición dada |
| `remove(int index)`              | `lista.remove(0);`          | Elimina el elemento en la posición dada |
| `removeFirst()` / `removeLast()` |                             | Elimina el primero o el último          |
| `size()`                         | `lista.size();`             | Devuelve el número de elementos         |

Ejemplo completo:

```java
import java.util.LinkedList;

public class EjemploLinkedList {
    public static void main(String[] args) {
        LinkedList<String> nombres = new LinkedList<>();

        nombres.add("Ana");
        nombres.add("Luis");
        nombres.addFirst("Carlos");  // Inserta al inicio
        nombres.addLast("Maria");    // Inserta al final

        System.out.println(nombres); // [Carlos, Ana, Luis, Maria]

        nombres.remove("Ana");
        System.out.println(nombres); // [Carlos, Luis, Maria]
    }
}
```

---

## ⚖️ Ventajas y Desventajas

### ✅ Ventajas:

1. **Inserciones y eliminaciones eficientes** (O(1)) si ya tienes una referencia al nodo.

   * No hay que mover todos los elementos como en un `ArrayList`.
2. **Tamaño dinámico**: crece y reduce según sea necesario.
3. **Ideal para listas donde se agregan o eliminan elementos frecuentemente**.

### ❌ Desventajas:

1. **Acceso lento por índice** (O(n)):

   * Para `get(index)` tiene que recorrer la lista desde el principio o el final.
2. **Mayor consumo de memoria**:

   * Cada nodo necesita referencias a los nodos anterior y siguiente.
3. **Menor rendimiento en búsquedas** comparado con `ArrayList` (por falta de acceso directo).
4. **No es eficiente en cache**, porque los elementos no están contiguos en memoria.

---

## 🆚 LinkedList vs ArrayList

| Característica                 | **LinkedList**                     | **ArrayList**              |
| ------------------------------ | ---------------------------------- | -------------------------- |
| Estructura interna             | Nodos enlazados                    | Arreglo dinámico           |
| Inserción/eliminación en medio | Rápida (O(1) si tienes referencia) | Lenta (O(n))               |
| Acceso por índice (`get(i)`)   | Lento (O(n))                       | Rápido (O(1))              |
| Memoria usada                  | Mayor                              | Menor                      |
| Ideal cuando...                | Se modifican muchos elementos      | Se accede mucho por índice |

---

## 🧠 En resumen

* **LinkedList** es una lista dinámica basada en **nodos enlazados**.
* **Uso ideal**: cuando haces **muchas inserciones y eliminaciones** en diferentes posiciones.
* **Evítala** si necesitas **acceso rápido por índice** o si la lista es muy grande y se consulta más que se modifica.

