public class  Main {

    public static void main(String[] args) {

        //We create a room
        Room gopher= new Room(500);

        //We create a simulation that will make the room live (every 10ms)
        Simulation test = new Simulation(gopher,50);

        //We display and control this simulation thanks to a GUI
        GUI display = new GUI(test,50);
    }
}
