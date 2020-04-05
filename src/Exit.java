import java.util.ArrayList;

public class Exit extends Entity {

    public Exit (Point e, Room a){
        super(a,-1);
        position = around(e);
        for (Person i: a.persons){
            i.finalTarget = position[0] ;
        }
        addPrint();
    }

    public Exit() {
        super();
        position=new Point[1];
    } //default constructor

}