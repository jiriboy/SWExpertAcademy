import java.util.Scanner;

public class Solution {
	static int[][] map;
	static Wormhole[] wormhole;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int i = 1; i <= t; i++) {
			int n = sc.nextInt();
			
			map = new int[n][n];
			wormhole = new Wormhole[5];
			wormhole[0] = new Wormhole();
			wormhole[1] = new Wormhole();
			wormhole[2] = new Wormhole();
			wormhole[3] = new Wormhole();
			wormhole[4] = new Wormhole();
			
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					int temp = sc.nextInt();
					map[j][k] = temp;
					if (temp > 5) {
						if (wormhole[temp - 6].x1 < 0 && wormhole[temp - 6].y1 < 0) {
							wormhole[temp - 6].x1 = j;
							wormhole[temp - 6].y1 = k;
						} else {
							wormhole[temp - 6].x2 = j;
							wormhole[temp - 6].y2 = k;
						}
					}
				}
			}
			
			int result = Solve();
			System.out.println("#" + i + " " + result);
		}
		sc.close();
	}
	
	static int Solve() {
		int max = 0;
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] != 0) {
					continue;
				}
				
				for (int k = 0; k < 4; k++) {
					int temp = Simulation(i, j, k);
					if (temp > max) {
						max = temp;
					}
				}
			}
		}
		
		return max;
	}
	
	static int Simulation(int i, int j, int direction) {
		boolean end = false;
		int score = 0;
		int x = i;
		int y = j;
		
		while (!end) {
			while (true) {
				if (direction == 0) {
					y--;
				} else if (direction == 1) {
					x--;
				} else if (direction == 2) {
					y++;
				} else if (direction == 3) {
					x++;
				}
				
				if (x < 0 || y < 0 || x == map.length || y == map.length) {
					score++;
					
					if (direction == 0) {
						y++;
						direction = 2;
					} else if (direction == 1) {
						x++;
						direction = 3;
					} else if (direction == 2) {
						y--;
						direction = 0;
					} else if (direction == 3) {
						x--;
						direction = 1;
					}
				}
				
				if (map[x][y] == -1 || (x == i & y == j)) {
					end = true;
					break;
				} else if (map[x][y] != 0) {
					break;
				}
			}
				
			if (map[x][y] == 1) {
				score++;
				
				if (direction == 0) {
					direction = 1;
				} else if (direction == 1) {
					direction = 3;
				} else if (direction == 2) {
					direction = 0;
				} else if (direction == 3) {
					direction = 2;
				}
			} else if (map[x][y] == 2) {
				score++;
				
				if (direction == 0) {
					direction = 3;
				} else if (direction == 1) {
					direction = 2;
				} else if (direction == 2) {
					direction = 0;
				} else if (direction == 3) {
					direction = 1;
				}
			} else if (map[x][y] == 3) {
				score++;
				
				if (direction == 0) {
					direction = 2;
				} else if (direction == 1) {
					direction = 0;
				} else if (direction == 2) {
					direction = 3;
				} else if (direction == 3) {
					direction = 1;
				}
			} else if (map[x][y] == 4) {
				score++;
				
				if (direction == 0) {
					direction = 2;
				} else if (direction == 1) {
					direction = 3;
				} else if (direction == 2) {
					direction = 1;
				} else if (direction == 3) {
					direction = 0;
				}
			} else if (map[x][y] == 5) {
				score++;
				
				if (direction == 0) {
					direction = 2;
				} else if (direction == 1) {
					direction = 3;
				} else if (direction == 2) {
					direction = 0;
				} else if (direction == 3) {
					direction = 1;
				}
			} else if (map[x][y] > 5) {
				if (x == wormhole[map[x][y] - 6].x1 && y == wormhole[map[x][y] - 6].y1) {
					int temp_x = wormhole[map[x][y] - 6].x2;
					int temp_y = wormhole[map[x][y] - 6].y2;
					x = temp_x;
					y = temp_y;
				} else {
					int temp_x = wormhole[map[x][y] - 6].x1;
					int temp_y = wormhole[map[x][y] - 6].y1;
					x = temp_x;
					y = temp_y;
				}
			}
		}
		
		return score;
	}
}

class Wormhole {
	int x1;
	int y1;
	int x2;
	int y2;
	
	Wormhole() {
		this.x1 = -1;
		this.y1 = -1;
		this.x2 = -1;
		this.y2 = -1;
	}
}
