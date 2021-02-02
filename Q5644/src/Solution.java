import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int i = 1; i <= t; i++) {
			int m = sc.nextInt();
			int a = sc.nextInt();
			
			int[] A = new int[m + 1];
			int[] B = new int[m + 1];
			ArrayList<BC> chargers = new ArrayList<>();
			
			for (int j = 1; j <= m; j++) {
				A[j] = sc.nextInt();
			}
			
			for (int j = 1; j <= m; j++) {
				B[j] = sc.nextInt();
			}
			
			for (int j = 0; j < a; j++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int distance = sc.nextInt();
				int power = sc.nextInt();
				
				chargers.add(new BC(x, y, distance, power));
			}
			
			Collections.sort(chargers);
			
			int result = 0;
			
			int ax = 1;
			int ay = 1;
			int bx = 10;
			int by = 10;
			
			for (int j = 0; j <= m; j++) {
				if (A[j] == 1) {
					ay--;
				} else if (A[j] == 2) {
					ax++;
				} else if (A[j] == 3) {
					ay++;
				} else if (A[j] == 4){
					ax--;
				}
				
				if (B[j] == 1) {
					by--;
				} else if (B[j] == 2) {
					bx++;
				} else if (B[j] == 3) {
					by++;
				} else if (B[j] == 4){
					bx--;
				}
				
				boolean a_connected = false;
				boolean b_connected = false;
				boolean overlap = false;
				
				for (BC c: chargers) {
					boolean ca = false;
					boolean cb = false;
					
					if (!a_connected && Math.abs(c.x - ax) + Math.abs(c.y - ay) <= c.distance) {
						result += c.power;
						a_connected = true;
						ca = true;
						if (overlap) {
							break;
						}
					}
					
					if (!b_connected && Math.abs(c.x - bx) + Math.abs(c.y - by) <= c.distance) {
						result += c.power;
						b_connected = true;
						cb = true;
						if (overlap) {
							break;
						}
					}
					
					if (ca && cb) {
						result -= c.power;
						a_connected = false;
						b_connected = false;
						overlap = true;
					}
				}				
			}
			
			System.out.println("#" + i + " " + result);
		}
		
		sc.close();
	}
	
	static class BC implements Comparable<BC> {
		int x;
		int y;
		int distance;
		int power;
		
		BC(int x, int y, int distance, int power) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.power = power;
		}

		@Override
		public int compareTo(BC o) {
			if (this.power > o.power) {
				return -1;
			} else if (this.power < o.power) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
