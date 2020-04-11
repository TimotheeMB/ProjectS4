public class  Main {

    public static void main(String[] args) {

        //We create a simulation
        Simulation simulation = new Simulation();

        //We display and control this simulation thanks to a GUI
        GUI display = new GUI(simulation,50);
    }
}
