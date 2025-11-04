# Guía TP5 — Árbol AVL (Java)

# Contenido principal (ubicación en este repositorio)
- `Guias/Tp5/src/main/Main.java` -> Runner demo no interactivo que ejecuta una secuencia fija de pasos para mostrar el comportamiento del `AVLTree<Integer>`.
- `Guias/Tp5/src/utils/ArbolAVL.java` -> Implementación del árbol AVL con utilidades didácticas (impresión lateral, in-order traversal, verificación `isAVL`, eliminación (`remove`) y rebalanceo con mensajes).

# Descripción del demo (`Main`)
El `Main` actual ejecuta una demo hardcodeada (no interactiva) que realiza las siguientes acciones en orden:

1) Construye un árbol preparado para que una eliminación provoque rebalanceo (método `createDemoTree()`).
2) Muestra el estado actual del árbol (impresión lateral), muestra el recorrido in-order y comprueba `isAVL()`.
3) Realiza búsquedas de ejemplo (por ejemplo 25 y 99) para mostrar casos existente/no existente.
4) Elimina un candidato predefinido (50) — esto provoca el rebalanceo mostrado en consola — y luego elimina un segundo valor (25). Después de cada eliminación se imprime el árbol y el recorrido.
5) Muestra un resumen final del árbol: impresión lateral, altura y resultado de `esAVL()` (en el `Main` la sección del resumen está rotulada como "6)" por compatibilidad con pasos anteriores).

## Características principales de `AVLTree`
- La clase está en `Guias/Tp5/src/utils/ArbolAVL.java` y está parametrizada (`AVLTree<T extends Comparable<T>>`).
- `createDemoTree()` — método de utilidad usado por el demo para construir un árbol de ejemplo (no devuelve el valor a eliminar; el `Main` usa el literal `50` como candidato).
- `printTree()` — imprime el árbol en vista lateral. Cada nodo muestra su balance factor: `(bf=<n>)`.
- `inOrderTraversal()` — devuelve una `List<T>` con los valores en orden creciente (in-order).
- `isAVL()` — verifica recursivamente que el árbol es un BST válido y que en cada nodo |height(left)-height(right)| ≤ 1.
- `contains(T value)` — busca si un valor existe en el árbol (usado en las búsquedas del demo).
- `remove(T value)` — elimina un elemento si existe; por diseño la implementación del demo evita eliminar la raíz (muestra un mensaje si se intenta). Si la eliminación modifica el árbol, se imprime el árbol resultante.

## Mensajes de rebalanceo
- Cuando durante la eliminación se detecta un desbalance y se aplica una rotación, `rebalance(...)` imprime el tipo de rotación aplicada y el nodo afectado. Los mensajes que verás en consola son del tipo (las líneas muestran el mensaje actual en inglés):
  - `Rebalance: Rotate right (LL) at node <value>`
  - `Rebalance: Rotate left (RR) at node <value>`
  - `Rebalance: Rotate left-right (LR) at node <value>`
  - `Rebalance: Rotate right-left (RL) at node <value>`

## PARA NOSOTROSSSSS

Perfecto — te explico de forma clara y práctica todo lo importante sobre árboles AVL y doy una guía función por función del código que tienes en `Guias/Tp5/src/utils/AVLTree.java` (y el `Main` que lo usa). Lo haré en español y con ejemplos paso a paso para que puedas "pensar" la lógica cuando implementes o depures.

Resumen rápido antes de profundizar
- Un AVL es un árbol binario de búsqueda (BST) autocontrolado por altura que mantiene la propiedad: en cada nodo |altura(izq) - altura(der)| ≤ 1.  
- Para restaurar esa propiedad se usan rotaciones (izquierda/derecha y combinadas LR/RL).  
- En el código las operaciones públicas principales son: construir demo (`createDemoTree`), imprimir (`printTree`), recorrido in-order (`inOrderTraversal`), búsqueda (`contains`), eliminación (`remove`) y chequeo `isAVL`.  
- Internamente hay helpers para altura, balance, rotaciones y eliminación recursiva.  

1) Conceptos clave (qué es y por qué importa)
- BST (Binary Search Tree): cada nodo tiene clave; todas las claves a la izquierda son menores y a la derecha mayores. Esto permite búsquedas/insert/eliminaciones en O(h) donde h = altura.
- Problema: un BST puede degenerar a lista (altura O(n)), perdiendo rendimiento.
- AVL: garantiza que la altura sea O(log n) manteniendo en cada nodo el factor de balance (height(left) - height(right)) en {-1,0,1}.  
- Altura y factor de balance:
  - altura(n) = 1 + max(altura(izq), altura(der))
  - factor = altura(izq) - altura(der). Si factor ∉ {-1,0,1} hay que rebalancear.
- Por qué las rotaciones resuelven el desequilibrio:
  - Rotaciones restauran la relación BST y balance local, afectando altura de subárboles de forma controlada. Pueden ser simples (LL o RR) o dobles (LR o RL).

2) Tipos de rotaciones (visual y cuándo usar)
- Caso LL (left-left): un desbalance se produce porque el hijo izquierdo tiene más altura y su hijo izquierdo es el "que causa". Solución: rotación derecha sobre el nodo.
  - Notación: rotarDerecha(nodo)
- Caso RR (right-right): simétrico; solución: rotación izquierda sobre el nodo.
  - rotarIzquierda(nodo)
- Caso LR (left-right): el hijo izquierdo está pesado a la derecha. Solución: rotación izquierda del hijo izquierdo, luego rotación derecha del nodo.
- Caso RL (right-left): simétrico del LR.

3) Complejidad
- Búsqueda/insert/eliminar: O(log n) amortizado (porque altura es O(log n)).
- Rotaciones: O(1) cada una.
- En el código actual, `remove` hace O(log n) en promedio y en peor caso por reequilibrio sigue O(log n).

4) Cómo "pensar" lógicamente al implementar o depurar
- Mantén invariante BST: cambios estructurales (rotaciones) nunca cambian el orden relativo.
- Siempre actualizar alturas de los nodos que cambian: se calcula como 1 + max(altura_hijo_izq, altura_hijo_der).
- Después de cualquier operación recursiva (insert/remove) en un subárbol, subir por la recursión y:
  1. actualizar altura del nodo actual,
  2. calcular su factor de balance,
  3. si fuera necesario, aplicar la rotación adecuada.
- Para decidir la rotación:
  - Si factor > 1 → lado izquierdo más alto → mirar balance del hijo izquierdo:
    - si balance(hijoIzq) ≥ 0 → LL (rotación derecha)
    - si balance(hijoIzq) < 0 → LR (rotación izquierda en hijoIzq, luego derecha)
  - Si factor < -1 → lado derecho más alto → mirar balance del hijo derecho:
    - si balance(hijoDer) ≤ 0 → RR (rotación izquierda)
    - si balance(hijoDer) > 0 → RL (rotación derecha en hijoDer, luego izquierda)

5) Función por función — qué hace y la lógica detrás (archivo: Guias/Tp5/src/utils/AVLTree.java)

Funciones públicas (API)
- `createDemoTree()`
  - Qué hace: construye manualmente un árbol de ejemplo con nodos {30,10,40,5,20,25,50} preparado para demostrar rebalanceos.
  - Por qué: es útil para pruebas y demos (no es un insert genérico).
  - Lógica: crea nodos y actualiza las alturas de los nodos desde las hojas hacia la raíz llamando a `updateHeight` en orden apropiado.

- `printTree()`
  - Qué hace: imprime el árbol en vista lateral (representación con prefijos tipo árbol).
  - Lógica: delega en `printTreeRec` que imprime recursivamente con prefijos para mostrar la jerarquía.

- `inOrderTraversal()`
  - Qué hace: devuelve una `List<T>` con las claves en orden ascendente.
  - Lógica: recorre en-order (izq, nodo, der) usando `inOrderRec`. Útil para verificar que la propiedad BST se mantiene.

- `isAVL()`
  - Qué hace: devuelve true si el árbol cumple la propiedad AVL (es BST y todos los subárboles balanceados).
  - Lógica: usa `isAVLRec(node, min, max)` que verifica a la vez la propiedad de BST (rangos min/max) y las alturas relativas, devolviendo un `Result` con `isAVL` y `height`. Esta verificación es útil para tests y depuración.

- `contains(T value)`
  - Qué hace: devuelve si un valor existe en el árbol.
  - Lógica: hace búsqueda binaria recursiva `searchRec`.

- `remove(T value)`
  - Qué hace: elimina `value` si existe y devuelve true si realizó un cambio; imprime mensajes y muestra el árbol después si cambió.
  - Lógica general:
    1. Si intentas eliminar la raíz mediante un camino restringido en demo (según el código del demo) se evita y se imprime mensaje.
    2. Guarda `before = inOrderTraversal()`, llama a `removeRec` recursivo para obtener la nueva raíz, calcula `after` y compara listas. Si iguales → no se eliminó.
    3. Si hubo cambio, imprime árbol resultante y devuelve true.
  - Por qué hace la comparación before/after: es una forma fácil de detectar si `removeRec` encontró y eliminó el nodo en este demo (aunque normalmente `removeRec` podría devolver una bandera booleana).

- `testIsAVL()` (pruebas)
  - Qué hace: ejecuta dos pruebas mínimas que construyen árboles y muestran `isAVL()` — útil para chequeos rápidos.

- `getHeight()`
  - Qué hace: devuelve la altura del árbol (altura de la raíz).
  - Lógica: delega en `nodeHeight(root)`.

Helpers privados (soporte interno)
- `searchRec(Node node, T value)`
  - Búsqueda recursiva normal de BST.
  - Lógica: comparar, ir a la izquierda si menor, a la derecha si mayor.

- `inOrderRec(Node node, List<T> result)`
  - Recorrido in-order recursivo que acumula en la lista.

- `nodeHeight(Node node)`
  - Devuelve 0 si node == null, o `node.height` (campo mantenido).
  - Lógica: convención de altura en este código asigna 1 a un nodo hoja.

- `updateHeight(Node node)`
  - Recalcula `node.height = 1 + max(nodeHeight(left), nodeHeight(right))`.
  - Uso: siempre llamar después de modificar subárboles o después de rotación.

- `getBalance(Node node)`
  - Devuelve `nodeHeight(left) - nodeHeight(right)`.
  - Lógica: sirve para decidir la necesidad y tipo de rotación.

- `rebalance(Node node)`
  - Núcleo de la restauración del balance tras cambios:
    1. calcula `balance = getBalance(node)`.
    2. según el signo y magnitud decide LL/RR/LR/RL y aplica la(s) rotaciones adecuadas.
    3. imprime mensajes de rebalanceo (en tu versión: mensajes en español).
  - Importante: `rebalance` no re-calcula alturas antes de decidir; en el flujo correcto se llama tras `updateHeight` y después de aplicar rotaciones las alturas se actualizan dentro de las funciones de rotación.

- `rotateLeft(Node y)` y `rotateRight(Node y)`
  - Implementan las rotaciones:
    - `rotateLeft(y)`: x = y.right; y.right = x.left; x.left = y; luego actualizar alturas de y y x; devuelve nuevo root x.
    - `rotateRight(y)`: simétrico.
  - Lógica: operación local O(1); preserva orden in-order.

- `removeRec(Node node, T value)`
  - Lógica detallada (es la parte crítica):
    1. Si node == null → devolver null (no encontrado).
    2. Comparar value con node.value:
       - Si < 0 → node.left = removeRec(node.left, value)
       - Si > 0 → node.right = removeRec(node.right, value)
       - Si == 0 → encontramos el nodo a eliminar:
         a. Si tiene 0 hijos → retornar null (se elimina).
         b. Si tiene 1 hijo → sustituir el nodo por su hijo y retornar ese hijo.
         c. Si tiene 2 hijos → buscar el sucesor inorder (mínimo en subárbol derecho), copiar su valor en el nodo, y luego eliminar recursivamente dicho sucesor en el subárbol derecho.
    3. Después de haber modificado `node.left` o `node.right`:
       - llamar `updateHeight(node)`
       - llamar `rebalance(node)` y devolver el resultado
    - Es crucial: la actualización de altura debe hacerse antes de rebalancear, porque las decisiones de rotación dependen de alturas actualizadas.

- `minValueNode(Node node)`
  - Devuelve el nodo con valor mínimo (navegar todo a la izquierda).
  - Usado en el caso de 2 hijos para encontrar el sucesor inorder.

- `printTreeRec(Node node, String prefix, boolean isTail)`
  - Imprime recursivamente con prefijos para ver estructura tipo árbol lateral; incluye el balance factor `(bf=...)` en cada nodo, lo que ayuda a entender qué nodos están desequilibrados.

Estructuras internas
- `Node<T>`: campos `value`, `left`, `right`, `height`.
  - `height` mantenido explícitamente para evitar recálculo costoso.
- `Result`: helper de `isAVLRec` con `isAVL` y `height`.

6) Ejemplo práctico (el demo del Main)
Árbol inicial (descrito en `createDemoTree`):
- Estructura (esquema):
      30
     /  \
   10    40
  /  \     \
 5   20     50
      \
      25

Alturas (aprox):
- hojas 5,25,50 => altura 1
- nodo 20 => altura 2 (por hijo derecho 25)
- nodo 10 => altura 3 (por subárbol izquierdo 5 altura1 y derecho 20 altura2 → max=2 → altura=3)
- nodo 40 => altura 2 (derecho 50)
- raíz 30 => altura 4 (max(3,2)+1)

Operación demo: remover 50
- 50 es hoja (caso simple). `removeRec` lo quita y retorna.
- Luego, `updateHeight` se llama en su ancestro (40) → ahora 40 queda con altura 1.
- `rebalance` comprueba balance de 40 y 30; probablemente no hay desequilibrio significativo (factor en 30 pasa a 3 - 1 = 2? hay que mirar los números exactos, pero dado la estructura, suele no causar rotación).
- Resultado: árbol equilibrado o con pequeños ajustes; `printTree` lo muestra.

Operación demo: remover 25
- 25 está en subárbol derecho de 20; al eliminarlo el nodo 20 pierde su hijo derecho → su altura decrece de 2 a 1.
- Esto puede causar que 10 tenga factor de balance > 1 (porque su subárbol izquierdo tiene altura 1 y su subárbol derecho ahora 1 o 0 según el resultado) — si se desbalancea (factor 2), se evalúa tipo de rotación:
  - mirar `getBalance(10.left)` y `getBalance(10.right)` determinará si es LL o LR.
- Si se detecta LR o LL, se aplicará la rotación correspondiente en `rebalance` y se actualizarán alturas localmente. Después de eso la raíz puede o no cambiar si la rotación ocurre en un nodo alto.
- El `printTree` y `inOrderTraversal` muestran el resultado.


## cuando borro 50
Actualización de alturas / bf hacia arriba

40 ahora tiene bf = 0 (hoja).

En 30: altura subárbol izquierdo = 3 (por 10), altura subárbol derecho = 1 (por 40) →
bf(30) = 3 - 1 = 2 → desbalanceado (|bf| > 1).

3) ¿Qué tipo de desbalance es? (LR, LL, RL, RR)

Nodo desbalanceado: 30 con bf = +2 → caso Left (subárbol izquierdo más alto).

Miramos 10 (hijo izquierdo de 30): bf(10) = -1 (su hijo derecho es más alto).

bf(30)=+2 y bf(10)=-1 → caso Left-Right (LR).

4) Rotaciones necesarias (LR)

Para LR se hacen dos rotaciones:

Rotación izquierda sobre 10 (su hijo derecho 20 sube).

Rotación derecha sobre 30.