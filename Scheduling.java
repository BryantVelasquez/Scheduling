

import java.util.*;

public class Scheduling {
    public static void main(String[] args) {
        List<Process> procs = Arrays.asList(
                new Process(1, 2),
                new Process(2, 1),
                new Process(3, 8),
                new Process(4, 4),
                new Process(5, 5)
        );

        System.out.println("----------------- FCFS -----------------");
        runFCFS(cloneList(procs));

        System.out.println("\n----------------- SJF -----------------");
        runSJF(cloneList(procs));
    }

   
    private static List<Process> cloneList(List<Process> original) {
        List<Process> copy = new ArrayList<>();
        for (Process p : original) {
            copy.add(new Process(p.id, p.burst));
        }
        return copy;
    }

    private static void runFCFS(List<Process> list) {
        int time = 0;
        int totalWait = 0;

        System.out.println("Process | Waiting Time | Turnaround Time");
        for (Process p : list) {
            p.waiting  = time;
            time      += p.burst;
            p.turnaround = time;
            totalWait += p.waiting;
            System.out.printf("   P%-3d |      %-6d |       %-6d%n",
                    p.id, p.waiting, p.turnaround);
        }

        double avg = (double) totalWait / list.size();
        System.out.printf("Average waiting time = %.2f ms%n", avg);
    }


    private static void runSJF(List<Process> list) {
        list.sort(Comparator.comparingInt(p -> p.burst));
        runFCFS(list);
    }
}


class Process {
    int id;
    int burst;
    int waiting = 0;
    int turnaround = 0;

    Process(int id, int burst) {
        this.id = id;
        this.burst = burst;
    }
}
