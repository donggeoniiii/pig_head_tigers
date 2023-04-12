 // 덱

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 덱 생성
		Deque<Integer> deque = new LinkedList<>();
		
		// 출력해야 하는 명령의 수
		int N = Integer.parseInt(input.nextLine());
		
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(input.nextLine());
			String order = st.nextToken();

			// 정수를 덱의 앞에 놓는다
			if (order.equals("push_front")) {
				deque.offerFirst(Integer.parseInt(st.nextToken()));
			} 
			// 정수를 덱의 뒤에 넣는다
			else if (order.equals("push_back")) {
				deque.offerLast(Integer.parseInt(st.nextToken()));
			}
			// head에 있는 수를 빼고 출력 
			else if (order.equals("pop_front") && !deque.isEmpty()) {
				sb.append(deque.pollFirst()+"\n");
			}
			// tail에 있는 수를 빼고 출력 
			else if (order.equals("pop_back") && !deque.isEmpty()) {
				sb.append(deque.pollLast()+"\n");
			}
			// 덱의 크기 
			else if (order.equals("size")) {
				sb.append(deque.size()+"\n");
			}
			// 덱이 비면 1, 아니면 0 
			else if (order.equals("empty")) {
				if (deque.isEmpty()) sb.append(1+"\n");
				else sb.append(0+"\n");
			}
			// head 출력, 없으면 -1 
			else if (order.equals("front") && !deque.isEmpty()) {
				sb.append(deque.peekFirst()+"\n");
			} 
			// back 출력, 없으면 -1
			else if (order.equals("back") && !deque.isEmpty()) {
				sb.append(deque.peekLast()+"\n");
			} 
			// 덱이 비어서 입력받은 것을 할 수 없는 상황이면 -1 출력
			else {
				sb.append(-1+"\n");
			}
		}
		System.out.println(sb.toString());
	}
}
