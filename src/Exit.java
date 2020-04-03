import java.util.ArrayList;

public class Exit {

    public Point exitLocation ;

    public Exit (Point e, Room a){
        exitLocation = e;
        for (Person i: a.persons){
            i.finalTarget = exitLocation ;
        }
    }
}