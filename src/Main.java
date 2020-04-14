public class  Main {

    public static void main(String[] args) {

        //We create a simulation
        Simulation simulation = new Simulation(500,500);

        //We display and control this simulation thanks to a GUI
        Window display = new Window(simulation);
    }
}
