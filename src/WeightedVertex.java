import java.util.*;

public class WeightedVertex implements Comparable<WeightedVertex>{
  public double value;
  public Point source;

  public WeightedVertex(Point source, double value){
      this.source = source;
      this.value = value;
  }

  public int hashCode(){
    return this.source.hashCode() + (int)this.value;
  }

  public boolean equals(Object obj){
     WeightedVertex vertex = (WeightedVertex)obj;
     return vertex.source == source && vertex.value == value;
  }

  public int compareTo(WeightedVertex other) {
     return this.equals(other) ? 1 : 0;
  }

  public String toString(){
     return "{" + source + " -> " + value + "}";
  }
}
