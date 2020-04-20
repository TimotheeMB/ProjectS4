# Crowd simulator

## Specifications
### The first idea

The main goal of our crowd simulator is to observe the displacements of people in a room in the special case where they all want to reach the exit as soon as possible (for example because of a fire inside a building).
With more details, we also wanted to :

- Choose or create an environment 
- Choose where to put the obstacles, the exit and the danger
- Choose the number of people inside, their position
- Choose different types of behaviors (panic, calm, selfish, aware of their environment)
- Be able to time the evacuation

### The final specifications

We almost reached all our goals, but for the different types of behaviors, we in fact just have the possibility to choose the "panic degree" in the room. We also added a few more functionalities:

- Save and reload multiple rooms
- Time management (play, pause, faster, slower, restart)
- Resizable window
- Display the distance to the closer exit thanks to color or equidistant lines

## Description of the problem
We knew we would have difficulties in making the people go to a special point avoiding being stuck by an obstacle.


## Principle of the algorithm
### Our first idea

The principle is the following : for each person created, before they make any move, the algorithm computes the shortest path to the chosen exit.
Then 2 options follow : if the path is free, the person goes to the exit. If not, we add as an intermediate target the closest vertex of the obstacle. Then, we repeat the same method. When the whole path is computed, the point follows it.
We still have problems with this method since for special configurations, people get stuck behind obstacles. It might happen when there is a lot of obstacles or when an obstacle touches the boundary of the room or another obstacle.

### The final solution

All the attempts to solve these problems failed and as the code were getting more and more complicated we chose to change the method and use the one proposed by our teacher. It is called Dijkstra's algorithm.

> "**Dijkstra's algorithm** (or **Dijkstra's Shortest Path First algorithm**, **SPF algorithm**)[[2\]](https://en.wikipedia.org/wiki/Dijkstra's_algorithm#cite_note-2) is an [algorithm](https://en.wikipedia.org/wiki/Algorithm) for finding the [shortest paths](https://en.wikipedia.org/wiki/Shortest_path_problem) between [nodes](https://en.wikipedia.org/wiki/Vertex_(graph_theory)) in a [graph](https://en.wikipedia.org/wiki/Graph_(abstract_data_type)), which may represent, for example, [road networks](https://en.wikipedia.org/wiki/Road_network).  It was conceived by [computer scientist](https://en.wikipedia.org/wiki/Computer_scientist) [Edsger W. Dijkstra](https://en.wikipedia.org/wiki/Edsger_W._Dijkstra) in 1956 and published three years later.[[3\]](https://en.wikipedia.org/wiki/Dijkstra's_algorithm#cite_note-3)[[4\]](https://en.wikipedia.org/wiki/Dijkstra's_algorithm#cite_note-Dijkstra_Interview-4)[[5\]](https://en.wikipedia.org/wiki/Dijkstra's_algorithm#cite_note-Dijkstra1959-5)" [Wikipedia](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)

The complete Dijkstra's algorithm allows to compute precisely the shorter path from a point to another, that's not exactly what we want because the persons still need to adapt to there immediate environment while moving (i.e., avoid other people). Hence we will just use the part that is dedicated to computing the distance to a given point (here an exit) in every point of the graph (here the map). But we will use it several times (for each exit) so that the persons can go to the closest exit from there position (accounting for obstacles).

You can find a more precise description of the functioning of the Dijkstra's algorithm adapted to our situation in the source code (Class: ``Simulation`` , Method: ``dijkstra()``)

There is no more problems of persons not being able to reach an exit and we can superimpose obstacles as much as we want, they can even touch the border of the simulation. This algorithm can take a certain time when we press ``play`` (``computing paths...``) especially if the room is big or if there is a lot of exits ([see improvements](##Possible improvements, bugs ...)).

Thanks to this algorithm we can display the distance to the exit in the simulation (equidistant lines, colors).

*Note: this algorithm is called each time we press ``play`` but also each time we add an exit or an obstacle if we are currently displaying the distance to the exit or the equidistant lines. This way the display of the distance can stay up to date.*


## Bibliography
Information on Dijkstra:
- Idea of *Diana Nurbakova* + [Wikipedia](https://fr.wikipedia.org/wiki/Algorithme_de_Dijkstra)
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
Simulation: +panic: double
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
Window: +loadSimulation(String)
Window: +loadSimulation(Simulation)
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
ChoicesPanel: +person: JButton
ChoicesPanel: +obstacle: JButton
ChoicesPanel: +start: JButton
ChoicesPanel: +exit: JButton
ChoicesPanel: +pause: JButton
ChoicesPanel: +save: JButton
ChoicesPanel: +restart: JButton
ChoicesPanel: +fast: JButton
ChoicesPanel: +slow: JButton
ChoicesPanel: +panic: JSlider
ChoicesPanel: +equidistant: JCheckBox
ChoicesPanel: +distanceToExit: JCheckBox
ChoicesPanel: +simulationChoice: JComboBox<String>
ChoicesPanel: +instructions: TextComponent
ChoicesPanel: +timing: TextComponent
ChoicesPanel: +vx: double
ChoicesPanel: +restart()
ChoicesPanel: +addListener()
ChoicesPanel: +actionPerformed(ActionEvent)
ChoicesPanel: +itemStateChanged(ItemEvent)
ChoicesPanel: +stateChanged(ChangeEvent)

Entity: +position: Point[]
Entity: +signature: int
Entity: +simulation: Simulation
Entity: +addPrint()
Entity: +removePrint()

Person: +initPosition: Point[]
Person: +nextStep()

Obstacle: +vertices: Point[]
Obstacle: +length()
Obstacle: +height()

Point: +x: int
Point: +y: int
Point: +around(boolean)

ValuedPoint: +value: int 
```

## Possible improvements, bugs ...
- The main problem of our crowd simulator is that when there is a small space between two obstacles or an obstacle and the boundary of the room the people try to pass even though their are too big and the get **stuck**. Indeed, the algorithm computes that there is available space, however, the person can't.
- We could display a message if you use several times the same name for simulations you want to save (to avoid overwriting them).
- We could add an ```Undo``` *(ctrl+z)* option.
- We could improved the efficiency of our piece of software by not using Dijkstra's algorithm when pressing ``play`` if any obstacle or exit have been added. And don't re-compute the algorithm for every exit when we just add one.

## Diary

We got the idea of a crowd simulator by looking at the emergency stairs of the canteen. We wanted to code a tool that would compute things that we can't. In this case the trajectory of a high number of people and the time to leave a building. We have done some researches and chosen to use *git* with a *github* repository to work together more easily. We have written a first idea of the wanted [specifications](#Specifications).

**At the beginning**

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
4. add the equidistant lines and the color to traduce the distance to the exit
5. compute the speed of the persons so that it is coherent with the size of the room
6. add a dialogue window to choose the size of the room
7. add a pause and restart buttons 
8. add buttons to modify the speed of the simulation
9. add a button to be able to save a simulation
10. add a ``ComboBox`` to load the simulations
11. Create "model" simulations


## Implication of the members

|  Member  |Work done |
|----------|----------|
| Violaine | 33,3333% |
| Claire   | 33,3333% |
| Timothée | 33,3333% |
