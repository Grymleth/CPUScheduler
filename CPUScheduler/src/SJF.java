import java.util.ArrayList;

public class SJF {
    ArrayList<Process> processes;
    
    private int n;
    private int pid[];
    private int at[]; // at means arrival time
    private int bt[]; // bt means burst time
    private int ct[]; // ct means complete time
    private int ta[]; // ta means turn around time
    private int wt[]; //wt means waiting time
    private int f[]; // f means it is flag it checks process is completed or not
    private int st, tot;
    private float avgwt, avgtat;


    public SJF(int burstTime[],int arrivalTime[]) {
        processes = new ArrayList<>();
        n = burstTime.length;
        //System.out.println ("enter no of process:");
        //n = sc.nextInt();
        pid = new int[n];
        at = new int[n]; // at means arrival time
        bt = new int[n]; // bt means burst time
        ct = new int[n]; // ct means complete time
        ta = new int[n]; // ta means turn around time
        wt = new int[n]; //wt means waiting time
        f = new int[n]; // f means it is flag it checks process is completed or not
        st = 0;
        tot = 0;
        avgwt = 0;
        avgtat = 0;
        
        for (int i = 0; i < n; i++) {
            at[i] = arrivalTime[i];
            bt[i] = burstTime[i];
            pid[i] = i;
            f[i] = 0;

        }
        
        process();
        
        for(int i=0;i<n;i++){
            processes.add(new Process(bt[i],at[i], pid[i]));
            processes.get(i).setCompletion(ct[i]);
            processes.get(i).setTurnaround(ta[i]);
            processes.get(i).setWaiting(wt[i]);
        }
        
        Process.displayTable(processes);
        System.out.println("AVGTAT: " + avgtat);
    }

    public static void main(String args[]) {

        int araybal[] = {
            0,
            2,
            1
        };
        int berst[] = {
            23,
            32,
            11
        };

        SJF tes = new SJF(berst, araybal);

    }


    void process() {
        boolean a = true;
        while (true) {
            int c = n, min = 999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;

            for (int i = 0; i < n; i++) {
                /*
                 * If i'th process arrival time <= system time and its flag=0 and burst<min 
                 * That process will be executed first 
                 */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                    min = bt[i];
                    c = i;
                }
            }

            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c == n)
                st++;
            else {
                ct[c] = st + bt[c];
                st += bt[c];
                ta[c] = ct[c] - at[c];
                wt[c] = ta[c] - bt[c];
                f[c] = 1;
                tot++;
            }
            
        }
        
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgtat += ta[i];
        }
        
        avgwt = (float) avgwt / n;
        avgtat = (float) avgtat / n;
        
    }
    
    public float getAvgwt(){return avgwt;}
    public float getAvgtat(){return avgtat;}        
}