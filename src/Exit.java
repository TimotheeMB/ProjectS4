public class Exit extends Entity {

    public Exit (Point centre, Simulation simulation){
        super(simulation,-1);
        position = centre.around(true);
        addPrint();
    }

}