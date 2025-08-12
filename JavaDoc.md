# 📄 JavaDoc en Java

## 📌 ¿Qué es JavaDoc?
**JavaDoc** es una herramienta oficial incluida en el **JDK** que permite generar documentación en formato **HTML** a partir de comentarios especiales escritos en el código fuente de Java.

Estos comentarios siguen un formato específico (`/** ... */`) y pueden incluir etiquetas que describen parámetros, valores de retorno, excepciones, autores, etc.

El resultado es una documentación navegable similar a la **API oficial de Java**.

---

## 🎯 ¿Para qué se usa?
- Documentar **clases, interfaces, métodos y atributos**.
- Facilitar la comprensión del código a otros desarrolladores (o a ti mismo en el futuro).
- Generar documentación **navegable en HTML** sin escribir HTML manualmente.
- Mantener la documentación y el código **sincronizados**.

---

## ⚙️ ¿Cómo se usa?

### 1️⃣ Escribir comentarios JavaDoc
Se escriben usando `/** ... */` encima de clases, métodos o atributos.

Ejemplo:
```java
/**
 * Clase que representa un producto en una tienda.
 * Proporciona métodos para calcular el precio con descuento.
 */
public class Producto {
    private String nombre;
    private double precio;

    /**
     * Crea un nuevo producto.
     * 
     * @param nombre Nombre del producto
     * @param precio Precio base del producto
     */
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Calcula el precio del producto aplicando un descuento.
     *
     * @param porcentajeDescuento Porcentaje de descuento (0-100)
     * @return Precio con el descuento aplicado
     * @throws IllegalArgumentException si el porcentaje es negativo o mayor a 100
     */
    public double calcularPrecioConDescuento(double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("Porcentaje inválido");
        }
        return precio - (precio * porcentajeDescuento / 100);
    }
}
