public class  Main {

    public static void main(String[] args) {

        boolean finished=false;
        GUI display = new GUI();

        Room gopher= new Room(500);
        int[] whereIsBrian={250,250};
        int[] whereBrianWantsToGo={0,0};
        Person Brian= new Person(whereIsBrian,whereBrianWantsToGo);
        gopher.addPerson(Brian);

        while(!finished){
            gopher.nextStep();
            //display.refresh();
            System.out.println(Brian.pos[0]+"   "+Brian.pos[1]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
