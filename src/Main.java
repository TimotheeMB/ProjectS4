public class  Main {

    public static void main(String[] args) {

        boolean finished=false;
        GUI display = new GUI();

        Room gopher= new Room(500);
        Person Brian= new Person();
        gopher.addPerson(Brian);

        while(!finished){
            gopher.nextStep();
            //display.refresh();
        }
    }
}
