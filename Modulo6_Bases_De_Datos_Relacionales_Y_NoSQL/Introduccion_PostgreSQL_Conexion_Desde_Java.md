# Introducción a PostgreSQL y Conexión desde Java

**PostgreSQL** (a menudo llamado simplemente **Postgres**) es uno de los sistemas de gestión de bases de datos de código abierto más potentes y avanzados del mundo.

A diferencia de las bases de datos que solo manejan tablas simples, PostgreSQL es un sistema **objeto-relacional**. Esto significa que, además de usar el lenguaje SQL estándar, tiene características que le permiten manejar estructuras de datos complejas y personalizadas.

## ¿Qué es una Base de Datos Relacional? Características de PostgreSQL

### Concepto Fundamental: El Modelo Relacional

Una base de datos relacional organiza los datos en **tablas**. Piensen en cada tabla como una hoja de cálculo.

- Cada tabla tiene **columnas** (también llamadas campos o atributos) que definen el tipo de datos que se almacenará (ej. `nombre`, `edad`, `fecha_creacion`).
- Cada fila (también llamada registro o tupla) en la tabla contiene un conjunto de valores para esas columnas, representando una instancia individual (ej. una persona, un producto específico).
- **Lo más importante**: las bases de datos relacionales permiten definir relaciones entre tablas. Esto se hace típicamente usando claves primarias (primary key) (que identifican únicamente una fila dentro de su tabla) y claves foráneas (foreign key) (que son columnas en una tabla que referencian la clave primaria de otra tabla).

### Rol en Aplicaciones Empresariales: Robustez y Estructura

Las bases de datos relacionales han sido el estándar por décadas por buenas razones:

- **Integridad de Datos**: Garantizan que los datos sean precisos y consistentes mediante esquemas (la definición de tablas y columnas) y restricciones (reglas que los datos deben cumplir).
- **Transacciones ACID**: Soportan transacciones que son Atómicas (todo o nada), Consistentes (mantienen la integridad), Aisladas (no interfieren entre sí) y Durables (los cambios persisten). Esto es vital para operaciones críticas (ej. transferencias bancarias).
- **Datos Estructurados**: Son ideales para manejar datos que tienen una estructura clara y predecible, y donde las relaciones entre diferentes tipos de datos son cruciales (ej. clientes que realizan pedidos, productos que pertenecen a categorías).

### PostgreSQL: Un Gigante de Código Abierto

PostgreSQL es uno de los sistemas de gestión de bases de datos relacionales (RDBMS) más avanzados, potentes y respetados que existen.

- Es de **código abierto** y gratuito, pero con capacidades a la par o superiores a muchos sistemas comerciales.
- Alto cumplimiento del **estándar SQL**. Lo que aprendan de SQL aquí les servirá en otras bases de datos.
- **Extensibilidad**: Permite añadir nuevos tipos de datos, funciones, etc.
- Soporte para **tipos de datos avanzados**: Puede manejar texto plano, números, fechas, booleanos, pero también tipos más complejos como JSON, arrays, rangos, tipos geométricos, etc.
- Es una elección sólida y fiable para casi cualquier tipo de aplicación.
* **Soporte NoSQL:** Además de tablas relacionales, maneja de forma excelente formatos **JSON** y **JSONB**, lo que permite competir con bases de datos como MongoDB.
* **Consultas Complejas:** Es extremadamente eficiente procesando grandes volúmenes de datos y uniones (*joins*) complicadas.
* **Concurrencia:** Utiliza un sistema llamado **MVCC** (Control de Concurrencia Multiversión), que permite que muchas personas lean y escriban datos al mismo tiempo sin bloquearse entre sí.
* **PostGIS:** Tiene la mejor extensión del mercado para manejar datos geográficos y mapas.

## Instalación, Configuración y Uso de Herramientas

### Instalación de PostgreSQL

El primer paso es instalar el servidor de base de datos en su computadora.

- **Windows/macOS**: La forma más sencilla es descargar el instalador oficial desde el sitio web de [PostgreSQL](https://www.postgresql.org/download/). El instalador los guiará en el proceso. Durante la instalación, les pedirá configurar la contraseña para el usuario `postgres` (el superusuario). ¡Guarden bien esta contraseña!
- **Linux**: Generalmente se instala a través del gestor de paquetes de su distribución (ej. `sudo apt update && sudo apt install postgresql postgresql-contrib` en sistemas basados en Debian/Ubuntu).

### Herramientas Gráficas de Administración

Interactuar directamente con la línea de comandos puede ser tedioso al principio. Las herramientas gráficas facilitan mucho la administración y consulta.

- **pgAdmin**: Es la herramienta gráfica oficial y más común para PostgreSQL. Permite crear bases de datos, diseñar tablas visualmente o mediante código SQL, ejecutar consultas, ver datos, monitorear el servidor, etc. Descarguen la versión 4 o superior.
- **DBeaver**: Es una herramienta universal que sirve para muchas bases de datos, incluyendo PostgreSQL. Si ya la usan para otra base de datos, pueden seguir usándola. Es muy potente y rica en funcionalidades.
- **Actividad**: Al instalar pgAdmin o DBeaver y conectarse a su servidor local de PostgreSQL (generalmente `localhost` o `127.0.0.1`, puerto `5432`, usuario `postgres`, con la contraseña que se configuro). Asegurese de abrir la herramienta, crear una nueva conexión y verificar que puede acceder a la base de datos.

## Creación de Esquemas, Tablas, Tipos de Datos y Restricciones

### Concepto de Esquema

- Piensen en una base de datos como un gran contenedor. Dentro de ese contenedor, pueden tener múltiples "esquemas".
- Un esquema es como una carpeta o un namespace que agrupa tablas, vistas, etc. El esquema por defecto, donde usualmente trabajamos al inicio, se llama `public`.
- Puedes crear esquemas para organizar objetos lógicos de tu aplicación. `CREATE SCHEMA nombre_esquema;`

### Creación de Tablas: La Sentencia `CREATE TABLE`

Así definimos la estructura de una tabla usando SQL. Si tienen dudas sobre la sintaxis, pueden usar herramientas como Cursor AI para generar ejemplos personalizados o resolver dudas específicas.

```sql
CREATE TABLE productos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio NUMERIC(10, 2) CHECK (precio > 0)
);
```

### Tipos de Datos Comunes en PostgreSQL

Al definir una columna, deben especificar qué tipo de datos contendrá:

- `INT`, `INTEGER`: Números enteros.
- `BIGINT`: Números enteros grandes.
- `VARCHAR(n)`: Texto con un límite máximo de n caracteres (ej. `VARCHAR(100)`).
- `TEXT`: Texto sin un límite de longitud práctico.
- `BOOLEAN`: `TRUE` o `FALSE`.
- `DATE`: Solo fecha (ej. '2023-10-27').
- `TIME`: Solo hora.
- `TIMESTAMP`: Fecha y hora.
- `NUMERIC(p, s)`, `DECIMAL(p, s)`: Números decimales con precisión exacta. `p` es la precisión total (número de dígitos), `s` es la escala (número de dígitos después del punto decimal).
- `SERIAL`: Un pseudotipo que crea una secuencia automáticamente y usa su próximo valor como valor por defecto para la columna. Ideal para claves primarias autogeneradas. Es equivalente a crear una secuencia y usar `DEFAULT nextval('nombre_secuencia')`.
  - `SMALLSERIAL`, `BIGSERIAL`: Versiones para números más pequeños o más grandes.

### Restricciones (Constraints): Reglas para la Integridad de Datos

Las restricciones son vitales para asegurar que los datos en sus tablas sean válidos y se relacionen correctamente.

- `PRIMARY KEY`:
  - Identifica de forma única cada fila en la tabla.
  - Solo puede haber una clave primaria por tabla.
  - No permite valores `NULL`.
  - A menudo se usa en una columna `SERIAL` o `BIGSERIAL`.
  - Ejemplo: `id BIGSERIAL PRIMARY KEY`
- `FOREIGN KEY`:
  - Establece y mantiene vínculos entre tablas.
  - Una columna (o grupo de columnas) en una tabla referencia la clave primaria (o única) en otra tabla.
  - Asegura la **integridad referencial** (no puedes tener un pedido para un cliente que no existe).
  - Ejemplo: `id_cliente BIGINT REFERENCES clientes(id)`
- `NOT NULL`:
  - Asegura que una columna debe tener un valor. No puede ser `NULL`.
  - Ejemplo: `nombre VARCHAR(100) NOT NULL`
- `UNIQUE`:
  - Asegura que todos los valores en una columna o grupo de columnas son únicos dentro de la tabla.
  - A diferencia de `PRIMARY KEY`, puede permitir valores `NULL` (si la columna no es también `NOT NULL`).
  - Ejemplo: `email VARCHAR(255) UNIQUE` (para asegurar que cada usuario tiene un email único).
- `CHECK`:
  - Permite definir una condición que cada fila debe cumplir para poder ser insertada o actualizada.
  - Ejemplo: `precio NUMERIC(10, 2) CHECK (precio > 0)`

## Configuración del archivo `application.properties` en Java

### Propiedades clave para la conexión

- `spring.datasource.url`: La URL de conexión JDBC. Sigue un formato `jdbc:nombre_db://host:puerto/nombre_base_datos`.
  - Ejemplo (para base de datos local `clase_db`): `spring.datasource.url=jdbc:postgresql://localhost:5432/clase_db`
- `spring.datasource.username`: El nombre de usuario para autenticarse en la base de datos.
  - Ejemplo: `spring.datasource.username=postgres`
- `spring.datasource.password`: La contraseña para autenticarse en la base de datos.
  - Ejemplo: `spring.datasource.password=su_contraseña_segura`
- `spring.jpa.hibernate.ddl-auto=none`: Es la opción recomendada para entornos productivos, ya que evita modificaciones automáticas en el esquema de la base de datos.