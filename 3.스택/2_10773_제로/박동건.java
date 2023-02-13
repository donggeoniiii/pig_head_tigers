import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			// input 입력받기
			int num = Integer.parseInt(br.readLine());
			// 입력받은 수가  0이 아니면 stack에 추가
			if (num != 0) {
				stack.add(num);
			// 0일 경우 최근값 pop으로 제거
			} else {
				stack.pop();
			}
		}
		
		// 정답 입력을 위한 변수 생성
		int answer = 0;
		
		// stack에 남아있는 숫자 총합 구하기
		for (int num : stack) {
			answer += num;
		}
		
		// 정답 출력 
		System.out.println(answer);
	}
}
