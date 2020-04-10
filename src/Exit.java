public class Exit extends Entity {

    public Exit (Point e, Room a){
        super(a,-1);
        position = e.around(true);
        addPrint();
    }

}