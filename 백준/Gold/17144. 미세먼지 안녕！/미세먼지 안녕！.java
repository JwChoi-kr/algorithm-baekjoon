import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int r, c, t;
    static int cleaner;

    static int[][] room;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        room = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < c; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());    
                if (room[i][j] == -1) {
                    cleaner = i;    // cleaner의 아래 쪽 행의 좌표가 저장됨.
                }
            }
        }

        while (t-- > 0) {
            room = spreadDust();
            blowWind(true);
            blowWind(false);
        }

        System.out.println(countDust());
    }

    static int[][] spreadDust() {
        int[][] newRoom = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (room[i][j] >= 5) {
                    int amount = room[i][j] / 5;

                    for (int k = 0; k < 4; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];

                        if (ni < 0 || nj < 0 || ni > r - 1 || nj > c - 1) {
                            continue;
                        }
                        if (room[ni][nj] == -1) {
                            continue;
                        }

                        newRoom[ni][nj] += amount;
                        room[i][j] -= amount;
                    }

                    newRoom[i][j] += room[i][j];
                } else if (0 < room[i][j] && room[i][j] < 5) {
                    newRoom[i][j] += room[i][j];
                } else if (room[i][j] == -1) {
                    newRoom[i][j] = -1;
                }
            }
        }

        return newRoom;
    }
    
    static void blowWind(boolean isUp) {
        int x = isUp ? cleaner - 2 : cleaner + 1;
        int y = 0;
        int d = 0;

        while (true) {
            int nx = x + dx[isUp ? d : (6 - d) % 4];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx > r - 1 || ny > c - 1 || (isUp && nx == cleaner) || (!isUp && nx == cleaner - 1)) {
                d++;
                continue;
            }

            if (room[nx][ny] == -1) {
                room[x][y] = 0;
                break;
            }

            room[x][y] = room[nx][ny];
            x = nx;
            y = ny;
        }
    }

    static int countDust() {
        int sum = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (room[i][j] > 0) {
                    sum += room[i][j];
                }
            }
        }

        return sum;
    }
}
