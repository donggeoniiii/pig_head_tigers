import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < T; i++) {
            String s = br.readLine();
            int num = Integer.parseInt(br.readLine());
            Deque<Integer> deque = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");

            for (int j = 0; j < num; j++) {
                deque.offer(Integer.parseInt(st.nextToken()));
            }
            boolean is_R = false;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'R') {
                    // 뒤에서 빼서 제일 앞으로
                    is_R = !is_R;
                } else {
                    if (deque.size() == 0) {
                        sb.append("error").append("\n");
                        break;
                    } else if (is_R) {
                        deque.pollLast();
                    } else deque.pollFirst();
                    
                }
                if (j == s.length() - 1) {

                    int size = deque.size();
                    if (size == 0) {
                        sb.append("[]").append("\n");
                    } else if (is_R) {
                        sb.append("[");
                        for (int k = 0; k < size; k++) {
                            sb.append(deque.pollLast()).append(",");
                        }

                        sb.deleteCharAt(sb.length() - 1);
                        sb.append("]").append("\n");

                    } else {
                        sb.append("[");
                        for (int k = 0; k < size; k++) {
                            sb.append(deque.pollFirst()).append(",");
                        }
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append("]").append("\n");
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
