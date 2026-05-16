Aquí tienes una **documentación técnica completa, estructurada y práctica** sobre **JPA + Hibernate en Spring Boot**, enfocada a que entiendas **qué es, cómo funciona internamente y cómo usarlo correctamente en proyectos reales**.

---

# 🔷 1. ¿Qué es JPA y Hibernate?

## 📌 JPA (Java Persistence API)

Es una **especificación de Java** (no una implementación) que define cómo mapear objetos Java a bases de datos relacionales (**ORM: Object Relational Mapping**).

👉 En otras palabras:

* Permite trabajar con **objetos en lugar de SQL directo**
* Define interfaces como:

  * `EntityManager`
  * `Query`
  * `Criteria API`

📌 Importante:

> JPA NO ejecuta nada por sí sola → necesita una implementación.

---

## 📌 Hibernate

Es la **implementación más usada de JPA**.

✔ Hibernate:

* Traduce objetos → SQL
* Ejecuta queries automáticamente
* Maneja cache, relaciones, lazy loading, etc.

---

## 📌 Spring Boot + JPA + Hibernate

Spring Boot:

* Auto-configura Hibernate como proveedor JPA
* Reduce configuración manual
* Integra repositorios automáticamente

---

# 🔷 2. ¿Para qué sirve?

Con JPA + Hibernate puedes:

✔ Persistir objetos Java en BD
✔ Evitar escribir SQL repetitivo
✔ Manejar relaciones (JOIN) con objetos
✔ Hacer consultas dinámicas
✔ Manejar transacciones automáticamente

---

# 🔷 3. Arquitectura básica

```
Controller → Service → Repository → JPA/Hibernate → DB
```

---

# 🔷 4. Configuración en Spring Boot

## 📌 Dependencias (Maven)

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```

---

## 📌 application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mi_db
spring.datasource.username=postgres
spring.datasource.password=1234

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

# 🔷 5. Entidades (Entity)

Una entidad representa una tabla.

## 📌 Ejemplo básico

```java
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(unique = true)
    private String email;

    // getters y setters
}
```

---

# 🔷 6. Anotaciones principales (MUY IMPORTANTE)

## 📌 Anotaciones de entidad

| Anotación         | Descripción                 |
| ----------------- | --------------------------- |
| `@Entity`         | Marca la clase como entidad |
| `@Table`          | Nombre de la tabla          |
| `@Id`             | Llave primaria              |
| `@GeneratedValue` | Auto incremento             |
| `@Column`         | Configuración de columna    |
| `@Transient`      | No persistir campo          |

---

## 📌 Relaciones

### 🔹 OneToOne

```java
@OneToOne
@JoinColumn(name = "perfil_id")
private Perfil perfil;
```

---

### 🔹 OneToMany

```java
@OneToMany(mappedBy = "usuario")
private List<Pedido> pedidos;
```

---

### 🔹 ManyToOne

```java
@ManyToOne
@JoinColumn(name = "usuario_id")
private Usuario usuario;
```

---

### 🔹 ManyToMany

```java
@ManyToMany
@JoinTable(
    name = "usuarios_roles",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id")
)
private List<Rol> roles;
```

---

## 📌 FetchType (Clave)

| Tipo    | Explicación        |
| ------- | ------------------ |
| `EAGER` | Carga inmediata    |
| `LAZY`  | Carga bajo demanda |

---

# 🔷 7. Repositories (Spring Data JPA)

Spring Boot elimina boilerplate usando interfaces.

## 📌 Ejemplo

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    List<Usuario> findByNombreContaining(String nombre);
}
```

---

## 📌 Métodos automáticos (Query Methods)

Spring genera queries automáticamente:

| Método                  | SQL equivalente  |
| ----------------------- | ---------------- |
| `findByNombre`          | WHERE nombre = ? |
| `findByEdadGreaterThan` | WHERE edad > ?   |
| `findByEmailAndNombre`  | AND              |

---

# 🔷 8. Service Layer

```java
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario guardar(Usuario usuario) {
        return repo.save(usuario);
    }

    public List<Usuario> listar() {
        return repo.findAll();
    }
}
```

---

# 🔷 9. Controller

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return service.guardar(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }
}
```

---

# 🔷 10. JPQL vs SQL

## 📌 JPQL (Java Persistence Query Language)

Trabaja con objetos, no tablas.

```java
@Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
List<Usuario> buscarPorNombre(@Param("nombre") String nombre);
```

---

## 📌 Native Query

```java
@Query(value = "SELECT * FROM usuarios WHERE nombre = ?1", nativeQuery = true)
List<Usuario> buscarSQL(String nombre);
```

---

# 🔷 11. Ciclo de vida de una entidad

Estados:

1. **Transient** → no está en BD
2. **Managed** → controlado por Hibernate
3. **Detached** → fuera del contexto
4. **Removed** → marcado para eliminar

---

# 🔷 12. Transacciones

```java
import org.springframework.transaction.annotation.Transactional;

@Transactional
public void guardarUsuarios(List<Usuario> usuarios) {
    usuarios.forEach(repo::save);
}
```

✔ Maneja commit/rollback automáticamente

---

# 🔷 13. Lazy Loading (MUY IMPORTANTE)

```java
@OneToMany(fetch = FetchType.LAZY)
private List<Pedido> pedidos;
```

⚠ Problema común:

```
LazyInitializationException
```

👉 Soluciones:

* Usar `@Transactional`
* DTOs
* `fetch join` en JPQL

---

# 🔷 14. Buenas prácticas

✔ No exponer entidades directamente (usar DTOs)
✔ Evitar `EAGER` innecesario
✔ Usar paginación:

```java
Page<Usuario> page = repo.findAll(PageRequest.of(0, 10));
```

✔ Indexar columnas en BD
✔ Evitar N+1 queries

---

# 🔷 15. Ejemplo completo (CRUD real)

### Entidad

```java
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double precio;
}
```

---

### Repository

```java
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
```

---

### Service

```java
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public Producto guardar(Producto p) {
        return repo.save(p);
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
```

---

### Controller

```java
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @PostMapping
    public Producto crear(@RequestBody Producto p) {
        return service.guardar(p);
    }
}
```

---

# 🔷 16. Ventajas y desventajas

## ✔ Ventajas

* Menos SQL manual
* Código más limpio
* Productividad alta
* Integración con Spring

## ❌ Desventajas

* Curva de aprendizaje
* Problemas de rendimiento si se usa mal
* Abstracción puede ocultar SQL ineficiente

---

# 🔷 17. Conceptos avanzados (para que subas nivel)

* EntityManager
* Criteria API
* Specifications (queries dinámicas)
* Caching (L1 y L2)
* Batch inserts
* Projections
* DTO mapping

---

# 🔷 18. Resumen rápido

👉 JPA = especificación
👉 Hibernate = implementación
👉 Spring Boot = automatiza todo

👉 Tú trabajas con objetos → Hibernate genera SQL

---

# 🚀 Si quieres subir al siguiente nivel

Puedo explicarte:

* 🔥 Cómo evitar N+1 queries (MUY importante en producción)
* 🔥 Cómo hacer consultas dinámicas tipo filtros (Specifications)
* 🔥 Cómo optimizar rendimiento en JPA
* 🔥 Diferencias entre JPA vs JDBC vs Dapper (te conviene por tu background .NET)

Solo dime 👍



Perfecto, aquí tienes una explicación **profunda, técnica y orientada a uso real en backend** de cada uno de esos conceptos avanzados de **JPA + Hibernate en Spring Boot**. Voy a ir más allá de la teoría para que entiendas **cómo funcionan internamente, cuándo usarlos y qué problemas resuelven**.

---

# 🔷 1. EntityManager (el núcleo de JPA)

## 📌 ¿Qué es?

Es la **interfaz principal de JPA** para interactuar con el contexto de persistencia.

👉 Es básicamente el “motor” que:

* Maneja entidades
* Ejecuta queries
* Controla el ciclo de vida de objetos

---

## 📌 Internamente

Hibernate implementa `EntityManager` usando:

```text
Session (Hibernate) → EntityManager (JPA wrapper)
```

---

## 📌 Operaciones clave

```java
@PersistenceContext
private EntityManager em;
```

### 🔹 Persistir

```java
em.persist(usuario);
```

### 🔹 Buscar

```java
Usuario u = em.find(Usuario.class, 1L);
```

### 🔹 Actualizar

```java
em.merge(usuario);
```

### 🔹 Eliminar

```java
em.remove(usuario);
```

---

## 📌 Contexto de persistencia (clave)

El EntityManager mantiene un **cache en memoria (L1)**:

```text
DB → EntityManager → Objeto (Managed)
```

👉 Si consultas el mismo ID dos veces:

* Solo hace 1 query a la BD

---

## 📌 Cuándo usarlo vs Repository

| Caso                                                       | Usa           |
| ---------------------------------------------------------- | ------------- |
| CRUD simple                                                | Repository    |
| Control fino (performance, transacciones, joins complejos) | EntityManager |

---

# 🔷 2. Criteria API (queries dinámicas tipo builder)

## 📌 ¿Qué es?

Una API para construir consultas **dinámicamente y type-safe** (sin strings como JPQL).

---

## 📌 Problema que resuelve

Cuando tienes filtros opcionales:

```java
if(nombre != null) ...
if(edad != null) ...
```

Evitas concatenar strings SQL.

---

## 📌 Ejemplo

```java
CriteriaBuilder cb = em.getCriteriaBuilder();
CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
Root<Usuario> root = query.from(Usuario.class);

List<Predicate> predicates = new ArrayList<>();

if (nombre != null) {
    predicates.add(cb.equal(root.get("nombre"), nombre));
}

if (edad != null) {
    predicates.add(cb.greaterThan(root.get("edad"), edad));
}

query.where(predicates.toArray(new Predicate[0]));

List<Usuario> result = em.createQuery(query).getResultList();
```

---

## 📌 Ventajas

✔ Type-safe
✔ Dinámico
✔ Evita errores en strings

---

## 📌 Desventajas

❌ Muy verboso
❌ Difícil de leer

👉 Por eso nacen las **Specifications**

---

# 🔷 3. Specifications (queries dinámicas elegantes)

## 📌 ¿Qué es?

Abstracción de Spring Data basada en Criteria API.

---

## 📌 Ejemplo

```java
import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecification {

    public static Specification<Usuario> nombreEquals(String nombre) {
        return (root, query, cb) ->
            nombre == null ? null : cb.equal(root.get("nombre"), nombre);
    }

    public static Specification<Usuario> edadMayor(Integer edad) {
        return (root, query, cb) ->
            edad == null ? null : cb.greaterThan(root.get("edad"), edad);
    }
}
```

---

## 📌 Uso

```java
Specification<Usuario> spec = Specification
    .where(nombreEquals("Juan"))
    .and(edadMayor(18));

List<Usuario> usuarios = repo.findAll(spec);
```

---

## 📌 Ventajas reales

✔ Composición dinámica
✔ Reutilizable
✔ Limpio vs Criteria API

---

## 📌 Cuándo usarlo

👉 Filtros complejos tipo:

* búsqueda avanzada
* e-commerce
* reportes

---

# 🔷 4. Caching (L1 y L2)

## 📌 L1 Cache (First Level)

✔ Automático
✔ Vive dentro del EntityManager

```text
Transaction → EntityManager → Cache
```

👉 Ejemplo:

```java
Usuario u1 = em.find(Usuario.class, 1L);
Usuario u2 = em.find(Usuario.class, 1L);
```

✔ Solo 1 query a la BD

---

## 📌 L2 Cache (Second Level)

✔ Compartido entre sesiones
✔ Opcional

👉 Se configura con:

* Ehcache
* Redis
* Hazelcast

---

## 📌 Ejemplo

```java
@Cacheable
@Entity
public class Usuario { }
```

---

## 📌 Problema que resuelve

👉 Reduce acceso a BD en:

* datos poco cambiantes
* catálogos
* configuraciones

---

## 📌 Riesgo

❌ Datos desactualizados (stale data)

---

# 🔷 5. Batch Inserts (alto rendimiento)

## 📌 Problema

Insertar 10,000 registros así:

```java
for (...) {
    repo.save(obj);
}
```

❌ genera 10,000 queries

---

## 📌 Solución: batching

### Configuración

```properties
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.order_inserts=true
```

---

## 📌 Uso optimizado

```java
for (int i = 0; i < lista.size(); i++) {
    em.persist(lista.get(i));

    if (i % 50 == 0) {
        em.flush();
        em.clear();
    }
}
```

---

## 📌 Qué hace internamente

```text
INSERT INTO tabla VALUES (...), (...), (...)
```

---

## 📌 Mejora

🚀 De minutos → segundos

---

# 🔷 6. Projections (consultas optimizadas)

## 📌 Problema

```java
SELECT * FROM usuarios
```

❌ trae todo (ineficiente)

---

## 📌 Solución: projections

Solo traes lo necesario.

---

## 📌 Interface Projection

```java
public interface UsuarioDTO {
    String getNombre();
    String getEmail();
}
```

---

## 📌 Uso

```java
List<UsuarioDTO> usuarios = repo.findAllProjectedBy();
```

---

## 📌 Query personalizada

```java
@Query("SELECT u.nombre AS nombre, u.email AS email FROM Usuario u")
List<UsuarioDTO> listar();
```

---

## 📌 Beneficio

✔ Menos memoria
✔ Mejor performance
✔ Menos tráfico DB

---

# 🔷 7. DTO Mapping (arquitectura limpia)

## 📌 Problema

No debes exponer entidades:

```java
return Usuario; ❌
```

---

## 📌 Solución

Usar DTOs:

```java
public class UsuarioDTO {
    private String nombre;
    private String email;
}
```

---

## 📌 Mapping manual

```java
UsuarioDTO dto = new UsuarioDTO();
dto.setNombre(usuario.getNombre());
dto.setEmail(usuario.getEmail());
```

---

## 📌 Mapping con streams

```java
List<UsuarioDTO> dtos = usuarios.stream()
    .map(u -> new UsuarioDTO(u.getNombre(), u.getEmail()))
    .toList();
```

---

## 📌 Herramientas profesionales

* MapStruct 🔥 (recomendado)
* ModelMapper

---

## 📌 Por qué es clave

✔ Seguridad (no expones IDs internos)
✔ Control de datos
✔ API limpia

---

# 🔷 🔥 Cómo se relaciona TODO (visión real)

```text
Controller
   ↓
Service (@Transactional)
   ↓
Repository / EntityManager
   ↓
Specification / Criteria
   ↓
Hibernate
   ↓
Cache (L1/L2)
   ↓
Database
```

---

# 🔷 🚨 Problemas reales que resuelven

| Problema                | Solución                 |
| ----------------------- | ------------------------ |
| N+1 queries             | Projections / fetch join |
| Queries dinámicas       | Specifications           |
| Bajo rendimiento masivo | Batch inserts            |
| Datos repetidos         | Cache                    |
| Control total           | EntityManager            |
| APIs limpias            | DTO mapping              |

---

# 🔷 🚀 Conclusión

Si lo ves a nivel profesional:

* **EntityManager** → control bajo nivel
* **Criteria / Specification** → queries dinámicas
* **Cache** → rendimiento
* **Batch** → procesamiento masivo
* **Projections + DTO** → APIs eficientes

---

# 🔥 Si quieres seguir subiendo nivel (muy recomendado)

Te puedo explicar:

* Cómo evitar el **N+1 problem (el error #1 en JPA)**
* Cómo usar **fetch join vs entity graph**
* Cómo optimizar consultas como un senior
* Comparación con **Dapper (.NET)** para que conectes conceptos

Solo dime 👍



Perfecto, aquí entramos en **nivel profesional real con JPA/Hibernate**, justo donde la mayoría de aplicaciones fallan en producción si no se entiende bien.

Te voy a explicar:

1. 🔥 N+1 Problem (el error más común)
2. 🔥 fetch join vs entity graph
3. 🔥 Optimización real de consultas
4. 🔥 Comparación con Dapper (.NET)

---

# 🔷 1. N+1 Problem (el enemigo #1 en JPA)

## 📌 ¿Qué es?

Ocurre cuando Hibernate hace:

* 1 query principal
* * N queries adicionales por relaciones

---

## 📌 Ejemplo clásico

Supón:

```java
@Entity
class Usuario {
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    List<Pedido> pedidos;
}
```

---

## 📌 Código

```java
List<Usuario> usuarios = repo.findAll();

for (Usuario u : usuarios) {
    System.out.println(u.getPedidos().size());
}
```

---

## 📌 ¿Qué pasa en SQL?

```sql
-- 1 query
SELECT * FROM usuario;

-- N queries (una por usuario)
SELECT * FROM pedido WHERE usuario_id = 1;
SELECT * FROM pedido WHERE usuario_id = 2;
SELECT * FROM pedido WHERE usuario_id = 3;
...
```

---

## 📌 Problema real

```text
1 + N queries = rendimiento terrible
```

👉 Si tienes 1000 usuarios → 1001 queries 😬

---

## 📌 Cómo detectarlo

✔ Logs (`spring.jpa.show-sql=true`)
✔ Herramientas APM (NewRelic, etc.)

---

# 🔷 2. Solución 1: FETCH JOIN (JPQL)

## 📌 Idea

Traer todo en **una sola query con JOIN**

---

## 📌 Ejemplo

```java
@Query("SELECT u FROM Usuario u JOIN FETCH u.pedidos")
List<Usuario> findAllWithPedidos();
```

---

## 📌 SQL generado

```sql
SELECT u.*, p.*
FROM usuario u
JOIN pedido p ON p.usuario_id = u.id;
```

---

## 📌 Ventajas

✔ Elimina N+1
✔ Muy eficiente

---

## 📌 Problema

❌ Puede duplicar resultados (cartesian explosion)

👉 Solución:

```java
@Query("SELECT DISTINCT u FROM Usuario u JOIN FETCH u.pedidos")
```

---

# 🔷 3. Solución 2: Entity Graph (más elegante)

## 📌 ¿Qué es?

Forma declarativa de decir:
👉 “qué relaciones cargar”

---

## 📌 Ejemplo

```java
@EntityGraph(attributePaths = {"pedidos"})
List<Usuario> findAll();
```

---

## 📌 Ventajas

✔ Más limpio que JPQL
✔ Reutilizable
✔ No ensucia queries

---

## 📌 Cuándo usarlo

👉 Cuando quieres controlar qué cargar sin escribir JPQL

---

# 🔷 4. FETCH JOIN vs ENTITY GRAPH

| Característica | Fetch Join | EntityGraph |
| -------------- | ---------- | ----------- |
| Control SQL    | Alto       | Medio       |
| Legibilidad    | Media      | Alta        |
| Reutilización  | Baja       | Alta        |
| Complejidad    | Mayor      | Menor       |

👉 Regla práctica:

* Query compleja → Fetch Join
* Uso general → EntityGraph

---

# 🔷 5. Optimización real (nivel producción)

Aquí es donde se diferencia un junior de un senior.

---

## 📌 1. Evitar SELECT *

❌ Malo:

```java
List<Usuario> usuarios = repo.findAll();
```

✔ Mejor:

```java
@Query("SELECT u.nombre, u.email FROM Usuario u")
```

👉 o usar **Projections**

---

## 📌 2. Usar paginación SIEMPRE

```java
Page<Usuario> page = repo.findAll(PageRequest.of(0, 20));
```

---

## 📌 3. Evitar EAGER por defecto

❌

```java
@OneToMany(fetch = FetchType.EAGER)
```

✔ Siempre usar:

```java
FetchType.LAZY
```

---

## 📌 4. Índices en BD

Si haces:

```sql
WHERE email = ?
```

👉 esa columna debe tener índice

---

## 📌 5. Evitar cargar colecciones grandes

❌

```java
usuario.getPedidos(); // miles de registros
```

✔ Mejor:

* paginar pedidos
* query específica

---

## 📌 6. Usar batch fetching

```properties
spring.jpa.properties.hibernate.default_batch_fetch_size=50
```

👉 Hibernate agrupa queries:

```sql
SELECT * FROM pedido WHERE usuario_id IN (1,2,3,...)
```

---

## 📌 7. Evitar lógica en entidades

❌

```java
@Entity
public class Usuario {
    public void metodoComplejo() { ... }
}
```

✔ Mantener entidades limpias

---

# 🔷 6. FETCH JOIN vs PROJECTION (decisión clave)

| Caso                       | Solución   |
| -------------------------- | ---------- |
| Necesitas entidad completa | Fetch Join |
| Solo datos específicos     | Projection |

---

# 🔷 7. Comparación con Dapper (.NET) 🔥

Tú vienes de .NET, esto te va a aclarar TODO.

---

## 📌 Dapper

✔ Micro ORM
✔ Tú escribes SQL
✔ Máximo control
✔ Muy rápido

```csharp
var usuarios = connection.Query<Usuario>(
    "SELECT * FROM usuarios WHERE id = @id", new { id = 1 });
```

---

## 📌 JPA/Hibernate

✔ ORM completo
✔ Genera SQL automáticamente
✔ Más abstracción
✔ Más fácil pero peligroso si no sabes optimizar

---

## 📌 Comparación directa

| Característica | JPA/Hibernate | Dapper   |
| -------------- | ------------- | -------- |
| Control SQL    | Bajo          | Alto     |
| Facilidad      | Alta          | Media    |
| Performance    | Media-Alta    | Muy alta |
| Abstracción    | Alta          | Baja     |
| Riesgo N+1     | Sí            | No       |

---

## 📌 Conclusión realista

👉 JPA:

* Ideal para sistemas grandes
* Productividad alta

👉 Dapper:

* Ideal cuando necesitas máximo rendimiento
* APIs críticas

---

# 🔷 🔥 Estrategia profesional (lo que hacen seniors)

👉 Mezclar ambos enfoques:

* CRUD → JPA
* Queries críticas → JPQL optimizado o Native SQL
* Reportes → Projections

---

# 🔷 🚀 Resumen brutal (para que no se te olvide)

* N+1 = muerte del rendimiento
* Fetch Join / EntityGraph = solución
* Projection = eficiencia
* DTO = arquitectura limpia
* Batch + Cache = escalabilidad

---

# 🔥 Si quieres subir aún más nivel

Te puedo enseñar:

* Cómo detectar N+1 automáticamente
* Cómo usar **EntityGraph dinámico**
* Cómo hacer **queries tipo filtros complejos estilo e-commerce**
* Cómo estructurar un backend profesional (como empresas grandes)

Solo dime y seguimos 👍
