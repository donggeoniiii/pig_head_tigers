import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[] stack = new int[100000];
	// stack의 현재 위치를 알려주는 pos
	static int pos = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int K = Integer.parseInt(br.readLine());
		
		// 입력 값이 0일 때 pop
		// 그 외 push
		for(int i = 0; i < K; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) pop();
			else push(num);
		}
		
		int sum = 0;
		for(int i = 0; i < pos; i++)
			sum += stack[i];
		bw.write(sum + "");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void push(int x) {
		stack[pos++] = x;
	}
	
	static void pop() {
		--pos;
	}
}
