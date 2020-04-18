import java.util.Comparator;

public class ValuedPointComparator implements Comparator<ValuedPoint> {
    public int compare(ValuedPoint one, ValuedPoint two) {
        return one.value-two.value ;
    }
}
