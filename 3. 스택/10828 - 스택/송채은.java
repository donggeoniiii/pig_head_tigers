import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] stack = new int[10000];
	static int pos = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			// 명령어 입력
			String comm = br.readLine();
			
			if(comm.charAt(0) == 'p') {
				// 1. push
				if(comm.charAt(1) == 'u') {
					StringTokenizer st = new StringTokenizer(comm, " ");
					st.nextToken();
					int num = Integer.parseInt(st.nextToken());
					push(num);
				}
				// 2. pop
				else
					bw.write(pop() + "\n");
			}
			// 3. size (현재 pos 값)
			else if(comm.charAt(0) == 's') {
				bw.write(size() + "\n");
			}
			// 4. empty
			else if(comm.charAt(0) == 'e') {
				bw.write(empty() + "\n");
			}
			// 5. top
			else
				bw.write(top() + "\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}
	
	static void push(int x) {
		stack[pos++] = x;
	}
	
	static int pop() {
		if(pos < 1)
			return -1;
		else
			return stack[--pos];
	}
	
	static int size() {
		return pos;
	}
	
	static int empty() {
		if(pos > 0)
			return 0;
		else
			return 1;
	}
	
	static int top() {
		if(pos < 1)
			return -1;
		else
			return stack[pos-1];
	}
}
