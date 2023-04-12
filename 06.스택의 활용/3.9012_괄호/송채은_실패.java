package _9012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Fail {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			Stack<Integer> s = new Stack<>();
			
			String input = br.readLine();
			input = input.replace("(", "1");
			input = input.replace(")", "0");
			s.push(input.charAt(0) - '0');
			
			for(int i = 1; i < input.length(); i++) {
				if(!s.isEmpty() && s.peek() - (input.charAt(i) - '0') == 1)
					s.pop();
				else s.push(input.charAt(i) - '0');
			}
			
			if(s.isEmpty())
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}
