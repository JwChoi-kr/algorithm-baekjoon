import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        arr[0] = Integer.parseInt(st.nextToken());
        int length = 0;

        for (int i = 1; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            
            if (x > arr[length]) {
                arr[++length] = x;
            } else if (x < arr[length]) {
                int lo = 0;
                int hi = length + 1;

                while (lo < hi) {
                    int mid = (lo + hi) / 2;
                    
                    if (arr[mid] <= x) {
                        lo = mid + 1;
                    } else {
                        hi = mid;
                    }
                }

                if (lo == 0 || lo > 0 && arr[lo - 1] < x) {
                    arr[lo] = x;
                }
            }
        }

        System.out.println(length + 1);
    }
}
