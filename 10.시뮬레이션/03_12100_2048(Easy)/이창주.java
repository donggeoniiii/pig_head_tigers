import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 방향 정하기 델타 탐색
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    // 맵 크기
    static int N;
    // 맵
    static int[][] map;
    // 복사할 맵
    static int[][] map2;

    // 최대 블록 숫자
    static int max;

    static void plus(int direction) {

        switch (direction) {
            case 0:



                // 하

                for (int i = 0; i < N; i++) {
                    int idx = N - 1;
                    for (int j = N - 1; j >= 0; j--) {
                        if (map2[j][i] > 0) {
                            int temp = map2[j][i];
                            map2[j][i] = 0;
                            map2[idx--][i] = temp;
                        }
                    }
                }

                for (int i = 0; i < N; i++) {
                    for (int j = N - 1; j > 0; j--) {

                        if (map2[j][i] == map2[j - 1][i]) {
                            map2[j][i] *= 2;
                            map2[j - 1][i] = 0;
                        }
                    }
                }

                for (int i = 0; i < N; i++) {
                    int idx = N - 1;
                    for (int j = N - 1; j >= 0; j--) {
                        if (map2[j][i] > 0) {
                            int temp = map2[j][i];
                            map2[j][i] = 0;
                            map2[idx--][i] = temp;
                        }
                    }
                }


                break;
            case 1:

                // 우

                for (int i = 0; i < N; i++) {
                    int idx = N - 1;
                    for (int j = N - 1; j >= 0; j--) {
                        if (map2[i][j] > 0) {
                            int temp = map2[i][j];
                            map2[i][j] = 0;
                            map2[i][idx--] = temp;
                        }
                    }
                }

                for (int i = 0; i < N; i++) {
                    for (int j = N - 1; j > 0; j--) {
                        if (map2[i][j] == map2[i][j - 1]) {
                            map2[i][j] *= 2;
                            map2[i][j - 1] = 0;
                        }
                    }
                }

                for (int i = 0; i < N; i++) {
                    int idx = N - 1;
                    for (int j = N - 1; j >= 0; j--) {
                        if (map2[i][j] > 0) {
                            int temp = map2[i][j];
                            map2[i][j] = 0;
                            map2[i][idx--] = temp;
                        }
                    }
                }

                break;
            case 2:

                // 상


                for (int i = 0; i < N; i++) {
                    int idx = 0;
                    for (int j = 0; j < N; j++) {
                        if (map2[j][i] > 0) {
                            int temp = map2[j][i];
                            map2[j][i] = 0;
                            map2[idx++][i] = temp;
                        }
                    }
                }

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N - 1; j++) {

                        if (map2[j][i] == map2[j + 1][i]) {
                            map2[j][i] *= 2;
                            map2[j + 1][i] = 0;
                        }
                    }
                }

                for (int i = 0; i < N; i++) {
                    int idx = 0;
                    for (int j = 0; j < N; j++) {
                        if (map2[j][i] > 0) {
                            int temp = map2[j][i];
                            map2[j][i] = 0;
                            map2[idx++][i] = temp;
                        }
                    }
                }

                break;
            case 3:


                // 좌

                for (int i = 0; i < N; i++) {
                    int idx = 0;
                    for (int j = 0; j < N; j++) {
                        if (map2[i][j] > 0) {
                            int temp = map2[i][j];
                            map2[i][j] = 0;
                            map2[i][idx++] = temp;
                        }
                    }
                }

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N - 1; j++) {
                        if (map2[i][j] == map2[i][j + 1]) {
                            map2[i][j] *= 2;
                            map2[i][j + 1] = 0;
                        }
                    }
                }

                for (int i = 0; i < N; i++) {
                    int idx = 0;
                    for (int j = 0; j < N; j++) {
                        if (map2[i][j] > 0) {
                            int temp = map2[i][j];
                            map2[i][j] = 0;
                            map2[i][idx++] = temp;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    // 맵복사
    static void dd(int[][] map3, int[][] map2) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map3[i][j] = map2[i][j];
            }
        }
    }

    // 2048 실행
    static void game(int n, int direction) {
        if (n == 5) {

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    max = Math.max(max, map2[i][j]);
                }
            }

            return;
        }
        int[][] map3 = new int[N][N];
        dd(map3, map2);

        // 방향에 따라서 숫자합치기 & 정렬하기

        plus(0);
        game(n + 1, 0);
        dd(map2, map3);


        plus(1);
        game(n + 1, 1);
        dd(map2, map3);


        plus(2);
        game(n + 1, 2);
        dd(map2, map3);


        plus(3);
        game(n + 1, 3);
        dd(map2, map3);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        map2 = new int[N][N];

        max = 2;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                map2[i][j] = map[i][j];
            }
        }
        game(0, -1);
        System.out.println(max);
    }
}