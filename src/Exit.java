import java.util.ArrayList;

public class Exit extends Entity {

    public Exit (Point e, Room a){
        super(a,-1);
        position = around(e);
        addPrint();
    }

    public Exit() { //default constructor
        super();
        position=new Point[1];
    }

}