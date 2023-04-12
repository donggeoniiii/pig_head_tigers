import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        // 제일위에 있는 카드를 버린다. -> pop 하나
        // 그 다음카드 제일 아래로 -> pop한 것을 push
        // N이 주어지고 queue에 1~N삽입
        // 마지막 남은 카드 한장
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        while (queue.size() > 1) {
            queue.remove();
            queue.add(queue.remove());
        }

        System.out.println(queue.remove());

    }
}
