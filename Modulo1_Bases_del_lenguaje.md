# Modulo 1 Bases del Lenguaje
# 📚 Índice

1. [Tipos De Datos](#tipos-de-datos)
   - [Primitivos](#primitivos)
     - [1. Enteros (números sin decimales)](#1-enteros-números-sin-decimales)
     - [2. Decimales (punto flotante)](#2-decimales-punto-flotante)
     - [3. Carácter](#3-carácter)
     - [4. Booleano (lógico)](#4-booleano-lógico)
	- [No Primitivos](#No Primitivos (**referenciados** o **reference types**))
2. [No Primitivos (referenciados o reference types)](#no-primitivos-referenciados-o-reference-types)
3. [Diferencia entre Tipos de Datos Primitivos y no Primitivos](#diferencia-entre-tipos-de-datos-primitivos-y-no-primitivos)
4. [Inferencia de Tipo con `var` (Java 10+)](#inferencia-de-tipo-con-var-java-10)
5. [Constantes](#constantes)
   - [Sintaxis](#-sintaxis)
   - [Ejemplo](#-ejemplo)
   - [¿Qué pasa si intento cambiar una constante?](#️-qué-pasa-si-intento-cambiar-una-constante)
   - [¿Dónde se usan?](#-dónde-se-usan)
6. [Objeto String](#objeto-string)
   - [¿Qué es un String en Java?](#-qué-es-un-string-en-java)
   - [¿Qué es un String literal?](#qué-es-un-string-literal)
   - [Métodos Para Los Strings](#métodos-para-los-strings)
   - [Cadena de caracteres multilínea](#cadena-de-caracteres-multilínea)
7. [Conversión de Tipos (Type Casting)](#conversión-de-tipos-type-casting)
   - [Conversión Implícita (Widening Conversion - Ampliación)](#conversión-implícita-widening-conversion---ampliación)
   - [Conversión Explícita (Casting - Narrowing Conversion - Estrechamiento)](#conversión-explícita-casting---narrowing-conversion---estrechamiento)

---

## ¿Qué Es Java?

**Java** es un **lenguaje de programación** y también una **plataforma de desarrollo** ampliamente utilizado en el mundo del software. Fue creado por **Sun Microsystems** en 1995 (ahora propiedad de **Oracle**).

### **¿Qué tipo de lenguaje es Java?**

- **Orientado a objetos:** Java organiza el código en clases y objetos.
- **Multiplataforma:** Gracias a la **JVM (Java Virtual Machine)**, el mismo programa puede ejecutarse en Windows, Linux, Mac, etc., sin modificaciones.
- **Compilado e interpretado:** Primero se compila a **bytecode**, y luego se ejecuta en la JVM.
- **Fuertemente tipado:** Las variables tienen tipos definidos y deben respetarse.
- **Popular en backend, móviles (Android), y sistemas empresariales.**

---

### 🔹 ¿Para qué se usa Java?

1. 🖥 **Aplicaciones de escritorio** (Swing, JavaFX)
2. 🌐 **Aplicaciones web backend** (Spring, Jakarta EE)
3. 📱 **Aplicaciones móviles Android** (junto con Kotlin)
4. ⚙ **Sistemas empresariales y bancarios**
5. ☁ **Aplicaciones en la nube y microservicios**
6. 📡 **Dispositivos embebidos, cajeros automáticos, etc.**

## Tipos De Datos

### Primitivos

En Java, los **tipos de datos primitivos** son los más básicos y fundamentales del lenguaje. No son objetos, y se almacenan directamente en memoria. Java tiene **8 tipos de datos primitivos**, organizados en 4 grupos principales:

### 🔹 1. **Enteros (números sin decimales)**

| Tipo | Tamaño | Rango aproximado | Ejemplo |
| --- | --- | --- | --- |
| `byte` | 8 bits | -128 a 127 | `byte edad = 25;` |
| `short` | 16 bits | -32,768 a 32,767 | `short dias = 365;` |
| `int` | 32 bits | -2,147,483,648 a 2,147,483,647 | `int numero = 1000;` |
| `long` | 64 bits | ±9 trillones (±9×10¹⁸) | `long poblacion = 8000000000L;` |

---

### 🔹 2. **Decimales (punto flotante)**

| Tipo | Tamaño | Precisión | Ejemplo |
| --- | --- | --- | --- |
| `float` | 32 bits | ~6-7 cifras dec. | `float pi = 3.14f;` |
| `double` | 64 bits | ~15 cifras dec. | `double e = 2.71828;` |

---

### 🔹 3. **Carácter**

| Tipo | Tamaño | Rango | Ejemplo |
| --- | --- | --- | --- |
| `char` | 16 bits | 0 a 65,535 (Unicode) | `char letra = 'A';` |

---

### 🔹 4. **Booleano (lógico)**

| Tipo | Tamaño | Valores posibles | Ejemplo |
| --- | --- | --- | --- |
| `boolean` | 1 bit | `true` o `false` | `boolean activo = true;` |

```java
public class TiposPrimitivosJava {
    public static void main(String[] args) {
        // Enteros
        byte miByte = 127;              // Rango: -128 a 127
        short miShort = 32000;          // Rango: -32,768 a 32,767
        int miInt = 2147483647;         // Rango: -2^31 a 2^31-1
        long miLong = 9223372036854775807L; // Rango: -2^63 a 2^63-1

        // Decimales
        float miFloat = 3.14f;          // Requiere la 'f' al final
        double miDouble = 3.14159265359;

        // Caracter
        char miChar = 'A';              // Un solo carácter (comillas simples)

        // Booleano
        boolean miBoolean = true;

        // Mostrar todos los valores
        System.out.println("byte: " + miByte);
        System.out.println("short: " + miShort);
        System.out.println("int: " + miInt);
        System.out.println("long: " + miLong);
        System.out.println("float: " + miFloat);
        System.out.println("double: " + miDouble);
        System.out.println("char: " + miChar);
        System.out.println("boolean: " + miBoolean);
    }
}
```

### No Primitivos (**referenciados** o **reference types**)

En Java, los **tipos de datos no primitivos** (también conocidos como **referenciados** o **reference types**) son aquellos que **no están predefinidos por el lenguaje**, sino que **se definen a partir de clases**. A diferencia de los tipos primitivos, que almacenan valores directamente, los tipos no primitivos almacenan **referencias** (punteros) a objetos en memoria, los tipos de datos no primitivos en java pueden ser:

- Clases
- String
- Interfaces
- Arrays
- Enumeraciones (Enums)
- Clases Wrapper (envoltorio de tipos primitivos): Cada tipo primitivo tiene una clase envoltorio (wrapper) correspondiente en `java.lang,`que permiten tratar los tipos primitivos como objetos.

### Diferencia entre Tipos de Datos Primitivos y no Primitivos

Los **tipos primitivos** son datos simples que almacenan valores directamente, mientras que los **tipos no primitivos** son referencias a objetos que pueden tener métodos, propiedades y funcionalidades más complejas.

```java
// Tipo primitivo, almacena directamente el valor 25
int edad = 25;

// Tipo no primitivo, alamcena una referencia al objeto "Carlos"
String nombre = "Carlos";
```

## **Inferencia de Tipo con `var` (Java 10+)**

A partir de Java 10, la palabra clave `var` te permite declarar variables locales sin especificar explícitamente su tipo, siempre y cuando el tipo pueda ser **inferido** por el compilador a partir de su valor inicial.

- **Concepto**: `var` no hace que Java sea un lenguaje de tipado dinámico. El tipo de la variable sigue siendo estático (se define en tiempo de compilación), solo que es el compilador quien lo deduce, en lugar de que tú lo escribas explícitamente.
- **Ventajas**:
    - **Concisión**: Reduce la verbosidad del código, especialmente con tipos complejos.
    - **Legibilidad**: Puede mejorar la legibilidad cuando el tipo es obvio a partir del inicializador.
- **Ejemplos**:
    
    ```java
    // En lugar de:
    // String nombreCompleto = "Ana María López";
    // int edadUsuario = 25;
    // double temperatura = 36.7;
    
    // Puedes usar 'var':
    var nombreCompleto = "Ana María López"; // Java infiere String
    var edadUsuario = 25;               // Java infiere int
    var temperatura = 36.7;             // Java infiere double
    
    System.out.println("Nombre: " + nombreCompleto);
    System.out.println("Edad: " + edadUsuario);
    System.out.println("Temperatura: " + temperatura);
    
    var listaNumeros = new java.util.ArrayList<Integer>(); // Java infiere ArrayList<Integer>
    // Es lo mismo que:
    // ArrayList<Integer> listaNumeros = new ArrayList<Integer>();
    ```
    
- **Limitaciones Importantes (Cuándo NO usar `var`)**:
    - **Solo para Variables Locales**: No se puede usar para declarar campos de clase, parámetros de métodos, o tipos de retorno de métodos.
    - **Debe ser Inicializada**: La variable `var` debe ser inicializada en la misma línea en que se declara. El compilador necesita el valor inicial para inferir el tipo.
        
        ```java
        var miVariable; // ERROR: No se puede usar 'var' sin inicializarla
        miVariable = 10;
        ```
        
    - **No puede inicializarse con `null`**: El compilador no puede inferir un tipo a partir de `null`.
        
        ```java
        var miObjeto = null; // ERROR: Tipo no se puede inferir
        ```
        
    - **No es tipado dinámico**: Una vez que el compilador infiere un tipo, ese tipo es fijo. No puedes reasignar un valor de un tipo diferente.
        
        ```java
        var contador = 0; // Infiere int
        // contador = "Hola"; // ERROR: No se puede asignar un String a un int
        ```
        
- **Buenas Prácticas con `var`**:
    - Úsala cuando el tipo sea obvio y la concisión mejore la legibilidad.
    - Evita usarla si el tipo no es evidente para el lector.

## Constantes

Una **constante** es una **variable cuyo valor no cambia nunca** después de que se ha inicializado.

En Java, las constantes se declaran usando la palabra clave:

```java
final
```

---

## 🔧 Sintaxis:

```java
final tipo NOMBRE = valor;
```

### 🧪 Ejemplo:

```java
final double PI = 3.1416;
final int MAX_USUARIOS = 100;
```

- `final` → Indica que **no se puede cambiar el valor**.
- `PI` y `MAX_USUARIOS` → Son constantes.
- Por convención, **se escriben en MAYÚSCULAS** y con `_` si hay más de una palabra.

---

## ⚠️ ¿Qué pasa si intento cambiar una constante?

```java
PI = 3.14; // ❌ ERROR: cannot assign a value to final variable 'PI'
```

El compilador te mostrará un error porque el valor **no puede modificarse**.

---

## 📌 ¿Dónde se usan?

1. Valores que **nunca cambian** (matemáticos, límites, configuraciones fijas).
2. Mejorar la **legibilidad del código**.
3. Evitar **errores** por reescritura accidental.
4. Usadas como constantes globales en clases utilitarias.

## Objeto String

En Java, un **`String`** es una clase que representa una secuencia de caracteres (como palabras, frases o textos en general). Es uno de los tipos más usados en Java y se encuentra en el paquete `java.lang`, este tipo de dato hace parte de los tipos de datos no primitivos.

### 🔤 ¿Qué es un `String` en Java?

- Es un **objeto inmutable**, lo que significa que **una vez creado, no se puede modificar** su contenido.
    
    (Si "modificas" un `String`, en realidad estás creando uno nuevo.)
    

🧱 ¿Qué es un **String literal**?

Un **string literal** es cualquier texto entre comillas dobles (`"..."`) en tu código fuente.

```java
String saludo = "Hola Mundo";
```

Cuando escribes un string así, Java automáticamente crea un objeto `String` y lo almacena en una **pila interna llamada el String Pool** (esto mejora el rendimiento y el uso de memoria).

### Métodos Para Los Strings

```java
String texto = "Java es genial";

// Longitud
int largo = texto.length(); // 14

// Reemplaza caracteres o subcadenas.
texto.replace('a', '@'); // "J@v@ es geni@l"
texto.replace("Java", "Python"); // "Python es genial"

// Verifica si mi string termina con el valor que le paso como parametro
boolean fin = texto.endsWith("nial"); // true

// Verifica si mi string inicia con el valor que le paso como parametro
boolean inicio = text.startsWith("Ja"); // true

// Mayúsculas
String mayus = texto.toUpperCase(); // "JAVA ES GENIAL"

// Minúsculas
String minus = texto.toLowerCase(); // "java es genial"

// Subcadena
String parte = texto.substring(0, 4); // "Java"

// Comparar
boolean esIgual = texto.equals("Java es genial"); // true

// Buscar
boolean contiene = texto.contains("genial"); // true

// Elimina espacios al inicio y al final.
"  Hola  ".trim(); // "Hola"

//Verifica si el string cumple con una expresión regular.
"abc123".matches("[a-z]+\\d+"); // true
```

- **Cadena de caracteres multilínea**:
    - Las cadenas de caracteres multilínea, también conocidas como bloques de texto, se introdujeron como una característica en Java 15. Estas permiten definir cadenas que abarcan varias líneas de una manera más legible y conveniente.
    - Se define utilizando tres comillas dobles (`"""`) al principio y al final del bloque de texto.
        
        ```
        String cadenaMultilinea = """
            Este es un ejemplo
            de una cadena
            multilínea en Java.
            """;
        ```
        
    - **Características y ventajas**
        - **Legibilidad**: Permite escribir cadenas largas y complejas de manera más clara y organizada.
        - **Manejo de espacios en blanco**: La indentación inicial se determina por la posición del cierre de las comillas triples, lo que ayuda a mantener el formato deseado.
        - **Compatibilidad con caracteres especiales**: No es necesario escapar caracteres especiales como las comillas dobles dentro del bloque de texto.
            
            ```java
            String consultaSQL = """
                SELECT *
                FROM usuarios
                WHERE edad > 18
                ORDER BY nombre;
                """;
            ```
            
        - E**spacios en blanco**: La indentación inicial se elimina automáticamente, pero cualquier espacio adicional dentro del bloque se mantiene.
        - **Errores comunes**: Asegúrate de que el bloque de texto comience con `"""` seguido de un salto de línea y termine con `"""` en una nueva línea para evitar errores de compilación.
            
            ```java
            String mensaje = """
                Hola,
                Este es un mensaje
                que abarca varias líneas.
                """;
            System.out.println(mensaje);
            ```
            

## **Conversión de Tipos (Type Casting)**

A veces, necesitas convertir un valor de un tipo de dato a otro. Java maneja esto de dos formas:

### **Conversión Implícita (Widening Conversion - Ampliación)**

- Ocurre automáticamente cuando conviertes un tipo de dato de "menor capacidad" a uno de "mayor capacidad".
- No hay riesgo de pérdida de datos.
- Ejemplo: Convertir un `int` a un `double`.
    
    ```java
    int miEntero = 10;
    double miDoble = miEntero; // Conversión implícita: 10 se convierte en 10.0
    
    System.out.println("Entero: " + miEntero); // Salida: Entero: 10
    System.out.println("Doble: " + miDoble);   // Salida: Doble: 10.0
    ```
    

### **Conversión Explícita (Casting - Narrowing Conversion - Estrechamiento)**

- Ocurre cuando conviertes un tipo de dato de "mayor capacidad" a uno de "menor capacidad".
- Requiere que le digas a Java explícitamente que estás de acuerdo con la posible pérdida de datos.
- Se usa el operador de casting `(tipoDato)`.
- **Advertencia**: Puede haber pérdida de información o truncamiento (decimales se pierden, valores fuera de rango se "envuelven").
- **Ejemplo**: Convertir un `double` a un `int`.
    
    ```java
    double otroDoble = 9.75;
    int otroEntero = (int) otroDoble; // Conversión explícita: 9.75 se trunca a 9
    
    System.out.println("Doble: " + otroDoble);     // Salida: Doble: 9.75
    System.out.println("Entero (cast): " + otroEntero); // Salida: Entero (cast): 9
    
    // Otro ejemplo con posible pérdida de información
    int numGrande = 200;
    byte numByte = (byte) numGrande; // byte solo va hasta 127. Ocurrirá un desbordamiento.
    System.out.println("Int original: " + numGrande); // Salida: Int original: 200
    System.out.println("Byte (cast): " + numByte);     // Salida: Byte (cast): -56 (valor incorrecto)
    ```
    

## Manejar Datos en Consola y Ventanas Emergentes

En Java, para **solicitar datos por consola** (entrada) y luego **mostrar resultados** (salida), se usa principalmente la clase `Scanner` para leer datos y `System.out.println()` para escribir en consola.

Cabe resaltar que cuando vamos a escanear o leer datos en consola, hay diferentes maneras de leer la información que recibimos dependiendo de lo que necesitemos, si el valor que vamos a recibir por la consola es un numero entero `int` lo que debemos hacer de una forma diferente como se ve a continuación:

```java
import java.util.Scanner;

public class ConsolaEjemplo {
    public static void main(String[] args) {
		    // Entrada de datos por consola
		    // Se crea una instancia de la clase Scanner
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingrese su edad: ");
        int edad = entrada.nextInt(); // Leer un numero entero 

        entrada.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese su nombre completo: ");
        String nombre = entrada.nextLine(); // Leer una linea entera con espacios un string

        System.out.print("Ingrese su estatura: ");
        float estatura = entrada.nextFloat(); // Leer un numero flotante

        System.out.print("Ingrese una letra: ");
         // Leer un caracter, en este caso definimos el indice del caracter a elegir
        char letra = entrada.next().charAt(0);

        System.out.println("---- DATOS INGRESADOS ----");
        System.out.println("Edad: " + edad);
        System.out.println("Nombre: " + nombre);
        System.out.println("Estatura: " + estatura);
        System.out.println("Letra: " + letra);
    }
}
```

En Java puedes solicitar y mostrar datos usando **ventanas emergentes (pop-ups)** gracias a la clase `JOptionPane`, que hace parte del paquete `javax.swing`.

```java
import javax.swing.JOptionPane;
// Todos los métodos son estáticos, así que no necesitas crear una instancia de JOptionPane.
// JOptionPane es ideal para interfaces sencillas, no para aplicaciones complejas.
// Siempre valida los datos si el usuario puede cancelar la ventana o escribir mal (usa try-catch).

public class VentanasEjemplo {
    public static void main(String[] args) {
		    // Para poder leer valores de variables que no sean string, se debe hacer la conversion a su respectivo tipo de dato
        String nombre = JOptionPane.showInputDialog("¿Cuál es tu nombre?");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("¿Qué edad tienes?"));
        float estatura = Float.parseFloat(JOptionPane.showInputDialog("¿Cuál es tu estatura?"));
        char letra = JOptionPane.showInputDialog("Escribe una letra").charAt(0);

        JOptionPane.showMessageDialog(null, 
            "Datos ingresados:\n" +
            "Nombre: " + nombre + "\n" +
            "Edad: " + edad + "\n" +
            "Estatura: " + estatura + "\n" +
            "Letra: " + letra
        );
    }
}
```

## Operadores

### 🔢 Operadores Aritméticos

Son símbolos que realizan operaciones matemáticas sobre valores o variables.

### ✅ Lista de operadores aritméticos en Java:

| **Operador** | **Descripción** | **Ejemplo (con resultado si `a = 10`, `b = 3`)** |
| --- | --- | --- |
| `+` | Suma | `a + b` → `13` |
| `-` | Resta | `a - b` → `7` |
| `*` | Multiplicación | `a * b` → `30` |
| `/` | División | `a / b` → `3` (división entera) |
| `%` | Módulo (resto división) | `a % b` → `1` |

---

### 📌 Detalles importantes:

### ➕ Suma:

```java
int x = 5 + 3; // 8
```

También sirve para **concatenar Strings**:

```java
String saludo = "Hola" + " mundo"; // "Hola mundo"
```

---

### ➗ División:

- Si ambos operandos son enteros (`int`), el resultado será **entero** (sin decimales).

```java
int res = 7 / 2; // Resultado: 3, no 3.5
```

- Para obtener decimales, al menos uno debe ser `double`:

```java
double res = 7.0 / 2; // Resultado: 3.5
```

---

### 🔢 Módulo `%`:

Devuelve el **resto** de una división.

```java
int r = 10 % 3; // 1, porque 10 ÷ 3 = 3 con residuo 1
```

---

## 🧮 Operadores combinados:

| Operador | Significado | Ejemplo |
| --- | --- | --- |
| `+=` | Suma y asigna | `x += 5;` ⇨ `x = x + 5;` |
| `-=` | Resta y asigna | `x -= 2;` ⇨ `x = x - 2;` |
| `*=` | Multiplica y asigna | `x *= 3;` ⇨ `x = x * 3;` |
| `/=` | Divide y asigna | `x /= 4;` ⇨ `x = x / 4;` |
| `%=` | Módulo y asigna | `x %= 2;` ⇨ `x = x % 2;` |

---

### 🧪 Ejemplo completo:

```java
int a = 10, b = 3;

System.out.println("Suma: " + (a + b));        // 13
System.out.println("Resta: " + (a - b));       // 7
System.out.println("Multiplicación: " + (a * b)); // 30
System.out.println("División: " + (a / b));    // 3
System.out.println("Módulo: " + (a % b));      // 1

// Esta es la manera de poder obtener el nuemro con decimales al realizar una division, con numeros flotantes y double
double d = 10.0 / 3.0; // = 3.3333333333
float m = 10f / 3f; // = 3.33333333333
```

### **Operadores de acumulación (Incremento y Decremento)**

| **Operador** | **Descripción** | **Ejemplo** | **Resultado** |
| --- | --- | --- | --- |
| `++` | Suma y asigna | `a++` | `a = a + 1` |
| `--` | Resta y asigna | `a--` | `a = a - 1` |
- Los operadores `++` y `-` en Java se utilizan para incrementar o decrementar el valor de una variable en 1, respectivamente.
- Estos operadores pueden ser utilizados en dos formas: **prefijo** y **sufijo**, y cada forma tiene un comportamiento ligeramente diferente.
- **Operador de Incremento (`++`)**
    - **Prefijo** (`++variable`): Incrementa el valor de la variable antes de que se utilice en la expresión.
    - **Sufijo** (`variable++`): Incrementa el valor de la variable después de que se utilice en la expresión.
- **Operador de Decremento (`-`)**
    - **Prefijo** (`--variable`): Decrementa el valor de la variable antes de que se utilice en la expresión.
    - **Sufijo** (`variable--`): Decrementa el valor de la variable después de que se utilice en la expresión.
    
    ```java
    int a = 5;
    int b = 5;
    
    // Incremento prefijo
    System.out.println("++a: " + ++a); // Output: 6
    // Incremento sufijo
    System.out.println("b++: " + b++); // Output: 5
    System.out.println("b después de b++: " + b); // Output: 6
    
    int c = 5;
    int d = 5;
    
    // Decremento prefijo
    System.out.println("--c: " + --c); // Output: 4
    // Decremento sufijo
    System.out.println("d--: " + d--); // Output: 5
    System.out.println("d después de d--: " + d); // Output: 4
    ```
    

### **Operadores de asignación compuesta**

| **Operador** | **Descripción** | **Ejemplo** | **Resultado** |
| --- | --- | --- | --- |
| `+=` | Suma y asigna | `a += b` | `a = a + b` |
| `-=` | Resta y asigna | `a -= b` | `a = a - b` |
| `*=` | Multiplica y asigna | `a *= b` | `a = a * b` |
| `/=` | Divide y asigna | `a /= b` | `a = a / b` |
| `%=` | Calcula el módulo y asigna | `a %= b` | `a = a % b` |
- Estos combinan una operación aritmética con una asignación.
- Estos operadores son útiles para simplificar el código y hacer las operaciones más concisas.
    
    ```java
    int x = 10;
    x += 5; // Ahora x es 15
    x -= 3; // Ahora x es 12
    x *= 2; // Ahora x es 24
    x /= 4; // Ahora x es 6
    x %= 5; // Ahora x es 1
    ```
    

### Operadores de Comparación

Comparan dos valores y devuelven `true` o `false`

| **Operador** | **Descripción** | **Ejemplo** | **Resultado** |
| --- | --- | --- | --- |
| `==` | Igual a | `5 == 5` | `true` |
| `!=` | Diferente de | `5 != 3` | `true` |
| `>` | Mayor que | `7 > 3` | `true` |
| `<` | Menor que | `2 < 5` | `true` |
| `>=` | Mayor o igual que | `5 >= 5` | `true` |
| `<=` | Menor o igual que | `3 <= 4` | `true` |

```java
int a = 10;
int b = 5;
boolean esIgual = (a == b); // false
boolean esMayor = (a > b);  // true
```

### Operadores Lógicos

Combinan o modifican expresiones booleanas. Son esenciales para crear condiciones más complejas.

- `&&` **(AND Lógico)**: El resultado es `true` si ambas condiciones son `true`. Si alguna es `false`, el resultado es `false`.
    
    
    | **`Condición 1`** | **`Condición 2`** | **`Condición 1 && Condición 2`** |
    | --- | --- | --- |
    | true | true | true |
    | true | false | false |
    | false | true | false |
    | false | false | false |
    
    ```java
    int edad = 20;
    double salario = 1500.0;
    
    // ¿Es mayor de 18 Y gana más de 1000?
    boolean cumpleRequisitos = (edad >= 18) && (salario > 1000); // true && true -> true
    ```
    
- `||` **(OR Lógico)**: El resultado es `true` si al menos una de las condiciones es `true`. Solo es `false` si ambas condiciones son `false`.
    
    
    | **`Condición 1`** | **`Condición 2`** | **`Condición 1 || Condición 2`** |
    | --- | --- | --- |
    | true | true | true |
    | true | false | true |
    | false | true | true |
    | false | false | false |
    
    ```java
    boolean tieneDescuento = false;
    boolean esClienteVIP = true;
    
    // ¿Tiene descuento O es cliente VIP?
    boolean aplicaBeneficio = tieneDescuento || esClienteVIP; // false || true -> true
    ```
    
- `!` **(NOT Lógico)**: Invierte el valor booleano de una condición. Si la condición es `true`, `!condicion` es `false`, y viceversa.
    
    
    | **`Condición`** | **`! Condición`** |
    | --- | --- |
    | true | false |
    | false | true |
    
    ```java
    boolean estaActivo = true;
    boolean estaInactivo = !estaActivo; // !(true) -> false
    ```
    

### **Precedencia de operadores**

La precedencia de operadores en Java determina el orden en que se evalúan los operadores en una expresión. Esto es crucial para asegurar que las operaciones se realicen en el orden correcto, similar a cómo se manejan las operaciones matemáticas.

| **Prioridad** | **Operador** |
| --- | --- |
| 1 | Operadores Postfijos: `expr++`, `expr--` |
| 2 | Operadores Unarios: `++expr`, `--expr`, `-expr` |
| 3 | Multiplicación y División: `*`, `/`, `%` |
| 4 | Suma y Resta: `+`, `-` |
| 5 | Asignación: `=`, `+=`, `-=`, `*=`, `/=`, `%=` |

Operadores de mayor precedencia (número de prioridad mas pequeño) se evalúan antes que los de menor precedencia. Operadores con la misma precedencia se evalúan de izquierda a derecha (asociatividad de izquierda) dependiendo del operador.

- **Uso de Paréntesis**: Los paréntesis se utilizan para alterar la precedencia natural de los operadores.
    
    ```java
    13 - 4 * ( 5 - 2 ) + 3 * ( 2 + 8 )
    13 - 4 *     3     + 3 *     10
    13 -   12          +   30
    31
    ```
    
    ```java
    int a = 10;
    int b = 5;
    int c = 2;
    
    // Ejemplo de precedencia
    int resultado = a + b * c; // Multiplicación se realiza primero
    System.out.println("Resultado: " + resultado); // Output: 20
    
    // Uso de paréntesis para alterar la precedencia
    resultado = (a + b) * c; // Suma se realiza primero
    System.out.println("Resultado con paréntesis: " + resultado); // Output: 30
    ```
    
    En el ejemplo anterior, sin paréntesis, la multiplicación se realiza antes que la suma debido a su mayor precedencia. Al usar paréntesis, forzamos a que la suma se realice primero.
    

## **Entrada y Salida de Datos por Consola y Diferencia Primitivos vs. Objetos**

### **Leyendo Entrada del Usuario con `Scanner`**

Para que tu programa pueda interactuar con el usuario y leer lo que este escribe en la consola, usamos la clase Scanner.

- **Pasos**:
    1. **Importar `Scanner`**: Al principio de tu archivo Java, antes de la declaración de la clase:
        
        ```java
        import java.util.Scanner;
        ```
        
    2. **Crear un objeto `Scanner`**: Dentro de tu método main:
        
        ```java
        Scanner scanner = new Scanner(System.in); // 'System.in' representa la entrada estándar (teclado)
        ```
        
    3. **Leer diferentes tipos de datos**:
        
        ```java
        System.out.print("Ingrese su edad: ");
        int edadUsuario = scanner.nextInt(); // Lee un entero
        
        System.out.print("Ingrese su salario: ");
        double salarioUsuario = scanner.nextDouble(); // Lee un double
        
        // ¡Importante! Limpiar el buffer después de nextInt/nextDouble
        // porque nextLine() solo lee el salto de línea restante.
        scanner.nextLine(); // Consume el salto de línea pendiente
        
        System.out.print("Ingrese su nombre completo: ");
        String nombreUsuario = scanner.nextLine(); // Lee una línea completa de texto
        
        System.out.println("Hola " + nombreUsuario + ", tienes " + edadUsuario + " años y ganas " + salarioUsuario);
        
        scanner.close(); // Es buena práctica cerrar el Scanner cuando ya no lo necesites
        ```
        
- **Nota sobre `scanner.nextLine()` después de `nextInt()`/`nextDouble()`**: Cuando usas `nextInt()` o `nextDouble()`, estos métodos leen solo el número, dejando el caracter de "salto de línea" (`\n`) en el buffer de entrada. Si llamas a `nextLine()` inmediatamente después, este leerá ese `\n` vacío y no esperará la entrada real del usuario. Por eso, se suele añadir un `scanner.nextLine()`; extra para "consumir" ese salto de línea pendiente.

### **Salida de Datos por Consola (`System.out`)**

Ya usamos `System.out.println()` para imprimir mensajes. Java ofrece otras formas para controlar la salida en la consola:

- `System.out.println()`: Imprime el contenido y luego inserta un salto de línea al final. El cursor se mueve a la siguiente línea.
    
    ```java
    System.out.println("Línea 1");
    System.out.println("Línea 2");
    
    // Salida:
    // Línea 1
    // Línea 2
    ```
    
- `System.out.print()`: Imprime el contenido sin añadir un salto de línea al final. El cursor permanece en la misma línea.
    
    ```java
    System.out.print("Parte 1");
    System.out.print(" Parte 2");
    System.out.println(" Parte 3 (con salto de línea al final)");
    System.out.print("Esto está en una nueva línea después del println.");
    
    // Salida:
    // Parte 1 Parte 2 Parte 3 (con salto de línea al final)
    // Esto está en una nueva línea después del println.
    ```
    
- `System.out.printf()` **(Salida Formateada)**: Permite imprimir texto con formato, similar a la función `printf` en C. Es muy útil para controlar el número de decimales, alinear texto, etc. Utiliza **especificadores de formato**.
    - **Especificadores comunes**:
        - `%s`: Cadena de texto (String).
        - `%d`: Número entero (Decimal integer).
        - `%f`: Número de punto flotante (Float/Double).
        - `%.2f`: Número de punto flotante con 2 decimales.
        - `%n`: Salto de línea (equivalente a `\n`).
    - **Ejemplo**:
        
        ```java
        String producto = "Laptop";
        double precio = 1250.758;
        int cantidad = 2;
        
        System.out.printf("El producto es %s, su precio es %.2f y la cantidad es %d.%n", producto, precio, cantidad);
        System.out.printf("Precio total: %.2f%n", precio * cantidad);
        
        // Salida:
        // El producto es Laptop, su precio es 1250.76 y la cantidad es 2.
        // Precio total: 2501.52
        ```
        
    - Observa cómo `%.2f` redondea el número y `%n` agrega un salto de línea.

### **Primitivos vs. Clases Envolventes (Wrapper Classes)**

Mientras que los tipos primitivos (`int`, `double`, `boolean`, etc.) almacenan directamente el valor, las **clases envolventes (Wrapper Classes)** son clases que "envuelven" a estos primitivos para darles funcionalidades de objeto. Esto es útil cuando necesitas tratar un valor primitivo como un objeto (ej. en colecciones de Java o cuando necesitas que un valor pueda ser `null`).

- Correspondencia de Wrapper Classes:
    - `byte` -> `Byte`
    - `short` -> `Short`
    - `int` -> `Integer`
    - `long` -> `Long`
    - `float` -> `Float`
    - `double` -> `Double`
    - `char` -> `Character`
    - `boolean` -> `Boolean`
- **Autoboxing y Unboxing**: Java realiza automáticamente las conversiones entre primitivos y sus wrappers cuando es necesario (ej. `Integer numero = 10;` esto es `autoboxing`).

## Estructuras Condicionales

Las estructuras condicionales te permiten ejecutar un bloque de código *solo si* una condición específica es verdadera (`true`).

### **1.1. Expresiones Booleanas: La Base de las Decisiones**

Una condición es una expresión que se evalúa a un valor booleano: `true` o `false`. Para construir condiciones, usamos:

- **Operadores de Comparación**: Comparan dos valores y devuelven `true` o `false`.
    
    
    | **Operador** | **Descripción** | **Ejemplo** | **Resultado** |
    | --- | --- | --- | --- |
    | `==` | Igual a | `5 == 5` | `true` |
    | `!=` | Diferente de | `5 != 3` | `true` |
    | `>` | Mayor que | `7 > 3` | `true` |
    | `<` | Menor que | `2 < 5` | `true` |
    | `>=` | Mayor o igual que | `5 >= 5` | `true` |
    | `<=` | Menor o igual que | `3 <= 4` | `true` |
    
    ```java
    int a = 10;
    int b = 5;
    boolean esIgual = (a == b); // false
    boolean esMayor = (a > b);  // true
    ```
    
- **Operadores Lógicos**: Combinan o modifican expresiones booleanas. Son esenciales para crear condiciones más complejas.
    - `&&` **(AND Lógico)**: El resultado es `true` si ambas condiciones son `true`. Si alguna es `false`, el resultado es `false`.
        
        
        | **`Condición 1`** | **`Condición 2`** | **`Condición 1 && Condición 2`** |
        | --- | --- | --- |
        | true | true | true |
        | true | false | false |
        | false | true | false |
        | false | false | false |
        
        ```java
        int edad = 20;
        double salario = 1500.0;
        
        // ¿Es mayor de 18 Y gana más de 1000?
        boolean cumpleRequisitos = (edad >= 18) && (salario > 1000); // true && true -> true
        ```
        
    - `||` **(OR Lógico)**: El resultado es `true` si al menos una de las condiciones es `true`. Solo es `false` si ambas condiciones son `false`.
        
        
        | **`Condición 1`** | **`Condición 2`** | **`Condición 1 || Condición 2`** |
        | --- | --- | --- |
        | true | true | true |
        | true | false | true |
        | false | true | true |
        | false | false | false |
        
        ```java
        boolean tieneDescuento = false;
        boolean esClienteVIP = true;
        
        // ¿Tiene descuento O es cliente VIP?
        boolean aplicaBeneficio = tieneDescuento || esClienteVIP; // false || true -> true
        ```
        
    - `!` **(NOT Lógico)**: Invierte el valor booleano de una condición. Si la condición es `true`, `!condicion` es `false`, y viceversa.
        
        
        | **`Condición`** | **`! Condición`** |
        | --- | --- |
        | true | false |
        | false | true |
        
        ```java
        boolean estaActivo = true;
        boolean estaInactivo = !estaActivo; // !(true) -> false
        ```
        

Combinando operadores de comparación y lógicos, puedes crear condiciones muy específicas:

```java
int temperatura = 25;
boolean estaLloviendo = false;

// ¿La temperatura está entre 20 y 30 grados (inclusive) Y NO está lloviendo?
boolean climaAgradable = (temperatura >= 20 && temperatura <= 30) && !estaLloviendo; // (true && true) && !(false) -> true && true -> true
```

### **1.2. La sentencia `if`**

Ejecuta un bloque de código si la condición dentro de los paréntesis es `true`.

```java
int numero = 10;

if (numero > 0) {
    // Este código se ejecuta SOLO si 'numero' es mayor que 0
    System.out.println("El número es positivo.");
}
// Si numero fuera -5, la línea anterior no se ejecutaría, y el programa continuaría aquí.
```

Si solo hay una línea de código dentro del `if`, las llaves `{ }` son opcionales, pero es una buena práctica usarlas siempre para evitar errores y mejorar la legibilidad.

### **1.3. La sentencia `if-else`**

Permite ejecutar un bloque de código si la condición es `true`, y un bloque alternativo si la condición es `false`.

```java
int numero = 7;

if (numero % 2 == 0) { // Si el residuo de la división por 2 es 0, es par
    System.out.println("El número es par.");
} else { // Si la condición del if es false...
    System.out.println("El número es impar.");
}
```

### **1.4. El Operador Ternario (`? :`)**

Es una forma concisa de escribir un `if-else` simple que **devuelve un valor**. Su sintaxis es: `condicion ? valor_si_true : valor_si_false;`. Es útil para asignaciones simples basadas en una condición.

```java
int edad = 20;
String mensaje;

// Usando if-else:
if (edad >= 18) {
    mensaje = "Mayor de edad";
} else {
    mensaje = "Menor de edad";
}
System.out.println(mensaje); // Salida: Mayor de edad

// Usando el operador ternario (equivalente al if-else anterior):
String mensajeTernario = (edad >= 18) ? "Mayor de edad" : "Menor de edad";
System.out.println(mensajeTernario); // Salida: Mayor de edad

// Otro ejemplo:
int num = 10;
String tipoNumero = (num % 2 == 0) ? "Par" : "Impar";
System.out.println("El número es: " + tipoNumero); // Salida: El número es: Par
```

Úsalo solo para lógicas simples que devuelven un valor; para lógicas más complejas o que no devuelven un valor, `if-else` es más legible, tener en cuenta que en algunos casos que deseemos evaluar mas de dos condiciones en un operador ternario se puede realizar de la siguiente forma:

```java
// Programa en java que recibe un numero entero y determina si este es
// impar, par o cero

int numero = 5;
String resultado = (numero == 0) ? "Es igual a cero" 
										: (numero % 2 == 0) ? "El numero es par" : "El numero es impar"

System.out.println(resultado); // Resultado: El numero es impar
```

En el operador ternario podemos evaluar mas de una condición y anidar ternarios para evaluar mas de una condición

### **1.5. La sentencia `if-else if-else`**

Es útil cuando necesitas evaluar `múltiples posibles condiciones` en secuencia. Java evalúa las condiciones de arriba hacia abajo. Ejecuta el primer bloque cuyo `if` o `else if` sea `true` y luego salta al final de toda la estructura `if-else if-else`. Si ninguna condición es `true`, ejecuta el bloque `else` final (si existe).

```java
int calificacion = 85;

if (calificacion >= 90) {
    System.out.println("Excelente");
} else if (calificacion >= 80) { // Se evalúa SOLO si la primera condición (calificacion >= 90) fue false
    System.out.println("Muy Bien");
} else if (calificacion >= 70) { // Se evalúa SOLO si las condiciones anteriores fueron false
    System.out.println("Bien");
} else { // Se ejecuta si ninguna de las condiciones anteriores fue true
    System.out.println("Necesita mejorar");
}
```

### **1.6. La sentencia `switch-case`**

Es una alternativa más limpia y legible al `if-else if` cuando necesitas seleccionar entre varias opciones basadas en el valor exacto de una variable. Funciona con tipos de datos como `int`, `byte`, `short`, `char`, `String`, y `enum` (veremos `enum` más adelante).

```java
int diaSemana = 3; // 1=Lunes, 2=Martes, etc.
String nombreDia;

switch (diaSemana) { // La variable a evaluar
    case 1: // Si diaSemana es 1...
        nombreDia = "Lunes";
        break; // Importante: sale del switch después de ejecutar el código del case
    case 2: // Si diaSemana es 2...
        nombreDia = "Martes";
        break;
    case 3: // Si diaSemana es 3...
        nombreDia = "Miércoles";
        break;
    case 4:
        nombreDia = "Jueves";
        break;
    case 5:
        nombreDia = "Viernes";
        break;
    case 6: // Múltiples casos pueden compartir el mismo bloque de código
    case 7:
        nombreDia = "Fin de semana";
        break;
    default: // Opcional: se ejecuta si el valor de diaSemana no coincide con ningún 'case'
        nombreDia = "Día inválido";
        break; // Es buena práctica poner break incluso en el default
}
System.out.println("El día es: " + nombreDia); // Salida: El día es: Miércoles
```

**¡Cuidado con el "fall-through"!** Si omites la palabra clave `break` al final de un `case`, Java continuará ejecutando el código del siguiente `case` (y los subsiguientes) hasta que encuentre un `break` o llegue al final del `switch`. Esto rara vez es deseado y es una fuente común de errores.

### **1.7. La expresión `switch` (Java 12+)**

A partir de Java 12 (estándar en Java 14), puedes usar el `switch` como una expresión que **devuelve un valor**. Esto elimina la necesidad de `break` para evitar el "fall-through" y hace el código más conciso. Usa la flecha `->` en lugar de dos puntos `:` y no requiere `break`.

```java
int diaSemana = 3; // 1=Lunes, 2=Martes, etc.

// Usando switch expression para asignar un valor a una variable
String nombreDiaModerno = switch (diaSemana) {
    case 1 -> "Lunes";
    case 2 -> "Martes";
    case 3 -> "Miércoles";
    case 4 -> "Jueves";
    case 5 -> "Viernes";
    case 6, 7 -> "Fin de semana"; // Múltiples casos separados por coma
    default -> "Día inválido";
}; // Nota el punto y coma al final de la expresión switch

System.out.println("El día (moderno) es: " + nombreDiaModerno); // Salida: El día (moderno) es: Miércoles
```

```java
// También puedes usar bloques con 'yield' si necesitas más de una línea de código en un case
int mes = 4;
int diasEnMes = switch (mes) {
    case 1, 3, 5, 7, 8, 10, 12 -> 31;
    case 4, 6, 9, 11 -> 30;
    case 2 -> {
        // Lógica más compleja si es necesario
        System.out.println("Considerando Febrero...");
        yield 28; // 'yield' devuelve el valor para este case
    }
    default -> {
        System.out.println("Mes inválido.");
        yield -1; // Devuelve -1 para un mes inválido
    }
};
System.out.println("Días en el mes " + mes + ": " + diasEnMes);
```

El `switch expression` es más seguro (no hay "fall-through" accidental) y más legible para asignaciones de valor basadas en múltiples casos.

## **Estructuras de Bucle**

Los bucles te permiten ejecutar un bloque de código varias veces sin tener que escribirlo repetidamente.

### **2.1. El ciclo `while`**

Repite un bloque de código mientras una condición sea `true`. La condición se verifica antes de cada posible iteración. Si la condición es `false` desde el principio, el código dentro del bucle nunca se ejecuta.

```java
int contador = 0; // Inicialización de una variable de control del bucle

while (contador < 5) { // Condición: el bucle se repite mientras 'contador' sea menor que 5
    System.out.println("Iteración: " + contador);
    contador++; // Actualización: incrementa 'contador' en 1. ¡Esto es CRUCIAL!
}
// Salida:
// Iteración: 0
// Iteración: 1
// Iteración: 2
// Iteración: 3
// Iteración: 4
// Cuando contador llega a 5, la condición (5 < 5) es false, y el bucle termina.
```

**¡Advertencia!** Si la condición del `while` nunca se vuelve `false`, tendrás un bucle infinito, y tu programa se quedará "colgado". Siempre asegúrate de que algo dentro del bucle modifique las variables involucradas en la condición para que eventualmente se vuelva `false`.

### **2.2. El ciclo `do-while`**

Similar a `while`, pero la condición se verifica **después** de cada iteración. Esto significa que el bloque de código dentro del `do-while` se ejecutará al menos una vez, incluso si la condición es `false` desde el principio.

```java
int numero;
Scanner scanner = new Scanner(System.in);

do {
    System.out.print("Ingrese un número positivo: ");
    numero = scanner.nextInt();
} while (numero <= 0); // La condición se evalúa después de la primera ejecución y en cada iteración subsiguiente

System.out.println("Número ingresado: " + numero);
scanner.close();
// Este bucle siempre pedirá un número al menos una vez. Si el primer número es positivo, la condición es false y el bucle termina. Si no, seguirá pidiendo hasta que se ingrese uno positivo.
```

Nota el punto y coma `;` al final de la línea `while (condicion);` en el `do-while`.

### **2.3. El ciclo `for`**

Es la estructura de bucle más común cuando sabes de antemano cuántas veces quieres que se repita un bloque de código, o cuando necesitas iterar sobre un rango de valores numéricos. Su sintaxis es muy compacta, agrupando la inicialización, la condición y la actualización en una sola línea.

```java
// Sintaxis: for (inicialización; condición; actualización) { // código }

for (int i = 1; i <= 10; i++) { // i empieza en 1; el bucle continúa mientras i <= 10; i se incrementa en 1 después de cada iteración
    System.out.println("Contando: " + i);
}
// Salida:
// Contando: 1
// Contando: 2
// ...
// Contando: 10
```

- **Inicialización**: Se ejecuta una sola vez al inicio del bucle. Aquí se suelen declarar e inicializar variables de control (ej. `int i = 1;`).
- **Condición**: Se evalúa antes de cada iteración. Si es `true`, el bucle continúa; si es `false`, el bucle termina.
- **Actualización**: Se ejecuta después de cada iteración (normalmente para modificar la variable de control, ej. `i++`).

Puedes omitir cualquiera de las tres partes del for (aunque la condición es necesaria para evitar un bucle infinito a menos que uses `break`), pero los puntos y comas `;` son obligatorios.

```java
// Ejemplo de for con decremento
for (int j = 5; j > 0; j--) { // j empieza en 5; se repite mientras j > 0; j se decrementa en 1
    System.out.println("Cuenta regresiva: " + j);
}
// Salida: Cuenta regresiva: 5 ... Cuenta regresiva: 1
```

## **3. Controlando el Flujo Dentro de los Bucles: `break` y `continue`**

Estas palabras clave te dan un control más fino sobre cómo se ejecutan los bucles.

### **3.1. `break`**

Sale *inmediatamente* del bucle más interno en el que se encuentra. La ejecución del programa continúa en la primera línea de código que sigue al bucle.

```java
for (int i = 1; i <= 10; i++) {
    if (i == 5) {
        System.out.println("Encontrado el número 5. Saliendo del bucle.");
        break; // Sale del bucle for
    }
    System.out.println("Procesando: " + i);
}
System.out.println("Después del bucle.");
// Salida:
// Procesando: 1
// Procesando: 2
// Procesando: 3
// Procesando: 4
// Encontrado el número 5. Saliendo del bucle.
// Después del bucle.
```

### **3.2. `continue`**

Salta el resto del código dentro del bucle para la *iteración actual* y pasa directamente a la *siguiente iteración*. En un bucle `for`, salta a la sección de actualización; en un `while` o `do-while`, salta a la evaluación de la condición.

```java
for (int i = 1; i <= 10; i++) {
    if (i % 2 != 0) { // Si i es impar...
        System.out.println("Saltando número impar: " + i);
        continue; // Salta el resto del código en esta iteración (el println de abajo)
    }
    // Este código solo se ejecuta si i es par
    System.out.println("Procesando número par: " + i);
}
// Salida:
// Saltando número impar: 1
// Procesando número par: 2
// Saltando número impar: 3
// Procesando número par: 4
// ... y así sucesivamente.
```

## Arrays

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

### **Arrays de Objetos**

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

## Métodos de Clase Y Métodos de Objeto

En Java, los **métodos de clase** y los **métodos de objeto** se diferencian principalmente por si pertenecen a la clase en sí o a una instancia (objeto) de la clase.

### ✅ Métodos de Clase (también llamados **métodos estáticos**)

- Se declaran con la palabra clave `static`
- **Pertenecen a la clase**, no a las instancias (objetos) de la clase
- **Se acceden usando el nombre de la clase**, no a través de un objeto

```java
// EJEMPLO
public class Utilidades {
    public static int sumar(int a, int b) {
        return a + b;
    }
}
// Uso
int resultado = Utilidades.sumar(3, 4);
```

### ✅ Métodos de Objeto (también llamados **métodos de instancia**)

- No se declaran con `static`
- Pertenecen a una instancia de la clase
- Solo se pueden usar después de crear un objeto de esa clase

```java
//EJEMPLO
public class Persona {
    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public void saludar() {
        System.out.println("Hola, soy " + nombre);
    }
}

// Uso
Persona p = new Persona("Carlos");
p.saludar();
```

Si necesitas un método para realizar una operación general que **no depende de datos del objeto**, usa un **método estático**.

Si el método depende de los atributos del objeto, debe ser un **método de instancia**.

## Objeto Math

En Java, el objeto `Math` es una **clase final** que proporciona métodos y constantes para realizar operaciones matemáticas comunes como:

- Potencias
- Raíces cuadradas
- Trigonometría
- Redondeo
- Máximos y mínimos
- Aleatoriedad, entre otros.

No necesitas crear una instancia de `Math` para usarla, ya que todos sus métodos son **estáticos**.

---

### 📌 Ejemplo general:

```java
double raiz = Math.sqrt(25);        // 5.0
int maximo = Math.max(10, 20);      // 20
double potencia = Math.pow(2, 3);   // 8.0
double aleatorio = Math.random();   // Un número entre 0.0 y 1.0
```

---

### ✅ Métodos más usados de `Math`

| Método | Descripción | Ejemplo |
| --- | --- | --- |
| `Math.abs(x)` | Valor absoluto | `Math.abs(-5)` → `5` |
| `Math.max(a, b)` | Retorna el mayor de dos valores | `Math.max(3, 7)` → `7` |
| `Math.min(a, b)` | Retorna el menor | `Math.min(3, 7)` → `3` |
| `Math.pow(base, exp)` | Potencia | `Math.pow(2, 4)` → `16.0` |
| `Math.sqrt(x)` | Raíz cuadrada | `Math.sqrt(9)` → `3.0` |
| `Math.cbrt(x)` | Raíz cúbica | `Math.cbrt(27)` → `3.0` |
| `Math.round(x)` | Redondeo al entero más cercano | `Math.round(5.6)` → `6` |
| `Math.ceil(x)` | Redondeo hacia arriba | `Math.ceil(4.2)` → `5.0` |
| `Math.floor(x)` | Redondeo hacia abajo | `Math.floor(4.9)` → `4.0` |
| `Math.random()` | Número aleatorio entre 0.0 y 1.0 | `Math.random()` |
| `Math.toRadians(x)` | Convierte grados a radianes | `Math.toRadians(180)` → `π` |
| `Math.toDegrees(x)` | Convierte radianes a grados | `Math.toDegrees(Math.PI)` → `180.0` |

---

### 📌 Constantes útiles

| Constante | Valor | Descripción |
| --- | --- | --- |
| `Math.PI` | 3.14159265... | El valor de π |
| `Math.E` | 2.7182818... | Base de los logaritmos naturales |

---

### 🎯 Ejemplo práctico: Cálculo de la hipotenusa

```java
public class EjemploMath {
    public static void main(String[] args) {
        double a = 3;
        double b = 4;
        double hipotenusa = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        System.out.println("Hipotenusa = " + hipotenusa);  // Resultado: 5.0
    }
}
```

### 🎯 3. **Números Aleatorios**

`Math.random()` genera un número aleatorio **entre 0.0 y 1.0** (no incluye el 1). Puedes escalarlo al rango que quieras.

### 📌 Ejemplo: Número aleatorio entre 1 y 10

```java
public class Aleatorios {
    public static void main(String[] args) {
        int numeroAleatorio = (int)(Math.random() * 10) + 1;
        System.out.println("Número aleatorio entre 1 y 10: " + numeroAleatorio);
    }
}
```

---

### 🔁 Bonus: Tirar un dado (de 1 a 6)

```java
int dado = (int)(Math.random() * 6) + 1;
System.out.println("Tiraste un: " + dado);
```

## Clase ( **NumberFormat )** para Formateo de números

La clase `NumberFormat` en Java es parte del paquete `java.text` y se utiliza para **formatear y analizar números** (como cantidades, monedas y porcentajes) de forma amigable para los humanos y respetando configuraciones regionales (como separadores de miles, decimales, símbolos de moneda, etc.).

---

### 🧩 ¿Qué hace `NumberFormat`?

- Convierte números (`int`, `double`, etc.) a cadenas de texto con formato legible.
- También puede **leer** cadenas con formato numérico y convertirlas en números (`parse()`).
- Tiene en cuenta el **Locale** (país/idioma), para adaptar formatos regionales (por ejemplo, usar punto o coma decimal).

---

### 🧪 Ejemplo básico

```java
import java.text.NumberFormat;
import java.util.Locale;

public class Ejemplo {
    public static void main(String[] args) {
        double numero = 1234567.89;

        // Formato general para Locale US
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        System.out.println("Número en US: " + nf.format(numero));  // 1,234,567.89

        // Formato general para Locale Colombia
        nf = NumberFormat.getInstance(Locale.of("es", "CO"));
        System.out.println("Número en Colombia: " + nf.format(numero));  // 1.234.567,89
    }
}
```

---

### 🪙 Tipos de formatos que puedes obtener con `NumberFormat`

```java
NumberFormat.getInstance()          // General (usa separadores)
NumberFormat.getNumberInstance()   // Igual que getInstance()
NumberFormat.getCurrencyInstance() // Formato de moneda ($, €, etc.)
NumberFormat.getPercentInstance()  // Porcentaje (0.85 → 85%)
```

---

### 🛠 Personalizar el número de decimales

```java
NumberFormat nf = NumberFormat.getNumberInstance();
nf.setMinimumFractionDigits(2);
nf.setMaximumFractionDigits(2);

System.out.println(nf.format(1234.5));  // 1,234.50
```

---

### 💲 Formateo como moneda

```java
NumberFormat moneda = NumberFormat.getCurrencyInstance(Locale.US);
System.out.println(moneda.format(1500));  // $1,500.00

moneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
System.out.println(moneda.format(1500));  // COP 1.500,00
```

---

### 📥 También puedes hacer parsing (convertir texto a número):

```java
try {
    NumberFormat nf = NumberFormat.getInstance(Locale.US);
    Number n = nf.parse("1,234.56");
    System.out.println(n.doubleValue());  // 1234.56
} catch (Exception e) {
    e.printStackTrace();
}
```

---

### ✅ Ventajas de usar `NumberFormat`

- Adaptación automática al país/idioma.
- Facilita la presentación de datos numéricos al usuario.
- Compatible con moneda y porcentaje sin lógica adicional.

## **Funciones, Modularidad**

## **1. Funciones (Métodos): Los Bloques de Construcción**

Una **función** o **método** es un bloque de código que tiene un nombre y realiza una tarea específica. Piensa en ellos como herramientas que puedes usar varias veces en diferentes partes de tu programa sin tener que reescribir el mismo código.

**¿Por qué usar funciones?**

- **Reutilización**: Escribes el código una vez y lo llamas (**invocas**) cada vez que lo necesitas.
- **Organización**: Dividen un programa grande en partes más pequeñas y manejables.
- **Legibilidad**: El código es más fácil de leer y entender cuando las tareas están separadas en funciones con nombres descriptivos.
- **Mantenimiento**: Es más fácil encontrar y corregir errores o modificar una tarea si está encapsulada en una función.

Ya has usado un método: el método `main`. Es el punto de entrada de tu programa.

```java
public class MiPrograma {
    // Este es el método main
    public static void main(String[] args) {
        // Código principal
    }
}
```

### **1.1. Declaración de Métodos (void)**

Para crear tus propias funciones, necesitas declararlas. Aquí vemos un método simple que no devuelve ningún valor (usa la palabra clave `void`) y no recibe información (no tiene **parámetros**).

```java
public class EjemploFunciones {

    // Declaración del método saludar
    // public static: Por ahora, siempre usaremos esto para los métodos que creamos en Módulo 1
    // void: Indica que este método NO devuelve ningún valor
    // saludar: Es el nombre del método
    // (): Indica que no recibe parámetros
    public static void saludar() {
        System.out.println("¡Hola desde una función!");
    }

    public static void main(String[] args) {
        System.out.println("Antes de llamar a la función.");

        saludar(); // Llamada o invocación al método saludar()

        System.out.println("Después de llamar a la función.");
    }
}
// Salida:
// Antes de llamar a la función.
// ¡Hola desde una función!
// Después de llamar a la función.
```

### **1.2. Parámetros y Argumentos**

Los métodos a menudo necesitan información para realizar su tarea. Esta información se pasa a través de **parámetros**. Cuando llamas a un método, los valores que le envías se llaman **argumentos**.

- **Parámetros**: Variables declaradas en la firma del método que reciben los valores.
- **Argumentos**: Los valores reales que pasas cuando llamas al método.

```java
public class EjemploFuncionesConParametros {

    // Método que recibe un parámetro de tipo String llamado 'nombre'
    public static void saludarNombre(String nombre) {
        System.out.println("¡Hola, " + nombre + "!");
    }

    // Método que recibe dos parámetros: un String y un int
    public static void imprimirMensajeRepetido(String mensaje, int veces) {
        for (int i = 0; i < veces; i++) {
            System.out.println(mensaje);
        }
    }

    public static void main(String[] args) {
        saludarNombre("Ana"); // "Ana" es el argumento para el parámetro 'nombre'
        saludarNombre("Juan"); // "Juan" es otro argumento

        imprimirMensajeRepetido("Repetir esto", 3); // "Repetir esto" y 3 son argumentos
    }
}
// Salida:
// ¡Hola, Ana!
// ¡Hola, Juan!
// Repetir esto
// Repetir esto
// Repetir esto
```

**Paso por Valor (para tipos primitivos)**: Cuando pasas un tipo de dato primitivo (`int`, `double`, `boolean`, `char`, etc.) como argumento a un método, Java crea una **copia** de ese valor y la pasa al parámetro del método. Si modificas el valor del parámetro dentro del método, la variable original fuera del método **no se ve afectada**.

```java
public class PasoPorValorEjemplo {

    public static void modificarNumero(int num) {
        System.out.println("Dentro del método - Antes de modificar: " + num); // Muestra la copia
        num = num * 2; // Modifica la copia local
        System.out.println("Dentro del método - Después de modificar: " + num); // Muestra la copia modificada
    }

    public static void main(String[] args) {
        int miNumero = 10;
        System.out.println("Antes de llamar al método: " + miNumero); // Salida: 10

        modificarNumero(miNumero); // Pasamos una copia de miNumero (el valor 10)

        System.out.println("Después de llamar al método: " + miNumero); // Salida: 10 (la variable original no cambió)
    }
}
```

### **1.3. Métodos con Retorno**

A menudo, quieres que una función no solo realice una tarea, sino que también te dé un **resultado**. Para esto, usas métodos que **devuelven un valor**. En lugar de `void`, especificas el **tipo de dato** que el método devolverá, y usas la palabra clave `return`.

```java
public class EjemploFuncionesConRetorno {

    // Método que suma dos números enteros y devuelve el resultado (un int)
    public static int sumar(int a, int b) {
        int resultado = a + b;
        return resultado; // Devuelve el valor de 'resultado'
        // Cualquier código después de 'return' en este método NO se ejecutará
    }

    // Método que verifica si un número es par y devuelve un boolean
    public static boolean esPar(int numero) {
        return numero % 2 == 0; // Devuelve directamente el resultado de la expresión booleana
    }

    // Método que concatena dos cadenas y devuelve un String
    public static String unirCadenas(String cad1, String cad2) {
        return cad1 + " " + cad2;
    }

    public static void main(String[] args) {
        // Llamamos a sumar() y usamos el valor retornado
        int sumaTotal = sumar(5, 3);
        System.out.println("La suma es: " + sumaTotal); // Salida: La suma es: 8

        // Llamamos a esPar() y usamos el valor retornado en un condicional
        int miNumero = 4;
        if (esPar(miNumero)) {
            System.out.println(miNumero + " es un número par."); // Salida: 4 es un número par.
        }

        // Llamamos a unirCadenas() y mostramos el resultado directamente
        System.out.println(unirCadenas("Hola", "Mundo")); // Salida: Hola Mundo
    }
}
```

El tipo de dato que especificas antes del nombre del método (`int`, `boolean`, `String`, etc.) indica el tipo de valor que debes devolver usando `return`. Un método declarado como `void` no puede usar `return` para devolver un valor (solo `return;` para salir anticipadamente).

## **2. Alcance de Variables (Scope)**

El alcance de una variable se refiere a la parte del código donde esa variable es accesible.

- **Alcance Local**: Las variables declaradas *dentro de un método* o *dentro de un bloque* de código específico (como el cuerpo de un `if`, `for`, `while`, etc.) tienen alcance local. Solo son visibles y utilizables desde el punto donde se declaran hasta el final del bloque en el que se encuentran.

```java
public class AlcanceLocalEjemplo {

    public static void metodo1() {
        int variableLocalMetodo1 = 10; // Alcance: solo dentro de metodo1()
        System.out.println("Dentro de metodo1: " + variableLocalMetodo1);
        // System.out.println(variableLocalMetodo2); // ERROR: variableLocalMetodo2 no es visible aquí
    }

    public static void metodo2() {
        int variableLocalMetodo2 = 20; // Alcance: solo dentro de metodo2()
        System.out.println("Dentro de metodo2: " + variableLocalMetodo2);
        // System.out.println(variableLocalMetodo1); // ERROR: variableLocalMetodo1 no es visible aquí
    }

    public static void main(String[] args) {
        int variableLocalMain = 30; // Alcance: solo dentro de main()
        System.out.println("Dentro de main: " + variableLocalMain);

        metodo1();
        metodo2();

        // System.out.println(variableLocalMetodo1); // ERROR: Fuera de alcance
        // System.out.println(variableLocalMetodo2); // ERROR: Fuera de alcance

        if (variableLocalMain > 0) {
            String mensaje = "Es positivo"; // Alcance: solo dentro de este bloque if
            System.out.println(mensaje);
        }
        // System.out.println(mensaje); // ERROR: 'mensaje' está fuera de alcance aquí
    }
}
```

- Las variables locales solo existen mientras se ejecuta el método o bloque donde están declaradas.
- **Alcance de Clase (Miembros de Clase)**: Las variables declaradas directamente dentro de la clase, pero fuera de cualquier método, se llaman **variables de instancia** o **variables estáticas** (si usan `static`). Tienen un alcance más amplio y pueden ser accedidas por todos los métodos de la clase (dependiendo de su nivel de acceso, que veremos más adelante).
    
    Por ahora, nos enfocaremos principalmente en el alcance local.
    

Comprender el alcance es crucial para evitar errores y escribir código que funcione correctamente.
