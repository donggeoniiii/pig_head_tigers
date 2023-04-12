import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());

        int[] queue = new int[2000001];
        int head = 0;
        int tail = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {

                case "push":
                    queue[tail++] = Integer.parseInt(st.nextToken());
                    break;

                case "pop" :
                    if (head == tail) {
                        sb.append("-1\n");
                    } else {
                        sb.append(queue[head++]).append("\n");

                    }
                    break;

                case "size" :
                    sb.append(tail-head).append("\n");
                    break;

                case "empty":
                    if(head == tail) sb.append("1\n");
                    else sb.append("0\n");
                    break;

                case "front":

                    if (head==tail) sb.append("-1\n");
                    else sb.append(queue[head]).append("\n");
                    break;

                case "back":
                    if(head==tail) sb.append("-1\n");
                    else sb.append(queue[tail-1]).append("\n");
                    break;
                default:
                    break;
            }
        }
        System.out.println(sb);
    }
}
