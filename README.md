# Northwind_ACT1_Ev_Acceso a Datos

## Autora
Sara Vázquez Ávila  
2º DAM – Módulo: Acceso a Datos

## Descripción del proyecto
Este proyecto implementa un sistema de gestión de datos utilizando una base de datos relacional y acceso a una API externa. Su objetivo es importar información de productos desde un servicio web y almacenarla en una base de datos local, permitiendo posteriormente realizar consultas e inserciones adicionales.

## Objetivos
- Conectar a una base de datos local mediante JDBC.
- Crear tablas y gestionar datos mediante sentencias SQL.
- Importar datos desde un servicio web utilizando JSON.
- Utilizar el patrón Singleton para gestionar la conexión a la base de datos.
- Trabajar con Maven como gestor de dependencias.

## Base de datos utilizada
Se utiliza la base de datos H2 en modo local. La aplicación genera automáticamente las tablas necesarias al ejecutarse:

### Tablas creadas
| Tabla | Descripción |
|-------|-------------|
| `productos` | Productos importados desde el JSON |
| `productos_fav` | Productos cuyo precio supera los 1000€ |
| `empleados` | Empleados registrados manualmente |
| `pedidos` | Pedidos relacionados con productos y empleados |

## Importación de datos desde JSON
Los productos se importan desde el siguiente enlace:

https://dummyjson.com/products

Para ello, la aplicación utiliza:
- `HttpClient` para realizar la petición HTTP.
- `Gson` para convertir el JSON en objetos Java.

## Funcionalidades principales
El sistema permite:
- Crear automáticamente todas las tablas en la base de datos.
- Importar e insertar en la base de datos los productos obtenidos desde la API.
- Registrar empleados y pedidos mediante consultas SQL.
- Insertar productos favoritos con precio superior a 1000€.
- Mostrar por consola:
  - Todos los productos almacenados.
  - Solo los productos favoritos.
  - Productos con precio inferior a 600€.

## Ejecución del programa
El programa debe ejecutarse desde la clase: Main 

No es necesario realizar configuraciones adicionales. La base de datos se crea automáticamente al iniciar la aplicación.

## Tecnologías utilizadas
- Java
- H2 Database
- JDBC
- HttpClient
- Gson
- Maven

## Estructura del proyecto
Northwind_ACT1_Ev_AccesoDatos
│
├── src/main/java/northwind
│ ├── Main.java
│ ├── model/Product.java
│ ├── service/ProductService.java
│ ├── service/JSONImporter.java
│ ├── db/DBConnection.java
│ └── db/DBInit.java
│
├── pom.xml
└── almacen.mv.db

