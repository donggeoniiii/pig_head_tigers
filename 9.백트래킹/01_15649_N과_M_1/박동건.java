// N과 M(1)
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();

	// 전체 선택지 개수
	static int N;
	
	// 골라야 하는 선택지 개수
	static int K;
	
	// 선택지를 담은 배열
	static int[] choice;
	
	// 방문 여부 체크하는 배열
	static boolean[] visited;
	
	
	static void backtrack(int k) {
		
		// base case: 선택이 끝났을 때 (== K = k-1)
		if (k == K) {
			// 배열 출력
			for (int idx = 0; idx < K; idx++) {
				sb.append(choice[idx]).append(" ");
			}
			sb.append("\n");
		}
		
		// recursive case: 아직 채워야 할 때
		// 모든 경우의 수 탐색
		for (int i = 1; i <= N; i++) {
			// 숫자 선택
			int num = i;
			
			// 이미 해당 숫자는 방문했다면 스킵
			if (visited[num]) continue;
			
			// 해당 숫자 방문체크
			visited[num] = true; 
			
			// 선택지에 추가
			choice[k] = num;
			
			// 백트래킹 on
			backtrack(k+1);
			
			// 해당 숫자가 들어가는 모든 경우에 대해 탐색이 끝났으므로 다시 방문체크 해제
			visited[num] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 전체 수 N
		N = input.nextInt();
		
		// 배열 크기 지정, visited는 숫자를 index로 사용하기 위해 +1
		choice = new int[10];
		visited = new boolean[10];
		
		// 선택할 개수 M
		K = input.nextInt();
		
		// 백트래킹으로 가능한 모든 경우 탐색
		backtrack(0);
		
		// 출력
		System.out.println(sb.toString());
	}
}