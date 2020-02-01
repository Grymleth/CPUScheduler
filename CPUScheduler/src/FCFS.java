import java.util.ArrayList;
import java.util.Collections;

public class FCFS {
    ArrayList < Process > processes;
    private float averageWaitingTIme = 0;
    private float averageTurnAroundTime = 0;

    public FCFS(int[] burst, int[] arrival) {
        int n = burst.length;
        processes = new ArrayList<>();
        int processNumber[] = new int[n]; // process ids
        int arrivalTime[] = arrival; // arrival times
        int burstTime[] = burst; // burst or execution times
        int completionTime[] = new int[n]; // completion times
        int turnAroundTime[] = new int[n]; // turn around times
        int waitingTime[] = new int[n]; // waiting times 
        int swap;
        

        for(int i=0;i<n;i++){
            processNumber[i]=i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (arrivalTime[j] > arrivalTime[j + 1]) {
                    swap = arrivalTime[j];
                    arrivalTime[j] = arrivalTime[j + 1];
                    arrivalTime[j + 1] = swap;
                    swap = burstTime[j];
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = swap;
                    swap = processNumber[j];
                    processNumber[j] = processNumber[j + 1];
                    processNumber[j + 1] = swap;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                completionTime[i] = arrivalTime[i] + burstTime[i];
            } else {
                if (arrivalTime[i] > completionTime[i - 1]) {
                    completionTime[i] = arrivalTime[i] + burstTime[i];
                } else
                    completionTime[i] = completionTime[i - 1] + burstTime[i];
            }
            turnAroundTime[i] = completionTime[i] - arrivalTime[i]; // turnaround time= completion time- arrival time
            waitingTime[i] = turnAroundTime[i] - burstTime[i]; // waiting time= turnaround time- burst time
            averageWaitingTIme += waitingTime[i]; // total waiting time
            averageTurnAroundTime += turnAroundTime[i]; // total turnaround time
            
            processes.add(new Process(burst[i], arrival[i], processNumber[i]));
            processes.get(i).setCompletion(completionTime[i]);
            processes.get(i).setTurnaround(turnAroundTime[i]);
            processes.get(i).setWaiting(waitingTime[i]);
          
        }
        
        Collections.sort(processes, new SortByID());

    }

}