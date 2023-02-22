import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 창고에 보관되는 토마토 중 잘 익지 않은 것
    // 익은 토마토 가로세로 영향
    // 검사
    // 토마토가 다 익어 있다면 0
    // 토마토가 익지 못하는 상황 -1
    // 토마토가 익지 못하는 상황이면
    // 익은 토마토가 영향을 끼치는 숫자가 0
    // 익은 토마토 숫자가 변화가 없으면
    // 토마토가 익지 못하는 것

    // 1번째 수행 때 완전탐색
    // 토마토 변화 가로 세로
    // 두 번째 가로 세로
    // 완전 탐색해서 큐에다가 미리 다 넣어두고 시작
    // {좌표, 며칠걸렸는지}
    // 큐가 다 비워졌는데, 익은 토마토 숫자가 == 맞지 않으면
    // return -1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // 토마토 배열
        int[][] tomato = new int[N][M];
        // 가로세로 이동 dx dy 배열
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        // 큐에
        Queue<int[]> queue = new LinkedList<>();
        int max = 0;
        int cnt = 0;
        int all = M * N;
        // 배열입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                // 배열에서 토마토면 큐에 좌표 값 입력, 날짜배열에 0 입력 토마토 cnt ++;
                String s = st2.nextToken();
                if (s.equals("1")) {
                    tomato[i][j] = 1;
                    queue.offer(new int[]{i, j, 0});
                    cnt++;
                } else if (s.equals("-1")) {
                    tomato[i][j] = -1;
                    all--;
                }

            }
        }

        // 큐가 비워질 때 까지 반복
        while (!queue.isEmpty()) {

            // 좌표 빼고, dx,dy 이동 반복문
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = poll[0] + dx[i];
                int y = poll[1] + dy[i];
                // 인덱스 이내에 있고, 날짜 -1 이고, 안익은 토마토라면
                if(x <0 || x >=N || y <0 || y >= M) continue;
                if(tomato[x][y] != 0) continue;
                // 토마토 익은 거로 바꾸고 큐에 넣기 날짜 +1 토마토 cnt ++
                queue.offer(new int[]{x, y, poll[2] + 1});
                tomato[x][y] = 1;
                cnt++;
                // max 보다 날짜가 크면 max 바꿔주기
                max = Math.max(poll[2] + 1, max);
            }
        }

        if (all == cnt) {
            System.out.println(max);
        } else System.out.println(-1);
        // M*N == cnt return max
        // else return -1
    }
}