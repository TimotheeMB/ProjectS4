import java.util.Comparator;

public class ValuedPointComparator implements Comparator<ValuedPoint> {
    @Override
    public int compare(ValuedPoint one, ValuedPoint two) {
        return one.value-two.value ;
    }
}
