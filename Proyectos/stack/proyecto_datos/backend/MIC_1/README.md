## Microservicio 1 - Java

### Introducción

Este servicio de backend que he creado es inicialmente diseñado para practicar
un diseño de arquitectura basado en bloques modulares. La idea es dividir para
vencer para que el monolito sea escalable y pueda consumir servicios y ser
consumido por otros servicios. Este proyecto esta dividido en seis bloques:

### Bloque de datos

Este bloque tiene la unica responsabilidad (Single) de comunicarse con la base de
datos. Mi idea es dividir esto es dos bloques:

#### 1. Bloque de entidades.

Este bloque es el que maneja la estructura de las tablas y las relaciones entre las
tablas de nuestra base de datos y contiene toda la estructura de la base de datos
relacional. Este bloque es de abierta extensión (Open) en el sentido de cuanto
cambie la base de datos, pero cerrado a modificaciones (Closed) pues queremos
proteger la integridad de los datos

##### 2. Bloque de repositorios

Este bloque esta diseñado unicamente para manejar querys necesarios para comunicarse
con la base de datos. La idea es que si se cambia de base de datos este archivo
pueda modificarse sin afectar la integridad del proyecto completa. Ahorrandonos tener
que ir por archivos buscando funciones y querys repetidos.


### Bloque de transferencia de datos