# Proyecto Gestor de Tareas (Tp1)

Este proyecto es una aplicación de consola en Java para la gestión de tareas pendientes, desarrollada como trabajo práctico para la materia Informática III.

## Objetivo
Permite al usuario agregar, listar, marcar como completadas, eliminar y guardar tareas en un archivo de texto. El objetivo es practicar la programación orientada a objetos, manejo de archivos y estructuras de datos en Java.

## Estructura del proyecto

-- `src/main/Main.java`: Clase principal que contiene el menú y la lógica de interacción con el usuario.
- `src/utils/GestorTareas.java`: Clase que gestiona la lista de tareas y todas las operaciones sobre ellas (agregar, listar, marcar, eliminar, guardar y cargar).
- `src/utils/Tarea.java`: Clase que representa una tarea individual, con descripción y estado (pendiente o completada).

## Funcionamiento
Al iniciar, el programa carga las tareas guardadas en el archivo `tareas.txt` (si existe). El usuario puede:

1. Agregar una nueva tarea.
2. Listar todas las tareas y su estado.
3. Marcar una tarea como completada.
4. Eliminar todas las tareas completadas.
5. Guardar los cambios y salir.

Las tareas se guardan en un archivo de texto para mantener la información entre ejecuciones.

