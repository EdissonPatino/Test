# Aplicacion de prueba

El presente proyecto esta realizado con el JDK 17 por lo que para poder ejecutar se debe dee realizar en una máquina que disponga de un JDK de la misma versión.

La aplicación incluye una base de datos embebida H2 y la carga inicial de información se realiza desde el archivo resources/data.sql

La aplicación se ejecuta por defecto en el puerto 8080 y tiene los siguientes endpoints:

### Creación de tareas

URL: http://localhost:8080/tarea/crear

TIPO: POST 

Este metodo permite crear nuevas tareas, para lo cual se debe de mandar un objeto de tipo JSON en el cuerpo de la petición, a continuaión se muestra un ejemplo:
```
{
	"nombre": "Juan",
	"apellido": "Perez",
	"telefono": "523223252",
	"email": "jperez@loqsea.com",
	"curp": "fsdgf2343",
	"rfc": "dsfdfs3423",
	"nombreTarea": "Tarea uno",
	"descripcion": "",
	"fechaInicio": "2024-01-01'T'00:00:00",
	"fechaFin": "2024-01-01'T'00:00:00",
	"estado": "PENDIENTE",
	"valor": 10.25
}
```
### Listar tareas del mes

URL: http://localhost:8080/tarea/listartareasmes

TIPO: GET

Mediante este método se puede obtener todas las tareas que se tienen en este mes, para lo cual se obtinene las tareas que su fecha de inicio o fin estén dentro del mes actual.

### Listar tareas del mes por estado

URL: http://localhost:8080/tarea/listartareasmes/{estado}

TIPO: GET

PARAMETRO: {estado}, puede tener los valores PAGADO, PENDIENTE

Mediante este método se puede obtener todas las tareas que se tienen en este mes, para lo cual se obtinene las tareas que su fecha de inicio o fin estén dentro del mes actual y que se encuentren en el estado especificado.

### Cambiar estado de tarea

URL: http://localhost:8080/tarea/cambiarpagada/{id}

TIPO: PUT

PARAMETRO: {id}, el id de la tarea que queremos cambiar el estado

Mediante este método se busca la tarea con el id especificado y se le cambia el estado a PAGADO junto con la fecha actual

### Obtener el promedio de gastos

URL: http://localhost:8080/tarea/crear/promedio/{tipopromedio}

TIPO: GET

PARAMETRO: {tipopromedio}, puede tener los valores DIA, SEMANA, QUINCENA

Mediante este metodo se obtiene los promedios de todos los gastos que se han realizado en base al tipo especificado


## Otros detalles

#### ¿Cómo decidió las opciones técnicas y arquitectónicas utilizadas como parte de su
  solución?

En base a los requerimentos señalados se procedio a utilizar SpringBoot como framework para el desarrollo. Este framework fue utilizado debido a la gran variedad de librerías que posee para realizar todo tipo de funcionalidades. Otro detalle importante es el us de la base de datos H2, la cual fue seleccionada por su facilidad de correr directamente junto a la aplicación si necesidad de interaccion con servicios externos.

#### ¿Qué haría de manera diferente si se le asignara más tiempo?
* Se habría dividido la entidad Tarea, creandose una entidad Persona para el manejo de los datos especificos. Y se hubira realizado un enlace a través de una relación de uno a muchos.
* Para el cálculo de los promedios se hubiera realizado funciones mas óptimas en las que se dividieran los rangos de fechas contenidas en las tareas y luego subdividió según el número de dias que se desea obtener.
