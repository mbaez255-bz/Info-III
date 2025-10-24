# Guía TP6 — Árbol Rojo-Negro (RBT)

## Descripción
Este TP implementa una versión didáctica de un Árbol Rojo-Negro (Red-Black Tree) en Java.
El objetivo es cubrir las tareas clásicas: sentinel NIL, rotaciones, inserción BST, clasificación
de casos para la reparación (fixInsert), recoloreos y rotaciones simples/dobles, successor/
predecessor, consultas por rango e invariantes (raíz negra, sin rojo-rojo, black-height).

Los archivos principales creados en esta guía están en:

- `Guias/Tp6/utils/ArbolRN.java` — implementación del árbol rojinegro (clase `ArbolRN`).
- `Guias/Tp6/main/Main.java` — menú interactivo para probar las funciones del TP.

## Estructura de archivos (resumen)

- `ArbolRN` contiene:
  - `RBNode` (nodo con key,val,left,right,parent,color y getters)
  - `NIL` sentinel negro y `root` inicializado a `NIL`
  - `rotateLeft`, `rotateRight`
  - `insertBST` (inserción como ABB, sin balance)
  - `clasificar` (devuelve TIO_ROJO, LL, RR, LR, RL)
  - `fixInsert` (recoloreo y rotaciones para restaurar propiedades RBT)
  - `minimo`, `maximo`, `successor`, `predecessor`
  - `rango(a,b)` (in-order acotado)
  - verificadores: `raizNegra()`, `sinRojoRojo()`, `alturaNegra()`

- `Main` (menú) ofrece demos preset y modo manual (introducir listas de enteros) para
  probar cada una de las funcionalidades.

## Mapeo menú → ejercicios del práctico

Las opciones del menú están alineadas con los enunciados del práctico:

1) Estado inicial / construir árbol (modo demo/manual) — ejercicio 1 (NIL y root)
2) Rotación izquierda (demo/manual) — ejercicio 2
3) Rotación derecha (demo/manual) — ejercicio 3
4) Insertar como BST (sin balance) — ejercicio 4
5) Clasificar caso para fixInsert — ejercicio 5
6) Recoloreo por tío rojo (insertarYArreglar demo/manual) — ejercicio 6
7) Rotación simple/doble (insert+fix demo/manual) — ejercicio 7
8) Successor / Predecessor (demo/manual) — ejercicio 8
9) Consulta por rango [a,b] (demo/manual) — ejercicio 9
10) Verificadores de invariantes (raíz negra, sin rojo-rojo, altura negra) — ejercicio 10
11) Mostrar árbol (vista lateral)

El menú es interactivo. Para las opciones que tienen demo/manual el programa preguntará
`Usar demo preset? (s/n)` — si respondés `s` correrá un ejemplo preconfigurado; si respondés
`n` te pedirá una lista de enteros separados por comas para construir el árbol (y, cuando aplique, pedirá claves o rangos adicionales según la opción).


## PARA QUE ENTIENDAS LOVERITAAAAAA DESPUES SACALOOOOOOOO
Resumen (función → qué hace → ejercicio que resuelve)

- Clase RBNode (constructor, campos, getters)
  - Qué hace: representa un nodo del RBT con key, val, punteros left/right/parent y color (rojo/negro). El constructor inicializa key/val/color y los punteros; agregué getters públicos `getKey()` y `getVal()`.
  - Ejercicio: 1) Nodo y NIL sentinel (define la estructura de nodo).

- Campo `NIL` y `root` (en el constructor de `ArbolRN`)
  - Qué hace: crea un sentinel `NIL` único (negro) y hace que `root = NIL`. También apunta `NIL.left/right/parent = NIL`.
  - Ejercicio: 1) Nodo y NIL sentinel (establece el NIL compartido y root inicial).

- imprimirArbol() / imprimirRec(...)
  - Qué hace: imprime el árbol en vista lateral (sideways) con cada nodo mostrando la clave y su color (R/B), para depuración y demostraciones.
  - Ejercicio: soporte visual para todo el práctico; no es un ejercicio numerado concreto, pero ayuda al 2,3,4,6,7,10 (ver cambios antes/después).

- rotateLeft(RBNode x)
  - Qué hace: realiza la rotación izquierda en el subárbol con raíz `x`, actualizando hijos y punteros parent, y actualiza `root` si corresponde.
  - Ejercicio: 2) Rotación izquierda (implementación y prueba en árbol pequeño).

- rotateRight(RBNode y)
  - Qué hace: rotación derecha simétrica a rotateLeft; actualiza punteros y `root` si hace falta.
  - Ejercicio: 3) Rotación derecha.

- insertBST(K key, V val)
  - Qué hace: inserta la clave como en un BST puro, creando un nodo nuevo de color rojo cuyo left/right/parent apuntan a `NIL`. No hace recoloreos ni rotaciones.
  - Ejercicio: 4) Inserción como ABB (sin balance).

- enum Caso { TIO_ROJO, LL, RR, LR, RL } y clasificar(RBNode z)
  - Qué hace: determina el caso de la configuración local después de insertar `z`, mirando su padre `p`, abuelo `g` y tío. Devuelve TIO_ROJO si el tío es rojo; de lo contrario devuelve LL/LR/RR/RL según la posición de `z` respecto de `p` y `g`.
  - Ejercicio: 5) Clasificador de caso para fixInsert.

- fixInsert(RBNode z)
  - Qué hace: algoritmo que, partiendo del nodo insertado `z` (suponiendo que fue creado rojo), corrige el árbol aplicando:
    - caso TIO_ROJO: recoloreo (padre y tío negros, abuelo rojo) y subir `z = g`,
    - LL/RR: rotación simple sobre el abuelo y recoloreo,
    - LR/RL: rotaciones dobles (primero en el padre, luego en el abuelo) y recoloreo.
    - Al final asegura `root` negro.
  - Ejercicio: 6) Recoloreo por tío rojo, y 7) Rotación simple vs doble (cubre ambas ramas con recoloreos/rotaciones).

- minimo(RBNode x) / maximo(RBNode x)
  - Qué hace: devuelve el nodo mínimo (más a la izquierda) o máximo (más a la derecha) en el subárbol con raíz `x`.
  - Ejercicio: utilidad para 8) successor y predecessor (sirven en la implementación de successor/predecessor).

- successor(RBNode x) / predecessor(RBNode x)
  - Qué hace: devuelve el sucesor (menor > x.key) o el predecesor (mayor < x.key) usando las reglas estándar del BST (si hay subárbol derecho/izquierdo usar mínimo/máximo, sino subir por parent).
  - Ejercicio: 8) successor y predecessor.

- rango(K a, K b) / rangoRec(...)
  - Qué hace: recorre en-order pero acotado para devolver la lista de claves en [a,b] en orden, evitando explorar subárboles fuera del rango.
  - Ejercicio: 9) Consulta por rango [a,b].

- raizNegra()
  - Qué hace: chequea que la raíz sea negra (o árbol vacío), devuelve true si root == NIL o root.rojo == false.
  - Ejercicio: 10) Verificadores de invariantes (raizNegra).

- sinRojoRojo() / sinRojoRojoRec(...)
  - Qué hace: verifica recursivamente que no exista un nodo rojo cuyo hijo izquierdo o derecho sea rojo (no hay dos rojos consecutivos).
  - Ejercicio: 10) Verificadores de invariantes (sinRojoRojo).

- alturaNegra() / alturaNegraRec(...)
  - Qué hace: calcula la black-height (número de nodos negros desde nodo hasta NIL) de forma recursiva; si detecta caminos con diferente black-height devuelve -1 para indicar violación.
  - Ejercicio: 10) Verificadores de invariantes (alturaNegra).

- getRoot() y buscarNodo(K key)
  - Qué hace: `getRoot()` devuelve la raíz (útil para demos). `buscarNodo` busca un nodo por clave y lo devuelve (o null si no existe).
  - Ejercicio: utilitarios usados por varios ejercicios (2,3,5,8) en los demos de `Main`.

- insertarYArreglar(K key, V val)
  - Qué hace: helper que combina `insertBST` + `fixInsert` para insertar y asegurar propiedades RBT inmediatamente (con recoloreo/rotaciones).
  - Ejercicio: integra 4 + 6 + 7 (inserta como BST y aplica fixInsert para que el árbol quede correcto), y sirve para pruebas en Main.

