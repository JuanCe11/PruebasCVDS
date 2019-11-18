# Proyecto CVDS 2019-II - Biblioteca

## Profesores 
	- Julian Velasco (Product Owner)
	- Santiago Alzate (Product Owner)
	
## Integrantes:
	- Juan Sebastián Gómez 
	- Sebastián Herrera
	- Eduardo Ocampo 
	- Juan David Navarro


## Descripción del producto:

### Descripción general:
	
El objetivo del sistema es permitir que los estudiantes, puedan reservar las salas de estudio, equipos de cómputo, tablero inteligente y demás recursos de la biblioteca. Deberían autenticarse con su correo institucional y hacer la reserva del recurso en el horario de su preferencia. Debe existir también una forma de gestionar aquellos recursos en caso que se deseen agregar o modificar los mismos, así como asignar horarios de disponibilidad específicos. La plataforma de gestión de recursos debe contar con una interfaz de usuario amigable con los usuarios.
		
	
## Arquitectura y Diseño detallado:
### Modelo E-R.
![Base de datos](https://github.com/Proyecto-CVDS-20192/Proyecto-CVDS-Biblioteca/blob/master/img/Base%20de%20datos.png)
### Diagrama de clases
![Diagrama de clases](https://github.com/Proyecto-CVDS-20192/Proyecto-CVDS-Biblioteca/blob/master/img/Diagrama%20de%20clases.png)	

### Tecnologías

 - Se ultiliza Guice para hacer la injeccion de los diferentes servicios de la biblioteca 
 junto con los elementos necesarios para usar MyBatis
 - MyBatis con el fin de hacer mas eficiente las consultas y acciones sobre la base de datos
 - PostgreeSQL, se relizo una base de datos en Heroku de PostgreeSQL para la capa de persistencia

### Enlace a la aplicación en Heroku:
Heroku APP : [https://cvds-proyecto-biblioteca.herokuapp.com/](https://cvds-proyecto-biblioteca.herokuapp.com/)


[![Codacy Badge](https://api.codacy.com/project/badge/Grade/936c2a405ca14b45a130fc6a40aaf9b9)](https://www.codacy.com/manual/JuanCe28/Proyecto-CVDS-Biblioteca?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Proyecto-CVDS-20192/Proyecto-CVDS-Biblioteca&amp;utm_campaign=Badge_Grade)

[![CircleCI](https://circleci.com/gh/Proyecto-CVDS-20192/Proyecto-CVDS-Biblioteca.svg?style=svg)](https://circleci.com/gh/Proyecto-CVDS-20192/Proyecto-CVDS-Biblioteca)


-----------------------------------------------------------------------------------


### Informacion Base de Datos
 - Host: ec2-54-163-230-199.compute-1.amazonaws.com
 - Database: d4cnn66hp1d27l
 - User: scvtynbcreheid
 - Port: 5432
 - Password: 4921657fe3425e23700bbb5ad721ccf8e7bd41bc6fe01ad35df337393ffcfea2
 - URI: postgres://scvtynbcreheid:4921657fe3425e23700bbb5ad721ccf8e7bd41bc6fe01ad35df337393ffcfea2@ec2-54-163-230-199.compute-1.amazonaws.com:5432/d4cnn66hp1d27l
 - Heroku CLI: heroku pg:psql postgresql-objective-64170 --app cvds-proyecto-biblioteca
