import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int length = 0;
        arr[length++] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            
            if (x > arr[length - 1]) {
                arr[length++] = x;
            } else {
                int lo = 0;
                int hi = length;

                while (lo < hi) {
                    int mid = (lo + hi) / 2;
                    
                    if (arr[mid] < x) {
                        lo = mid + 1;
                    } else {
                        hi = mid;
                    }
                }

                arr[lo] = x;
            }
        }

        System.out.println(length);
    }
}
