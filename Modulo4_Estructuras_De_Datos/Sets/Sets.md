
# 🧩 **Los `Set` en Java**

## 📘 **¿Qué es un `Set` en Java?**

En Java, un `Set` es una **colección (Collection)** que **no permite elementos duplicados**.
Pertenece al **framework de colecciones de Java** (`java.util`) y es una **interfaz** que extiende de `Collection<E>`.

### 🔹 Definición técnica:

> Un `Set` es una colección que contiene elementos únicos, sin orden definido (aunque algunos tipos de `Set` sí mantienen cierto orden, como `TreeSet` o `LinkedHashSet`).

---

## 🌍 **¿Qué representa en la vida real?**

En la vida real, un `Set` puede representar cualquier conjunto de elementos donde **no tiene sentido tener duplicados**.

### Ejemplos:

* Un conjunto de **números de serie únicos** de productos.
* Una **lista de países** en los que se vende una empresa (no puede haber “España” dos veces).
* Los **nombres de usuarios registrados** en una plataforma (no debe haber repetidos).
* Los **colores disponibles** en un catálogo (no tiene sentido tener el mismo color repetido).

En general, si en tu modelo de datos **no se permiten repeticiones**, el `Set` es la estructura adecuada.

---

## ⚙️ **Características principales de un `Set`**

| Propiedad                         | Descripción                                                                                                           |
| --------------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| **No permite duplicados**         | Dos elementos son considerados iguales si `a.equals(b)` es `true`.                                                    |
| **No tiene índices**              | No se puede acceder por posición como en una lista (`List`).                                                          |
| **Puede permitir valores `null`** | Depende de la implementación (`HashSet` sí, `TreeSet` no).                                                            |
| **Ordenamiento**                  | Varía según el tipo: no ordenado (`HashSet`), ordenado por inserción (`LinkedHashSet`), ordenado natural (`TreeSet`). |
| **Basado en hash o árboles**      | Internamente usa estructuras como `HashMap` o `TreeMap`.                                                              |

---

## 🧠 **Diferencias entre `Set` y otras colecciones**

| Característica            | `Set`                | `List`                      | `Map`                |
| ------------------------- | -------------------- | --------------------------- | -------------------- |
| Permite duplicados        | ❌ No                 | ✅ Sí                        | ❌ (claves únicas)    |
| Orden de elementos        | Depende del tipo     | Mantiene orden de inserción | No aplica            |
| Acceso por índice         | ❌ No                 | ✅ Sí                        | ❌ No                 |
| Clave-valor               | ❌ No                 | ❌ No                        | ✅ Sí                 |
| Ejemplo de implementación | `HashSet`, `TreeSet` | `ArrayList`, `LinkedList`   | `HashMap`, `TreeMap` |

---

# 🧩 **Tipos de `Set` en Java y sus usos**

## ### 🔸 5.1 `HashSet`

`HashSet` es una clase de Java que implementa la interfaz `Set`, y sirve para almacenar una colección de elementos únicos (sin duplicados) sin mantener ningún orden específico.

`HashSet` almacena elementos sin duplicados, sin orden, y ofrece operaciones muy rápidas (búsqueda, inserción y eliminación) en promedio.

`HashSet` usa internamente un `HashMap` para guardar sus elementos.
Cada elemento que agregas al `HashSet` se convierte en una clave dentro del `HashMap` (el valor asociado es un objeto constante interno llamado PRESENT).

* **Paquete:** `java.util.HashSet`
* **Estructura interna:** Usa un `HashMap` para almacenar elementos.
* **Orden:** No garantiza ningún orden de los elementos.
* **Permite `null`:** Sí (solo un elemento `null`).
* **Complejidad:**

  * Inserción, eliminación y búsqueda promedio: **O(1)**
  * Peor caso (colisiones): **O(n)**. Una colisión sucede cuando dos elementos diferentes generan el mismo valor hash, y por tanto, Java intenta guardarlos en la misma posición del arreglo interno

#### 🔹 Ejemplo:

```java
import java.util.HashSet;

public class EjemploHashSet {
    public static void main(String[] args) {
        // Crear un HashSet de Strings
        HashSet<String> nombres = new HashSet<>();

        // Agregar elementos
        nombres.add("Juan");
        nombres.add("María");
        nombres.add("Pedro");
        nombres.add("Juan"); // Duplicado, no se agrega

        // Mostrar los elementos
        System.out.println(nombres); // Orden aleatorio

        // Verificar si contiene un elemento
        System.out.println(nombres.contains("Pedro")); // true

        // Eliminar un elemento
        nombres.remove("María");

        // Recorrer el HashSet
        for (String nombre : nombres) {
            System.out.println(nombre);
        }

        // Tamaño
        System.out.println("Tamaño: " + nombres.size());
    }
}
```

## 💡 **Ejemplo práctico con objetos personalizados**

Cuando agregas objetos creados por ti (por ejemplo, una clase `Persona`), **debes sobrescribir `hashCode()` y `equals()`** para que `HashSet` sepa cómo comparar duplicados.

```java
import java.util.HashSet;
import java.util.Objects;

class Persona {
    String nombre;
    int edad;

    Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, edad);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona p = (Persona) o;
        return edad == p.edad && Objects.equals(nombre, p.nombre);
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + ")";
    }
}

public class EjemploHashSetObjeto {
    public static void main(String[] args) {
        HashSet<Persona> personas = new HashSet<>();

        personas.add(new Persona("Ana", 25));
        personas.add(new Persona("Luis", 30));
        personas.add(new Persona("Ana", 25)); // Duplicado, no se agrega

        System.out.println(personas);
    }
}
```

➡️ Este programa imprimirá:

```
[Ana (25), Luis (30)]
```

### ✅ **Ventajas**

* Muy rápido en operaciones básicas.
* Elimina duplicados automáticamente.
* Permite `null`.
* Ideal para grandes volúmenes de datos sin orden.

### ❌ **Desventajas**

* No mantiene orden de inserción ni natural.
* Sensible a una mala implementación de `hashCode()` o `equals()`.
* Puede consumir más memoria que otras estructuras.

---

## ### 🔸 5.2 `LinkedHashSet`

`LinkedHashSet` es una clase de Java que implementa la interfaz `Set`, al igual que `HashSet`, pero con una diferencia clave:

> 🔹 **Mantiene el orden en que se agregan los elementos (orden de inserción).**

### 🧠 **Cómo funciona internamente**

`LinkedHashSet` está basado en una combinación de:

* Un **`HashMap`** para almacenar los elementos (como `HashSet`).
* Una **lista doblemente enlazada** para mantener el orden de inserción.

---

### 🔹 Funcionamiento paso a paso

1. Cuando agregas un elemento con `add()`, internamente se calcula su `hashCode()`.
2. El elemento se guarda en un `HashMap` (igual que `HashSet`).
3. Además, se crea un **enlace (nodo)** que apunta al **anterior** y **siguiente elemento** insertado.
4. Así, al iterar sobre el `LinkedHashSet`, los elementos **se recorren en el mismo orden en que se agregaron**.
---

* **Paquete:** `java.util.LinkedHashSet`
* **Estructura interna:** Combina un `HashSet` con una lista doblemente enlazada.
* **Orden:** Mantiene el **orden de inserción**.
* **Permite `null`:** Sí.
* **Complejidad:**

  * Inserción, eliminación, búsqueda promedio: **O(1)**

#### 🔹 Ejemplo:

```java
import java.util.LinkedHashSet;

public class EjemploLinkedHashSet {
    public static void main(String[] args) {
        LinkedHashSet<String> nombres = new LinkedHashSet<>();

        nombres.add("Juan");
        nombres.add("María");
        nombres.add("Pedro");
        nombres.add("Ana");
        nombres.add("María"); // Duplicado, no se agrega

        System.out.println(nombres); // [Juan, María, Pedro, Ana]

        // Verificar si contiene un elemento
        System.out.println(nombres.contains("Pedro")); // true

        // Eliminar un elemento
        nombres.remove("Juan");

        // Recorrer el LinkedHashSet
        for (String nombre : nombres) {
            System.out.println(nombre);
        }

        // Tamaño
        System.out.println("Tamaño: " + nombres.size());
    }
}
```

### 🌐 **💡Aplicaciones reales**

| Aplicación                                | Ejemplo                                                                          |
| ----------------------------------------- | -------------------------------------------------------------------------------- |
| **Historial de visitas**                  | Almacenar las páginas visitadas en el orden en que se visitaron, sin duplicados. |
| **Listas únicas ordenadas**               | Registro de clientes únicos en el orden de llegada.                              |
| **Cachés simples (LRU)**                  | Guardar los últimos elementos accedidos.                                         |
| **Sistemas de logging**                   | Mantener los mensajes en orden cronológico, sin repetirse.                       |
| **Ordenar sin perder inserción original** | Filtrar duplicados en una lista sin cambiar su orden original.                   |

---

### ✅ **Ventajas**

* Mantiene el orden de inserción.
* Operaciones rápidas (`O(1)` promedio).
* Evita duplicados automáticamente.
* Permite `null`.

### ❌ **Desventajas**

* Ligeramente más lento que `HashSet` (por los enlaces).
* No permite ordenar por valor (solo conserva el orden en que se insertan).
* Requiere más memoria (por la lista doblemente enlazada interna).

---

## ### 🔸 5.3 `TreeSet`

`TreeSet` es una clase de Java que implementa la interfaz **`NavigableSet`**, la cual a su vez extiende **`SortedSet`** y **`Set`**.
Sirve para **almacenar elementos únicos** (sin duplicados) pero **ordenados automáticamente**.

> Un `TreeSet` es una colección ordenada que almacena elementos **sin duplicados**, manteniéndolos **ordenados de forma natural** o según un **comparador (`Comparator`)** que definas.

Internamente, `TreeSet` usa una estructura de datos llamada **árbol rojo-negro**
(red-black tree), que es un tipo de **árbol binario auto-balanceado**.

### 🔹 Paso a paso del funcionamiento

1. Cuando agregas un elemento, el `TreeSet` lo compara con los demás usando:

   * Su **orden natural** (por ejemplo, `String` se ordena alfabéticamente, `Integer` de menor a mayor), o
   * Un **`Comparator` personalizado** que tú definas.

2. El elemento se inserta en la posición correcta dentro del árbol, manteniendo el orden.

3. Si intentas agregar un elemento igual (`compareTo()` o `Comparator` devuelve 0), **no se inserta** (porque sería duplicado).

---

* **Paquete:** `java.util.TreeSet`
* **Estructura interna:** Implementado mediante un **árbol rojo-negro** (auto-balanceado).
* **Orden:** Orden **natural** (por ejemplo, alfabético) o definido por un **Comparator**.
* **Permite `null`:** ❌ No (lanza `NullPointerException`).
* **Complejidad:**

  * Inserción, eliminación, búsqueda: **O(log n)**

### 🔹 Ejemplo:

```java
import java.util.TreeSet;

public class EjemploTreeSet {
    public static void main(String[] args) {
        TreeSet<Integer> numeros = new TreeSet<>();

        numeros.add(5);
        numeros.add(1);
        numeros.add(3);
        numeros.add(2);
        numeros.add(3); // Duplicado, no se agrega

        System.out.println(numeros); // [1, 2, 3, 5]
    }
}
```

### 🧠 **Orden personalizado con `Comparator`**

Puedes indicar cómo quieres ordenar los elementos usando un **`Comparator`**.
Por ejemplo, si deseas ordenarlos **de mayor a menor**:

```java
import java.util.TreeSet;
import java.util.Comparator;

public class EjemploComparator {
    public static void main(String[] args) {
        TreeSet<Integer> numeros = new TreeSet<>(Comparator.reverseOrder());

        numeros.add(10);
        numeros.add(5);
        numeros.add(20);

        System.out.println(numeros); // [20, 10, 5]
    }
}
```

También puedes crear comparadores personalizados para tus propias clases.

---

### 🧩 **Ejemplo con objetos personalizados**

Si usas tus propias clases, debes definir cómo se comparan (ordenan).
Tienes dos opciones:

#### Opción 1: Implementar `Comparable`

```java
import java.util.TreeSet;

class Persona implements Comparable<Persona> {
    String nombre;
    int edad;

    Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public int compareTo(Persona p) {
        return Integer.compare(this.edad, p.edad); // Ordenar por edad
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + ")";
    }
}

public class EjemploTreeSetObjetos {
    public static void main(String[] args) {
        TreeSet<Persona> personas = new TreeSet<>();

        personas.add(new Persona("Ana", 25));
        personas.add(new Persona("Luis", 30));
        personas.add(new Persona("Carlos", 20));

        System.out.println(personas);
    }
}
```

📤 **Salida:**

```
[Carlos (20), Ana (25), Luis (30)]
```

#### Opción 2: Usar un `Comparator`

```java
TreeSet<Persona> personas = new TreeSet<>((a, b) -> a.nombre.compareTo(b.nombre));
```

➡️ Esto ordena las personas por nombre, en lugar de edad.

---

### 🧩 **Métodos más comunes**

| Método                   | Descripción                                                             |
| ------------------------ | ----------------------------------------------------------------------- |
| `add(E e)`               | Agrega un elemento si no existe.                                        |
| `remove(Object o)`       | Elimina un elemento.                                                    |
| `contains(Object o)`     | Verifica si el conjunto contiene un elemento.                           |
| `first()`                | Devuelve el primer elemento (menor).                                    |
| `last()`                 | Devuelve el último elemento (mayor).                                    |
| `higher(E e)`            | Devuelve el siguiente elemento mayor al dado.                           |
| `lower(E e)`             | Devuelve el siguiente elemento menor al dado.                           |
| `headSet(E toElement)`   | Devuelve un subconjunto de elementos menores a `toElement`.             |
| `tailSet(E fromElement)` | Devuelve un subconjunto de elementos mayores o iguales a `fromElement`. |
| `subSet(E from, E to)`   | Devuelve los elementos entre esos dos valores.                          |

---

## 🌐 **💡 Aplicaciones reales**

| Aplicación                  | Ejemplo                                                         |
| --------------------------- | --------------------------------------------------------------- |
| **Listas ordenadas únicas** | Listado de nombres o números en orden ascendente.               |
| **Rangos numéricos**        | Filtrar elementos mayores o menores que cierto valor.           |
| **Ranking de usuarios**     | Ordenar jugadores por puntaje.                                  |
| **Gestión de inventario**   | Mantener productos ordenados por código o nombre.               |
| **Autocompletado**          | Buscar palabras que comienzan con un prefijo (usando `subSet`). |

---

### ✅ **Ventajas**

* Mantiene los elementos ordenados automáticamente.
* No permite duplicados.
* Ofrece operaciones de rango (`headSet`, `tailSet`, etc.).
* Ideal para comparaciones y búsquedas ordenadas.

### ❌ **Desventajas**

* Más lento que `HashSet` y `LinkedHashSet` (usa O(log n)).
* No permite `null` (lanza `NullPointerException`).
* Mayor consumo de memoria (por estructura de árbol).

---

### ### 🔸 5.4 `EnumSet`

`EnumSet` es una **implementación especial de la interfaz `Set`** diseñada específicamente para trabajar con **tipos enumerados (`enum`)** en Java.

👉 Es parte del paquete:

```java
java.util
```

> 💡 En pocas palabras:
> `EnumSet` es una colección optimizada para almacenar **valores de un mismo tipo `enum`**, sin duplicados y con **máxima eficiencia**.

---

## ⚙️ **Características principales**

| Propiedad               | Descripción                                                |
| ----------------------- | ---------------------------------------------------------- |
| **Solo acepta**         | Elementos de un tipo `enum` específico.                    |
| **Duplicados**          | ❌ No permite.                                              |
| **Orden**               | ✅ Mantiene el orden natural de declaración del `enum`.     |
| **Eficiencia**          | ⚡ Extremadamente rápida (usa operaciones a nivel de bits). |
| **Permite `null`**      | ❌ No. Lanza `NullPointerException`.                        |
| **Inmutable o mutable** | ✅ Mutable (pero puede crearse una versión inmutable).      |

---

## 🧠 **¿Por qué existe `EnumSet`?**

Cuando trabajas con enumeraciones, podrías usar un `HashSet` o `TreeSet`,
pero sería **ineficiente** porque esas clases no están diseñadas para `enum`.

`EnumSet`:

* Usa **bits internos** para representar los valores de `enum`.
* Cada valor del `enum` ocupa **1 bit**, lo que lo hace increíblemente rápido y ligero.

Por eso es **la mejor opción** para manejar conjuntos de valores `enum`.

---

## 🌍 **Ejemplo del mundo real**

Supón que tienes un `enum` que representa los **días de la semana**:

```java
public enum Dia {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
}
```

Si quieres representar:

* Los días laborales.
* Los días de descanso.

👉 Usarías `EnumSet` porque:

* Solo hay un conjunto limitado de valores posibles.
* No quieres duplicados.
* Quieres eficiencia al realizar operaciones como unión, diferencia, etc.

---

## 💻 **Ejemplo básico**

```java
import java.util.EnumSet;

public class EjemploEnumSet {
    enum Dia {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
    }

    public static void main(String[] args) {
        // Crear un EnumSet con algunos días
        EnumSet<Dia> diasLaborales = EnumSet.of(
                Dia.LUNES, Dia.MARTES, Dia.MIERCOLES, Dia.JUEVES, Dia.VIERNES);

        EnumSet<Dia> finDeSemana = EnumSet.of(Dia.SABADO, Dia.DOMINGO);

        System.out.println("Días laborales: " + diasLaborales);
        System.out.println("Fin de semana: " + finDeSemana);
    }
}
```

📤 **Salida:**

```
Días laborales: [LUNES, MARTES, MIERCOLES, JUEVES, VIERNES]
Fin de semana: [SABADO, DOMINGO]
```

📌 **Observa:**

* El orden se mantiene igual al orden de declaración del `enum`.
* No hay duplicados.
* Muy simple de usar.

---

## ⚙️ **Cómo funciona internamente**

Internamente, `EnumSet` usa **máscaras de bits** para representar los elementos del `enum`.

Por ejemplo, si tu `enum` tiene 7 valores:

```
LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
```

Y tú creas:

```java
EnumSet.of(LUNES, MIERCOLES, VIERNES)
```

El conjunto se representará internamente como:

```
0101010
```

(donde cada bit encendido representa un valor presente).

👉 Esto hace que operaciones como:

* Unión (`addAll`)
* Intersección (`retainAll`)
* Diferencia (`removeAll`)

sean **extremadamente rápidas** (porque son simples operaciones binarias).

---

## 🧩 **Formas de crear un `EnumSet`**

| Método                            | Descripción                                                              | Ejemplo                                 |
| --------------------------------- | ------------------------------------------------------------------------ | --------------------------------------- |
| `EnumSet.allOf(TipoEnum.class)`   | Crea un conjunto con **todos los valores** del `enum`.                   | `EnumSet.allOf(Dia.class)`              |
| `EnumSet.noneOf(TipoEnum.class)`  | Crea un conjunto **vacío** del tipo indicado.                            | `EnumSet.noneOf(Dia.class)`             |
| `EnumSet.of(E... elementos)`      | Crea un conjunto con los elementos especificados.                        | `EnumSet.of(Dia.LUNES, Dia.MARTES)`     |
| `EnumSet.range(E desde, E hasta)` | Crea un conjunto con todos los valores en un **rango**.                  | `EnumSet.range(Dia.LUNES, Dia.VIERNES)` |
| `EnumSet.complementOf(EnumSet)`   | Crea un conjunto con los **elementos que no están** en el conjunto dado. | `EnumSet.complementOf(diasLaborales)`   |

---

## 🧩 **Ejemplos prácticos**

### ✅ Crear todos los días:

```java
EnumSet<Dia> todos = EnumSet.allOf(Dia.class);
System.out.println(todos); // [LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO]
```

### ✅ Crear un conjunto vacío:

```java
EnumSet<Dia> ninguno = EnumSet.noneOf(Dia.class);
ninguno.add(Dia.MARTES);
System.out.println(ninguno); // [MARTES]
```

### ✅ Crear un rango:

```java
EnumSet<Dia> semana = EnumSet.range(Dia.LUNES, Dia.VIERNES);
System.out.println(semana); // [LUNES, MARTES, MIERCOLES, JUEVES, VIERNES]
```

### ✅ Crear el complemento:

```java
EnumSet<Dia> finDeSemana = EnumSet.complementOf(semana);
System.out.println(finDeSemana); // [SABADO, DOMINGO]
```

## 🌐 **💡 Aplicaciones reales**

| Aplicación                     | Ejemplo                                       |
| ------------------------------ | --------------------------------------------- |
| **Gestión de permisos**        | `EnumSet.of(Permiso.LEER, Permiso.ESCRIBIR)`  |
| **Estados de un sistema**      | `EnumSet.of(Estado.ACTIVO, Estado.PENDIENTE)` |
| **Configuraciones o banderas** | Activar/desactivar opciones de una app.       |
| **Días laborales o festivos**  | Controlar días activos en un horario.         |
| **Tipos de notificaciones**    | Email, SMS, Push, etc.                        |

---

## 💡 **Ejemplo completo**

```java
import java.util.EnumSet;

public class DemoEnumSet {
    enum Permiso {
        LEER, ESCRIBIR, EJECUTAR, ELIMINAR
    }

    public static void main(String[] args) {
        EnumSet<Permiso> permisosUsuario = EnumSet.of(Permiso.LEER, Permiso.ESCRIBIR);

        System.out.println("Permisos del usuario: " + permisosUsuario);

        // Agregar un permiso
        permisosUsuario.add(Permiso.EJECUTAR);
        System.out.println("Después de agregar: " + permisosUsuario);

        // Eliminar un permiso
        permisosUsuario.remove(Permiso.ESCRIBIR);
        System.out.println("Después de eliminar: " + permisosUsuario);

        // Complemento (qué permisos le faltan)
        EnumSet<Permiso> faltantes = EnumSet.complementOf(permisosUsuario);
        System.out.println("Permisos faltantes: " + faltantes);
    }
}
```

📤 **Salida:**

```
Permisos del usuario: [LEER, ESCRIBIR]
Después de agregar: [LEER, ESCRIBIR, EJECUTAR]
Después de eliminar: [LEER, EJECUTAR]
Permisos faltantes: [ESCRIBIR, ELIMINAR]
```
---

* **Paquete:** `java.util.EnumSet`
* **Estructura interna:** Representación altamente optimizada de enums usando bits.
* **Solo admite:** Elementos de un tipo `enum`.
* **Orden:** Orden natural del `enum`.
* **No permite `null`.**

### ✅ **Ventajas**

* Extremadamente rápido y eficiente en memoria.
* Mantiene el orden de declaración del `enum`.
* Facilita operaciones en conjuntos (unión, intersección, diferencia).
* Evita errores de tipo (solo acepta un tipo de `enum`).
* Ideal para valores finitos y predefinidos.

### ❌ **Desventajas**

* Solo funciona con tipos `enum`.
* No permite `null`.
* No puede almacenar tipos genéricos o clases normales.
* No es sincronizado (no es thread-safe por defecto).

---

### ### 🔸 5.5 `CopyOnWriteArraySet`

* **Paquete:** `java.util.concurrent`
* **Estructura interna:** Basado en `CopyOnWriteArrayList`.
* **Propósito:** Seguridad en hilos (**thread-safe**).
* **Orden:** Mantiene el orden de inserción.
* **Complejidad:** Costosa en inserciones, eficiente en lecturas.

#### 🔹 Ejemplo:

```java
import java.util.concurrent.CopyOnWriteArraySet;

public class EjemploCopyOnWriteArraySet {
    public static void main(String[] args) {
        CopyOnWriteArraySet<String> usuarios = new CopyOnWriteArraySet<>();
        usuarios.add("Alice");
        usuarios.add("Bob");
        usuarios.add("Alice"); // Duplicado, no se agrega

        System.out.println(usuarios);
    }
}
```

#### ✅ Ventajas:

* Ideal para entornos concurrentes.
* Lecturas seguras sin bloqueos.

#### ❌ Desventajas:

* Inserciones y eliminaciones costosas (se copia internamente el arreglo).

---

## 🧮 **Métodos más usados en `Set`**

| Método                    | Descripción                             |
| ------------------------- | --------------------------------------- |
| `add(E e)`                | Agrega un elemento si no está presente. |
| `remove(Object o)`        | Elimina un elemento.                    |
| `contains(Object o)`      | Verifica si un elemento existe.         |
| `size()`                  | Devuelve el tamaño del conjunto.        |
| `isEmpty()`               | Indica si está vacío.                   |
| `clear()`                 | Elimina todos los elementos.            |
| `addAll(Collection c)`    | Unión de conjuntos.                     |
| `retainAll(Collection c)` | Intersección.                           |
| `removeAll(Collection c)` | Diferencia.                             |
| `iterator()`              | Itera sobre los elementos.              |

---

## 🌐 **Aplicaciones en el mundo real**

| Aplicación                           | Ejemplo                                          |
| ------------------------------------ | ------------------------------------------------ |
| **Bases de datos o sistemas únicos** | IDs únicos de usuarios.                          |
| **Filtros de duplicados**            | Evitar repetir correos, nombres, registros, etc. |
| **Procesamiento de datos**           | Eliminar duplicados en una lista grande.         |
| **Conjuntos matemáticos**            | Uniones, intersecciones y diferencias.           |
| **Control de acceso**                | Roles únicos de usuarios en un sistema.          |
| **Sistemas de cache o registros**    | Almacenar solo claves únicas.                    |

---

Los `Set` en Java son estructuras **ideales para manejar colecciones sin duplicados**, y existen múltiples variantes que equilibran **rendimiento**, **orden** y **seguridad en hilos**.
Elegir la implementación adecuada depende de tus necesidades:

| Necesidad                      | Implementación ideal  |
| ------------------------------ | --------------------- |
| Máxima velocidad               | `HashSet`             |
| Mantener orden de inserción    | `LinkedHashSet`       |
| Ordenar elementos naturalmente | `TreeSet`             |
| Trabajar con enums             | `EnumSet`             |
| Entorno multihilo seguro       | `CopyOnWriteArraySet` |


# 📘 **Tabla: Complejidad (Notación Big O) de los diferentes `Set` en Java**

| Operación / Tipo de Set     | **HashSet**                                          | **LinkedHashSet**                       | **TreeSet**                      | **EnumSet**        | **CopyOnWriteArraySet**       |
| --------------------------- | ---------------------------------------------------- | --------------------------------------- | -------------------------------- | ------------------ | ----------------------------- |
| **Estructura interna**      | `HashMap`                                            | `LinkedHashMap` (hash + lista doble)    | `TreeMap` (árbol rojo-negro)     | Bitmask interna    | `CopyOnWriteArrayList`        |
| **Orden de los elementos**  | No garantizado                                       | Orden de inserción                      | Orden natural o por `Comparator` | Orden del `enum`   | Orden de inserción            |
| **Insertar (`add`)**        | **O(1)** promedio<br>**O(n)** peor caso (colisiones) | **O(1)** promedio<br>**O(n)** peor caso | **O(log n)**                     | **O(1)**           | **O(n)**                      |
| **Eliminar (`remove`)**     | **O(1)** promedio<br>**O(n)** peor caso              | **O(1)** promedio<br>**O(n)** peor caso | **O(log n)**                     | **O(1)**           | **O(n)**                      |
| **Buscar (`contains`)**     | **O(1)** promedio<br>**O(n)** peor caso              | **O(1)** promedio<br>**O(n)** peor caso | **O(log n)**                     | **O(1)**           | **O(n)**                      |
| **Iterar (recorrer todos)** | **O(n)**                                             | **O(n)**                                | **O(n)**                         | **O(n)**           | **O(n)**                      |
| **Uso típico**              | Velocidad                                            | Velocidad + orden de inserción          | Ordenamiento automático          | Conjuntos de enums | Lecturas seguras en multihilo |

---

# 🧮 **Significado de las notaciones Big O**

| Notación       | Se lee como        | Significado intuitivo                                                      | Ejemplo práctico                                   |
| -------------- | ------------------ | -------------------------------------------------------------------------- | -------------------------------------------------- |
| **O(1)**       | “Tiempo constante” | La operación tarda lo mismo sin importar el número de elementos.           | Buscar un número en una tabla hash sin colisiones. |
| **O(log n)**   | “Logarítmico”      | El tiempo crece muy lentamente al aumentar los datos.                      | Buscar en un árbol ordenado (como `TreeSet`).      |
| **O(n)**       | “Lineal”           | El tiempo crece proporcionalmente a la cantidad de elementos.              | Recorrer una lista entera para buscar un elemento. |
| **O(n log n)** | “Quasilineal”      | Común en algoritmos de ordenamiento; no aplica a `Set`, pero útil saberlo. | Ordenar una lista grande con `Collections.sort()`. |
| **O(n²)**      | “Cuadrático”       | Muy costoso; cada elemento se compara con todos los demás.                 | Comparar todos los pares en una lista.             |

---

# ⚙️ **Interpretación práctica en los `Set`**

| Tipo de Set             | Rendimiento promedio                                         | Cuándo usarlo                                                                       |
| ----------------------- | ------------------------------------------------------------ | ----------------------------------------------------------------------------------- |
| **HashSet**             | ⚡ Más rápido en operaciones básicas (`O(1)`)                 | Cuando **no te importa el orden** y quieres **máxima velocidad**.                   |
| **LinkedHashSet**       | ⚡ Rápido y mantiene el orden de inserción (`O(1)` promedio)  | Cuando necesitas **preservar el orden en que se agregan los elementos**.            |
| **TreeSet**             | 📈 Más lento (`O(log n)`), pero **ordenado automáticamente** | Cuando necesitas **conjuntos ordenados** o **rangos de valores**.                   |
| **EnumSet**             | 🚀 Ultra rápido (`O(1)`), pero solo con `enum`               | Cuando trabajas con **valores predefinidos** (como días de la semana, roles, etc.). |
| **CopyOnWriteArraySet** | 🧵 Seguro en hilos, pero lento en inserciones (`O(n)`)       | Cuando tienes **múltiples hilos leyendo** y **pocas escrituras**.                   |


