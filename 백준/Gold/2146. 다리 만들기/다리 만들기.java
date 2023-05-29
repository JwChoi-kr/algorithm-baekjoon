import java.io.*;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int n;

    static int[][] map;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int min = 10000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        continentNaming();
        buildBridge();
        
        System.out.println(min);
        
    }

    static void continentNaming() {
        int continentIndex = 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    Queue<Spot> q = new LinkedList<>();
                    q.add(new Spot(i, j));
                    map[i][j] = continentIndex;

                    while (!q.isEmpty()) {
                        Spot s = q.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = s.x + dx[k];
                            int ny = s.y + dy[k];

                            if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) {
                                continue;
                            }

                            if (map[nx][ny] == 1) {
                                q.add(new Spot(nx, ny));
                                map[nx][ny] = continentIndex;
                            }
                        }
                    }

                    continentIndex++;
                }
            }
        }
    }

    static void buildBridge() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 0) { // 육지라면
                    Queue<Spot> q = new LinkedList<>();
                    int[][] visited = new int[n][n];

                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) {
                            continue;
                        }

                        if (map[nx][ny] == 0) { // 해안선이라면
                            q.add(new Spot(nx, ny)); // q에 해변가를 대입
                            visited[nx][ny] = 1;
                        }
                    }

                    if (!q.isEmpty()) { // q가 비었다는건 해안선이 아니라는 뜻이므로 pass
                        boolean flag = false;

                        while (!flag) {
                            Spot s = q.poll();

                            if (visited[s.x][s.y] >= min) { // 이미 min을 넘었으니 탐색할 필요 X
                                break;
                            }

                            for (int k = 0; k < 4; k++) {
                                int nx = s.x + dx[k];
                                int ny = s.y + dy[k];
        
                                if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) {
                                    continue;
                                }

                                if (map[nx][ny] > 0 && map[nx][ny] != map[i][j]) {
                                    min = Math.min(min, visited[s.x][s.y]);
                                    flag = true;
                                    break;
                                }

                                if (map[nx][ny] == 0 && visited[nx][ny] == 0) {
                                    q.add(new Spot(nx, ny));
                                    visited[nx][ny] = visited[s.x][s.y] + 1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

class Spot {
    int x, y;

    Spot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
