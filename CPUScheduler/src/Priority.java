
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class Priority {
    ArrayList<Process> processes;
    
    private float avgwt;
    private float avgtat;
    
    public Priority(int[] burst,int[] priority){
        processes = new ArrayList<>();
        
        for(int i=0;i<burst.length;i++){
            processes.add(new Process(burst[i], 0, priority[i], i));
        }
        
        Collections.sort(processes, new SortByPriority());
        completeTable();
        
        Collections.sort(processes, new SortByID());
    }
    
    public final void completeTable(){
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
            avgwt += process.getWaiting();
            avgtat += process.getTurnaround();
        }
        
        avgwt = (float) avgwt / processes.size();
        avgtat = (float) avgtat / processes.size();
    }

    public float getAvgwt(){return avgwt;}
    public float getAvgtat(){return avgtat;}   
}
