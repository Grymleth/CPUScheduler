import java.util.ArrayList;
import java.util.Iterator;

public class Process {
    private int processID;
    private int burst;
    private int completion;
    private int turnaround;
    private int waiting;
    private int remBurst;
    private int priority;
    
    public Process(int burst){
        this.burst=burst;
        this.remBurst=burst;
    }

    public Process(int burst, int processID){
        this(burst);
        this.processID=processID;
    }
    
    public Process(int burst, int priority, int processID){
        this(burst, processID);
        this.priority=priority;
    }
    
    public int getBurst(){return burst;}
    public void setBurst(int burst){this.burst=burst;}
    public int getID(){return this.processID;}
    public void setCompletion(int completion){this.completion=completion;}
    public int getCompletion(){return this.completion;}
    public int getPriority(){return this.priority;}
    public int getTurnaround(){return this.turnaround;}
    public int getWaiting(){return this.waiting;}
    public int getRemBurst(){return this.remBurst;}
    public void decrement(int decrement){
        this.remBurst -= decrement;
    }
    
    public void compute(){
        turnaround=completion;
        waiting=turnaround-burst;
    }

    public static void displayTable(ArrayList<Process> processes){
        System.out.println("BT\tCT\tTAT\tWT");
        Iterator it = processes.iterator();
        Process obj;
        while(it.hasNext()){
            obj = (Process) it.next();
            System.out.printf("%d\t%d\t%d\t%d\n",obj.getBurst(),obj.getCompletion(),obj.getTurnaround(),
                    obj.getWaiting());
        }
    }
    
}
