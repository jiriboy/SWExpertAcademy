import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int i = 0; i < t; i++) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			
			String str = sc.next();
			str += str.substring(0, n - 1);
			
			ArrayList<String> arr = new ArrayList<>();
			
			int l = n / 4;
			
			for (int j = 0; j < l; j++) {
				if (!arr.contains(str.substring(j, j + l))) {
					arr.add(str.substring(j, j + l));
				}
				if (!arr.contains(str.substring(j + l, j + 2 * l))) {
					arr.add(str.substring(j + l, j + 2 * l));
				}
				if (!arr.contains(str.substring(j + 2 * l, j + 3 * l))) {
					arr.add(str.substring(j + 2 * l, j + 3 * l));
				}
				if (!arr.contains(str.substring(j + 3 * l, j + 4 * l))) {
					arr.add(str.substring(j + 3 * l, j + 4 * l));
				}
			}
			
			Collections.sort(arr);
			
			String out = arr.get(arr.size() - k);
			
			int result = 0;
			int temp = 1;
			
			for (int j = out.length() - 1; j >= 0; j--) {
				if (Character.isDigit(out.charAt(j))) {
					result += (out.charAt(j) - '0') * temp;
				} else if (out.charAt(j) == 'A') {
					result += 10 * temp;
				} else if (out.charAt(j) == 'B') {
					result += 11 * temp;
				} else if (out.charAt(j) == 'C') {
					result += 12 * temp;
				} else if (out.charAt(j) == 'D') {
					result += 13 * temp;
				} else if (out.charAt(j) == 'E') {
					result += 14 * temp;
				} else if (out.charAt(j) == 'F') {
					result += 15 * temp;
				}
				temp *= 16;
			}
			
			System.out.println("#" + (i + 1) + " " + result);
		}
		sc.close();
	}
}
