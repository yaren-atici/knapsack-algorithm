import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Result {
        int maxBenefit;
        List<Integer> selectedItems;

        Result(int maxBenefit, List<Integer> selectedItems) {
            this.maxBenefit = maxBenefit;
            this.selectedItems = selectedItems;
        }
    }

    public static Result knapsack(int[] weights, int[] benefits, int W) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            dp[i - 1][w - weights[i - 1]] + benefits[i - 1]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        List<Integer> selectedItems = new ArrayList<>();
        int w = W;

        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selectedItems.add(i);
                w -= weights[i - 1];
            }
        }

        return new Result(dp[n][W], selectedItems);
    }

    public static void main(String[] args) {

        // SET 1
        int[] weights1 = {2, 3, 4, 5};
        int[] benefits1 = {3, 4, 5, 6};
        int W1 = 5;

        Result r1 = knapsack(weights1, benefits1, W1);
        System.out.println("SET 1");
        System.out.println("b_max = " + r1.maxBenefit);
        System.out.println("Selected items: " + r1.selectedItems);
        System.out.println();

        // SET 2
        int[] weights2 = {2, 3, 4, 5, 6};
        int[] benefits2 = {3, 4, 5, 6, 7};
        int W2 = 7;

        Result r2 = knapsack(weights2, benefits2, W2);
        System.out.println("SET 2");
        System.out.println("b_max = " + r2.maxBenefit);
        System.out.println("Selected items: " + r2.selectedItems);
    }
}
