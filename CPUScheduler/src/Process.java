import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class Process {
    private int[] details;
    
    private static final int PID = 0;
    private static final int PRIORITY = 1;
    private static final int BURST = 2;
    private static final int ARRIVAL = 3;
    private static final int COMPLETION = 4;
    private static final int TURNAROUND = 5;
    private static final int WAITING = 6;
    private static final int REM_BURST = 7;
    

    public Process(int burst, int arrival, int processID){
        details = new int[8];
        details[BURST] = details[REM_BURST] = burst;
        details[ARRIVAL] = arrival;
        details[PID] = processID;
    }
    
    public Process(int burst, int arrival, int priority, int processID){
        this(burst, arrival, processID);
        details[PRIORITY] = priority;
    }
    
    public int getBurst(){return details[BURST];}
    public void setBurst(int burst){details[BURST]=burst;}
    public int getArrival(){return details[ARRIVAL];}
    public void setArrival(int arrival){details[ARRIVAL]=arrival;}
    public int getID(){return details[PID];}
    public void setCompletion(int completion){details[COMPLETION]=completion;}
    public int getCompletion(){return details[COMPLETION];}
    public int getPriority(){return details[PRIORITY];}
    public int getTurnaround(){return details[TURNAROUND];}
    public void setTurnaround(int turnaround){details[TURNAROUND]=turnaround;}
    public int getWaiting(){return details[WAITING];}
    public void setWaiting(int waiting){details[WAITING]=waiting;}
    public int getRemBurst(){return details[REM_BURST];}
    public void decrement(int decrement){
        details[REM_BURST] -= decrement;
    }
    
    public void compute(){
        details[TURNAROUND]=details[COMPLETION] - details[ARRIVAL];
        details[WAITING]=details[TURNAROUND]-details[BURST];
    }

    public static void displayTable(ArrayList<Process> processes){
        System.out.println("PID\tPrio\tBT\tAT\tCT\tTAT\tWT");
        Iterator it = processes.iterator();
        Process obj;
        while(it.hasNext()){
            obj = (Process) it.next();
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\t%d\n",
                    obj.getID(),
                    obj.getPriority(),
                    obj.getBurst(),
                    obj.getArrival(),
                    obj.getCompletion(),
                    obj.getTurnaround(),
                    obj.getWaiting());
        }
    }
    
    public String[] toStringArray(boolean isPriority){
        int size = isPriority?7:6;
        String[] array = new String[size];
        
        array[0] = Integer.toString((details[PID]+1));
        for(int i=1, j=1; i<details.length-1; i++){
            if(!isPriority && i == PRIORITY)
                continue;
            
            array[j] = Integer.toString(details[i]);
            j++;
        }
        
        return array;
    }
    
    public static void main(String[] args) {
        
    }
}
