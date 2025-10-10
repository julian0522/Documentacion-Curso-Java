# Arrays

En Java, un **arreglo (array)** es una estructura de datos que **almacena una colección de elementos del mismo tipo**, en una **posición fija y ordenada**. Se usa para manejar listas de datos cuando sabes de antemano cuántos elementos vas a tener.

Es una **colección de variables del mismo tipo**, almacenadas **en posiciones contiguas de memoria** y accesibles por un **índice numérico** que comienza en **0**.

### 🔒 En Java, los **arreglos (`arrays`) tienen tamaño fijo**, lo que significa que:

- **No puedes agregar ni eliminar elementos directamente** una vez creado el arreglo.
- El tamaño del arreglo **se define al crearlo** y **no puede cambiarse después**.

### ❗ Características importantes

- **Tamaño fijo**: no puedes cambiar el tamaño una vez creado.
- **Índices inician en 0**.
- Lanza `ArrayIndexOutOfBoundsException` si accedes a una posición inválida.

## 🛠️ ¿Cómo se crean los arreglos en Java?

### 🧪 1. Declarar y crear con tamaño fijo:

```java
int[] numeros = new int[5]; // arreglo de 5 enteros
```

### 🧪 2. Declarar y asignar valores directamente:

```java
String[] frutas = {"Manzana", "Banana", "Pera"};
```

### 🧪 3. Declaración separada:

```java
int[] edades;           // declaración
edades = new int[3];    // creación
```

## 🔁 ¿Cómo se usan los arreglos?

### ✅ Acceder a un elemento:

```java
System.out.println(frutas[1]); // "Banana"
```

### ✅ Modificar un elemento:

```java
frutas[0] = "Mango"; // cambia "Manzana" por "Mango"
```

### ✅ Obtener la longitud:

```java
System.out.println(frutas.length); // 3
```

### ✅ Recorrer con un `for` clásico:

```java
for (int i = 0; i < frutas.length; i++) {
    System.out.println(frutas[i]);
}
```

### ✅ Recorrer con `for-each`:

```java
for (String fruta : frutas) {
    System.out.println(fruta);
}
```

Los Arreglos en java no cuentan con métodos como el objeto String, ya que estos  **NO tienen métodos propios**, porque los arreglos en Java son una **estructura de bajo nivel** que se comporta como un **objeto especial** en la JVM.

### **El error (excepción) `ArrayIndexOutOfBoundsException`**

Es un error común. Ocurre cuando intentas acceder a un elemento usando un índice que está fuera del rango válido (menor que `0` o mayor o igual a `length`).

```java
int[] smallArray = new int[2]; // Índices válidos: 0 y 1
System.out.println(smallArray[2]); // Esto causaría un ArrayIndexOutOfBoundsException
```

## **Arrays de Objetos**

Puedes crear arrays para almacenar referencias a objetos.

```java
// Asumiendo que tienes una clase Dog
Dog[] dogs = new Dog[4]; // Crea un array para 4 referencias a objetos Dog (inicializadas a null)

dogs[0] = new Dog("Buddy"); // Crea un objeto Dog y lo asigna al primer elemento
dogs[1] = new Dog("Max");

System.out.println(dogs[0].getName()); // Accede a un método del objeto en el array
System.out.println(dogs[2]); // Salida: null (el tercer elemento no ha sido inicializado con un objeto)
```

Es importante recordar que el array solo almacena las *referencias*. Debes crear los objetos individualmente y asignarlos a las posiciones del array.

### 🔧 ¿Y entonces cómo se trabaja con arreglos?

Java te ofrece herramientas auxiliares en clases utilitarias, como:

### 📦 `java.util.Arrays`

Esta clase **sí** tiene muchos métodos útiles para trabajar con arreglos, aunque **no son métodos del arreglo como tal**, sino **métodos estáticos** que reciben arreglos como parámetro.

## 🧰 Métodos útiles de la clase `Arrays`

| Método | Descripción |
| --- | --- |
| `Arrays.toString(array)` | Convierte el arreglo en una cadena legible |
| `Arrays.sort(array)` | Ordena los elementos del arreglo |
| `Arrays.copyOf(array, newLen)` | Crea una copia con nuevo tamaño |
| `Arrays.equals(a1, a2)` | Compara si dos arreglos son iguales |
| `Arrays.fill(array, valor)` | Rellena todo el arreglo con un valor |
| `Arrays.binarySearch(array, x)` | Busca un valor (el arreglo debe estar ordenado) |
| `Arrays.deepToString(arraymultiple)` | Convierte un array multidimensional a una cadena legible |

Ejemplo

```java
import java.util.Arrays;

public class EjemploArrays {
    public static void main(String[] args) {
        int[] numeros = {3, 1, 4, 2};

        Arrays.sort(numeros); // Ordena: [1, 2, 3, 4]
        System.out.println(Arrays.toString(numeros));

        int[] copia = Arrays.copyOf(numeros, 6); // [1, 2, 3, 4, 0, 0]
        System.out.println(Arrays.toString(copia));
				
				// Significa: desde el índice 4 (inclusive) hasta el 6 (exclusivo), pone el valor 9.
        Arrays.fill(copia, 4, 6, 9); // [1, 2, 3, 4, 9, 9]
        System.out.println(Arrays.toString(copia));
    }
}
```

## 📌 ¿Qué son los arreglos multidimensionales?

Son **arreglos de arreglos**, es decir, una estructura en forma de tabla (o más profunda), como una **matriz** en matemáticas.

---

## ✅ Arreglos **bidimensionales** (matrices)

### 🧠 Declaración:

```java
int[][] matriz = new int[3][4]; // 3 filas, 4 columnas
```

Esto crea una **matriz de 3 filas y 4 columnas**, llena de ceros por defecto.

---

### 🧪 Ejemplo: acceso y asignación

```java
matriz[0][0] = 10;
matriz[2][3] = 99;
System.out.println(matriz[0][0]); // 10
System.out.println(matriz[2][3]); // 99
```

---

### ✍️ Inicializar directamente:

```java
int[][] numeros = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
// numeros[1][2] es 6
```

---

### 🔁 Recorrer con bucles:

```java
for (int i = 0; i < numeros.length; i++) { // filas
    for (int j = 0; j < numeros[i].length; j++) { // columnas
        System.out.print(numeros[i][j] + " ");
    }
    System.out.println();
}
```

---

## ✅ Arreglos de más dimensiones (3D o más)

```java
int[][][] cubo = new int[2][3][4]; // 2 bloques, cada uno con 3 filas y 4 columna
```

Acceso:

```java
cubo[0][2][3] = 5;
System.out.println(cubo[0][2][3]); // 5
```

Pero en la mayoría de los programas, lo más común es usar **hasta 2 dimensiones**.

---

## 🚨 Importante:

- Los arreglos multidimensionales en Java **no son matrices reales** como en otros lenguajes.
- Son **arreglos de arreglos**, por eso puedes hacer cosas como:

```java
int[][] desigual = {
    {1, 2},
    {3, 4, 5},
    {6}
};
```

Esta matriz tiene:

- Fila 0: 2 columnas
- Fila 1: 3 columnas
- Fila 2: 1 columna

Eso se llama **arreglo irregular o "jagged array"**.

## Ejemplo de como se Usan los Arreglos Bidimensionales

```java
System.out.println();
        var scanner = new Scanner(System.in);

        System.out.print("Ingresa el numero de filas: ");
        var len = scanner.nextInt(); // Numero filas

        int[][] board = new int[len][]; // Se define la primera dimension (filas)

        for (int row = 0; row < board.length; row++) { // Recorriendo cada fila del tablero

            System.out.printf("Ingrese el numero de columnas de la fila %d: ", (row + 1));
            len = scanner.nextInt();

            board[row] = new int[len]; // Se define el tamaño del array para la segunda dimension (columnas)

            for (int column = 0; column < board[row].length; column++) { // recorriendo las columnas de la fila del
                                                                         // tablero

                System.out.printf("Ingrese el len para la fila %d, columna %d: ",
                        (row + 1), (column + 1));

                board[row][column] = scanner.nextInt(); // Asignar el valor del teclado a la fila, columna del tablero
            }
        }
        scanner.close();

        // Imprimir el array creado anteriormente

        System.out.println("=== Contenido del tablero ===");
        for (int row = 0; row < board.length; row++) { // Recorro cada fila del tablero
            for (int column = 0; column < board[row].length; column++) { // Recorro cada columna de la fila del tablero
                System.out.print(board[row][column] + " "); // Imprimir el contenio de esa fila, columna
            }
            System.out.println();
        }
```