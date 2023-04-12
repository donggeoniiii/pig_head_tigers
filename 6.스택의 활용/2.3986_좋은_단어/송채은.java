package _3986;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int ans = 0;
		for(int tc = 0; tc < T; tc++) {
			Stack<Character> s = new Stack<>();
			String input = br.readLine();
			
			s.push(input.charAt(0));
			for(int i = 1; i < input.length(); i++) {
				if(!s.isEmpty() && s.peek() == input.charAt(i))
					s.pop();
				else
					s.push(input.charAt(i));
			}
			if(s.isEmpty()) ans++;
		}
		
		System.out.println(ans);
	}
}
