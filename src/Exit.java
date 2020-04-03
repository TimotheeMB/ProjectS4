import java.util.ArrayList;

public class Exit extends Entity {

    public Point exitLocation ;

    public Exit (Point e, Room a){
        super(a,-1);
        exitLocation = e;
        for (Person i: a.persons){
            i.finalTarget = exitLocation ;
        }
        addPrint();
    }

    public Exit() {
        super();
    } //default constructor

    @Override
    public void addPrint() {
        for (Point p: around(exitLocation)) {
            room.map[p.x][p.y]+=signature;
        }
    }

    @Override
    public void removePrint() {
        for (Point p: around(exitLocation)) {
            room.map[p.x][p.y]-=signature;
        }
    }
}