import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        int min = 100000;

        int lo = 0;
        int hi = 1;

        while (hi <= n) {
            int sum = arr[hi] - arr[lo];

            if (sum < s) {
                hi++;
            } else {
                min = Math.min(min, hi - lo);
                lo++;
            }
        }

        System.out.println(min == 100000 ? 0 : min);
    }
}
