import java.io.*;
import java.util.StringTokenizer;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            int ans = 0;

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {  // 처리한 적 없는 i에 대해서만
                    Stack<Integer> s = new Stack<>();
                    s.add(i);
                    visited[i] = true;

                    while (true) {
                        if (!visited[arr[s.peek()]]) {  // 방문한 곳을 못찾으면 계속 돈다.
                            visited[arr[s.peek()]] = true;
                            s.add(arr[s.peek()]);
                        } else {  // 방문한 곳을 찾았다!
                            if (s.firstElement() == arr[s.peek()]) {  // 조가 딱 완성
                                break;
                            } else {
                                int temp = arr[s.peek()];
                                int size = s.size();

                                while (true) {
                                    if (s.isEmpty()) {  // 조가 아예 안생김
                                        ans += size;
                                        break;
                                    }

                                    if (s.pop() == temp) {  // 조가 만들어지고 나머지는 떨궈짐
                                        ans += s.size();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}
