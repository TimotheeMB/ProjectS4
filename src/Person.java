public class Person {
    int [] pos;
    int [] target;
    int [][] around;

    public Person(int[] pos, int[] target) {
        this.pos = pos;
        this.target = target;
        around = new int[][]{{pos[0] + 1, pos[1]},
                            {pos[0] + 1, pos[1] - 1},
                            {pos[0], pos[1] - 1},
                            {pos[0] - 1, pos[1] - 1},
                            {pos[0] - 1, pos[1]},
                            {pos[0] - 1, pos[1] + 1},
                            {pos[0], pos[1] + 1},
                            {pos[0] + 1, pos[1] + 1}};
    }

    public void move(){
        this.pos=around[chooseDirection()];
    }

    public int chooseDirection(){
        int direction=0;
        double minDistance=distance(around[0],target);
        for (int i = 1; i < 8 ; i++) {
            double d=distance(around[i],target);
            if(d<minDistance){
                minDistance=d;
                direction=i;
            }
        }
        return direction;
    }

    public double distance(int[] pos1, int[] pos2){
        return Math.sqrt((pos1[0]-pos2[0])^2+(pos1[1]-pos2[1]));
    }
}
