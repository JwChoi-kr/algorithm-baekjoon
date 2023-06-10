import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        int cnt = 0;

        int lo = 0;
        int hi = 1;

        while (hi <= n) {
            int sum = arr[hi] - arr[lo];

            if (sum < m) {
                hi++;
            } else if (sum > m) {
                lo++;
            } else {
                hi++;
                cnt++;
            }
        }
        
        System.out.println(cnt);
    }
}
