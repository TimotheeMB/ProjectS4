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
        around = new int[][]{{pos[0] + 1, pos[1]},
                {pos[0] + 1, pos[1] - 1},
                {pos[0], pos[1] - 1},
                {pos[0] - 1, pos[1] - 1},
                {pos[0] - 1, pos[1]},
                {pos[0] - 1, pos[1] + 1},
                {pos[0], pos[1] + 1},
                {pos[0] + 1, pos[1] + 1}};
    }

    public int chooseDirection(){
        System.out.println("I choose direction");
        int direction=0;
        double minDistance=distance(around[0],target);
        System.out.println("d= "+direction+" min= "+minDistance);
        for (int i = 1; i < 8 ; i++) {
            double d=distance(around[i],target);
            if(d<minDistance){
                minDistance=d;
                direction=i;
                System.out.println("d= "+direction+" min= "+minDistance);
            }
        }
        System.out.println("my descition ="+direction);
        return direction;

    }

    public double distance(int[] pos1, int[] pos2){
        return Math.sqrt((pos1[0]-pos2[0])*(pos1[0]-pos2[0])+(pos1[1]-pos2[1])*(pos1[1]-pos2[1]));
    }
}
