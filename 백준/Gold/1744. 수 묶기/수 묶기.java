import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();
        int oneCount = 0;
        boolean haveZero = false;

        int sum = 0;
        
        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(br.readLine());
            
            if (k > 1) {
                plus.add(k);
            } else if (k == 1) {
                oneCount++;
            } else if (k < 0) {
                minus.add(k);
            } else {
                haveZero = true;
            }
        }

        Collections.sort(plus);
        Collections.sort(minus);

        sum += oneCount;

        for (int i = plus.size() - 1; i > 0; i -= 2) {
            sum += plus.get(i) * plus.get(i - 1);
        }
        if (plus.size() % 2 == 1) {
            sum += plus.get(0);
        }

        for (int i = 0; i < minus.size() - 1; i += 2) {
            sum += minus.get(i) * minus.get(i + 1);
        }
        if (minus.size() % 2 != 0 && !haveZero) {
            sum += minus.get(minus.size() - 1);
        }

        System.out.println(sum);
    }
}
