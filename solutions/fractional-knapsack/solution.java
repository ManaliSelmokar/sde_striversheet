import java.util.Arrays;

class Solution {
    // Helper class to represent an item
    static class Item {
        int value;
        int weight;

        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    public double fractionalKnapsack(int w, Item[] arr, int n) {
        // Sort items in descending order of value-to-weight ratio
        Arrays.sort(arr, (a, b) -> {
            double r1 = (double) a.value / a.weight;
            double r2 = (double) b.value / b.weight;
            return Double.compare(r2, r1);
        });

        int currentWeight = 0;
        double totalValue = 0.0;

        for (int i = 0; i < n; i++) {
            // If full item can fit in the knapsack
            if (currentWeight + arr[i].weight <= w) {
                currentWeight += arr[i].weight;
                totalValue += arr[i].value;
            } else {
                // Take the fractional part of the item
                int remainingWeight = w - currentWeight;
                totalValue += ((double) arr[i].value / arr[i].weight) * remainingWeight;
                break; // Knapsack is full
            }
        }

        return totalValue;
    }
}
