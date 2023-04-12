import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Character> stack = new Stack<>();
        int sum = 0;
        int num = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                num *= 2;
                stack.push(c);
            } else if (c == '[') {
                num *= 3;
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    System.out.println(0);
                    return;
                }
                if(s.charAt(i - 1) == '(') sum += num;
                stack.pop();
                num /= 2;
            } else {
                if (stack.isEmpty() || stack.peek() != '[') {
                    System.out.println(0);
                    return;
                }
                if (s.charAt(i - 1) == '[') {

                    sum += num;
                }
                stack.pop();
                num /= 3;
            }
        }
        if(stack.isEmpty()) System.out.println(sum);
        else System.out.println(0);
    }
}
