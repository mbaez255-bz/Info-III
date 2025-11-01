# Guía TP6 — Árbol Rojo-Negro (RBT)

## Descripción (actualizada)
Esta guía contiene una implementación didáctica de un Árbol Rojo-Negro (Red-Black Tree) en Java.
El código está pensado para ser usado con demos reproducibles: `Guias/Tp6/main/Main.java` ahora es un
runner no interactivo que ejecuta, en secuencia, ejemplos hardcodeados que muestran las operaciones
clave del práctico y —cuando aplica— el estado "antes" y "después" del balanceo.

Cambios relevantes respecto a la versión interactiva original:
- `Main` dejó de ser interactivo; ahora corre una secuencia de demos (pasos 1..11) y el paso 12 fue eliminado.
- En el paso 1 `Main` crea un árbol de ejemplo que se reutiliza en la opción 2 (evita duplicar inserciones).
- El paso 5 ahora incluye dos ejemplos (10,20,30 y 30,20,10) y muestra el estado antes y después de aplicar
  `fixInsert` al nodo insertado.
- Los pasos 6 y 7 muestran también el árbol antes y después de aplicar el balanceo (se usa `insertBST` + `fixInsert`)
  para que se aprecie la transformación.
- Se eliminó el getter `getVal()` del `RBNode` porque no era utilizado por los demos.

Los archivos principales están en:

- `Guias/Tp6/utils/ArbolRN.java` — implementación del árbol rojinegro (clase `ArbolRN`).
- `Guias/Tp6/main/Main.java` — runner no interactivo con demos hardcodeados (pasos 1..11).

## Estructura de archivos (resumen)

- `ArbolRN` contiene:
  - `RBNode` (nodo con key,val,left,right,parent,color y getters públicos necesarios)
  - `NIL` sentinel negro y `root` inicializado a `NIL`
  - `rotateLeft`, `rotateRight`
  - `insertBST` (inserción como ABB, sin balance)
  - `clasificar` (devuelve TIO_ROJO, LL, RR, LR, RL)
  - `fixInsert` (recoloreo y rotaciones para restaurar propiedades RBT)
  - `minimo`, `maximo`, `successor`, `predecessor`
  - `rango(a,b)` (in-order acotado)
  - verificadores: `raizNegra()`, `sinRojoRojo()`, `alturaNegra()`
  - helpers de prueba: `getRoot()`, `buscarNodo()`, `insertarYArreglar()`

- `Main` (runner no interactivo): ejecuta una secuencia de demos preset y muestra información
  descriptiva antes de cada paso (qué demo se está usando). Los demos incluyen impresiones
  del árbol en vista lateral y, en los pasos relevantes, el estado antes y después del balanceo.

## Qué cubren los demos en `Main` (mapeo rápido)

1) Crear árbol / estado inicial — muestra NIL/root y crea el árbol de ejemplo que se usa en la opción 2.
2) Rotación izquierda — usa el árbol creado en el paso 1 y aplica `rotateLeft` mostrando antes/después.
3) Rotación derecha — demo independiente con `rotateRight`.
4) Insertar como BST (sin balance) — inserciones simples y visualización.
5) Clasificar caso para fixInsert — dos ejemplos (10,20,30 y 30,20,10) mostrando antes/después del `fixInsert`.
6) Recoloreo por tío rojo — inserciones que provocan recoloreos; se muestra antes/después usando `insertBST`+`fixInsert`.
7) Rotación simple/doble — muestra antes/después del caso LL (30,20,10) y fuerza un LR insertando 25 (before/after).
8) Successor / Predecessor — demo con 5,10,15.
9) Consulta por rango [3,7] — árbol 1..10.
10) Verificadores de invariantes — construye un árbol de ejemplo y muestra `raizNegra()`, `sinRojoRojo()` y `alturaNegra()`.
11) Mostrar árbol final (repetición/visualización).

> Nota: los demos están diseñados para ser pedagógicos y reproducibles; si querés volver a una versión interactiva
> puedo restaurarla o añadir una bandera CLI para escoger entre modo `demo` y `manual`.

## Cómo compilar y ejecutar (PowerShell / Windows)

Compilar los archivos modificados y ejecutar el demo:

```powershell
javac -d out "Guias/Tp6/utils/ArbolRN.java" "Guias/Tp6/main/Main.java";
if ($LASTEXITCODE -eq 0) { java -cp out Tp6.main.Main }
```

O, si preferís hacerlo en pasos separados:

```powershell
javac -d out "Guias/Tp6/utils/ArbolRN.java" "Guias/Tp6/main/Main.java"
java -cp out Tp6.main.Main
```

## Notas y recomendaciones

- Mantuvimos la mayoría de los comentarios explicativos dentro de `ArbolRN.java` para facilitar la lectura.
- Para evitar warnings de genéricos en demos se usaron casts o helpers específicos en algunos proyectos; si querés que
  elimine las advertencias puedo adaptar `Main` para usar `insertarYArreglar` o crear un `ArbolRN<Integer,String>`
  explícito en vez de realizar casts.
- Si preferís que los demos escriban su salida en archivos de log en lugar de la consola, lo agrego en un parche pequeño.

## Resumen de los cambios recientes

- `Guias/Tp6/main/Main.java`: convertido a runner no interactivo que ejecuta pasos 1..11 en secuencia; se añadieron
  mensajes "Demo a usar" antes de cada paso; paso 12 eliminado; paso 5 ampliado a dos ejemplos y pasos 6/7 muestran
  antes/después del balanceo.
- `Guias/Tp6/utils/ArbolRN.java`: se eliminó el getter innecesario `getVal()` y se mantuvieron los métodos públicos
  necesarios por los demos (`insertBST`, `fixInsert`, `insertarYArreglar`, `rotateLeft/Right`, `clasificar`, `rango`, etc.).

Si querés, compilo y ejecuto el demo ahora y pego la salida del run para que confirmes que las transformaciones
aparecen como esperás. ¿Lo corro?

