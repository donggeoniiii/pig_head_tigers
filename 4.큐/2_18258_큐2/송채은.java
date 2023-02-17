import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		// 배열 대신 Queue 인터페이스를 사용한 경우
		Queue<Integer> q = new LinkedList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		// back 명령어 입력 시, 가장 최근에 들어간 수를 출력하기 위해 사용하는 변수
		int recent = 0;
		for(int i = 0; i < N; i++) {
			String comm = br.readLine();
			if(comm.contains("push")) {
				StringTokenizer st = new StringTokenizer(comm, " ");
				st.nextToken();
				int input = Integer.parseInt(st.nextToken());
				// push 할 때마다 recent에 input 값을 갱신
				recent = input;
				q.add(input);
			}
			else if(comm.contains("pop")) {
				if(q.isEmpty())
					sb.append("-1\n");
				else
					sb.append(q.poll() + "\n");
			}
			else if(comm.contains("size"))
				sb.append(q.size() + "\n");
			else if(comm.contains("empty")) {
				if(q.isEmpty())
					sb.append("1\n");
				else
					sb.append("0\n");
			}
			else if(comm.contains("front")) {
				if(q.isEmpty())
					sb.append("-1\n");
				else
					sb.append(q.peek() + "\n");
			}
			else {
				if(q.isEmpty())
					sb.append("-1\n");
				else
					sb.append(recent + "\n");
			}
		}
		System.out.println(sb.toString());
	}
}
