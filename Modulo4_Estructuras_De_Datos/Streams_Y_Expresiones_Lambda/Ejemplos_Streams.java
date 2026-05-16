package Modulo4_Estructuras_De_Datos.Streams_Y_Expresiones_Lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Ejemplos_Streams {
    
    /*
     * 1. Arreglos y Listas básicas
     * 
     * Objetivo: Practicar la manipulación de arreglos y listas.
     * Ejercicio:
     * 
     * -Crea un arreglo de enteros con 10 números aleatorios.
     * -Convierte ese arreglo a una List<Integer>.
     * -Usa un forEach y un Stream para imprimir los números pares.
     * -Calcula el promedio de todos los números usando mapToInt().average().
     */

    private Integer[] arrayNumeros = new Integer[10];
    Random rand = new Random();

    public void solucionEjercicio1() {
        // Se rellena el arreglo con numeros aleatorios del 1 al 99
        for (int i = 0; i < arrayNumeros.length; i++) {
            arrayNumeros[i] = rand.nextInt(100);
        }

        // Se muestra el arreglo
        System.out.println("Arreglo de numeros aleatorios");
        System.out.println(Arrays.toString(arrayNumeros));

        // Se convierte el arreglo en una lista de numeros enteros
        List<Integer> listaNumeros = Arrays.stream(arrayNumeros)
                .collect(Collectors.toList());

        System.out.println("\nNúmeros pares (usando forEach con Stream):");
        listaNumeros.stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);

        double promedio = listaNumeros.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0); // usado por si la lista se encuentra vacia ya que el metodo average retorna un
                            // OptionalDouble
        System.out.println("Promedio de todos los numeros: " + promedio);
    }


    /*
     * 2. Listas y ordenamiento
     * 
     * Objetivo: Usar Collections.sort() y Comparator.
     * Ejercicio:
     * 
     * -Crea una clase Persona con nombre y edad.
     * -Guarda varias personas en una List<Persona>.
     * -Ordena la lista primero por edad ascendente, luego por nombre
     * alfabéticamente.
     * -Usa Streams para filtrar las personas mayores de 18 años y mostrar sus
     * nombres.
     */

    private List<Persona> listaPersonas = new ArrayList<>();

    public void solucionEjericio2() {
        // Agregamos personas a la lista de personas
        listaPersonas.add(new Persona("Julian", 23));
        listaPersonas.add(new Persona("Maria", 22));
        listaPersonas.add(new Persona("Betsy", 48));
        listaPersonas.add(new Persona("German", 52));
        listaPersonas.add(new Persona("Dylan", 10));
        listaPersonas.add(new Persona("Luciana", 4));

        System.out.println("Lista original:");
        listaPersonas.forEach(System.out::println);

        //Ordenar por edad ascendente, luego por nombre alfabéticamente
        // thenComparing() se usa para encadenar varios criterios de ordenamiento en un Comparator.
        listaPersonas.sort(Comparator.comparing(Persona::getEdad).thenComparing(Persona::getNombre));
        System.out.println("\nLista ordenada (por edad y nombre):");
        listaPersonas.forEach(System.out::println);

        System.out.println("Personas mayores de edad");
        listaPersonas.stream()
                .filter(p -> p.getEdad() > 18)
                .map(Persona::getNombre)
                .forEach(System.out::println);
    }


    /*
     * 3. Set y eliminación de duplicados
     * 
     * Objetivo: Comprender HashSet, LinkedHashSet y TreeSet.
     * Ejercicio:
     * 
     * -Crea una lista con nombres repetidos.
     * -Convierte la lista a un Set para eliminar duplicados.
     * -Muestra las diferencias entre los tres tipos de set (orden, rendimiento,
     * duplicados).
     * -Usa un TreeSet para mostrar los nombres en orden alfabético.
     */

    public void solucionejercicio3() {
        List<String> listaNombres = Arrays.asList(
            "Ana", "Luis", "Carlos", "Ana", "Beatriz", "Luis", "Diana", "Carlos"
        );

        System.out.println("Lista original con duplicados:");
        System.out.println(listaNombres);

        // Eliminar duplicados con HashSet
        Set<String> hashSet = new HashSet<>(listaNombres);
        System.out.println("\nHashSet (sin duplicados, orden impredecible):");
        System.out.println(hashSet);

        // Usar LinkedHashSet (mantiene el orden de inserción)
        Set<String> linkedHashSet = new LinkedHashSet<>(listaNombres);
        System.out.println("\nLinkedHashSet (sin duplicados, mantiene orden de inserción):");
        System.out.println(linkedHashSet);

        // Usar TreeSet (sin duplicados, orden alfabético)
        Set<String> treeSet = new TreeSet<>(listaNombres);
        System.out.println("\nTreeSet (sin duplicados, orden alfabético):");
        System.out.println(treeSet);

        // Mostrar resumen de diferencias
        System.out.println("\n Diferencias principales:");
        System.out.println("• HashSet → No mantiene orden, más rápido (usa hash).");
        System.out.println("• LinkedHashSet → Mantiene el orden de inserción.");
        System.out.println("• TreeSet → Ordena automáticamente (usa árbol rojo-negro, más lento).");

    }


    /*
     * 4. Mapas básicos
     * 
     * Objetivo: Practicar HashMap, LinkedHashMap y TreeMap.
     * Ejercicio:
     * 
     * -Crea un mapa que relacione nombres de alumnos con sus calificaciones.
     * -Muestra:
     * -Los alumnos aprobados (nota ≥ 5).
     * -El promedio de todas las notas.
     * -Usa un TreeMap para mostrar los nombres ordenados.
     */

    public void solucionejercicio4() {
        Map<String, Double> estudiantes = Map.of(
                "Julian", 7.0,
                "Maria", 3.5,
                "German", 10d,
                "Sofia", 2.6,
                "Betsy", 5.9,
                "Antonio", 4.8);

        System.out.println("Estudiantes del aula");
        System.out.println(estudiantes);

        var promedio1 = estudiantes.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
        System.out.println("Promedio de todos los estudiantes usando el metodo values() de los Maps\n: " + promedio1);

        // Esto devuelve un conjunto(set) de entradas (Set<Map.Entry<K,V>>) del mapa.
        // Cada elemento del conjunto(set) es un Map.Entry, que tiene un par
        // clave–valor.
        var promedio2 = estudiantes.entrySet().stream()
                .mapToDouble(Map.Entry::getValue)
                .average()
                .orElse(0);
        System.out.println("Promedio de todos los estudiantes usando el metodo entrySet() de los Maps\n: " + promedio2);

        System.out.println("Estudiantes Aprobados, solo los nombres");
        estudiantes.entrySet().stream()
                .filter(e -> e.getValue() >= 5)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

        System.out.println("Estudiantes ordenados por orden alfabetico de sus llaves usando treeMap");
        Map<String, Double> estudiantesOrdenados = new TreeMap<>(estudiantes);
        System.out.println(estudiantesOrdenados);
    }


    /*
     * 5. Agrupar elementos (Collectors.groupingBy)
     * 
     * Objetivo: Practicar agrupaciones con Streams.
     * Ejercicio:
     * 
     * -Usa una lista de personas con nombre, edad y ciudad.
     * -Agrúpalas por ciudad usando Collectors.groupingBy().
     * -Muestra el número de personas por ciudad.
     * -Encuentra la edad promedio por ciudad.
     */

    public void ejercicio5() {
        List<Persona> personas = Arrays.asList(
                new Persona("Julian", 23, Ciudad.IBAGUE),
                new Persona("Maria", 22, Ciudad.BOGOTA),
                new Persona("German", 45, Ciudad.CALI),
                new Persona("Felipe", 56, Ciudad.ARMENIA),
                new Persona("Daniel", 34, Ciudad.MEDELLIN),
                new Persona("Angelica", 13, Ciudad.IBAGUE),
                new Persona("Eduardo", 32, Ciudad.BOGOTA),
                new Persona("Betsy", 40, Ciudad.CALI),
                new Persona("Ester", 67, Ciudad.ARMENIA),
                new Persona("Laura", 78, Ciudad.MEDELLIN));

        System.out.println("Lista original de personas:");
        personas.forEach(System.out::println);

        // Agrupar por ciudad
        Map<Ciudad, List<Persona>> personasPorCiudad = personas.stream()
                .collect(Collectors.groupingBy(Persona::getCiudad));

        System.out.println("\nPersonas agrupadas por ciudad:");
        personasPorCiudad.forEach((ciudad, lista) -> {
            System.out.println(ciudad + ": " + lista);
        });

        // Contar número de personas por ciudad
        Map<Ciudad, Long> conteoPorCiudad = personas.stream()
                .collect(Collectors.groupingBy(Persona::getCiudad, Collectors.counting()));

        System.out.println("\nNúmero de personas por ciudad:");
        conteoPorCiudad.forEach((ciudad, cantidad) -> System.out.println(ciudad + ": " + cantidad));

        // Calcular la edad promedio por ciudad
        // el groupingBy tambien Permite aplicar otro colector dentro de cada grupo.
        // Por ejemplo, contar, promediar, sumar, etc, como se ve en este agrupamiento que se hace 
        Map<Ciudad, Double> promedioEdadPorCiudad = personas.stream()
                .collect(Collectors.groupingBy(Persona::getCiudad,
                        Collectors.averagingInt(Persona::getEdad)));

        System.out.println("\nEdad promedio por ciudad:");
        promedioEdadPorCiudad.forEach((ciudad, promedio) -> System.out.printf("%s: %.2f%n", ciudad, promedio));
    }


    /*
     * 6. Uso de Optional
     * 
     * Objetivo: Evitar valores null de forma segura.
     * Ejercicio:
     * 
     * -Crea un método buscarPersonaPorNombre(String nombre) que retorne un
     * Optional<Persona>.
     * -Si la persona existe, muestra su edad; si no, muestra “No encontrado”.
     * -Usa ifPresentOrElse() y orElseGet() para manejar los casos.
     */

    List<Persona> personas = List.of(
            new Persona("Juan", 30),
            new Persona("Ana", 25),
            new Persona("Luis", 40));

    public Optional<Persona> ejercicio6(String nombre) {
        return personas.stream().filter(p -> p.getNombre().equals(nombre)).findFirst();
    }

    public void mostrarEdadPersona(String nombre) {
        var resultado = ejercicio6(nombre);

        // ifPresentOrElse() Ejecuta una acción si el valor existe; si no, ejecuta otra acción.
        resultado.ifPresentOrElse(persona -> System.out.println("Edad: " + persona.getNombre()),
                () -> System.out.println("No Encontrado"));

        // orElseGet() para obtener el objeto o uno por defecto
        Persona personaEncontrada = resultado.orElseGet(() -> new Persona("Desconocido", 0));

        System.out.println("Resultado final: " + personaEncontrada.getNombre());
    }


    /*
     * 
     * Dentro del marco de la API de Streams de Java, el método flatMap ejecuta dos
     * operaciones:
     * 
     * Transformación: Cada elemento del stream original se convierte en un stream
     * interno (por ejemplo, cada lista se convierte en un Stream<Integer>).
     * 
     * Aplanamiento (flattening): Los streams internos resultantes se concatenan en
     * un único stream continuo de elementos, eliminando un nivel de anidamiento.
     */

    public void ejercicio7() {
        List<List<Integer>> listaEnLista = List.of(
                List.of(1, 2, 3, 4),
                List.of(5, 6, 7),
                List.of(8, 9, 10));

        // Aplanar con flatMap
        List<Integer> listaPlana = listaEnLista.stream()
                .flatMap(lista -> lista.stream())
                .toList();

        // Suma total con reduce
        int sumaTotal = listaPlana.stream()
                .reduce(0, Integer::sum);

        // Buscar máximo y mínimo
        int maximo = listaPlana.stream()
                .max(Integer::compareTo)
                .orElseThrow();

        int minimo = listaPlana.stream()
                .min(Integer::compareTo)
                .orElseThrow();

        // Resultados
        System.out.println("Lista plana: " + listaPlana);
        System.out.println("Suma total: " + sumaTotal);
        System.out.println("Máximo: " + maximo);
        System.out.println("Mínimo: " + minimo);
    }


    /*
     * 8. Manipulación de texto con Streams
     * 
     * Objetivo: Combinar Streams y colecciones en contextos reales.
     * Ejercicio:
     * 
     * Dado un texto largo, separa las palabras (usando split("\\W+")).
     * 
     * Convierte todas a minúsculas.
     * 
     * Crea un mapa con la cantidad de ocurrencias de cada palabra.
     * 
     * Muestra las 5 palabras más frecuentes.
     */

    public void ejercicio8() {
        String texto = "Este es un texto de ejemplo. Este texto sirve para mostrar "
                + "cómo usar Streams para contar palabras en un texto largo. "
                + "Este ejemplo es simple pero útil.";

        // 1. Separar palabras usando split("\\W+")
        // 2. Convertir todas a minúsculas
        // 3. Contar ocurrencias en un mapa
        Map<String, Long> conteo = Arrays.stream(texto.split("\\W+"))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(
                        palabra -> palabra,
                        Collectors.counting()
                ));

        // 4. Mostrar las 5 palabras más frecuentes
        System.out.println("Top 5 palabras más frecuentes:");
        conteo.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }


    /*
     * 9. Uso combinado de Map y Optional
     * 
     * Objetivo: Encadenar Optional con Map.
     * Ejercicio:
     * 
     * Dado un Map<String, Persona>, intenta obtener una persona por su nombre
     * usando Optional.ofNullable(map.get(nombre)).
     * 
     * Usa map() para transformar el valor a su edad, y orElse(-1) si no se
     * encuentra.
     * 
     * Imprime el resultado.
     */

    public void ejercicio9() {

        Map<String, Persona> personas = Map.of(
                "Julian", new Persona("Julian", 23),
                "Maria", new Persona("Maria", 23),
                "Betsy", new Persona("Betsy", 23),
                "German", new Persona("German", 23),
                "Diana", new Persona("Diana", 23));

        int edad = Optional.ofNullable(personas.get("Julian"))
                .map(Persona::getEdad)
                .orElse(-1);

        System.out.println("Edad:" + edad);
    }


    /*
     * 10. Mini proyecto: gestión de empleados
     * 
     * Objetivo: Integrar todos los conceptos.
     * Ejercicio:
     * 
     * -Crea una clase Empleado con nombre, departamento, salario.
     * -Usa una List<Empleado> con varios registros.
     * -Implementa con Streams:
     * -Filtrar empleados con salario > 2000.
     * -Agrupar por departamento y calcular salario promedio.
     * -Obtener el empleado con mayor salario (usando max() y Optional).
     * -Mostrar todos los nombres ordenados alfabéticamente sin duplicados.
     */

    public void ejercicio10() {
        List<Empleado> empleados = List.of(
                new Empleado("Ana", Departamento.SISTEMAS, 3000d),
                new Empleado("Luis", Departamento.SISTEMAS, 2500d),
                new Empleado("Maria", Departamento.CONTABILIDAD, 1800d),
                new Empleado("Pedro", Departamento.CONTABILIDAD, 2200d),
                new Empleado("Julian", Departamento.SISTEMAS, 2100d),
                new Empleado("Maria", Departamento.SISTEMAS, 2100d),
                new Empleado("Julian", Departamento.SISTEMAS, 2100d),
                new Empleado("German", Departamento.SISTEMAS, 2100d),
                new Empleado("Betsy", Departamento.SISTEMAS, 2100d));

        var salariosAltos = empleados.stream()
                .filter(p -> p.getSalario() > 2000)
                .toList();

        System.out.println("Empleados con salario > 2000:");
        salariosAltos.forEach(e -> System.out.println(e.getNombre()));

        var salariosPorDepartamento = empleados.stream()
                .collect(Collectors.groupingBy(Empleado::getDepartamento,
                        Collectors.averagingDouble(Empleado::getSalario)));

        System.out.println("\nSalario promedio por departamento:");
        salariosPorDepartamento.forEach((d, s) -> System.out.println(d + ": " + s));

        var empleadoMejorPagado = empleados.stream().max(Comparator.comparingDouble(Empleado::getSalario));

        empleadoMejorPagado.ifPresent(e ->
                System.out.println("\nEmpleado con mayor salario: " +
                        e.getNombre() + " - " + e.getSalario())
        );

        var nombresOrdenados = empleados.stream()
            .map(Empleado::getNombre)
            .distinct()
            .sorted()
            .toList();

        System.out.println("\nNombres ordenados sin duplicados:");
        nombresOrdenados.forEach(System.out::println);
    }
}

class Persona {
    private String nombre;
    private Integer edad;
    private Ciudad ciudad;

    public Persona(String nombre, Integer edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Persona(String nombre, Integer edad, Ciudad ciudad) {
        this(nombre, edad);
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }
    public Integer getEdad() {
        return edad;
    }
    public Ciudad getCiudad() {
        return ciudad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
}

class Empleado {
    private String nombre;
    private Departamento departamento;
    private Double salario;

    public Empleado(String nombre, Departamento departamento, Double salario) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Double getSalario() {
        return salario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}

enum Ciudad {
    IBAGUE,
    BOGOTA,
    CALI,
    MEDELLIN,
    ARMENIA
}

enum Departamento {
    SISTEMAS,
    CONTABILIDAD,
    RECURSOS_HUMANOS,
    PRODUCCION
}
