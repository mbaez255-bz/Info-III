## TP7 — Montículos / Heaps (Práctica)
Un montículo binario (o binary heap) es una estructura de datos en forma de árbol binario completo que sirve para ordenar y acceder rápidamente al mínimo o máximo elemento.

🔹 Puede ser:
Montículo mínimo (min-heap): el valor más chico está en la raíz.El valor de cada nodo es menor o igual que el de sus hijos.
Montículo máximo (max-heap): el valor más grande está en la raíz.
El valor de cada nodo es mayor o igual que sus hijos. 

🔹 Se guarda normalmente en un array, donde:
Hijo izquierdo = 2*i + 1
Hijo derecho = 2*i + 2
Padre = (i - 1)/2

Contenido principal
- `src/main/Main.java` — Programa demo interactivo con un menú en español que ejecuta varias demos.
- `src/utils/MinHeap.java` — Implementación de min-heap para enteros (array-backed) con métodos: add, poll, peek, heapsort, printArray, printTree.
- `src/utils/MaxHeap.java` — Implementación de max-heap para enteros.
- `src/utils/integrador/MinHeapGeneric.java` — Montículo mínimo genérico que usa `Comparator<T>` (implementación basada en `Object[]`).
- `src/utils/integrador/Patient.java`, `Task.java`, `TaskAgenda.java` — clases auxiliares usadas en los demos (cola de prioridad y agenda).

Estructura de paquetes
- Los ficheros fuente están organizados bajo `src` y usan los siguientes paquetes Java (coinciden con la estructura de carpetas):
  - `main` → `src/main`
  - `utils` → `src/utils`
  - `utils.integrador` → `src/utils/integrador`


Ejemplos paso a paso (para comprensión)
Insert (add) en MinHeap con array [20, 5, 15, 3, 11] (proceso general):
Insert 20 → [20]
Insert 5 → append → [20,5] → percolateUp: 5 < 20 → swap → [5,20]
Insert 15 → append → [5,20,15] → 15 ≥ 5 → stop
Insert 3 → append → [5,20,15,3] → 3 < parent(20) → swap → [5,3,15,20] → ahora 3 < parent(5)? 3 < 5 → swap → [3,5,15,20]
Insert 11 → append → [3,5,15,20,11] → 11 < parent(5)? no → stop.
Poll (extract min) de [3,5,15,20,11]:
Guardar 3.
Mover último (11) a root → [11,5,15,20]
percolateDown desde 11: comparar hijos 5 y 15 → menor 5 → 11 > 5 → swap → [5,11,15,20]
luego 11 tiene child 20 (mayor) → stop.
Resultado: devuelve 3, heap ahora [5,11,15,20].
Heapify (array -> heap) con arr = {20,5,15,3,11}
Start from last parent index = floor(n/2)-1 = 1 (element 5 at index 1)
siftDown index 1, then 0, etc. Resultado lineal O(n) tiempo.
Complejidad resumen
add: O(log n)
poll: O(log n)
peek: O(1)
heapify: O(n)
heapsort: O(n log n)
showAll (TaskAgenda): O(n log n) (poll + reinserción)

Cuando eliminás el mínimo (la raíz):
Sacás la raíz (el menor elemento).
Movés el último elemento al índice 0 (raíz nueva).
Lo comparás con sus dos hijos (izquierdo y derecho).
Si alguno de los hijos es menor, intercambiás con el más pequeño de los dos.
Repetís hasta que el elemento esté en posición correcta.
