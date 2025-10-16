# Estructuras de Datos

Una **estructura de datos** es una forma particular de organizar y almacenar datos en una computadora para que puedan ser utilizados de manera eficiente.  
Las estructuras de datos son fundamentales en la programación y el desarrollo de software porque permiten manejar grandes cantidades de información de manera organizada y optimizada.

---

## Tipos de Estructuras de Datos

### 🧩 Estructuras de Datos Lineales
- **Arreglos (Arrays):** Colecciones de elementos del mismo tipo, accesibles mediante índices.  
- **Listas Enlazadas (Linked Lists):** Colecciones de nodos donde cada nodo contiene un valor y una referencia al siguiente nodo.  
- **Pilas (Stacks):** Colecciones de elementos que siguen el principio *LIFO* (*Last In, First Out*).  
- **Colas (Queues):** Colecciones de elementos que siguen el principio *FIFO* (*First In, First Out*).

### 🌳 Estructuras de Datos No Lineales
- **Árboles (Trees):** Estructuras jerárquicas con un nodo raíz y nodos hijos. Ejemplos incluyen árboles binarios y árboles de búsqueda binaria.  
- **Grafos (Graphs):** Conjuntos de nodos conectados por aristas, utilizados para representar relaciones entre elementos.

### 🔑 Estructuras de Datos Asociativas
- **Tablas Hash (Hash Tables):** Estructuras que asocian claves únicas a valores, permitiendo búsquedas rápidas.  
- **Mapas (Maps):** Colecciones de pares clave-valor, similares a las tablas hash.

---

## 📈 Importancia de las Estructuras de Datos

- **Eficiencia:** Permiten el acceso y manipulación rápida de datos.  
- **Organización:** Facilitan la organización lógica de la información.  
- **Optimización:** Mejoran el rendimiento de los algoritmos y aplicaciones.  
- **Escalabilidad:** Ayudan a manejar grandes volúmenes de datos sin degradar el rendimiento.

---

> En resumen, elegir la estructura de datos adecuada para una tarea específica puede hacer una gran diferencia en la eficiencia y efectividad de un programa.


# Documentación De metodos mas utilizados en las Estructuras de datos en JAVA

Este documento recopila los **métodos principales** de las colecciones más utilizadas en Java.  
Cada estructura tiene su propia utilidad y comportamiento específico.

---

## 1. ArrayList<E>
Una lista dinámica basada en arrays. Permite acceso rápido por índice.

### Métodos principales
- **add(E e)** → Agrega un elemento al final.  
- **add(int index, E e)** → Inserta un elemento en una posición específica.  
- **get(int index)** → Retorna el elemento en la posición indicada.  
- **set(int index, E e)** → Reemplaza el elemento en esa posición.  
- **remove(int index)** → Elimina el elemento en esa posición.  
- **size()** → Devuelve la cantidad de elementos.  
- **contains(Object o)** → Verifica si existe un elemento.  

```java
ArrayList<String> lista = new ArrayList<>();
lista.add("Luna");
lista.add("Marte");
System.out.println(lista.get(0)); // Luna
```

---

## 2. LinkedList<E>
Lista doblemente enlazada. Ideal para inserciones y eliminaciones frecuentes.

### Métodos principales
- **addFirst(E e)** → Inserta al inicio.  
- **addLast(E e)** → Inserta al final.  
- **removeFirst()** → Elimina el primero.  
- **removeLast()** → Elimina el último.  
- **getFirst()** → Obtiene el primero.  
- **getLast()** → Obtiene el último.  
- **offer(E e)** → Inserta al final (como cola).  
- **poll()** → Retorna y elimina el primer elemento (como cola).  

```java
LinkedList<String> cola = new LinkedList<>();
cola.add("Uno");
cola.add("Dos");
System.out.println(cola.poll()); // Uno
```

---

## 3. HashSet<E>
Conjunto sin duplicados. No garantiza orden.

### Métodos principales
- **add(E e)** → Agrega un elemento si no existe.  
- **remove(Object o)** → Elimina un elemento.  
- **contains(Object o)** → Verifica si existe.  
- **size()** → Cantidad de elementos.  
- **isEmpty()** → Verifica si está vacío.  
- **clear()** → Vacía el conjunto.  

```java
HashSet<Integer> numeros = new HashSet<>();
numeros.add(10);
numeros.add(20);
numeros.add(10); // No se agrega
System.out.println(numeros.size()); // 2
```

---

## 4. LinkedHashSet<E>
Conjunto sin duplicados que mantiene el orden de inserción.

### Métodos principales
Mismos que `HashSet`, pero preserva el **orden en que se insertan los elementos**.

```java
LinkedHashSet<String> ordenado = new LinkedHashSet<>();
ordenado.add("Rojo");
ordenado.add("Verde");
ordenado.add("Azul");
System.out.println(ordenado); // [Rojo, Verde, Azul]
```

---

## 5. TreeSet<E>
Conjunto ordenado de manera **natural** o mediante un `Comparator`.

### Métodos principales
- **first()** → Retorna el primer elemento.  
- **last()** → Retorna el último.  
- **headSet(E toElement)** → Subconjunto de los menores que el elemento dado.  
- **tailSet(E fromElement)** → Subconjunto de los mayores o iguales al dado.  
- **subSet(E from, E to)** → Subconjunto entre dos valores.  
- **ceiling(E e)** → El menor elemento mayor o igual a `e`.  
- **floor(E e)** → El mayor elemento menor o igual a `e`.  

```java
TreeSet<Integer> arbol = new TreeSet<>();
arbol.add(5);
arbol.add(1);
arbol.add(10);
System.out.println(arbol); // [1, 5, 10]
```

---

## 6. HashMap<K, V>
Mapa sin orden, asocia claves únicas con valores.

### Métodos principales
- **put(K key, V value)** → Inserta o reemplaza un valor con su clave.  
- **get(Object key)** → Obtiene el valor asociado a una clave.  
- **remove(Object key)** → Elimina la entrada con esa clave.  
- **containsKey(Object key)** → Verifica si existe la clave.  
- **containsValue(Object value)** → Verifica si existe el valor.  
- **keySet()** → Retorna el conjunto de claves.  
- **values()** → Retorna la colección de valores.  
- **entrySet()** → Retorna pares clave-valor.  

```java
HashMap<String, String> mapa = new HashMap<>();
mapa.put("CO", "Colombia");
mapa.put("MX", "México");
System.out.println(mapa.get("CO")); // Colombia
```

---

## 7. TreeMap<K, V>
Mapa ordenado por la clave (orden natural o `Comparator`).

### Métodos principales
- **firstKey()** → Retorna la primera clave.  
- **lastKey()** → Retorna la última clave.  
- **headMap(K toKey)** → Mapa de claves menores que `toKey`.  
- **tailMap(K fromKey)** → Mapa de claves mayores o iguales a `fromKey`.  
- **subMap(K fromKey, K toKey)** → Mapa entre dos claves.  
- **ceilingKey(K key)** → Menor clave mayor o igual que `key`.  
- **floorKey(K key)** → Mayor clave menor o igual que `key`.  

```java
TreeMap<Integer, String> ordenado = new TreeMap<>();
ordenado.put(2, "B");
ordenado.put(1, "A");
ordenado.put(3, "C");
System.out.println(ordenado); // {1=A, 2=B, 3=C}
```

---

## 8. LinkedHashMap<K, V>
Mapa que mantiene el orden de inserción de las claves.  
(Existe también con orden de acceso si se configura).

### Métodos principales
Mismos que `HashMap`, pero preserva el orden en que se insertan las claves.

```java
LinkedHashMap<String, Integer> ordenadoMapa = new LinkedHashMap<>();
ordenadoMapa.put("Uno", 1);
ordenadoMapa.put("Dos", 2);
ordenadoMapa.put("Tres", 3);
System.out.println(ordenadoMapa); // {Uno=1, Dos=2, Tres=3}
```

---

# Nota final
- **ArrayList / LinkedList** → listas con diferencias en rendimiento.  
- **HashSet / LinkedHashSet / TreeSet** → conjuntos sin duplicados con distintos tipos de orden.  
- **HashMap / LinkedHashMap / TreeMap** → mapas con distintas estrategias de orden.  