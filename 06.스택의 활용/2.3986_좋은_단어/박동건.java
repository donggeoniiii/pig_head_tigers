// 좋은 단어

import java.util.Scanner;
import java.util.Stack;

/*
 * 선끼리 교차하지 않는다 == 괄호와 유사
 * 이번엔 같은 것끼리 만나면 pop이 되게
 * 만약 stack에 다 넣었는데 stack에 뭐가 남았다면 나쁜 단어
 */

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Stack<String> stack;
		// 단어의 수
		int N = Integer.parseInt(input.nextLine());
		
		// 착한 단어 카운트
		int cnt = 0;
		
		for (int tc = 1; tc <= N; tc++) {
			// 문자열 입력받아서 하나씩 쪼개기
			String[] words = input.nextLine().split("");
			stack = new Stack<>();
			
			for (String word : words) {
				
				// 비어있으면 추가
				if (stack.isEmpty()) {
					stack.push(word);
				}
				
				else {
					// 만약 top과 들어오는 단어가 같다면 pop
					if (stack.peek().equals(word)) stack.pop();
					// 아니면 그냥 추가
					else stack.push(word);
				}
			}
			
			
			// 만약 stack이 비면 착한 단어
			if (stack.isEmpty()) cnt++;
		}
		
		// 정답 출력
		System.out.println(cnt);
		
	}
}
