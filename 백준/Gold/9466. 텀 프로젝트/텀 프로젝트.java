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
                    visited[i] = true;
                    if (i != arr[i]) {  // 자기자신을 가리키는 경우는 빼고
                        Stack<Integer> s = new Stack<>();
                        s.add(i);

                        while (true) {
                            if (!visited[arr[s.peek()]]) {  // 방문한 곳을 못찾으면 계속 돈다.
                                visited[arr[s.peek()]] = true;
                                s.add(arr[s.peek()]);
                            } else {
                                if (s.firstElement() == arr[s.peek()]) {  // 조가 딱 완성
                                    break;
                                } else {
                                    if (!s.contains(arr[s.peek()])) {
                                        ans += s.size();  // 아무것도 안이어짐
                                    } else {
                                        int temp = arr[s.peek()];
                                        while (true) {
                                            if (s.pop() == temp) {
                                                ans += s.size();
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
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
