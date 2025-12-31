# Validación con javax.validation **(Bean Validation)**

## 1. Conceptos Fundamentales

Bean Validation utiliza un modelo basado en metadatos (anotaciones) para validar el estado de un objeto. El motor de referencia más común para implementar esta especificación es **Hibernate Validator**.

### Dependencias (Maven)

Para usarlo en un proyecto Java estándar o Spring Boot, necesitas la implementación:

```xml
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>8.0.0.Final</version>
</dependency>

```

---

## 2. Anotaciones Comunes

Aquí tienes las anotaciones más utilizadas divididas por categorías:

#### **Para cualquier tipo de objeto**

* **`@NotNull`**: El valor no puede ser `null`.
* **`@Null`**: El valor debe ser obligatoriamente `null`.

### Validaciones de Cadenas (Strings)

* **`@NotEmpty`**: No puede ser nulo y su longitud debe ser mayor a 0.
* **`@NotBlank`**: No puede ser nulo y debe contener al menos un carácter que no sea un espacio en blanco.
* **`@Size(min=x, max=y)`**: Valida que la longitud esté dentro de un rango.
* **`@Email`**: Valida que el formato sea una dirección de correo válida.
* **`@Pattern(regexp="...")`**: Valida mediante una expresión regular.

### Validaciones Numéricas

* **`@Min(value)`**: Debe ser mayor o igual al valor.
* **`@Max(value)`**: Debe ser menor o igual al valor.
* **`@Positive`**: Debe ser un número estrictamente positivo (> 0).
* **`@PositiveOrZero`**: Debe ser mayor o igual a 0.
* **`@Negative`**: Debe ser estrictamente menor a 0.
* **`@NegativeOrZero`**: Debe ser menor o igual a 0.
* **`@DecimalMin(value)`**: Igual que `@Min` pero acepta decimales y una opción `inclusive`.
* **`@DecimalMax(value)`**: Igual que `@Max` para decimales.
* **`@Digits(integer=, fraction=)`**: Valida el número de dígitos en la parte entera y decimal.

#### **Para Fechas (LocalDate, Date, etc.)**

* **`@Past`**: La fecha debe ser anterior a hoy.
* **`@PastOrPresent`**: La fecha debe ser hoy o anterior.
* **`@Future`**: La fecha debe ser posterior a hoy.
* **`@FutureOrPresent`**: La fecha debe ser hoy o posterior.

#### **Para Booleanos**

* **`@AssertTrue`**: El valor debe ser `true`.
* **`@AssertFalse`**: El valor debe ser `false`.

---

### 2. Validaciones Extras de Hibernate Validator

Estas son muy potentes pero requieren que uses Hibernate como proveedor (que es el estándar de facto). Se encuentran en el paquete `org.hibernate.validator.constraints`.

* **`@URL`**: Valida que el String sea una URL válida (protocolo, host, etc.).
* **`@CreditCardNumber`**: Valida que el número de tarjeta pase el algoritmo de Luhn (no verifica si tiene fondos, solo si el número es coherente).
* **`@LuhnCheck`**: Aplica el algoritmo de Luhn a cualquier cadena numérica.
* **`@Range(min=, max=)`**: Una combinación de `@Min` y `@Max`.
* **`@Length(min=, max=)`**: Similar a `@Size` pero solo para Strings.
* **`@ISBN`**: Valida que un String sea un código de libro ISBN válido.
* **`@EAN`**: Valida códigos de barras EAN-13.
* **`@CodePointLength`**: Similar a `@Size` pero maneja mejor caracteres especiales (emojis, alfabetos complejos).
* **`@UUID`**: Valida que el formato sea un Identificador Único Universal.
* **`@Currency`**: Valida que sea una unidad monetaria válida (ej: "USD", "EUR").

---

### 3. Validaciones de Colecciones

Si tienes una lista de objetos, puedes validar el contenido:

* **`@Size(min=x, max=y)`**: En una lista, valida la **cantidad de elementos** que tiene la lista.
* **`@NotEmpty`**: La lista debe tener al menos un elemento.
* **`@Valid`**: Esta es crucial. Si tienes una lista de objetos (ej: `List<Direccion>`), poner `@Valid` hará que el motor entre a validar cada objeto dentro de la lista.

---

## 4. Ejemplo de Implementación en un Bean

Imagina una clase `UsuarioDTO`. Así es como aplicarías las reglas:

```java
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UsuarioDTO {

    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @Email(message = "Debe proporcionar un email válido")
    private String email;

    @Min(value = 18, message = "Debes ser mayor de 18 años")
    private int edad;

    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    // Getters y Setters...
}

```

---

## 5. Cómo Ejecutar la Validación

### Manualmente (Java SE)

Si no usas un framework como Spring, puedes validar el objeto manualmente usando el `ValidatorFactory`.

```java
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNombre(""); // Invalidará @NotBlank

        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuario);

        for (ConstraintViolation<UsuarioDTO> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }
}

```

### Automáticamente (Spring Boot)

En un controlador de Spring, solo necesitas añadir la anotación `@Valid` o `@Validated` en el parámetro del método.

```java
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping
    public ResponseEntity<String> crear(@Valid @RequestBody UsuarioDTO usuario) {
        // Si el JSON no cumple las validaciones, Spring lanzará una MethodArgumentNotValidException
        return ResponseEntity.ok("Usuario válido");
    }
}

```

---

## 6. Validaciones Personalizadas (Custom Constraints)

Si las anotaciones estándar no son suficientes, puedes crear la tuya. Requiere dos partes:

1. **La Anotación**: Define el mensaje y los grupos.
2. **El Validador**: Contiene la lógica lógica de Java.

### Ejemplo: Validador de NIF/DNI

```java
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = DniValidator.class) // Clase con la lógica
public @interface ValidDni {
    String message() default "Formato de DNI inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

```

---

## Resumen de Mejores Prácticas

* **Mensajes claros**: Siempre define `message` para que el cliente (Frontend/API) sepa qué falló.
* **Validación en Cascada**: Si tienes un objeto dentro de otro, usa `@Valid` sobre el atributo del objeto hijo para que el validador entre a revisar sus campos.
* **Capas**: Valida en los DTOs de entrada para evitar procesar datos basura en tu capa de servicio.



























Para darte una lista completa, debemos dividir las validaciones en dos grupos: las **estándar** (definidas por la especificación Jakarta/Javax Bean Validation) y las **específicas de Hibernate Validator** (que son extras muy útiles).

Aquí tienes el catálogo completo:

### 1. Validaciones Estándar (JSR 303/380)

Estas funcionan en cualquier implementación y son las más comunes.

#### **Para cualquier tipo de objeto**

* **`@NotNull`**: El valor no puede ser `null`.
* **`@Null`**: El valor debe ser obligatoriamente `null`.

#### **Para Cadenas de Texto (Strings)**

* **`@NotBlank`**: El texto no puede estar vacío ni contener solo espacios en blanco.
* **`@NotEmpty`**: El texto no puede ser nulo ni estar vacío (pero puede tener solo espacios).
* **`@Size(min=, max=)`**: Valida que la longitud esté entre los límites.
* **`@Email`**: Verifica que tenga un formato de correo electrónico válido.
* **`@Pattern(regexp=)`**: Valida mediante una expresión regular (Regex).

#### **Para Números**

* **`@Min(value)`**: Debe ser mayor o igual al valor.
* **`@Max(value)`**: Debe ser menor o igual al valor.
* **`@Positive`**: Debe ser un número estrictamente positivo (> 0).
* **`@PositiveOrZero`**: Debe ser mayor o igual a 0.
* **`@Negative`**: Debe ser estrictamente menor a 0.
* **`@NegativeOrZero`**: Debe ser menor o igual a 0.
* **`@DecimalMin(value)`**: Igual que `@Min` pero acepta decimales y una opción `inclusive`.
* **`@DecimalMax(value)`**: Igual que `@Max` para decimales.
* **`@Digits(integer=, fraction=)`**: Valida el número de dígitos en la parte entera y decimal.

#### **Para Fechas (LocalDate, Date, etc.)**

* **`@Past`**: La fecha debe ser anterior a hoy.
* **`@PastOrPresent`**: La fecha debe ser hoy o anterior.
* **`@Future`**: La fecha debe ser posterior a hoy.
* **`@FutureOrPresent`**: La fecha debe ser hoy o posterior.

#### **Para Booleanos**

* **`@AssertTrue`**: El valor debe ser `true`.
* **`@AssertFalse`**: El valor debe ser `false`.

---

### 2. Validaciones Extras de Hibernate Validator

Estas son muy potentes pero requieren que uses Hibernate como proveedor (que es el estándar de facto). Se encuentran en el paquete `org.hibernate.validator.constraints`.

* **`@URL`**: Valida que el String sea una URL válida (protocolo, host, etc.).
* **`@CreditCardNumber`**: Valida que el número de tarjeta pase el algoritmo de Luhn (no verifica si tiene fondos, solo si el número es coherente).
* **`@LuhnCheck`**: Aplica el algoritmo de Luhn a cualquier cadena numérica.
* **`@Range(min=, max=)`**: Una combinación de `@Min` y `@Max`.
* **`@Length(min=, max=)`**: Similar a `@Size` pero solo para Strings.
* **`@ISBN`**: Valida que un String sea un código de libro ISBN válido.
* **`@EAN`**: Valida códigos de barras EAN-13.
* **`@CodePointLength`**: Similar a `@Size` pero maneja mejor caracteres especiales (emojis, alfabetos complejos).
* **`@UUID`**: Valida que el formato sea un Identificador Único Universal.
* **`@Currency`**: Valida que sea una unidad monetaria válida (ej: "USD", "EUR").

---

### 3. Validaciones de Colecciones

Si tienes una lista de objetos, puedes validar el contenido:

* **`@Size(min=x, max=y)`**: En una lista, valida la **cantidad de elementos** que tiene la lista.
* **`@NotEmpty`**: La lista debe tener al menos un elemento.
* **`@Valid`**: Esta es crucial. Si tienes una lista de objetos (ej: `List<Direccion>`), poner `@Valid` hará que el motor entre a validar cada objeto dentro de la lista.

---

### ¿Cómo se aplican? (Ejemplo rápido)

```java
public class Registro {
    @NotBlank @Size(min=3)
    private String username;

    @Email
    private String correo;

    @Min(18) @Max(99)
    private int edad;

    @URL
    private String sitioWeb;

    @Future
    private LocalDate fechaEvento;

    @NotEmpty
    private List<@NotBlank String> etiquetas; // Valida que la lista no esté vacía y que cada string dentro no esté en blanco
}

```

**Nota importante:** Casi todas estas anotaciones permiten un atributo `message`, por lo que puedes personalizar el error:
`@NotNull(message = "Por favor, ingresa tu nombre")`.

¿Deseas que guardemos esta lista en una nota o que te explique cómo crear una **validación propia** si ninguna de estas te sirve?
