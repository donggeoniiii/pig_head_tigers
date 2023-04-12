import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		Stack<Integer> numStack = new Stack<>();
		
		// Testcase 만큼 입력을 받고, contains() 메소드를 통해 명령어 확인
		for (int i = 0; i < T; i++) {
			String s = br.readLine();
			// push인 경우 문자열에서 "push "를 replace() 메소드를 통해 지우고 parseInt로 정수화
			if (s.contains("push")) {
				numStack.push(Integer.parseInt(s.replace("push ", "")));
			}
			// pop인 경우 stack이 비어있는지 isEmpty() 메소드로 확인, 비어있으면 -1 아닌 경우 pop으로 top 출력
			else if (s.contains("pop")) {
				if (numStack.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(numStack.pop());
				}
			} 
			// size인 경우 size() 메소드로 size 출력
			else if (s.contains("size")) {
				System.out.println(numStack.size());
			} 
			// empty인 경우 isEmpty() 메소드로 비어있음 1 아니면 0 출력
			else if (s.contains("empty")) {
				if (numStack.isEmpty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
			} 
			// top인 경우 pop()으로 top에 있는 정수 출력 후 값을 저장해두었다가 다시 add()로 원위치
			else if (s.contains("top")) {
				if (numStack.isEmpty()) {
					System.out.println(-1);
				} else {
					int a = numStack.pop();
					numStack.add(a);
					System.out.println(a);
				}
			}
		}
        br.close();
	}
}
