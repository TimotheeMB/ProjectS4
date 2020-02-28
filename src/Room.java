public class Room {
    final int SIZE;
    boolean [][] map;
    Person [] persons;
    Obstacle [] obstacles;

    public Room(int size) {
        SIZE = size;
        map = new boolean[SIZE][SIZE];
        for (int i = 0; i <SIZE; i++) {
            for (int j = 0; j <SIZE ; j++) {
                map[i][j]=false;
            }
        }
    }


}
