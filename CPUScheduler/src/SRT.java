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
public class SRT {
    ArrayList<Process> processes;
    ArrayList<Process> ganttBar;
    //System.out.println ("enter no of process:");
    int n;
    int pid[]; // it takes pid of process
    int at[]; // at means arrival time
    int bt[]; // bt means burst time
    int ct[]; // ct means complete time
    int ta[]; // ta means turn around time
    int wt[]; // wt means waiting time
    int f[]; // f means it is flag it checks process is completed or not
    int k[]; // it is also stores brust time
    int st, tot;
    float avgwt, avgta;
    //Scanner sc=new Scanner(System.in);

    public SRT(int burstTime[], int arrivalTime[]) {
        processes = new ArrayList<>();
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
        avgta = 0;
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
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
            0,
            2,
            1
        };
        int berst[] = {
            23,
            32,
            11
        };
        SRT tes = new SRT(berst, araybal);

    }

    void process() {

        while (true) {
            int min = 99, c = n;
            if (tot == n)
                break;

            for (int i = 0; i < n; i++) {
                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                    min = bt[i];
                    c = i;
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
        }

        for (int i = 0; i < n; i++) {
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - k[i];
            avgwt += wt[i];
            avgta += ta[i];
        }

        System.out.println("pid  arrival  burst  complete turn waiting");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + k[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
        }

        System.out.println("\naverage tat is " + (float)(avgta / n));
        System.out.println("average wt is " + (float)(avgwt / n));
    }
}