import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        // charAt으로 순회
        // stack 여는 괄호가 나오면 스택에 담기
        //  닫는 괄호가 나오면 스택에 젤 위의 값과 합쳐지는지 확인 후 지우기
        // 짝이 맞지 않다면 no
        // 짝이 맞다면 지우기
        // 모든 문자열 순회 후 스택이 비어으면 yes 아니면 no

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        StringBuffer sb = new StringBuffer();
        while (!(s = br.readLine()).equals(".")) {
            Stack<Character> balance = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '[') {
                    balance.push(c);
                } else if (c == ')') {
                    if (!balance.isEmpty() && balance.peek() == '(') {
                        balance.pop();
                    } else {
                        sb.append("no").append("\n");
                        break;
                    }
                } else if (c == ']') {
                    if (!balance.isEmpty() && balance.peek() == '[') {
                        balance.pop();
                    } else {
                        sb.append("no").append("\n");
                        break;
                    }
                } else if (c == '.') {
                    if (balance.isEmpty()) {
                        sb.append("yes").append("\n");
                    } else {
                        sb.append("no").append("\n");
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
