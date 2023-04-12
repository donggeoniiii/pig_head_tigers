import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // stack

        // 새로운 숫자와 이전에 있던 숫자 비교

        // 새로운 숫자가 이전에 있던 숫자보다 작으면 스택에 쌓인다.

        // 새로운 숫자가 이전에 있던 숫자보다 크면 스택을 pop 한다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> index = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty()) {

                if (num > stack.peek()) {
                    stack.pop();
                    index.pop();

                } else {
                    sb.append(index.peek()).append(" ");
                    break;
                }

            }
            if(stack.isEmpty()) sb.append("0 ");
            stack.push(num);
            index.push(i + 1);
        }
        System.out.println(sb);

    }
}
