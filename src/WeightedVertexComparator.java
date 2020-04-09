import java.util.*;

public class WeightedVertexComparator implements Comparator<WeightedVertex> {
		public int compare(WeightedVertex one, WeightedVertex two) {
			return (int)(one.value-two.value);
		}
}
