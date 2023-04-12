import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
    정수 저장 스택 구현
    명령 처리
    명령 처리 스위치문으로 처리
    or 함수
 */

public class Main {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 정수 저장 자료구조 스택
        Stack<Integer> stack = new Stack<>();

        // 명령의 수
        int n = Integer.parseInt(br.readLine());
        
        // 출력
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push":
                    stack.push(Integer.parseInt(st.nextToken()));
                    break;

                case "top":
                    if (stack.isEmpty()) sb.append("-1").append("\n");
                    else sb.append(stack.peek()).append("\n");
                    break;

                case "pop":
                    if(stack.isEmpty()) sb.append("-1").append("\n");
                    else sb.append(stack.pop()).append("\n");
                    break;

                case "size" :
                    sb.append(stack.size()).append("\n");
                    break;

                case "empty":
                    if (stack.isEmpty()) {
                        sb.append("1").append("\n");
                    } else {
                        sb.append("0").append("\n");
                    }
                    break;

                default : break;
            }

        }

        System.out.println(sb);

    }
}
