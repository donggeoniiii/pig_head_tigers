// 트럭

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int n = input.nextInt(); // 건널 트럭 대수
		int w = input.nextInt(); // 다리의 길이
		int L = input.nextInt(); // 다리의 최대 하중
		
		// 건널 트럭을 차례대로 보관할 queue
		Queue<Integer> truckQueue = new LinkedList<>();
		
		// queue에 트럭 무게 정보 보관
		for (int truck = 1; truck <= n; truck++) {
			int truckWeight = input.nextInt();
			truckQueue.offer(truckWeight);
		}
		
		// 다리의 현재 상태를 보관하는 queue
		Queue<Integer> bridgeQueue = new LinkedList<>();
		
		// 다리가 비어있는 상태로 queue 초기화
		for (int blank = 1; blank <= w; blank++) {
			bridgeQueue.offer(0);
		}
		
		// 다리의 현재 하중을 보관하는 변수
		int curBridgeWeight = 0;
		
		// 시간을 보관하는 변수
		int time = 0;
		
		// 두 queue가 다 비는 순간까지
		while (!(truckQueue.isEmpty() && bridgeQueue.isEmpty())) {

			// 다리에 새로운게 올라올거니까 맨 먼저 올라온 거 한 칸 빼기
			int deletedWeight = bridgeQueue.poll();
			
			// 하중 제거(사실 안해도 되긴 함)
			curBridgeWeight -= deletedWeight;

			// 트럭을 올리려면 일단 올릴 트럭이 있는지 봐야 하니까
			if (!truckQueue.isEmpty()) {
				
				// 다음에 다리에 올라갈 트럭 무게 체크
				int nextTruckWeight = truckQueue.peek();
				
				// 만약 다리에 이 트럭이 올라가도 하중이 버틴다면
				if (curBridgeWeight + nextTruckWeight <= L) {
					
					// 트럭 빼서
					truckQueue.poll();
					
					// 다리에 올리기
					bridgeQueue.offer(nextTruckWeight);
					
					// 다리에 하중 추가
					curBridgeWeight += nextTruckWeight;
					
				}
				
				// 안되면 이번 차례엔 다리 한 칸 비워놓기
				else {
					bridgeQueue.offer(0);
				}
			}
			
			// 1초 경과
			time++;
		}

		// 정답 출력, 다리가 다 건너는 시간은 
		System.out.println(time);
		
		input.close();
	}
}