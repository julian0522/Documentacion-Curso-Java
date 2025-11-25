
## 💻 ¿Qué es Spring Boot?

**Spring Boot** es una extensión del framework **Spring** (uno de los frameworks de desarrollo de aplicaciones Java más populares). Su principal objetivo es **simplificar** la configuración y el despliegue de aplicaciones basadas en Spring.

Piensa en el framework Spring original como una enorme caja de herramientas muy flexible, pero que requiere mucho trabajo manual para armar cada proyecto (configurar XML, dependencias, servidores, etc.).

**Spring Boot** se encarga de todo ese trabajo pesado de configuración por ti. Te permite crear aplicaciones **"listas para ejecutar"** (standalone) con una **configuración mínima**.

---

## ✨ ¿Para qué sirve Spring Boot?

El propósito fundamental de Spring Boot es mejorar la **productividad del desarrollador** y reducir el tiempo de configuración inicial de un proyecto.

Sirve principalmente para:

### 1. **Crear Aplicaciones Standalone (Autocontenidas)**
Spring Boot incluye un **servidor web embebido** (como Tomcat, Jetty o Undertow). Esto significa que puedes empaquetar tu aplicación en un solo archivo `.jar` y ejecutarlo directamente con el comando `java -jar mi-app.jar`, sin necesidad de desplegarlo manualmente en un servidor de aplicaciones externo.

### 2. **Configuración Automática (Auto-Configuration)**

Analiza las dependencias que has incluido en tu proyecto y **automáticamente configura** muchos de los componentes del framework Spring por ti. Por ejemplo, si detecta que has agregado la dependencia para la base de datos H2, automáticamente configura la conexión básica.

### 3. **Configuración Opinada por Defecto (Opinionated Defaults)**
Ofrece un conjunto de **configuraciones por defecto** bien pensadas y prácticas que funcionan para la mayoría de los casos de uso. Esto te evita tener que escribir código de configuración repetitivo o complejo. Si lo necesitas, siempre puedes anular estas configuraciones.

### 4. **Gestión de Dependencias Simplificada**
Utiliza **"starters"** (dependencias pre-agrupadas) que incluyen todo lo que necesitas para una funcionalidad específica. Por ejemplo, si quieres crear una aplicación web, solo agregas el `spring-boot-starter-web` y obtienes todas las dependencias necesarias para desarrollo web (incluyendo el servidor embebido).

### 5. **Monitorización y Gestión en Producción**
Incluye un módulo llamado **Spring Boot Actuator** que añade *endpoints* HTTP a tu aplicación (como `/health`, `/metrics`, `/info`). Estos *endpoints* son vitales para monitorizar, gestionar y obtener información sobre el estado de tu aplicación una vez que está en producción.

---

## ⚙️ Funcionalidades Clave de Spring Boot

---

### 1. **Convention over Configuration (Convención sobre Configuración)**

* **¿Qué es?** Es un principio de diseño de software que intenta **reducir el número de decisiones** que un desarrollador debe tomar. En lugar de requerir que el desarrollador configure cada aspecto de la aplicación (como la ubicación de los archivos o los nombres de los *beans*), Spring Boot asume por defecto que sigues ciertas **convenciones**.
* **¿Cómo funciona?**
    * **Ejemplo:** Por convención, Spring Boot espera encontrar la configuración en un archivo llamado `application.properties` o `application.yml` en el directorio de recursos. También asume que tu clase principal de la aplicación se llama de cierta manera o está anotada con `@SpringBootApplication`.
* **Beneficio:** Si sigues estas convenciones, la configuración es **mínima o nula**. Si las convenciones por defecto no te sirven, tienes la libertad de **anularlas** (configuración explícita), pero para el 90% de los casos, la configuración ya está hecha.

---

### 2. **Auto-Configuration (Autoconfiguración)**

* **¿Qué es?** Es la capacidad de Spring Boot de **configurar automáticamente** las partes de tu aplicación Spring en función de las **dependencias** (`JARs`) que detecta en tu *classpath*.
* **¿Cómo funciona?**
    * Spring Boot inspecciona tu proyecto.
    * Si detecta que tienes la librería de **JPA/Hibernate** y un *driver* de base de datos (como MySQL o H2), automáticamente configura un *DataSource*, un *EntityManager* y otros *beans* necesarios para la conexión a la base de datos.
    * Si detecta la librería **Spring MVC**, configura un *DispatcherServlet* y otras piezas necesarias para manejar peticiones web.
* **Beneficio:** Evita que tengas que escribir y mantener **cientos de líneas de código de configuración Java o XML** para los componentes comunes de Spring.

---

### 3. **Servidores Embebidos (Embedded Servers)**

* **¿Qué son?** Son servidores web como **Apache Tomcat**, **Jetty** o **Undertow** que se incluyen directamente *dentro* del archivo de aplicación ejecutable (`.jar`).
* **¿Cómo funciona?**
    * Al construir tu aplicación, Spring Boot incluye el servidor web como una dependencia.
    * Cuando ejecutas el archivo JAR (usando `java -jar nombre-de-la-app.jar`), el servidor se **inicia automáticamente** en un puerto predefinido (generalmente el **8080**) y despliega tu aplicación en él.
* **Beneficio:**
    * Crea aplicaciones **"standalone"** (autocontenidas) que puedes ejecutar en cualquier lugar con Java.
    * **Simplifica el despliegue**, ya que no necesitas instalar, configurar ni gestionar un servidor de aplicaciones externo (como antes se hacía con WebSphere o JBoss).

---

### 4. **Starters (Dependencias de Inicio)**

* **¿Qué son?** Son conjuntos de **descriptores de dependencias** convenientes que puedes incluir en tu proyecto para obtener toda la tecnología necesaria para una funcionalidad específica de forma rápida y coherente.
* **¿Cómo funciona?**
    * Un *Starter* típico (por ejemplo, `spring-boot-starter-web`) **agrupa** todas las dependencias relacionadas que necesitas para el desarrollo web (Spring Core, Spring MVC, Jackson para JSON, y el servidor Tomcat embebido).
    * Todos los *starters* están optimizados para trabajar juntos.
* **Ejemplos Comunes:**
    * `spring-boot-starter-data-jpa`: Para acceder a bases de datos relacionales con JPA/Hibernate.
    * `spring-boot-starter-test`: Para escribir y ejecutar pruebas unitarias y de integración.
* **Beneficio:** Simplifican el archivo de configuración de dependencias (`pom.xml` o `build.gradle`) y garantizan que las versiones de todas las librerías agrupadas son **compatibles**.

---

### 5. **Actuator (Módulo de Gestión)**

* **¿Qué es?** Es una característica que proporciona **puntos finales de producción** (*endpoints* HTTP) listos para usar, que te permiten monitorizar, gestionar e inspeccionar el estado interno de tu aplicación cuando está en ejecución. 
* **¿Cómo funciona?**
    * Al agregar la dependencia `spring-boot-starter-actuator`, tu aplicación expone automáticamente URL específicas.
    * Puedes acceder a ellas a través de HTTP o JMX.
* **Endpoints Típicos:**
    * `/actuator/health`: Muestra el estado de salud de la aplicación (si la base de datos y otras dependencias están operativas).
    * `/actuator/info`: Muestra información personalizada sobre la versión y el entorno.
    * `/actuator/metrics`: Muestra métricas del sistema (uso de memoria, CPU, llamadas HTTP, etc.).
    * `/actuator/env`: Muestra las variables de entorno y propiedades de configuración.
* **Beneficio:** Es fundamental para la **monitorización en entornos de producción** y para integrarse con herramientas de gestión (como Prometheus o Grafana).

---

## 🚀 Pasos para crear un proyecto en Spring Initializr

Puedes acceder a la herramienta en [https://start.spring.io/](https://start.spring.io/).

---

### 1. **Metadatos del Proyecto (Project Metadata)**

Esta sección define las características fundamentales de tu proyecto.

| Campo | Propósito | Ejemplo Típico |
| :--- | :--- | :--- |
| **Project** (Tipo de Proyecto) | Elige el sistema de compilación. **Maven** es el más común, pero **Gradle** también es popular. | Maven Project |
| **Language** (Lenguaje) | Selecciona el lenguaje de programación principal. **Java** es el predeterminado, pero puedes elegir Kotlin o Groovy. | Java |
| **Spring Boot** (Versión) | La versión del *framework* Spring Boot que deseas utilizar. Generalmente, se recomienda la versión **estable más reciente** (sin la etiqueta `SNAPSHOT`). | 3.2.0 |

---

### 2. **Metadatos del Artefacto (Project Metadata)**

Esta sección se utiliza para dar una identidad única a tu proyecto.

| Campo | Propósito | Explicación |
| :--- | :--- | :--- |
| **Group** (Grupo) | Es el **identificador de la organización** o dominio, siguiendo la convención de paquetes de Java (dominio inverso). | `com.ejemplo` o `io.micompania` |
| **Artifact** (Artefacto) | El **nombre de tu proyecto** o módulo. Se utiliza para nombrar el archivo JAR o WAR generado. | `mi-aplicacion-web` |
| **Name** (Nombre) | Un nombre más legible para el proyecto (a menudo el mismo que *Artifact*). | `mi-aplicacion-web` |
| **Description** (Descripción) | Una breve descripción de lo que hace tu proyecto. | `Proyecto demo de una API REST` |
| **Package name** (Nombre del Paquete) | La combinación de *Group* y *Artifact*. Es el paquete base para tus clases de Java. | `com.ejemplo.miapp` |
| **Packaging** (Empaquetado) | Define cómo se empaquetará la aplicación. **Jar** (Java Archive) es lo más común para aplicaciones web autocontenidas. **War** (Web Archive) se usa si planeas desplegarlo en un servidor de aplicaciones tradicional (como Tomcat). | Jar |
| **Java** (Versión de Java) | La versión del JDK (Java Development Kit) que utilizarás para compilar y ejecutar tu aplicación. | 21 o 17 |



---

### 3. **Dependencias (Dependencies)**

Este es el paso **más importante**. Aquí eliges las librerías o módulos de Spring Boot que necesita tu proyecto para funcionar. Al hacer clic en el botón **"Add Dependencies"**, puedes buscar y seleccionar:

* **Spring Web:** Necesaria si vas a crear **APIs REST** o aplicaciones web.
* **Spring Data JPA:** Si necesitas interactuar con una **base de datos relacional** (como MySQL, PostgreSQL).
* **H2 Database:** Una base de datos en memoria, ideal para **pruebas** y desarrollo local.
* **Thymeleaf:** Si vas a crear una aplicación web que renderiza vistas (servidor-side rendering).
* **Lombok:** Una librería útil para reducir el *boilerplate* (código repetitivo) en clases Java (getters, setters, etc.).
* **Spring Security:** Si necesitas **autenticación y autorización** en tu aplicación.
* **Spring DevTools:** Herramientas que facilitan el desarrollo, como la recarga automática del servidor.

---

### 4. **Generar el Proyecto**

1.  Una vez que hayas llenado todos los campos y seleccionado tus dependencias, haz clic en el botón **"Generate"** (Generar).
2.  Se descargará un archivo **ZIP** que contiene la estructura base de tu proyecto Spring Boot (archivos de configuración, el archivo `pom.xml` o `build.gradle`, la clase `Application.java`, etc.).
3.  Descomprime el archivo e impórtalo en tu **IDE** (como IntelliJ IDEA, VS Code o Eclipse) como un proyecto Maven o Gradle.

¡Y eso es todo! Ya tienes un proyecto Spring Boot listo para empezar a codificar.

---

-----

## 🏗️ Estructura Base de un Proyecto Spring Boot

Al descomprimir el archivo ZIP de Spring Initializr e importarlo en tu IDE (usando Maven como ejemplo), verás una jerarquía de carpetas y archivos similar a esta:

```
├── my-project
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── ejemplo
│   │   │   │           └── MyProjectApplication.java  <-- Clase principal
│   │   │   └── resources
│   │   │       ├── application.properties  <-- Archivo de configuración
│   │   │       └── static/
│   │   │       └── templates/
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── ejemplo
│   │                   └── MyProjectApplicationTests.java  <-- Clases de prueba
│   └── pom.xml  <-- Archivo de configuración de Maven
```

-----

## 📂 Archivos y Carpetas Clave

Los archivos y carpetas más importantes se encuentran en las raíces de tu proyecto (`pom.xml`) y dentro de la carpeta `src/main`.

### 1\. **Archivos de Compilación y Dependencias**

| Archivo | Ubicación | Propósito |
| :--- | :--- | :--- |
| **`pom.xml`** | Raíz del proyecto | Es el archivo de configuración principal de **Maven**. Define: <br> \* **Metadatos** del proyecto (`group`, `artifact`, `java version`). <br> \* Las **dependencias** (librerías) que necesita tu aplicación. <br> \* Los **plugins** para la construcción, como el plugin de Spring Boot para generar el JAR ejecutable. |
| **`build.gradle`** | Raíz del proyecto | Cumple la misma función que `pom.xml`, pero se usa si elegiste **Gradle** como sistema de compilación. |

### 2\. **Archivos de Código Fuente (`src/main/java`)**

| Archivo | Ubicación | Propósito |
| :--- | :--- | :--- |
| **`MyProjectApplication.java`** | En el paquete base (`com.ejemplo.myproject`) | Es la **Clase Principal** de tu aplicación. Contiene el método `main` y la anotación **`@SpringBootApplication`**. Esta anotación marca la clase como el punto de inicio de la aplicación, habilitando la autoconfiguración de Spring Boot y el escaneo de componentes. |

---

### 🎯 `@SpringBootApplication`: El Corazón de tu App
___
La anotación **`@SpringBootApplication`** es el punto de inicio de tu aplicación Spring Boot. Es una anotación de conveniencia que combina las funcionalidades de **tres anotaciones clave** de Spring:

| Anotación | Función Principal |
| :--- | :--- |
| **`@Configuration`** | Designa la clase como una fuente de **definición de *beans*** (componentes) para el *framework*. |
| **`@EnableAutoConfiguration`** | Activa la **configuración automática** de Spring Boot. Configura elementos (como el servidor web o la conexión a la base de datos) basándose en las dependencias que tienes en tu proyecto. |
| **`@ComponentScan`** | Le indica a Spring que **busque y registre** otros componentes (como `@Controller`, `@Service`, `@Repository`) en el paquete de la clase principal y sus subpaquetes. |

Al usar **`@SpringBootApplication`** en tu clase principal, le dices a Spring: "Configura automáticamente la aplicación, escanea mis componentes y usa esta clase como base para la configuración." Permite ejecutar toda la aplicación con un solo método: `SpringApplication.run()`.

> ⭐ **Dato Clave:** Spring Boot es una aplicación **autoejecutable**. Esta clase principal lanza un servidor web integrado (como Tomcat) y ejecuta toda tu lógica de negocio.

### 3\. **Archivos de Recursos (`src/main/resources`)**

Esta carpeta contiene todos los archivos que no son código Java, como configuraciones, *templates* de vistas, y archivos estáticos.

| Archivo/Carpeta | Propósito |
| :--- | :--- |
| **`application.properties`** o **`application.yml`** | El archivo de **configuración principal** de Spring Boot. Aquí defines propiedades como: <br> \* El puerto en el que se ejecutará la aplicación (ej: `server.port=8081`). <br> \* La configuración de la base de datos (URL, usuario, contraseña). <br> \* Opciones específicas de los *frameworks* que uses. |
| **`static/`** | Se usa para almacenar **archivos estáticos** que el cliente necesita: <br> \* Hojas de estilo (**CSS**). <br> \* Archivos **JavaScript**. <br> \* **Imágenes** que se acceden directamente por URL. |
| **`templates/`** | Se usa para almacenar **vistas** o **plantillas** (como archivos `.html` con Thymeleaf o Freemarker). Spring las procesa en el servidor antes de enviarlas al navegador. |

### 4\. **Archivos de Prueba (`src/test/java`)**

| Archivo | Ubicación | Propósito |
| :--- | :--- | :--- |
| **`MyProjectApplicationTests.java`** | En el paquete base de pruebas | Contiene la clase base para ejecutar **pruebas unitarias y de integración**. La anotación `@SpringBootTest` carga el contexto completo de la aplicación Spring para asegurar que los componentes trabajen juntos correctamente. Es esencial para garantizar la calidad del código. |

-----


## ⚙️ ¿Qué es `application.properties`?

Es un archivo de configuración que Spring Boot lee automáticamente al inicio de la aplicación. Su función principal es **externalizar la configuración**, lo que significa que puedes cambiar los ajustes (como el puerto, la conexión a la base de datos, o niveles de *logging*) sin tener que recompilar el código fuente.

### 📝 Formato Básico

Utiliza un formato sencillo de **clave-valor** (key-value), donde la clave sigue una convención jerárquica y el valor es la configuración deseada.

> **Clave = Valor**
>
> Ejemplo:
> `server.port = 8081`

-----

## 🛠️ Tipos de Configuración Comunes

Aquí tienes los tipos de configuraciones más importantes que se suelen realizar en este archivo:

### 1\. Configuración del Servidor Web

Permite controlar cómo se comporta el servidor web integrado (por defecto, Tomcat).

| Clave | Propósito | Ejemplo |
| :--- | :--- | :--- |
| `server.port` | Define el puerto en el que se ejecutará la aplicación. | `server.port=8081` |
| `server.servlet.context-path` | Define un prefijo de contexto global para todas las rutas. | `server.servlet.context-path=/api/v1` |
| `server.max-http-header-size` | Define el tamaño máximo permitido para los encabezados HTTP. | `server.max-http-header-size=16KB` |

### 2\. Configuración de la Base de Datos (DataSource)

Es esencial para conectar tu aplicación con una base de datos relacional (como MySQL, PostgreSQL, o H2).

| Clave | Propósito | Ejemplo |
| :--- | :--- | :--- |
| `spring.datasource.url` | La URL de conexión a la base de datos. | `jdbc:mysql://localhost:3306/mi_db` |
| `spring.datasource.username` | Nombre de usuario para la conexión. | `spring.datasource.username=root` |
| `spring.datasource.password` | Contraseña para la conexión. | `spring.datasource.password=12345` |
| `spring.datasource.driver-class-name` | La clase *driver* a utilizar. | `com.mysql.cj.jdbc.Driver` |


### 3\. Configuración de Logging (Registro)

Permite controlar qué tan detallados son los mensajes que imprime la aplicación en la consola.

| Clave | Propósito | Ejemplo |
| :--- | :--- | :--- |
| `logging.level.<paquete>` | Define el nivel de *logging* (ej. `INFO`, `DEBUG`, `WARN`, `ERROR`) para un paquete específico. | `logging.level.com.ejemplo.miapp=DEBUG` |
| `logging.level.root` | Define el nivel por defecto para toda la aplicación. | `logging.level.root=INFO` |

### 4\. Propiedades Personalizadas

Puedes definir tus **propias propiedades** para usarlas en tu código Java, lo que es útil para configuraciones específicas de tu lógica de negocio (ej. límites, claves de APIs externas).

| Clave | Propósito | Ejemplo |
| :--- | :--- | :--- |
| `<propiedad.personalizada>` | Cualquier configuración única que tu aplicación necesite. | `app.version=1.0.0` <br> `api.externa.key=XYZ123ABC` |

En tu código Java, puedes inyectar estos valores usando la anotación `@Value`:

```java
@Value("${app.version}")
private String appVersion;
```

-----

## 💡 Alternativa: `application.yml`

Spring Boot también soporta el formato **YAML** (Yet Another Markup Language), que utiliza sangría para representar la jerarquía, lo que muchos desarrolladores encuentran más limpio y legible, especialmente para configuraciones complejas:

```yaml
server:
  port: 8081
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mi_db
    username: root
```

-----

El `application.properties` (o `application.yml`) es una parte esencial de la filosofía de **convención sobre configuración** de Spring Boot, ya que ofrece un lugar centralizado y fácil de gestionar para todas las configuraciones.