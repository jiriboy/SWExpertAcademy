import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int i = 1; i <= t; i++) {
			int n = sc.nextInt();
			int x = sc.nextInt();
			
			int[][] map = new int[n][n];
			
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					map[j][k] = sc.nextInt();
				}
			}
			
			int result = 0;
			
			for (int j = 0; j < n; j++) {
				boolean possible = true;
				
				for (int k = 0; k < n - 1; k++) {
					if (map[j][k] == map[j][k + 1]) {
						continue;
					} else if (Math.abs(map[j][k] - map[j][k + 1]) > 1) {
						possible = false;
						break;
					} else if (map[j][k] - map[j][k + 1] == 1) {
						if (k + x >= n) {
							possible = false;
							break;
						}
						
						for (int l = 1; l < 2 * x; l++) {
							if (k + l + 1 == n) {
								break;
							}
							
							if (l < x && map[j][k + l] != map[j][k + l + 1]) {
								possible = false;
								break;
							}
							
							if (map[j][k + l] - map[j][k + l + 1] == -1) {
								possible = false;
								break;
							}
						}
					} else if (map[j][k] - map[j][k + 1] == -1) {
						if (k - x + 1 < 0) {
							possible = false;
							break;
						}
						
						for (int l = 0; l < x - 1; l++) {
							if (map[j][k - l] != map[j][k - l - 1]) {
								possible = false;
								break;
							}
						}
					}
				}
				
				if (possible) {
					result++;
				}
			}
			
			for (int j = 0; j < n; j++) {
				boolean possible = true;
				
				for (int k = 0; k < n - 1; k++) {
					if (map[k][j] == map[k + 1][j]) {
						continue;
					} else if (Math.abs(map[k][j] - map[k + 1][j]) > 1) {
						possible = false;
						break;
					} else if (map[k][j] - map[k + 1][j] == 1) {
						if (k + x >= n) {
							possible = false;
							break;
						}
						
						for (int l = 1; l < 2 * x; l++) {
							if (k + l + 1 == n) {
								break;
							}
							
							if (l < x && map[k + l][j] != map[k + l + 1][j]) {
								possible = false;
								break;
							}
							
							if (map[k + l][j] - map[k + l + 1][j] == -1) {
								possible = false;
								break;
							}
						}
					} else if (map[k][j] - map[k + 1][j] == -1) {
						if (k - x + 1 < 0) {
							possible = false;
							break;
						}
						
						for (int l = 0; l < x - 1; l++) {
							if (map[k - l][j] != map[k - l - 1][j]) {
								possible = false;
								break;
							}
						}
					}
				}
				
				if (possible) {
					result++;
				}
			}
			
			System.out.println("#" + i + " " + result);
		}
		
		sc.close();
	}
}
