import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        int[] button = new int[n];
        int[] light = new int[n];
        int[] light2 = new int[n];
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            button[Integer.parseInt(st.nextToken()) - 1] = i;
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            light[temp - 1] = i;
            light2[i] = temp;
        }

        for (int i = 0; i < n; i++) {
            arr[button[i]] = light[i];
        }

        int[] turnOn = new int[n];
        int[] track = new int[n];
        
        turnOn[0] = arr[0];
        int length = 1;

        for (int i = 1; i < n; i++) {
            if (turnOn[length - 1] < arr[i]) {
                turnOn[length] = arr[i];
                track[i] = length;
                length++;
            } else {
                int lo = 0;
                int hi = length;

                while (lo < hi) {
                    int mid = (lo + hi) / 2;

                    if (turnOn[mid] < arr[i]) {
                        lo = mid + 1;
                    } else {
                        hi = mid;
                    }
                }

                turnOn[lo] = arr[i];
                track[i] = lo;
            }
        }
        
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] != turnOn[track[i]]) {
                if (turnOn[track[i] + 1] > arr[i]) {
                    turnOn[track[i]] = arr[i];
                }
            }
        }

        sb.append(length).append("\n");
        
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            temp[i] = light2[turnOn[i]];
        }
        Arrays.sort(temp);

        for (int i : temp) {
            sb.append(i + " ");
        }

        System.out.println(sb);
    }
}
