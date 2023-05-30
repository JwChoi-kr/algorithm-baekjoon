import java.io.*;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int r, c;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[][] map = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (true) {
            fillAir(map);

            int[][] newMap = meltCheese(map);
            time++;
            
            if (!isExist(newMap)) {
                sb.append(time);
                sb.append("\n");
                sb.append(countCheese(map));
                break;
            }

            map = newMap;
        }

        System.out.println(sb);
    }

    static void fillAir(int[][] map) {
        buildWall(map);
        
        Queue<Spot> q = new LinkedList<>();
        boolean[][] visited = new boolean[r][c];
        
        for (int i = 1; i < r - 1; i++) {
            for (int j = 1; j < c - 1; j++) {
                if (map[i][j] == 0 && (i == 1 || j == 1 || i == r - 2 || j == c - 2)) {
                    q.add(new Spot(i, j));
                    visited[i][j] = true;
                    map[i][j] = 2;
                }
            }
        }

        while (!q.isEmpty()) {
            Spot s = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = s.x + dx[i];
                int ny = s.y + dy[i];
                
                if (nx < 1 || ny < 1 || nx > r - 2 || ny > c - 2) {
                    continue;
                }
                
                if (map[nx][ny] == 0 && !visited[nx][ny]) {
                    q.add(new Spot(nx, ny));
                    visited[nx][ny] = true;
                    map[nx][ny] = 2;
                }
            }
        }
    }

    static void buildWall(int[][] map) {
        for (int i = 0; i < r; i++) {
            map[i][0] = map[i][c - 1] = 2;
        }

        for (int i = 1; i < c - 1; i++) {
            map[0][i] = map[r - 1][i] = 2;
        }
    }
    
    static int[][] meltCheese(int[][] map) {
        int[][] newMap = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        
                        if (nx < 0 || ny < 0 || nx > r - 1 || ny > c - 1) {
                            continue;
                        }
                        
                        if (map[nx][ny] == 2) {
                            newMap[i][j] = 0;
                            map[i][j] = 3;
                            break;
                        } else {
                            newMap[i][j] = 1;
                        }
                    }
                }
            }
        }

        return newMap;
    }

    static boolean isExist(int[][] map) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    static int countCheese(int[][] map) {
        int cnt = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 3) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static void debug(int[][] map) {
        for (int i = 0; i < r; i++) {
            System.out.println();
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
        System.out.println();
    }
}

class Spot {
    int x, y;

    Spot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
