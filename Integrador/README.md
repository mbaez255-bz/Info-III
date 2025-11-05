# PRÁCTICO INTEGRADOR - Sistema de Gestión de Turnos Médicos

## Descripción
Sistema completo de gestión de turnos médicos que implementa 10 ejercicios con diferentes estructuras de datos y algoritmos avanzados.

## Estructuras de Datos Implementadas

### 1. Árbol AVL (Balanceado)
- **Uso**: Agenda por médico ordenada por fecha/hora
- **Operaciones**: O(log n) para insert, remove, siguiente
- **Justificación**: Mantiene turnos ordenados y permite búsqueda eficiente del próximo turno disponible

### 2. Hash Table con Chaining + Rehash
- **Uso**: Índice rápido de pacientes por DNI
- **Load Factor**: 0.75 (rehash automático)
- **Función Hash**: Método de división con chaining para colisiones
- **Operaciones**: O(1) promedio para put, get, remove
- **Justificación**: Búsqueda en tiempo constante de pacientes

### 3. Cola Circular (Queue)
- **Uso**: Sala de espera física con capacidad limitada
- **Operaciones**: O(1) para llega, atiende, peek, size
- **Overflow Control**: Las nuevas llegadas desbordan al paciente más antiguo
- **Justificación**: FIFO natural para gestión de espera

### 4. Min-Heap (Priority Queue)
- **Uso**: Planificador de quirófano (prioridad = tiempo libre del médico)
- **Operaciones**: O(log Q + log K) donde Q = quirófanos, K = top-K
- **Justificación**: Acceso rápido al recurso con menor carga

### 5. Stack (Pilas)
- **Uso**: Sistema de auditoría con Undo/Redo
- **Implementación**: Dos pilas (acciones y rehacer)
- **Operaciones**: O(1) para push/pop
- **Justificación**: LIFO natural para deshacer/rehacer acciones

## Decisiones de Diseño

### Hash Function para DNI (Strings)

#### Implementación
```java
private int hash(String clave) {
    int hash = 0;
    for (int i = 0; i < clave.length(); i++) {
        hash = (hash * 31 + clave.charAt(i)) % capacidad;
    }
    return Math.abs(hash);
}
```

#### Estrategia: Polynomial Rolling Hash con constante 31
- **Fórmula**: `hash = (hash * 31 + char) % capacidad`
- **Constante 31**: Número primo que minimiza colisiones
  - Es impar (evita perder información con shifts binarios)
  - Suficientemente grande para distribuir bien
  - Optimizable por compilador: `31 * i = (i << 5) - i`
  
#### Por qué es una buena distribución:
1. **Efecto avalancha**: Un pequeño cambio en el DNI produce hash muy diferente
   - "12345678" vs "12345679" → hashes muy distintos
2. **Uniformidad**: DNIs similares se distribuyen uniformemente en la tabla
3. **Evita clustering**: No concentra valores en pocas posiciones

#### Ejemplo de distribución:
```
DNI: "32045982"
hash = 0
  → (0*31 + '3') % 10 = 3
  → (3*31 + '2') % 10 = 5
  → (5*31 + '0') % 10 = 0
  → ... (continúa)
Resultado: índice bien distribuido
```

#### Comparación con alternativas:
| Estrategia | Distribución | Colisiones | Complejidad |
|------------|--------------|------------|-------------|
| Módulo simple (`dni % M`) | ⚠️ Regular | Alta | O(n) |
| Suma de dígitos | ❌ Mala | Muy alta | O(n) |
| **Polynomial (31)** | ✅ Excelente | Baja | O(n) |
| Cryptographic hash | ✅ Perfecta | Muy baja | O(n) pero costoso |

#### Manejo de colisiones:
- **Método**: Chaining (listas enlazadas)
- **Ventaja**: Simple, sin clustering secundario
- **Complejidad**: O(1 + α) donde α = load factor

#### Rehashing automático:
- **Trigger**: Cuando load factor > 0.75
- **Acción**: Duplica capacidad y redistribuye
- **Justificación**: Mantiene O(1) promedio al limitar longitud de cadenas

### Manejo de Fechas
- **Uso**: `LocalDateTime` de Java 8+
- **Comparación**: Por fecha/hora natural
- **Validación**: No permite fechas pasadas ni duración ≤ 0

### Balanceo AVL
- **Rotaciones**: Simple (LL, RR) y Doble (LR, RL)
- **Factor de balanceo**: Calculado en cada inserción/eliminación
- **Justificación**: Garantiza O(log n) en peor caso

### Complejidades Objetivo Cumplidas
1. ✅ Carga inicial: O(n log n) - parseo + inserción en estructuras
2. ✅ Agenda por médico: O(log n) insert/remove, O(log n) siguiente
3. ✅ Búsqueda hueco libre: O(log n + k) donde k = turnos saltados
4. ✅ Sala de espera: O(1) todas las operaciones
5. ✅ Recordatorios: O(log n) push/pop del más próximo
6. ✅ Hash pacientes: O(1) promedio put/get/remove
7. ✅ Merge agendas: O(|A|+|B|) con detección de conflictos
8. ✅ Ordenamientos: Insertion O(n²), Shellsort O(n^1.5), Quicksort O(n log n)
9. ✅ Undo/Redo: O(1) por operación
10. ✅ Planificador quirófano: O(log Q + log K) procesamiento

## Estructura del Proyecto

```
Integrador/
├── README.md (este archivo)
├── data/
│   ├── pacientes.csv
│   ├── medicos.csv
│   └── turnos.csv
├── src/
│   ├── Main.java (menú principal)
│   ├── models/
│   │   ├── Paciente.java
│   │   ├── Medico.java
│   │   ├── Turno.java
│   │   ├── Recordatorio.java
│   │   └── SolicitudCirugia.java
│   ├── structures/
│   │   ├── ArbolAVL.java
│   │   ├── HashMapPacientes.java
│   │   ├── ColaCircular.java
│   │   ├── MinHeap.java
│   │   └── Pila.java
│   ├── services/
│   │   ├── CargadorDatos.java
│   │   ├── AgendaMedico.java
│   │   ├── SalaEspera.java
│   │   ├── PlannerRecordatorios.java
│   │   ├── IndicePacientes.java
│   │   ├── ConsolidadorAgendas.java
│   │   ├── GeneradorReportes.java
│   │   ├── AuditoriaHistorial.java
│   │   └── PlanificadorQuirofano.java
│   └── utils/
│       ├── Validador.java
│       └── Ordenamientos.java
```

## Instrucciones de Ejecución

### Compilar
```bash
javac -d bin -sourcepath src src/Main.java
```

### Ejecutar
```bash
java -cp bin Main
```

## Archivos CSV de Ejemplo

### pacientes.csv
```csv
dni,nombre
32045982,Gómez Ana
32458910,Pérez Juan
31247856,López María
31890432,Torres Luis
32500890,Acosta Carla
```

### medicos.csv
```csv
matricula,nombre,especialidad
M001,Dr. Pérez,Cardiología
M002,Dr. Gómez,Traumatología
M003,Dra. Torres,Pediatría
```

### turnos.csv
```csv
id,dniPaciente,matriculaMedico,fechaHora,duracionMin,motivo
T-001,32045982,M001,2025-11-22T09:00,30,Control
T-015,32458910,M001,2025-11-22T09:30,45,ECG
T-022,31247856,M001,2025-11-22T10:00,30,Consulta general
```
