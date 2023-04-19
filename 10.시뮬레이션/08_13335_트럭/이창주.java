import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 트럭의 수
    static int N;
    // 다리의 길이
    static int W;
    // 최대하중
    static int L;
    // 최솟값
    static int min;

    // 트럭 건널 큐
    static Queue<Node> q;

    // 트럭 큐에 사용할 노드
    static class Node {
        // 하중
        int ai;
        // 다리위에서 경과한 시간
        int time;
        // 다리위에 올라갔는지 여부
        boolean onB;

        public Node(int ai, int time, boolean onB) {
            this.ai = ai;
            this.time = time;
            this.onB = onB;
        }
    }

    // 다리건너기
    static void cross() {
        // 다리 건너는 시간
        int tempTime = 0;
        // 다리를 건넌 트럭 숫자
        int crossTruck = 0;
        // 다리 위에 올라간 트럭의 하중
        int wB = 0;

        // 다리를 다 건널때 까지
        while (crossTruck < N) {
            tempTime++;
            // 최솟값 갱신 못하면 더이상 탐색하지 않음
            if (tempTime >= min) return;
            int size = q.size();

            boolean con = false;
            for (int i = 0; i < size; i++) {

                Node node = q.poll();
                if(con) {
                    q.offer(node);
                    continue;
                }
                if (node.time == W) {
                    crossTruck++;
                    wB -= node.ai;
                    continue;
                } else if (node.onB) node.time++;
                else {
                    if (wB + node.ai <= L) {
                        node.onB = true;
                        node.time++;
                        wB += node.ai;
                    }
                    con = true;
                }
                q.offer(node);
            }
        }
        min = tempTime;
    }



    // 메인
    public static void main(String[] args) throws IOException {

//        입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
//        배열,큐, 최솟값 초기화
        min = Integer.MAX_VALUE;
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            q.offer(new Node(Integer.parseInt(st2.nextToken()), 0, false));
        }
        cross();
//      최솟값 출력
        System.out.println(min);
    }
}