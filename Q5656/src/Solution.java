import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		for (int test_case = 1; test_case <= t; test_case++) {
			int n = sc.nextInt();
			int w = sc.nextInt();
			int h = sc.nextInt();
			
			int[][] map = new int[h][w];
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			int min = Integer.MAX_VALUE;
			int result = 0;
			
			for (int i = 0; i < w; i++) {
				int[][] test = new int[h][w];
				
				for (int j = 0; j < h; j++) {
					for (int k = 0; k < w; k++) {
						test[j][k] = map[j][k];
					}
				}
				
				result = Select(i, n, test);
				
				if (result < min) {
					min = result;
				}
			}
			
			System.out.println("#" + test_case + " " + min);
		}
		sc.close();
	}
	
	static int Select(int n, int depth, int[][] test) {
		if (depth == 0) {
			int count = 0;
			
			for (int i = 0; i < test.length; i++) {
				for (int j = 0; j < test[0].length; j++) {
					if (test[i][j] != 0) {
						count++;
					}
				}
			}
			
			return count;
		}
		
		for (int i = 0; i < test.length; i++) {
			if (test[i][n] != 0) {
				Bomb(i, n, test);
				Arrange(test);
				break;
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < test[0].length; i++) {
			int[][] copy = new int[test.length][test[0].length];
			
			for (int j = 0; j < test.length; j++) {
				for (int k = 0; k < test[0].length; k++) {
					copy[j][k] = test[j][k];
				}
			}
			
			int temp = Select(i, depth - 1, copy);
			
			if (temp < min) {
				min = temp;
			}
		}
		
		return min;
	}
	
	static void Bomb(int h, int w, int[][] test) {
		int n = test[h][w];
		test[h][w] = 0;
		
		for (int i = 1; i < n; i++) {
			if (w + i < test[0].length) {
				if (test[h][w + i] != 0) {
					Bomb(h, w + i, test);
				}
			} else {
				break;
			}
		}
		
		for (int i = 1; i < n; i++) {
			if (w - i >= 0) {
				if (test[h][w - i] != 0) {
					Bomb(h, w - i, test);
				}
			} else {
				break;
			}
		}
		
		for (int i = 1; i < n; i++) {
			if (h + i < test.length) {
				if (test[h + i][w] != 0) {
					Bomb(h + i, w, test);
				}
			} else {
				break;
			}
		}
		
		for (int i = 1; i < n; i++) {
			if (h - i >= 0) {
				if (test[h - i][w] != 0) {
					Bomb(h - i, w, test);
				}
			} else {
				break;
			}
		}
	}
	
	static void Arrange(int[][] test) {
		for (int i = test.length - 2; i >= 0; i--) {
			for (int j = 0; j < test[0].length; j++) {
				int x = i;
				int y = j;
				
				while (test[x][y] != 0 && x + 1 < test.length && test[x + 1][y] == 0) {
					test[x + 1][y] = test[x][y];
					test[x][y] = 0;
					x++;
				}
			}
		}
	}
}