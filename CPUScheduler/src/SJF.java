import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 201811249
 */
public class SJF {
    ArrayList<Process> processes;
    
    int n;
    int pid[];
    int at[]; // at means arrival time
    int bt[]; // bt means burst time
    int ct[]; // ct means complete time
    int ta[]; // ta means turn around time
    int wt[]; //wt means waiting time
    int f[]; // f means it is flag it checks process is completed or not
    int st, tot;
    float avgwt, avgta;


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
        avgta = 0;

        for (int i = 0; i < n; i++) {
            //System.out.println ("enter process " + (i+1) + " arrival time:");
            at[i] = arrivalTime[i];
            System.out.println("arrival time of process " + i + 1 + " = " + arrivalTime[i]);
            bt[i] = burstTime[i];
            System.out.println("Burst time of process " + i + 1 + " = " + burstTime[i]);
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

        System.out.println("\npid  arrival brust  complete turn waiting");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgta += ta[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
        }
        System.out.println("\naverage tat is " + (float)(avgta / n));
        System.out.println("average wt is " + (float)(avgwt / n));
        
    }
}