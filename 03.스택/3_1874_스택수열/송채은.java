import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		// 입력된 수열 arr에 저장
		int[] arr = new int[n];
		
		// arr의 index를 가르키는 변수
		// stack에서 pop 할 때마다 pos++
		int pos = 0;
		
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= n; i++) {
			arr[i-1] = Integer.parseInt(br.readLine());
			
			// stack에 들어가는 수와 입력된 수열의 pos 번째 수가 같은 경우
			// push와 pop을 진행하므로 + - 출력
			// 그렇지 않은 경우, stack에 push
			if(arr[pos] == i) {
				sb.append("+\n-\n");
				pos++;
			} else {
				stack.push(i);
				sb.append("+\n");
			}
			
			// 입력된 수열이 stack의 최상단에 있는 경우 pop 진행
			if(!stack.isEmpty() && stack.peek() == arr[pos]) {
				while(!stack.isEmpty() && stack.peek() == arr[pos]) {
					stack.pop();
					sb.append("-\n");
					pos++;
				}
			}
		}
		
		// pos가 입력된 수열보다도 작을 때
		// 즉, 수열을 완성하지 못했을 떄 "No" 출력
		if(pos < n - 1)
			bw.write("NO");
		else bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}
}
