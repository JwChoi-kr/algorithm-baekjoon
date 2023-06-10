import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int[][] arr = new int[a.length + 1][b.length + 1];
        
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                } else {
                    arr[i][j] = Math.max(arr[i][j - 1], arr[i - 1][j]);
                }
            }
        }
        
        int xcor = a.length, ycor = b.length;

        while (true) {
            if (a[xcor - 1] == b[ycor - 1]) {
                sb.append(a[xcor - 1]);
                xcor--;
                ycor--;
            } else {
                if (arr[xcor - 1][ycor] > arr[xcor][ycor - 1]) {
                    xcor--;
                } else {
                    ycor--;
                }
            }

            if (arr[xcor][ycor] == 0) {
                break;
            }
        }


        System.out.println(arr[a.length][b.length]);
        System.out.println(sb.length() > 0 ? sb.reverse() : "");
    }
}
