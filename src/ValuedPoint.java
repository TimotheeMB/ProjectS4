public class ValuedPoint extends Point {
    int value;

    public ValuedPoint(Point point,int value) {
        super(point.x, point.y);
        this.value=value;
    }
}
