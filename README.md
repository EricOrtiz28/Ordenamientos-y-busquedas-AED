# Sort & Search App

## Descripción del Proyecto
Este proyecto es una aplicación Java desarrollada como parte del curso de Algoritmos y Estructuras de Datos. La aplicación proporciona una interfaz gráfica de usuario (GUI) para demostrar y visualizar diferentes algoritmos de ordenamiento y búsqueda, así como implementaciones personalizadas de diversas estructuras de datos.

## Características Principales
- **Algoritmos de Ordenamiento:** Implementación y visualización de algoritmos de ordenamiento tanto para arreglos de enteros como para objetos (por ejemplo, Heap Sort).
- **Algoritmos de Búsqueda:** Funciones para buscar elementos dentro de colecciones de datos.
- **Estructuras de Datos Personalizadas:**
  - *Listas Enlazadas (Simple, Doble y Circular).*
  - *Tipos de Datos Abstractos (ADT)* para listas.
  - Implementaciones específicas como `Directory` y un registro para manejo de objetos complejos (`Bicicleta`).
- **Números Grandes (Big Numbers):** Lógica para la manipulación y cálculo con números de gran longitud mediante listas enlazadas.
- **Interfaz Gráfica (GUI):** Formularios en Swing (e.g., `FrmMain`, `FrmIntSorter`, `FrmLinkedList`, `FrmHeapSortTree`) que permiten al usuario interactuar y probar las estructuras de datos y algoritmos en tiempo real.

## Arquitectura y Tecnologías
- **Lenguaje:** Java
- **Interfaz Gráfica:** Java Swing / AWT.
- **Gestión de Proyecto:** Maven (`pom.xml`).
- **Diseño del Código:** Separación en paquetes (Modelo, GUI, y lógica en `uni.aed.*` para cada tipo de estructura de datos o algoritmo).

## Estructura del Proyecto
```text
src/
├── main/
│   └── java/
│       ├── gui/              # Formularios y ventanas (FrmMain, FrmIntSorter, FrmLinkedList, etc.)
│       ├── model/            # Entidades y modelos de datos (Person.java)
│       └── uni/aed/          # Implementación de algoritmos y estructuras
│           ├── bicycle/          # Lista de bicicletas
│           ├── bignumbers/       # Manejo de BigInt
│           ├── circularLinkedList/ # Listas enlazadas circulares
│           ├── directory/        # Gestión de un directorio
│           ├── doublelinkedlist/ # Listas doblemente enlazadas
│           ├── linkedlist/       # Listas enlazadas simples
│           ├── linkedListADT/    # Interfaz e iteradores ADT
│           ├── search/           # Algoritmos de búsqueda
│           └── sorting/          # Algoritmos de ordenamiento
```

## Instalación y Ejecución

1. Asegúrate de tener JDK 11 o superior instalado.
2. Clona el proyecto en tu máquina local.
3. Puedes compilar y empaquetar el proyecto usando Maven:
   ```bash
   mvn clean install
   ```
4. Ejecuta la clase principal de la interfaz gráfica (`gui.FrmMain` o la clase definida como `mainClass` en el POM) desde tu IDE preferido (Eclipse, IntelliJ, NetBeans).
