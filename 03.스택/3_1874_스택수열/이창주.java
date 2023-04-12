import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int index = 1;
        int nums =0;
        for (int i = 0; i < n; i++) {
            nums = Integer.parseInt(br.readLine());
            while (index <= nums) {
                stack.push(index);
                sb.append("+\n");
                index++;
            }
            if(stack.pop() == nums) {
                sb.append("-\n");
            } else {
                System.out.println("NO");
                return;
            }
        }
        System.out.println(sb);
    }
}
