// Queue2

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// push, pop, size, empty, front, back 6가지 구현
// Queue 인터페이스 상에 있는 메서드만 사용하기!
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Deque<Integer> queue = new LinkedList<>();
		
		int N = Integer.parseInt(br.readLine());
		
		// back 명령 처리를 위한 변수
		for (int tc = 0; tc < N; tc++) {
			String s = br.readLine();
			
			if (s.contains("push")) {
				StringTokenizer st = new StringTokenizer(s);
				// 앞에 "push" 날리고
				st.nextToken();
				queue.offer(Integer.parseInt(st.nextToken()));
			} else if (s.contains("pop")) {
				if (queue.isEmpty()) bw.write(-1 + "\n");
				else bw.write(queue.poll() + "\n");
				
			} else if (s.contains("size")) {
				bw.write(queue.size() + "\n");
				
			} else if (s.contains("empty")) {
				if (queue.isEmpty()) bw.write(1 + "\n");
				else bw.write(0 + "\n");
				
			} else if (s.contains("front")) {
				if (queue.isEmpty()) bw.write(-1 + "\n");
				else bw.write(queue.peek() + "\n");
				
			} 
			// back, deque에 있는 메소드인 peekLast() 활용
			else {
				if (queue.isEmpty()) bw.write(-1 + "\n");
				else bw.write(queue.peekLast() + "\n");
			} 
		}
		
		// 정답 출력
		bw.flush();
		bw.close();
		
		
		
	}
}
