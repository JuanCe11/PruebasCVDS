# SUPERHEX+

[![M-S-Games](https://circleci.com/gh/M-S-Games/SUPERHEXPlus.svg?style=svg)](https://github.com/M-S-Games/SUPERHEXPlus)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/35265f294e494069b52aee3218a51106)](https://www.codacy.com/gh/M-S-Games/SUPERHEXPlus?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=M-S-Games/SUPERHEXPlus&amp;utm_campaign=Badge_Grade)


## Integrantes 
- Miguel Ángel Castellanos Salamanca 
- Juan Sebastian Gómez López 
## Resumen:
Para este proyecto se tomó la decisión de reinventar un juego que ya existe con un sistema de competencia mucho más dinámico y emocionante. Mas adelante se explicará el juego y las modificaciones que se van a implementar para el mismo.

## SUPERHEX:
Con la gran acogida que tuvieron los juegos .io en el mundo, llegó SUPERHEX.io. El juego consiste en ganar puntos consiguiendo territorio en un tablero llenando hexágonos de tu color y matando a otros jugadores. Al ganar puntos se va escalando en una tabla de posiciones con todos los jugadores del servidor, el objetivo final del juego es llegar a los primeros lugares y estar el mayor tiempo posible.
Para nuestra versión del juego solo vamos a tomar las mecánicas del juego, pero ahora vamos a modificar el objetivo del juego y vamos a agregar un nuevo elemento. En la parte modificaciones se explicarán en detalle el juego.
### [Reglamento](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Reglamento.md)

## Modificaciones:
Las modificaciones se dividen en dos partes:
1. Objetivo del juego: El objetivo en SUPERHEX+ es ser el ultimo jugador vivo en el servidor. Se Inician con un máximo de 25 jugadores en la partida y se tiene un tamaño inicial del tablero, al pasar el tiempo el tamaño del tablero se va reduciendo mientras los jugadores van ganando terreno y van matando a otros jugadores. La partida termina cuando solo hay un jugador vivo.
2. 	Minas: en el tablero se distribuyen aleatoriamente minas las cuales afectarán al jugador solo si éste pasa sobre el hexágono. Las minas son visibles a 3 hexágonos de distancia.
### [Definición](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Definicion.md)

## Mockups
### Pantalla incial 

![pantalla inicial](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Images/inicial.PNG)

Es una pantalla muy sencilla donde el usuario tiene las opciones de crear una sala para jugar o unirse a una sala ya creada.

### Crear sala

![crearSala](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Images/crearSala.PNG)

Aqui el usuario tiene un espacio de texto para poner el nombre de la sala y un boton para crearla.

### Esperando crear partida

![esperandoCrearSala](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Images/esperandoCrear.PNG)

El usuario que creo la sala (el líder de la sala) ve en tiempo real los usuarios que van ingresando a la sala y tiene un boton para cancelar la sala. Al momento de cancelar la sala el líder vuelve a la pantalla de crear la sala.

### Unirse a sala

![unirseSala](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Images/unirseSala.PNG)

Aqui el usuario tiene un espacio de texto para poner el nombre de la sala y un boton para unirse.

### Esperando unirse a partida

![esperandoUnirSala](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Images/esperandoUnir.PNG)

Cuando se une a una sala puede ver los jugadores que ya se unieron y tiene un boton para abandonar la sala. Si el usuario abandona la sala o el líder cancela la partida, el usuario se redirecciona a la pantalla de unirse a una sala.

### Pantalla de juego 

![pantalla juego 1](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Images/2.png)
![pantalla juego 2](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Images/3.png)

En la pantalla de juego solo se tiene información de estado de la partida a la parte superiroir y el resto es el movimiento del jugador.

### Pantalla final

![pantalla final](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Images/4.png)

Se muestra un resumen de la partida del jugador junto a un aviso de fin de partida y un boton de volver a jugar que lo lleva a la pantalla incial.

## Casos de Uso

![CU](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Images/CasosUso.png)

### 1. Crear Sala.
- Como jugador quiero crear una sala para jugar con amigos o crear mi propia partida.
- Criterios: Para poder crear una sala debo ingresar mi nombre de usuario y asignarle un nombre a la sala; además debo tener la opción de cancelarla en cualquier momento.
### 2. Unirme a Sala.
- Como jugador quiero ver las salas disponibles y unirme a una para jugar con amigos o ingresar a una partida.
- Criterios: Debo poder ver todas las salas que están disponibles en el momento y poder seleccionar una.
### 3. Dominar Area. 
- Como jugador quiero encerrar un área y dominarla para comenzar a acumular en la puntuación. 
- Criterios: Solamente se podrá dominar el área si ningún jugador choca con los bordes de la misma antes de cerrarla por completo.
### 4. Eliminar Adversario.
- Como jugador quiero poder eliminar a mi adversario para acercarme a la victoria.
- Criterios: Para poder eliminar al adversario debo chocar con la linea de una zona que no haya terminado de delimitar también es posible guiarlo hacia una mina o los limites de la zona.
### 5. Ver Score Board.
- Como jugador quiero ver el Score Board para poder tener la información de los jugadores compitiendo en la partida.
### 6. Ver Historial.
- Criterios: El Score Board se debe mostrar permanentemente, indicando el número de asesinatos hasta el momento y la cantidad de jugadores que aún siguen compitiendo en la partida.
- Como usuario quiero ver el historial de partidas jugadas en la plataforma para analizar el uso de la misma.
- Criterios: Se debe generar un reporte con el historico de las partidas jugadas en la plataforma.

## Base de Datos

![DB](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Images/DB.png)

## Diagrama de Clases

![DC](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Images/Clases.png)

## Diagrama de Componentes

![Comp](https://github.com/M-S-Games/SUPERHEXPlus/blob/master/Images/Componentes.png)
