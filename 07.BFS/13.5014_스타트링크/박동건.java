package boj5014_스타트링크; // 스타트링크 

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 얘도 숨바꼭질...?

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// F, S, G, U, D 순으로 입력
		int F = input.nextInt(); // 전체 층수
		int S = input.nextInt(); // 강호의 현재 위치
		int G = input.nextInt(); // 스타트링크의 위치
		int U = input.nextInt(); // 위로 가는 버튼(층수)
		int D = input.nextInt(); // 아래로 가는 버튼(층수)
		
		// 탐색을 위한 큐
		Queue<Integer> queue = new LinkedList<>();
		
		// 빌딩배열: 버튼을 몇번째 눌렀을 때 방문했는지 저장, 층수를 index로 사용하기 위해 전체 배열 크기 +1
		int[] building = new int[F+1];
		
		// 시작하는 층이랑 방문하지 않은 층을 헷갈리지 않기 위해 -1로 초기화
		Arrays.fill(building, -1);
		
		// 도착여부 체크
		boolean arrived = false;
		
		// S층에서 탐색 시작
		queue.offer(S);
		
		// 시작점 방문체크
		building[S] = 0;
		
		// 처음부터 도착해있다면 도착체크
		if (S == G) arrived = true;
		
		// 더 탐색할 층이 없을 때까지
		while (!queue.isEmpty()) {
			
			// 좌표 데이터 받아오기
			int cur = queue.poll();
			
			// 델타? 배열, D는 아래로 가는 거니까 부호 반대로
			int[] df = {U,-D};
		
			// 델타탐색
			for (int delta : df) {
				int nf = cur + delta;
				
				// 만약 천장이나 땅을 뚫게 되거나 이미 와본 층이면 스킵
				if (nf > F || nf <= 0 || building[nf] >= 0) continue;
				
				// 새로운 탐색지점 추가
				queue.offer(nf);
				
				// 몇번째에 방문했는지 체크
				building[nf] = building[cur] + 1;
				
				// 만약 도착한 층이 G층이면 도착체크
				if (nf == G) arrived = true;
			}
			
			// 도착했다면 더이상 탐색하지 않고 break;
			if (arrived) break;
			
		}
		
		// 정답 출력
		if (arrived) System.out.println(building[G]);
		else System.out.println("use the stairs");
		
		
	}
}
