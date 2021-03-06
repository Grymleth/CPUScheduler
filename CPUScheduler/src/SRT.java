import java.util.ArrayList;

public class SRT {
    ArrayList<Process> processes;
    ArrayList<Process> ganttBar;
    //System.out.println ("enter no of process:");
    private int n;
    private int pid[]; // it takes pid of process
    private int at[]; // at means arrival time
    private int bt[]; // bt means burst time
    private int ct[]; // ct means complete time
    private int ta[]; // ta means turn around time
    private int wt[]; // wt means waiting time
    private int f[]; // f means it is flag it checks process is completed or not
    private int k[]; // it is also stores brust time
    private int st, tot;
    private float avgwt, avgtat;
    //Scanner sc=new Scanner(System.in);

    public SRT(int burstTime[], int arrivalTime[]) {
        processes = new ArrayList<>();
        ganttBar = new ArrayList<>();
        n = burstTime.length;
        pid = new int[n]; // it takes pid of process
        at = new int[n]; // at means arrival time
        bt = new int[n]; // bt means burst time
        ct = new int[n]; // ct means complete time
        ta = new int[n]; // ta means turn around time
        wt = new int[n]; // wt means waiting time
        f = new int[n]; // f means it is flag it checks process is completed or not
        k = new int[n]; // it is also stores brust time
        st = 0;
        tot = 0;
        avgwt = 0;
        avgtat = 0;
        for (int i = 0; i < n; i++) {
            pid[i] = i;
            at[i] = arrivalTime[i];
            System.out.println("Arrival time of process " + "\t\t" + (i + 1) + " = " + at[i]);
            bt[i] = burstTime[i];
            System.out.println("Burst time of process " + "\t\t\t" + (i + 1) + " = " + bt[i] + "\n");
            k[i] = bt[i];
            f[i] = 0;
        }
        process();
        
        for(int i=0;i<n;i++){
            processes.add(new Process(k[i],at[i], pid[i]));
            processes.get(i).setCompletion(ct[i]);
            processes.get(i).setTurnaround(ta[i]);
            processes.get(i).setWaiting(wt[i]);
        }
    }

    public static void main(String args[]) {
        int araybal[] = {
            2,5,1,0,4
        };
        int berst[] = {
            6,2,8,3,4
        };
        SRT tes = new SRT(berst, araybal);

    }

    void process() {
        int prev_c = -1;
        int gi = -1;
        while (true) {
            int min = 99, c = n;
            gi = ganttBar.size();
            if (tot == n){
                ganttBar.add(new Process(0, 0, prev_c));
                ganttBar.get(gi).setCompletion(st);
                ganttBar.get(gi).setBurst(ganttBar.get(gi).getCompletion()-ganttBar.get(gi-1).getCompletion());
                break;
            }
                

            for (int i = 0; i < n; i++) {
                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                    min = bt[i];  
                    c = i;
                    
                }
            }
            
            if(c != prev_c && prev_c > -1 && prev_c != n){
                if(ganttBar.isEmpty()){
                    
                    ganttBar.add(new Process(st-at[prev_c], 0, prev_c));
                    System.out.println(st);
                    ganttBar.get(gi).setCompletion(st);
                    
                }
                else{
                    ganttBar.add(new Process(0, 0, prev_c));
                    ganttBar.get(gi).setCompletion(st);
                    ganttBar.get(gi).setBurst(ganttBar.get(gi).getCompletion()-ganttBar.get(gi-1).getCompletion());
                }
            }

            if (c == n)
                st++;
            else {
                bt[c]--;
                st++;
                if (bt[c] == 0) {
                    
                    ct[c] = st;
                    f[c] = 1;
                    tot++;
                }
            }
            if(c != n)
                prev_c = c;
            System.out.println(prev_c);
        }

        for (int i = 0; i < n; i++) {
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - k[i];
            avgwt += wt[i];
            avgtat += ta[i];
        }
        
        avgtat = (float) avgtat/n;
        avgwt = (float) avgwt/n;

        System.out.println("pid  arrival  burst  complete turn waiting");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + k[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
        }

        System.out.println("\naverage tat is " + avgtat);
        System.out.println("average wt is " + avgwt);
    }
    
    public float getAvgwt(){return avgwt;}
    public float getAvgtat(){return avgtat;}
}