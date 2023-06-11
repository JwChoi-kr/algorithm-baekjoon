import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, r, c;
    static int idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        partition(0, 0, n);
    }

    static void partition(int row, int col, int size) {
        if (row == r && col == c) {
            System.out.println(idx);
            System.exit(0);
        }

        if (size == 1) {
            return;
        }
            
        size = size / 2;

        if (search(row, col, size)) {
            idx += (int) Math.pow(size, 2) * 0;
            partition(row, col, size);
        } else if (search(row, col + size, size)) {
            idx += (int) Math.pow(size, 2) * 1;
            partition(row, col + size, size);
        } else if (search(row + size, col, size)) {
            idx += (int) Math.pow(size, 2) * 2;
            partition(row + size, col, size);
        } else if (search(row + size, col + size, size)) {
            idx += (int) Math.pow(size, 2) * 3;
            partition(row + size, col + size, size);
        }
    }

    static boolean search(int row, int col, int size) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (i == r && j == c) {
                    return true;
                }
            }
        }

        return false;
    }
}
