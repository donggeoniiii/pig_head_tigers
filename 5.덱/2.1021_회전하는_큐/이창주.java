import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            deque.offer(i);
        }
        int cnt = 0;

        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st2.nextToken());
            int index = 0;
            while (deque.getFirst() != target) {
                index++;
                deque.offer(deque.poll());
            }
            if (index > deque.size()/2) {
                cnt += deque.size() - index;
            } else cnt += index;
            deque.pollFirst();
        }

        System.out.println(cnt);
    }
}