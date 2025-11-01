# Info-III — Guías y prácticos de Estructuras de Datos

Repositorio de ejercicios y prácticos usados en la materia de Estructuras / Algoritmos (organizado por trabajos prácticos y pequeñas guías de apoyo).

Contenido principal
- `Ordenamiento/` — pequeño proyecto de demostración de ordenamientos (ejecución Java de ejemplo).
- `Tp1/` .. `Tp6/` — carpetas para los trabajos prácticos 1 a 6. Cada TP contiene código fuente (carpeta `src` o `main`/`utils`) y un README con instrucciones específicas.

Foco de esta entrega
- TP5 (AVL): implementación didáctica de un Árbol AVL en `Guias/Tp5/utils/ArbolAVL.java` y demo no interactivo en `Guias/Tp5/main/Main.java`.
  - El demo es reproducible (no requiere entrada interactiva), llama al método `crearArbolAVL()` y muestra operaciones como impresión, búsqueda, eliminación y mensajes de rebalanceo indicando el tipo de rotación (LL, RR, LR, RL).

- TP6 (Red-Black Tree): implementación didáctica en `Guias/Tp6/utils/ArbolRN.java` y runner demo en `Guias/Tp6/main/Main.java`.
  - `Main` es un runner no interactivo que ejecuta en secuencia pasos numerados (1..11). Antes de cada paso imprime qué demo se va a usar.
  - Cambios destacados en `Tp6`:
    - Paso 1 crea un árbol de ejemplo que se reutiliza en la opción 2 (evita duplicados).
    - Paso 2 aplica `rotateLeft` mostrando el antes y después.
    - Paso 5 incluye dos ejemplos para `clasificar` (10,20,30 y 30,20,10) y muestra antes/después aplicando `fixInsert`.
    - Pasos 6 y 7 muestran el árbol antes y después de aplicar `fixInsert` (usamos `insertBST` + `fixInsert` para evidenciar las transformaciones).
    - Se eliminó el getter `getVal()` de `RBNode` porque no era usado por los demos.

Cómo compilar y ejecutar (PowerShell / Windows)

Compilar y ejecutar el demo de TP6:

```powershell
javac -d out "Guias/Tp6/utils/ArbolRN.java" "Guias/Tp6/main/Main.java"
if ($LASTEXITCODE -eq 0) { java -cp out Tp6.main.Main }
```

Compilar y ejecutar el demo de TP5 (AVL):

```powershell
javac -d out "Guias/Tp5/utils/ArbolAVL.java" "Guias/Tp5/main/Main.java"
if ($LASTEXITCODE -eq 0) { java -cp out Tp5.main.Main }
```

Notas y recomendaciones
- Los demos fueron convertidos a runners no interactivos para facilitar la reproducción de ejemplos en clase. Si preferís volver a una versión interactiva, puedo añadir una bandera `--interactive` o restaurar el menú.
- Algunas decisiones de implementación son didácticas (por ejemplo: uso de `insertBST` + `fixInsert` en demos para mostrar el before/after). Si querés, puedo:
  - eliminar advertencias de genéricos (re‑tipar ArbolAVL como `ArbolAVL<Integer>` y usar la API pública),
  - reducir la visibilidad de helpers que son internos (convertir `minimo`/`maximo` a `private`),
  - o añadir tests unitarios minimalistas para validar comportamiento.

Contribuir / próximos pasos
- Si querés que compile y ejecute los demos y pegue la salida aquí, confirmá y lo hago.
- Puedo también generar pequeños tests JUnit para Tp5/Tp6 para garantizar que las operaciones clave (insert, delete, rotaciones, invariantes) siguen funcionando tras cambios.

---

Archivo actualizado: resumen del estado actual de los demos (Tp5/Tp6) y comandos para ejecución rápida.
