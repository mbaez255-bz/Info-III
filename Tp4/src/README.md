
# Guía TP4 — Lista Enlazada (LinkedList)

Esta carpeta contiene una implementación didáctica de una lista enlazada simple (no concurrente) usada en el práctico 4.

Archivos principales

- `Guias/Tp4/src/main/LinkedListMenu.java` — demo/menú para interactuar con la lista (ejemplos y pruebas).
- `Guias/Tp4/src/main/LinkedListMenuGeneric.java` — versión genérica del menú (si aplica en el proyecto).
- `Guias/Tp4/src/utils/LinkedList.java` — implementación de la lista enlazada (operaciones básicas: insertar al frente/final, eliminar, buscar, longitud, imprimir, etc.).
- `Guias/Tp4/src/utils/ListNode.java` — definición del nodo (`ListNode<T>`) usado por `LinkedList`.

Qué incluye la implementación

- Inserción al inicio y al final.
- Borrado por valor (primer ocurrencia) y borrado por posición.
- Búsqueda de elementos, obtención de tamaño y verificación de si la lista está vacía.
- Impresión en consola de la lista en orden.
- Versión genérica para soportar distintos tipos (`LinkedList<T>`) y una versión no genérica si el práctico lo requiere.

Cómo compilar y ejecutar (PowerShell / Windows)

Compilar los fuentes de TP4 y ejecutar el menú principal:

```powershell
javac -d out "Guias/Tp4/src/utils/ListNode.java" "Guias/Tp4/src/utils/LinkedList.java" "Guias/Tp4/src/main/LinkedListMenu.java"
if ($LASTEXITCODE -eq 0) { java -cp out Tp4.main.LinkedListMenu }
```

Si querés ejecutar la versión genérica del menú (si existe):

```powershell
javac -d out "Guias/Tp4/src/utils/ListNode.java" "Guias/Tp4/src/utils/LinkedList.java" "Guias/Tp4/src/main/LinkedListMenuGeneric.java"
if ($LASTEXITCODE -eq 0) { java -cp out Tp4.main.LinkedListMenuGeneric }
```

Ejemplos de uso (qué verás en el menú)

- Crear una lista vacía y verificar `isEmpty()`.
- Insertar valores al frente y al final; imprimir la lista tras cada inserción.
- Buscar un valor y borrar la primera ocurrencia.
- Borrar por índice y consultar la longitud antes y después.

Notas y recomendaciones

- Los demos pueden ser convertidos a runners no interactivos si preferís ejemplos reproducibles para clase.
- Puedo añadir tests JUnit minimalistas para cubrir operaciones básicas (insertar, eliminar, buscar, invariantes de tamaño).
- Si la implementación actual usa tipos sin genéricos, puedo migrarla a una `LinkedList<T>` genérica para eliminar warnings.

Contacto

Si querés que ejecute los demos y pegue la salida aquí (para verificar comportamiento), indicamelo y lo hago.

