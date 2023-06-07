import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] length = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            length[i] = 1;

            for (int j = 0; j <= i; j++) {
                if (arr[j] < arr[i]) {
                    length[i] = Math.max(length[j] + 1, length[i]);
                }
            }

            max = Math.max(max, length[i]);
        }

        System.out.println(max);
    }
}
