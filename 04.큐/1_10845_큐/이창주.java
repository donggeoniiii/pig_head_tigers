import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int MX = 10001;
    public static int[] queue = new int[MX];

    public static int head = 0, tail = 0;

    public static void push(int x) {
        queue[tail++] = x;
    }

    public static void  pop() {
        head++;
    }

    public static int front() {

        return queue[head];
    }

    public static int back() {

        return queue[tail-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {

                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;

                case "pop" :
                    if (head == tail) {
                        sb.append("-1");
                    } else {
                        sb.append(front());
                        pop();
                    }
                    sb.append("\n");
                    break;

                case "size" :
                    sb.append(tail - head).append("\n");
                    break;

                case "empty":
                    if(tail-head  == 0) sb.append("1");
                    else sb.append("0");
                    sb.append("\n");
                    break;

                case "front":

                    if (tail == head) sb.append("-1");
                    else sb.append(front());
                    sb.append("\n");
                    break;

                case "back":
                    if(tail == head) sb.append("-1");
                    else sb.append(back());
                    sb.append("\n");

                    break;
                default:
                    break;
            }
        }
        System.out.println(sb);
    }
}
