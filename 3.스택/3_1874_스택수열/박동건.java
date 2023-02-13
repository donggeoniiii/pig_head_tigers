package BOJ1874;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/*
 * stack을 이용해 임의의 수열을 만드는 데 걸리는 연산의 수
 * push, pop이 얼마나 일어나는 지 계산
 * 같은 수는 나오지 않음, 입럭이 n일 경우 무조건 1부터 n까지의 수가 한번씩 등장
 * 
 * 한 턴에 하는 일: 
 * 1. num push, "+" 추가
 * 2. 배열과 같은지 확인, 같을 시 peek()으로 본 top과 배열의 index값이 같은지 확인
 * 3. 같으면 pop & "-" 추가 후 index++, 다르면 불가능하므로 "No" 출력 후 종료
 * 4. num++
 * 
 * push와 pop이 N번씩 일어나면 종료
 */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		
		int N = Integer.parseInt(br.readLine());
		int[] numArr = new int[N];
		
		// 만들려는 배열 입력받기
		for (int test_case = 0; test_case < N; test_case++) {
			numArr[test_case] = Integer.parseInt(br.readLine());
		}
		
		// 부호 입력할 string 배열 생성
		int num = 1, idx = 0;
		ArrayList<String> answer = new ArrayList<>();
		
		while (true) {
			// 1. num push, "+" 추가
			stack.add(num);
			answer.add("+");
			
			// 2. 배열과 같은지 확인, 같을 시 peek()으로 본 top과 배열의 index값이 같은지 확인
			if (num == numArr[idx]) {
				
				int top = stack.peek();
				
				// 같으면 pop & "-" 추가 후 index++
				if (numArr[idx] == top) {
					stack.pop();
					answer.add("-");
					idx++;
				}
				
				// 다르면 불가능하므로 "No" 출력 후 종료
				else {
					break;
				}
				
			}

			// push와 pop이 각각 N번 일어나면 끝난 것이므로 while loop 탈출
			if (answer.size() == 2*N) break;

			num++;
		}

		// pop한 횟수가 N이라면 배열을 완성한 것이므로 출력
		if (idx == N) {
			for (String sign : answer) 
				System.out.println(sign);
		} else {
			System.out.println("NO");
		}
		
	}
}