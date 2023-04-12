// 균형잡힌 세상

import java.util.Scanner;
import java.util.Stack;

/*
 * 소괄호 중괄호만 stack에 추가
 * stack에 뭔가 남아있으면 균형이 깨진 세상
 */
public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 문자열 입력
		while (true){
			// 한 line씩 확인
			String s = input.nextLine();
			
			// 종료조건 입력
			if (s.equals(".")) break;
			
			// line마다 stack으로 확인
			Stack<Character> stack = new Stack<>();
			
			// line 안에서 문자 하나마다 확인
			for (char word : s.toCharArray()) {
				// 괄호만 입력받기
				if (word == '(' || word == ')' || word == '[' || word == ']') {
					// 비어있으면 입력
					if (stack.isEmpty()) stack.push(word);
					// top에 괄호가 있을 때
					else {
						// 소괄호가 닫히면 pop
						if (stack.peek() == '(' && word == ')') {
							stack.pop();
						}
						// 중괄호가 닫히면 pop
						else if (stack.peek() == '[' && word == ']') {
							stack.pop();
						}
						// 아니면 추가
						else stack.push(word);
					}
				}
				// 괄호가 아니면 넘기기
				else continue;
			}
			
			// 정답 출력
			if (stack.isEmpty()) System.out.println("yes");
			else System.out.println("no");
		}

	}
		
}
