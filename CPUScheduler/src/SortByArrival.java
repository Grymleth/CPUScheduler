
import java.util.Comparator;

public class SortByArrival implements Comparator<Process>{

    @Override
    public int compare(Process a, Process b) {
        return a.getArrival()-b.getArrival();
    }
}
