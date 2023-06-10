import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        int[] input = new int[n + 1];
        int[] arr = new int[n];
        
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            input[Integer.parseInt(st2.nextToken())] = i;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = input[Integer.parseInt(st1.nextToken())];
        }
        
        int[] lis = new int[n];
        lis[0] = arr[0];
        int length = 1;
        
        for (int i = 1; i < n; i++) {
            if (lis[length - 1] < arr[i]) {
                lis[length] = arr[i];
                length++;
            } else {
                int lo = 0;
                int hi = length;

                while (lo < hi) {
                    int mid = (lo + hi) / 2;
                    
                    if (lis[mid] < arr[i]) {
                        lo = mid + 1;
                    } else {
                        hi = mid;
                    }
                }
                
                lis[lo] = arr[i];
            }
        }

        System.out.println(length);
    }
}
