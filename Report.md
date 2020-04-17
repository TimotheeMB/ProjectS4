Crowd simulator

## Specifications
The main goal of our crowd simulator is to observe the displacements of people in a room in the special case where they all want to reach the exit as soon as possible (for example because of a fire inside a building).
With more details, we also wanted to :

- Choose or create an environment 
- Choose where to put the obstacles, the exit and the danger
- Choose the number of people inside, their position
- Choose different types of behaviors (panic, calm, selfish, aware of their environment)

## Description of the problem
We knew we would have difficulties in making the people go to a special point avoiding being stuck by an obstacle.


## Principle of the algorithm
The principle is the following : for each person created, before they make any move, the algorithm computes the shortest path to the chosen exit.
Then 2 options follow : if the path is free, the person goes to the exit. If not, we add as an intermediate target the closest vertex of the obstacle. Then, we repeat the same method. When the whole path is computed, the point follows it.
We still have problems with this method since for special configurations, people get stuck behind obstacles. It might happen when a person is at the same distance from several vertices of an obstacle or when the obstacle touches the boundary of the room.

All the attempts to solve these problems failed and as the code were being more and more complicated we chose to change the method and use the one proposed by our teacher. It is called Dijkstra's algorithm. It allows to compute the shortest path between two points. This is exactly what we need.
It works as follow : we initialize the exit to be at a nil distance to the exit. Then we take all the points around the exit and give the value 1 to the closest to the person, the other points around the exit have the value infinity. We do the same around this new point of value 1 and give the value 2 to the closest point from the person. We give infinite values to obstacles. At the end, the person follows the path (or recompute it not to meet people).  
There is no more problems of persons being not being able to reach an exit.


## Bibliographaaaïïïïïï
Informations on Dijkstra:
- idea of *Diana Nurbakova* + [Wikipedia] (https://fr.wikipedia.org/wiki/Algorithme_de_Dijkstra)
- Lecture of *Jilles S. Dibangoye* 

Icons:
- Icons made by <a href="https://www.flaticon.com/authors/xnimrodx" title="xnimrodx">xnimrodx</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com </a>

## UML diagram

```mermaid
classDiagram

Window *-- DisplayPanel
Window *-- ChoicesPanel
Window *-- Simulation
Entity <|-- Obstacle
Entity <|-- Exit
Entity <|-- Person
Simulation -- *Entity
Point --* Entity
Point<|--ValuedPoint

Simulation: +persons: ArrayList<Person> 
Simulation: +obstacles: ArrayList<Obstacle>
Simulation: +exits: ArrayList<Exit>
Simulation: +width: int
Simulation: +height: int
Simulation: +map: int[][][]
Simulation: +ININITY: int
Simulation: +timer: Timer
Simulation: +time: long
Simulation: +NORMAL_STEP_DURATION: int
Simulation: +panic: boolean
Simulation: +nextStep()
Simulation: +dijkstra()
Simulation: +addPerson()
Simulation: +addObstacle()
Simulation: +addExit()
Simulation: +inBounds(Point)
Simulation: +emptyAround(Point)
Simulation: +isRunning()
Simulation: +start()
Simulation: +pause()
Simulation: +restart()
Simulation: +speedTimes(double)
Simulation: +actionPerformed(ActionEvent)

Window: +simulation: Simulation
Window: +displayInterval: int
Window: +timer: Timer
Window: +displayPan: DisplayPanel
Window: +choicesPan: ChoicesPanel
Window: +total: JPanel
Window: +drawEquidistant: boolean
Window: +drawDistanceToExit: boolean
Window: +wait: HashMap<JButton,Boolean>
Window: +chargeSimulation(String)
Window: +chargeSimulation(Simulation)
Window: +saveSimulation()
Window: +actionPerformed(ActionEvent)

DisplayPanel: +window: Window
DisplayPanel: +beginningObstacle: Point
DisplayPanel: +paint(Graphics)
DisplayPanel: +mousePressed(MouseEvent)
DisplayPanel: +mouseReleased(MouseEvent)
DisplayPanel: +scaleX()
DisplayPanel: +scaleY()

ChoicesPanel: +window: Window
ChoicesPanel: +text: HashMap<JButton, String>
ChoicesPanel: +person
ChoicesPanel: +obstcle
ChoicesPanel: +
ChoicesPanel: +

```

## Possible improvements, bugs ...
The main problem of our crowd simulator is that when we create an obstacle very close to the boundary of the room, without touching it, if the person is too big to go through the space, it will stay blocked behind the obstacle. Indeed, the algorithm computes that there is available space, however, the person can't.
We could display a message if you use several times the same name for simulations you want to save. 

## Diary

 We have got the idea of a crowd simulator by looking at the emergency stairs of the canteen. We wanted to code a tool that would compute things that we can. In this case the trajectory of a high number of people and the time
 We have done some researches and chosen to use git with a github repository to work together more easily. We have written a first idea of the wanted [specifications](#Specifications).

**At the beginning 

- Claire is in charge of the Window.
- Violaine thinks about the principle to code the motion of people and the special cases to solve
- Timothée starts to write the "Person" class

**Through the weeks** 
- Claire continued the graphical interface
- Timothée coded the displacement of people and the parameters of the room
- Violaine was in charge of the obstacle class and wrote the report, she also participated in the global aspect of the crowd simulator

**First steps of the program**

1. create several persons which move toward the exit as well as a simple user interface
2. create obstacles and buttons to choose what to add : a person or an obstacle
3. test different algorithms to make the people go toward the exit
4. add a timer
5. add a button to choose where to put the exit and modify the code to make the persons go toward it as quick as possible
6. change the complicated algorithm to the Dijkstra one

**More advanced steps**
1. add a refresh button
2. add a panic mode that makes the person take random paths
3. correct the bugs linked to the people that leave the room due to the panic mode
4. add the equipotential lines and the color to traduce the distance to the exit
5. compute the speed of the persons so that it is coherent with the size of the room
6. add a window to choose the size of the room
7. add a break button 
8. add buttons to modify the speed of the simulation
9. add a button to be able to save a simulation and create model simulations


## Implication of the members

|  Member  |Work done |
|----------|----------|
| Violaine | 33,3333% |
| Claire   | 33,3333% |
| Timothée | 33,3333% |
