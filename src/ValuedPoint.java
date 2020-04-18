public class ValuedPoint extends Point {
    /*** used in the dijkstra algorithm ***/

    int value;

    public ValuedPoint(Point point,int value) {
        super(point.x, point.y);
        this.value=value;
    }
}
