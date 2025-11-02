## TP7 — Montículos / Heaps (Práctica)

Este directorio contiene la implementación didáctica de montículos (heaps) para la guía TP7.

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

Compilar y ejecutar (Windows PowerShell)

Desde la carpeta `Guias/Tp7` se puede compilar y ejecutar con los comandos siguientes (usa `src` como source-root):

```powershell
cd "c:\Users\Flor\Documents\IUA\2 Año\2 Semestre\InfoIII\Guias\Tp7"
javac -encoding UTF-8 -d out src\main\Main.java src\utils\*.java src\utils\integrador\*.java
java -cp out main.Main
```

Notas importantes
- Codificación: algunos comentarios tenían caracteres no-ASCII. Compilamos usando `-encoding UTF-8` para evitar errores de "unmappable character" en Windows. Evita caracteres especiales en comentarios o configura tu IDE para UTF-8.
- Warnings: `MinHeapGeneric.java` usa internamente `Object[]` y casteos, lo que provoca warnings de "unchecked or unsafe operations". Es funcional, pero si querés puedo refactorizar para reducir/eliminar estos warnings (por ejemplo usando `ArrayList<T>` o una fábrica de arrays genéricos).
- IDE / "rojo": si tu editor sigue marcando paquetes en rojo, probablemente la raíz de fuentes (source root) no está configurada como `Guias/Tp7/src`. Dos soluciones:
  1. Configurar `src` como source root en tu IDE (recomendado).
  2. O bien mover las fuentes dentro de `src/tp7/...` y usar paquetes `tp7.main` / `tp7.utils` (opción válida pero implica renombrar paquetes a minúsculas según convención Java).

Ejemplos de uso
- El `Main` es interactivo y muestra un menú en español con demos (MinHeap, MaxHeap, Heapify, Heapsort, cola de prioridad, agenda de tareas). Tras compilar, ejecutá `java -cp out main.Main` y elegí la opción deseada del menú.

Próximos pasos sugeridos
- (Opcional) Refactorizar `MinHeapGeneric` para eliminar warnings.
- (Opcional) Renombrar paquetes a `tp7.*` si preferís seguir la convención de paquetes con prefijo de proyecto.

Contacto
- Si querés que aplique alguna de las opciones anteriores (mover/renombrar paquetes, refactorizar genéricos, añadir tests), decime cuál y lo hago.
