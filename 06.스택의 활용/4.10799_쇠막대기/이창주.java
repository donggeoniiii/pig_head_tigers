import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        // 쇠막대기 자르기
        // 괄호가 열리면 +1 닫히면 -1
        // 레이저 이전 괄호의 개수 만큼 + n개

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int cnt = 0;
        int result = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                result++;
                cnt++;
            } else {
                cnt--;
                if (stack.peek() == '(') {
                    result--;
                    result += cnt;
                }
            }
            stack.push(c);
        }
        System.out.println(result);
    }
}
