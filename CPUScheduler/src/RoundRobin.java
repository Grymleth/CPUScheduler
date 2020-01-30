
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public class RoundRobin {
    private final int quantum;
    
    ArrayList<Process> processes;
    ArrayList<Process> ganttBar;
    
    
    public RoundRobin(int[] burst, int quantum){
        processes = new ArrayList<>();
        ganttBar = new ArrayList<>();
        
        for(int i=0;i<burst.length;i++){
            processes.add(new Process(burst[i], i));
        }
        this.quantum=quantum;

        gantt();
        completeTable();
        
        Process.displayTable(processes);
    }
    
    public final void gantt(){
        int decrement;
        int repeats;
        int completion = 0;
        Process obj;
        Iterator it;
        for(int i=0;!isDone();i++){
            it = processes.iterator();
            for(int j=0;it.hasNext();j++){
                obj = (Process) it.next();
                decrement = 0;
                
                if(obj.getRemBurst() == 0){
                    continue;
                }
                
                int x=0;
                repeats = repeats(j,completion);
                do{
                    if(obj.getRemBurst() >= quantum){
                        decrement += quantum;
                    }
                    else{
                        decrement += obj.getRemBurst();
                    }

                    System.out.printf("Process %d quantum lapse %d | ",j+1,decrement);
                    x++;
                }
                while(x<repeats);

                System.out.printf("Process %d decremented by %d \n",j+1,decrement);
                completion+=decrement;
                obj.decrement(decrement);

                obj = new Process(decrement,obj.getID());
                obj.setCompletion(completion);
                ganttBar.add(obj);

                System.out.println("completion: :"+obj.getCompletion());
                
            }
            System.out.println("Round!!");
        }
    }
    
    public final void completeTable(){

        for(int i=0;i<processes.size();i++){
            for(int j=ganttBar.size()-1;j>=0;j--){
                if(ganttBar.get(j).getID() == processes.get(i).getID()){
                    processes.get(i).setCompletion(ganttBar.get(j).getCompletion());
                    break;
                }
            }
        }

        Process process;

        Iterator it1 = processes.iterator();
        while(it1.hasNext()){
            process = (Process) it1.next();
            process.compute();
        }
    }
    

    
    boolean isDone(){
        for(Process x:processes){
            if(x.getRemBurst() > 0){
                return false;
            }
            
        }
        return true;
    }
    
    public int repeats(int index, int completion){
        if(isDone()){
            return processes.get(index).getRemBurst()/quantum;
        }
        else{
            return 1;
        }
    }
    
    public void displayList(ArrayList<Process> list){
        for(Process x: processes){
            System.out.printf("%d\t%d\t%d\t%d\n",x.getID(),x.getPriority(),x.getBurst(),x.getCompletion());
        }
    }

    
    public static void main(String[] args) {
        int[] burst = {3,4,3};
        RoundRobin x = new RoundRobin(burst, 1);
    }

    public ArrayList<Process> shit(){
        return processes;
    }


}
