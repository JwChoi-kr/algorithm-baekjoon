import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        int[] dp = new int[n];
        
        int max = 1;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }

            if (dp[i] > max) {
                max = dp[i];
            }
        }

        sb.append(max).append("\n");

        Stack<Integer> s = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == max) {
                max--;
                s.add(arr[i]);
            }
        }

        while (!s.isEmpty()) {
            sb.append(s.pop()).append(" ");
        }

        System.out.println(sb);
    }
}
