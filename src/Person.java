public class Person extends Entity{

    public Point[] initPosition;

    /* CONSTRUCTOR */
    public Person(Point center, Simulation simulation) {

        /*I computePaths my attributes*/
        super(simulation,1);
        this.position = center.around(true);
        this.initPosition = position;

        /*I put myself in the simulation*/
        addPrint();
    }


    /* METHOD */
    public void nextStep(){
        removePrint();//I disappear from my last position

        if(simulation.panic||simulation.distAt(position[0])==Integer.MAX_VALUE){
            Point randomPosition = position[(int)(Math.random()*21)];
            if(simulation.emptyAround(randomPosition)){
                position = randomPosition.around(true);
            }
        }else {
            int minDist= simulation.distAt(position[0]);
            int index=0;
            for (int i = 0; i <position.length ; i++) {
                if (simulation.emptyAround(position[i])) {
                    if (simulation.distAt(position[i]) < minDist) {
                        minDist = simulation.distAt(position[i]);
                        index = i;
                    }
                }
            }
            position= position[index].around(true);
        }

        if(simulation.distAt(position[0])>40) {
            addPrint();//I appear in my new position
        }
    }
}
