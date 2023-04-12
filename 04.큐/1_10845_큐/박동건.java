 // Queue

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// push, pop, size, empty, front, back 6가지 구현
// Queue 인터페이스 상에 있는 메서드만 사용하기!
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> queue = new LinkedList<>();
		
		int N = Integer.parseInt(br.readLine());
		
		// back 명령 처리를 위한 변수
		int lastInput = 0;
		for (int tc = 0; tc < N; tc++) {
			String s = br.readLine();
			
			if (s.contains("push")) {
//				System.out.println("push");
				String[] list = s.split(" ");
				lastInput = Integer.parseInt(list[1]);
				queue.offer(lastInput);
			} else if (s.contains("pop")) {
//				System.out.println("pop");
				if (queue.isEmpty()) System.out.println(-1);
				else System.out.println(queue.poll());
				
			} else if (s.contains("size")) {
//				System.out.println("size");
				System.out.println(queue.size());
				
			} else if (s.contains("empty")) {
//				System.out.println("empty");
				if (queue.isEmpty()) System.out.println(1);
				else System.out.println(0);
				
			} else if (s.contains("front")) {
//				System.out.println("front");
				if (queue.isEmpty()) System.out.println(-1);
				else System.out.println(queue.peek());
				
			} 
			// back
			else {
//				System.out.println("back");
				if (queue.isEmpty()) System.out.println(-1);
				else System.out.println(lastInput);
			} 
			
			
			
			
		}
		
		
	}
}
