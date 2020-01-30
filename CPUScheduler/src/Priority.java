
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;


public class Priority {
    ArrayList<Process> processes;
    
    public Priority(int[] burst, int[] arrivalTime,int[] priority){
        processes = new ArrayList<>();
        
        for(int i=0;i<burst.length;i++){
            processes.add(new Process(burst[i],priority[i], i));
        }
        
        Collections.sort(processes, new SortByPriority());
        completeTable();
        Process.displayTable(processes);
    }
    
    public void completeTable(){
        int completion = 0;

        Iterator it = processes.iterator();
        Process process;
        while(it.hasNext()){
            process = (Process) it.next();
            completion += process.getBurst();
            process.setCompletion(completion);
        }

        it = processes.iterator();

        while(it.hasNext()){
            process = (Process) it.next();
            process.compute();
        }

    }
    
    public void displayList(ArrayList<Process> list){
        for(Process x: processes){
            System.out.printf("%d\t%d\t%d\t%d\n",x.getID(),x.getPriority(),x.getBurst(),x.getCompletion());
        }
    }
    
    public static void main(String[] args) {
        int[] burst = {10,5,8};
        int[] arrival = {0,0,0};
        int[] priority = {2,0,1};
        Priority x = new Priority(burst, arrival, priority);



    }
}
