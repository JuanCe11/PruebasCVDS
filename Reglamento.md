
# Reglamento SuperHex+

## Objetivo

El objetivo del juego es ser el último jugador con vida en el tablero.

### Partes

-  **Tablero**: Es la zona de juego, tiene forma de hexágono y cada lado tiene 100 hexágonos al inicio de la partida. Cada minuto pierde 2 hexágonos de lado hasta un tamaño mínimo 5 hexágonos por lado. 

-  **Hexágono**: Es una unidad dentro del tablero de juego, representa 1 punto para el jugador al momento de ser conquistada. Los Hexágonos pueden contener una mina.

-  **Mina**: Por partida aparecen 25 minas repartidas aleatoriamente en los hexágonos del tablero. Las minas solo son visibles a 3 hexágonos de distancia de la cabeza del jugador. El jugador puede eliminar las minas si conquista con su color el hexágono donde esta la mina, al momento de eliminarla suma 200 puntos.

- **Jugador**:  Cada jugador tiene 3 partes asociadas a el:

  - Cabeza: La cabeza es el punto inicial del jugador la cual sigue el movimiento del cursor.
  - Cola: Es la trayectoria que ha llevado la cabeza desde que salió de su territorio.
  - Territorio: Es el conjunto de hexágonos que el jugador ha conquistado de su color. El jugador al iniciar la partida cuanta con un territorio de 8 hexágonos y su cabeza está en el medio.
  
### Conquistar territorio

-  Un jugador conquista territorio cuando sale de su zona conquistada, rodea una zona que sea de otro jugador o territorio neutral y vuelve a su zona ya conquistada. Todos los hexágonos dentro de esta nueva zona cambian de color al color del jugador y se le suman los respectivos puntos.
Formas de morir

- Un Jugador puede morir en cualquiera de los siguientes escenarios:
  - Muere cuando otro jugador toca con la cabeza su cola.
  - Cuando él mismo toca con su cabeza la cola.
  - Cuando choca su cabeza con otro jugador en territorio conquistado por el otro jugador.
  - Cuando estrella su cabeza con un hexágono que demarca el límite del tablero de juego.
  - Dos jugadores mueren al tiempo cuando ambos jugadores estrellan su cabeza en un hexágono sin conquistar.
-  Cuando un jugador mata a otro jugador suma 500 puntos.
