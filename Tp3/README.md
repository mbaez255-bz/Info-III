# Proyecto Pizzería (Tp3)

## Descripción
Este proyecto es una aplicación de consola en Java que simula la gestión de pedidos en una pizzería. Permite agregar, eliminar, listar y ordenar pedidos según diferentes criterios, aplicando algoritmos de ordenamiento clásicos.

## Objetivo
El objetivo es practicar la programación orientada a objetos, el uso de colecciones, el manejo de entrada por consola y la implementación de algoritmos de ordenamiento (inserción, shellsort y quicksort) en un contexto real.

## Estructura del proyecto
```
Tp3/
 ├── app/
 │    └── App.java           # Clase principal con el menú interactivo
 └── utils/
			├── Ordenador.java         # Algoritmos de ordenamiento
			├── Pedido.java            # Clase que representa un pedido
			├── Pizzeria.java          # Gestión de la lista de pedidos
			└── TiempoOrdenamiento.java# Medición de tiempo de ordenamiento
```

## Funcionalidades principales
- Agregar un pedido (nombre de cliente, tiempo de preparación, precio)
- Eliminar un pedido por número
- Listar todos los pedidos
- Ordenar pedidos por:
	- Tiempo de preparación (inserción)
	- Precio total (shellsort)
	- Nombre de cliente (quicksort)
- Medir el tiempo de ejecución de cada algoritmo de ordenamiento

## Funcionamiento
Al ejecutar el programa, se muestra un menú interactivo. El usuario puede elegir entre las distintas opciones para gestionar los pedidos. Los algoritmos de ordenamiento se aplican sobre la lista de pedidos y el tiempo de ejecución se muestra en milisegundos.

