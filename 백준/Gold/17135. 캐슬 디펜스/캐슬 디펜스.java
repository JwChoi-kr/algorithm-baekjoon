import java.io.*;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static int n, m, attackRange;
    static int killCount = 0, maxKillCount = 0;

    static int[][] map, mosterBlueprint;

    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};

    static int MAX_ARCHORS = 3;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        attackRange = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m];
        mosterBlueprint = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                mosterBlueprint[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        placeArchors(0, 0);

        System.out.println(maxKillCount);
    }
    
    static void placeArchors(int archorCount, int startLocation) {
        if (archorCount == MAX_ARCHORS) {
            placeMonster();
            startSimulation();
            return;
        }
        
        for (int i = startLocation; i < m; i++) {
            map[n][i] = 8;
            placeArchors(archorCount + 1, i + 1);
            map[n][i] = 0;
        }
    }

    static void placeMonster() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = mosterBlueprint[i][j];
            }
        }
    }
    
    static void startSimulation() {
        killCount = 0;

        while (isMonsterRemain()) {
            archorPhase();
            eliminateMonsters();
            moveMonsters();
        }
        
        updateMaxKillCount();
    }
        
    static boolean isMonsterRemain() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    return true;
                }
            }
        }
        
        return false;
    }

    static void archorPhase() {
        for (int i = 0; i < m; i++) {
            if (map[n][i] == 8) {
                attackMonsters(i);
            }
        }
    }
    
    static void attackMonsters(int archorLocation) {
        Queue<Spot> q = new LinkedList<>();
        q.add(new Spot(n, archorLocation));

        int[][] visited = new int[n + 1][m];
        visited[n][archorLocation] = 1;

        while (!q.isEmpty()) {
            Spot s = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = s.x + dx[i];
                int ny = s.y + dy[i];

                if (nx < 0 || ny < 0 || nx > n - 1 || ny > m - 1) { // 처음 화살은 무조건 위로만 쏘도록 한다.
                    continue;
                }

                if (map[nx][ny] >= 1) { // 적을 만나면 공격
                    map[nx][ny]++;
                    return;
                }

                if (visited[nx][ny] == 0 && visited[s.x][s.y] < attackRange) {
                    q.add(new Spot(nx, ny));
                    visited[nx][ny] = visited[s.x][s.y] + 1;
                }
            }
        }
    }

    static void eliminateMonsters() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 1) {
                    map[i][j] = 0;
                    killCount++;
                }
            }
        }
    }

    static void moveMonsters() {
        for (int i = n - 2; i >= 0; i--) {  // 몬스터 이동
            for (int j = 0; j < m; j++) {
                map[i + 1][j] = map[i][j];
            }
        }
        
        for (int i = 0; i < m; i++) { // 맨 윗줄 몬스터 제거
            map[0][i] = 0;
        }
    }


    static void updateMaxKillCount() {
        maxKillCount = Math.max(maxKillCount, killCount);
    }
}

class Spot {
    int x, y;

    Spot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
