import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n + 1][m + 1];
        
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n + 1][m + 1];
        int[][] track = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k <= i; k++) {
                    if (dp[i][j] < dp[i - k][j - 1] + arr[k][j]) {
                        dp[i][j] = dp[i - k][j - 1] + arr[k][j];
                        track[i][j] = k;
                    }
                }
            }
        }

        sb.append(dp[n][m]).append("\n");

        int[] investment = new int[m + 1];
        
        int h = n;
        for (int i = m; i > 0; i--) {
            investment[i] = track[h][i];
            h -= track[h][i];
        }

        for (int i = 1; i <= m; i++) {
            sb.append(investment[i] + " ");
        }

        System.out.println(sb);
    }
}
