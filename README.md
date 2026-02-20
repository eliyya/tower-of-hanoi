# Problema de las Torres de Hanói (Tower of Hanoi)

Proyecto Java que resuelve el clásico problema de las **Torres de Hanói**
utilizando búsqueda en profundidad (DFS).
El problema consiste en mover una pila de discos de distintos tamaños
desde una torre origen hasta una torre destino, usando una torre auxiliar,
sin colocar nunca un disco grande sobre uno más pequeño.

## Enfoque

- Se modela cada configuración del problema como un `State`,
  representando la distribución de discos en las tres torres.
- `Node` representa un nodo del árbol de búsqueda, con referencia al padre
  y al movimiento aplicado.
- Se realiza una búsqueda en profundidad (DFS) desde el estado inicial
  hasta alcanzar el estado objetivo.
- Cuando se encuentra el estado objetivo, se reconstruye la secuencia
  de movimientos utilizando las referencias `parent`.

El algoritmo explora recursivamente cada posible movimiento válido
hasta llegar a una solución.

## Reglas del problema

- Solo se puede mover un disco a la vez.
- Solo se puede mover el disco que está en la parte superior de una torre.
- Nunca se puede colocar un disco grande sobre uno más pequeño.

## Estructura del proyecto

- `src/main/java/com/eli/` contiene las clases principales:
  - `App.java` - punto de entrada y algoritmo DFS
  - `Node.java` - representación de nodos y generación de hijos
  - `State.java` - representación del estado de las torres
  - `Validations.java` - utilidades de validación y búsqueda auxiliar

- `tree.json` (generado) — representación JSON del árbol para visualización.

## Cómo compilar y ejecutar

Requisitos: Java (la versión configurada en `pom.xml`, actualmente `release 25`) y Maven.

Compilar y ejecutar con Maven:

```bash
mvn package
java -cp target/classes com.eli.App
```

El programa escribe `tree.json` en la raíz del proyecto; puedes visualizarlo en 
https://jsoncrack.eliyya.dev/editor o en https://jsoncrack.com/editor pegando el contenido.

## Generar la documentación Javadoc

Puedes generar la documentación Javadoc con Maven:

```bash
mvn javadoc:javadoc
```

La salida estará en `target/site/apidocs`.

## Notas

- El `pom.xml` incluye `--enable-preview` y `release 25`. Asegúrate de
  tener la JDK adecuada si quieres compilar tal cual.
- El algoritmo implementado es una DFS simple; no se ha optimizado para
  re-visitar estados (podrías mejorar evitando estados repetidos).
