// 숨바꼭질

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 수빈이와 동생의 위치
		int N = input.nextInt();
		int K = input.nextInt();
		
		// 방문 여부 및 도달한 시점을 저장하는 배열
		// N과 K를 index로 쓰기 위해 배열의 크기는 1 크게 생성
		int[] visited = new int[100001];
		
		// 수빈이의 선택에 따른 다음 위치를 저장하는 큐
		Queue<Integer> queue = new LinkedList<>();
		
		// 시작지점 offer
		queue.offer(N);
		
		while (!queue.isEmpty()) {
			
			// 위치 정보로 실시간 동생과 위치 비교
			int loc = queue.poll();
			
			// 같으면 바로 정답 출력
			if (loc == K) break;
			
			// 다르면 -1, +1, *2로 탐색
			// 이미 도달한 곳이면 스킵
			// 다음 좌표의 값은 현재 좌표 +1
			if (loc-1 >= 0 && loc-1 < visited.length && visited[loc-1] == 0) {
				queue.offer(loc-1);
				visited[loc-1] = visited[loc] +1;
			}
			
			if (loc+1 >= 0 && loc+1 < visited.length && visited[loc+1] == 0) {
				queue.offer(loc+1);
				visited[loc+1] = visited[loc] +1;
			}
			
			if (loc*2 >= 0 && loc*2 < visited.length && visited[loc*2] == 0) {
				queue.offer(loc*2);
				visited[loc*2] = visited[loc] +1;
			}
			
			
		}
		
		// 정답출력
		System.out.println(visited[K]);
		
	}
}