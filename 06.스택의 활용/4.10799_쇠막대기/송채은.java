package _10799;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		input = input.replace("()", "1");
		
		Stack<Character> s = new Stack<>();
		int ans = 0;
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '(') s.push(input.charAt(i));
			else if(input.charAt(i) == '1') ans += s.size();
			else {
				s.pop();
				ans++;
			}
		}
		System.out.println(ans);
	}
}
