public class  Main {

    public static void main(String[] args) {

        //We create a room
        Room gopher= new Room(720);

        //We create a person
        Point whereIsBrian=new Point(250,250);
        Point whereBrianWantsToGo=new Point(400,700);
        Person Brian= new Person(whereIsBrian,whereBrianWantsToGo);

        //We put the person in the room
        gopher.addPerson(Brian);

        //We create a simulation that will make the room live (every 10ms)
        Simulation test = new Simulation(gopher,10);

        //We display and control this simulation thanks to a GUI
        GUI display = new GUI(test,50);

    }
}
