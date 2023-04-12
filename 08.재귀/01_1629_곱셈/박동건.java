// 곱셈

import java.util.Scanner;

public class Main {
	// 곱하려는 수, 곱하는 횟수, 나눌 수
	static long A, B, C;
	
	// 재귀 알고리즘: 분할 정복
	static long recursion(long A, long B) {
		// base case: B가 1이면 나눈 결과 return
		if (B == 1) return A % C;
			
		// recursive case
		// 만약 카운트 b가 짝수면 b제곱 == b/2제곱 2개, 나머지 공식 이용해 리턴
		long half = recursion(A, B/2);
		if (B % 2 == 0) return (half * half) % C;
		// 만약 카운트 b가 홀수면 b제곱 == b/2제곱 2개 * A
		return (half * half % C) * A % C;
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 곱하려는수, 곱할 횟수, 나눌 수 입력
		A = input.nextInt();
		B = input.nextInt();
		C = input.nextInt();
		
		// 정답 출력
		double answer = recursion(A, B) % C;
		System.out.println((int) answer);
	}
}