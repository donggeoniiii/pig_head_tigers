import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 큐를 배열로 구현한 방식
	static int[] queue = new int[10000];
	// head : 큐에서 데이터를 꺼낼 수 있는 부분의 인덱스
	// tail : 큐에 데이터를 넣을 수 있는 부분의 인덱스
	static int head = 0;
	static int tail = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			// N 개의 명령어 입력
			String comm = br.readLine();
			
			// charAt 방식 대신 contains() 사용
			if(comm.contains("push")) {
				StringTokenizer st = new StringTokenizer(comm, " ");
				st.nextToken();
				int input = Integer.parseInt(st.nextToken());
				push(input);
			}
			else if(comm.contains("pop"))
				sb.append(pop()).append("\n");
			else if(comm.contains("size"))
				sb.append(size()).append("\n");
			else if(comm.contains("empty"))
				sb.append(empty()).append("\n");
			else if(comm.contains("front"))
				sb.append(front()).append("\n");
			else
				sb.append(back()).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	// push() : x를 큐에 넣음
	static void push(int x) {
		queue[tail++] = x;
	}
	
	// pop() : 큐에서 가장 앞에 있는 수를 뺌
	// head == tail 인 경우 : 큐가 비어있을 때
	static int pop() {
		if(head == tail)
			return -1;
		else
			return queue[head++];
	}
	
	// size() : 큐에 들어있는 정수의 개수를 출력 
	static int size() {
		return tail - head;
	}
	
	// empty() : 큐가 비었는지 아닌지 확인
	static int empty() {
		if(head == tail)
			return 1;
		else
			return 0;
	}
	
	// front : 큐의 가장 앞부분에 있는 수 출력
	static int front() {
		if(head == tail)
			return -1;
		else
			return queue[head];
	}
	
	// back : 큐의 가장 뒷부분에 있는 수 출력
	static int back() {
		if(head == tail)
			return -1;
		else
			return queue[tail-1];
	}
}
