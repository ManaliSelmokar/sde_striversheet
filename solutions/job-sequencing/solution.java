import java.util.*;

// A class representing a Job with id, deadline, and profit
class Job {
    // Job Id
    int id;    
    // Deadline of job
    int dead;  
    // Profit if job is completed before or on deadline
    int profit; 
}

class Solution {

    // Comparator function to sort jobs by profit in decreasing order
    public static boolean comparison(Job a, Job b) {
        // Return true if a's profit is greater than b's
        return a.profit > b.profit; 
    }

    // Function to find the maximum profit and the number of jobs done
    public static Pair<Integer, Integer> JobScheduling(Job[] arr, int n) {

        // Sort the jobs by profit in descending order
        Arrays.sort(arr, (a, b) -> b.profit - a.profit);

        // Find the maximum deadline among all jobs
        int maxi = arr[0].dead;
        for (int i = 1; i < n; i++) {
            // Find the latest deadline
            maxi = Math.max(maxi, arr[i].dead); 
        }

        // Create an array to store the slots for the jobs
        int[] slot = new int[maxi + 1];
        // Initialize all slots as unoccupied
        Arrays.fill(slot, -1);  

        int countJobs = 0, jobProfit = 0;

        // Try to assign jobs to the slots
        for (int i = 0; i < n; i++) {

            // Find a slot for the current job, starting from the job's deadline
            for (int j = arr[i].dead; j > 0; j--) {
                // If the slot is available
                if (slot[j] == -1) {  
                // Assign the job to the slot
                    slot[j] = i;  
                    // Increment the job count
                    countJobs++;   
                    // Add the profit of the job
                    jobProfit += arr[i].profit;  
                    break;
                }
            }
        }

        // Return the number of jobs done and total profit
        return new Pair<>(countJobs, jobProfit);
    }
}

public class Main {

    public static void main(String[] args) {

        // Driver Code
        int n = 4;

        // Define the edges (source, destination, weight)
        Job[] arr = new Job[] {
            new Job() {{id = 1; dead = 4; profit = 20;}},
            new Job() {{id = 2; dead = 1; profit = 10;}},
            new Job() {{id = 3; dead = 2; profit = 40;}},
            new Job() {{id = 4; dead = 2; profit = 30;}}
        };

        // Call the JobScheduling function
        Pair<Integer, Integer> ans = Solution.JobScheduling(arr, n);

        // Output the result
        System.out.println(ans.getKey() + " " + ans.getValue());
    }
}
