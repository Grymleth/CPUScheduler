import java.util.Comparator;

public class SortByPriority implements Comparator<Process>{
    
    @Override
    public int compare(Process a, Process b) {
        return a.getPriority()-b.getPriority();
    }
}
