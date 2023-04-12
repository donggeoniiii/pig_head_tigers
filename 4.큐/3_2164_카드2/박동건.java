// 카드

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Queue<Integer> queue = new LinkedList<>();
		
		int N = input.nextInt();
		
		// FIFO 구조이므로 1이 가장 위에 있으려면 1부터 들어가야 한다!!!
		for (int i = 1; i <= N; i++) queue.offer(i);
		
		// 1. 일단 하나를 뺀다
		// 2. 또 하나를 빼고 그걸 바로 넣는다
		// 3. 만약 1~2 사이에 N == 1이면 break;
		while (N > 0) {
			if (queue.size() == 1) break;
			queue.poll();
			if (queue.size() == 1) break;
			queue.offer(queue.poll());
		}
		
		System.out.println(queue.peek());
		
		
		
	}
}	
