# Guía TP5 — Árbol AVL (Java)

# Contenido principal (ubicación en este repositorio)
- `Guias/Tp5/main/Main.java` -> Runner demo no interactivo que ejecuta una secuencia fija de pasos para mostrar el comportamiento del `ArbolAVL<Integer>`.
- `Guias/Tp5/utils/ArbolAVL.java` -> Implementación del árbol AVL con utilidades didácticas (impresión lateral, recorrido in-order, verificación esAVL, eliminación y rebalanceo con mensajes).

# Descripción del demo (`Main`)
El `Main` actual ejecuta una demo hardcodeada (no interactiva) que realiza las siguientes acciones en orden:

1) Construye un árbol preparado para que una eliminación provoque rebalanceo (método `crearArbolAVL()`).
2) Muestra el estado actual del árbol (impresión lateral), muestra el recorrido in-order y comprueba `esAVL()`.
3) Realiza búsquedas de ejemplo (por ejemplo 25 y 99) para mostrar casos existente/no existente.
4) Elimina un candidato predefinido (50) — esto provoca el rebalanceo mostrado en consola — y luego elimina un segundo valor (25). Después de cada eliminación se imprime el árbol y el recorrido.
5) Muestra un resumen final del árbol: impresión lateral, altura y resultado de `esAVL()` (en el `Main` la sección del resumen está rotulada como "6)" por compatibilidad con pasos anteriores).

# Características principales de `ArbolAVL`
- La clase está en `Guias/Tp5/utils/ArbolAVL.java` y está parametrizada (`ArbolAVL<T extends Comparable<T>>`).
- `crearArbolAVL()` — método de utilidad usado por el demo para construir un árbol de ejemplo (no devuelve el valor a eliminar; el `Main` usa el literal `50` como candidato).
- `imprimirArbol()` — imprime el árbol en vista lateral. Cada nodo muestra su factor de equilibrio: `(fe=<n>)`.
- `recorridoEnOrden()` — devuelve una `List<T>` con los valores en orden creciente (in-order).
- `esAVL()` — verifica recursivamente que el árbol es un ABB válido y que en cada nodo |altura(izq)-altura(der)| ≤ 1. Imprime `true es avl` o `false no es avl` cuando se usa en el demo.
- `buscar(T dato)` — busca si un valor existe en el árbol (usado en las búsquedas del demo).
- `eliminar(T dato)` — elimina un elemento si existe; por diseño la implementación del demo evita eliminar la raíz (muestra un mensaje si se intenta). Si la eliminación modifica el árbol, se imprime el árbol resultante.

# Mensajes de rebalanceo
- Cuando durante la eliminación se detecta un desbalance y se aplica una rotación, `rebalancear(...)` imprime el tipo de rotación aplicada y el nodo afectado. Los mensajes que verás en consola son del tipo:
  - `Rebalanceo: Rotación derecha (LL) en nodo <valor>`
  - `Rebalanceo: Rotación izquierda (RR) en nodo <valor>`
  - `Rebalanceo: Rotación izquierda-derecha (LR) en nodo <valor>`
  - `Rebalanceo: Rotación derecha-izquierda (RL) en nodo <valor>`

# Notas y diferencias respecto a versiones anteriores
- El demo actual es no interactivo y usa `crearArbolAVL()` para preparar el caso de prueba.
- Se eliminó la llamada a las pruebas estáticas `pruebasEsAVL()` desde el flujo principal del demo.
- No hay un método público `insertar(...)` utilizado por el demo: el árbol de ejemplo se construye directamente por `crearArbolAVL()` para garantizar el caso didáctico de rebalanceo.

# Cómo ejecutar (localmente)
Compilar:
```powershell
javac -d out "Guias/Tp5/utils/ArbolAVL.java" "Guias/Tp5/main/Main.java"
```

Ejecutar demo:
```powershell
java -cp out Tp5.main.Main
```

Si querés que yo compile y ejecute el demo ahora y pegue la salida de la consola, decímelo y lo hago.


