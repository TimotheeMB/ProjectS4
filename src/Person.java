public class Person extends Entity{
/*** Hello my name is Brian, I'm an instance of that class, and I will explain you how I work ***/

    public Point[] initPosition;

    /* My birth */
    public Person(Point center, Simulation simulation) {

        /*I set my attributes*/
        super(simulation,1);
        this.position = center.around(true);
        this.initPosition = position;// I do not forget where I come from

        /*I put myself in the simulation*/
        addPrint();
    }


    /* METHOD */
    public void nextStep(){
        removePrint();//I disappear from my last position

        if(((int)(Math.random()*(1/simulation.panic))==0)||(simulation.distAt(position[0])==Integer.MAX_VALUE)){//If there is panic in the room or if there is no way I can reach the exit
            Point randomPosition = position[(int)(Math.random()*21)]; // I choose my next position randomly
            if(simulation.emptyAround(randomPosition)){// And if I can..
                position = randomPosition.around(true); //I go to that position
            }
        }else {
            //here I will choose the point of my position which is the closer to the exit (and where I can go)
            int minDist= simulation.distAt(position[0]);
            int index=0;
            for (int i = 0; i <position.length ; i++) {
                if (simulation.emptyAround(position[i])) {//check if I can go
                    if (simulation.distAt(position[i]) < minDist) {
                        minDist = simulation.distAt(position[i]);
                        index = i;
                    }
                }
            }
            position= position[index].around(true);//I go to that position
        }

        if(simulation.distAt(position[0])>40) {
            addPrint();//I appear in my new position
        }
    }
}
