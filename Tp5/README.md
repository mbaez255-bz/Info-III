# Guía TP5 — Árbol AVL (Java)

# Contenido principal
- `src/main/Main.java` -> Menú interactivo que usa `ArbolAVL<Integer>` (insertar, buscar, eliminar, comprobar esAVL).
- `src/utils/ArbolAVL.java` -> Implementación del árbol AVL con impresiones didácticas (factor de equilibrio, rotaciones) y utilidades.
	(En esta copia del repositorio los archivos están en `Guias/Tp5/main/Main.java` y `Guias/Tp5/utils/ArbolAVL.java`).

# Caracts importantes
- Inserción (`insertar`): el programa imprime el árbol y el recorrido in-order antes y después de la inserción. Si se detecta un duplicado, solo muestra el mensaje
	`No se permiten duplicados: <valor>` y no modifica el árbol.
- Eliminación (`eliminar`): por diseño no se permite eliminar la raíz. Si el usuario intenta hacerlo aparece:
	`Opción no válida: no se puede eliminar la raíz`.
	Si la clave no existe se muestra `No se encontró: <valor>`. Si la eliminación tuvo efecto se imprime el árbol actualizado.
- EsAVL (`esAVL`): opción que verifica en una sola pasada recursiva que el árbol respete la propiedad de ABB y que todos los nodos cumplen el balance AVL (|altura(izq)-altura(der)| ≤ 1).
	- En el menú la comprobación imprime exactamente `true es avl` o `false no es avl`.
- Impresión: el árbol se muestra en vista lateral (sideways) con cada nodo seguido por `(fe=<n>)` donde `fe` es el factor de equilibrio.
- Mensajes de rebalanceo: durante inserciones (modo pedagógico) el árbol reporta el FE del nodo y la acción (LL, RR, LR, RL) antes de aplicar la rotación.


