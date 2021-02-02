import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int i = 1; i <= t; i++) {
			Cell[][] map = new Cell[650][650];
			ArrayList<Cell> act = new ArrayList<>();
			ArrayList<Cell> deact = new ArrayList<>();
			
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();
			
			for (int j = 300; j < n + 300; j++) {
				for (int l = 300; l < m + 300; l++) {
					int temp = sc.nextInt();
					if (temp > 0) {
						Cell c = new Cell(j, l, temp);
						map[j][l] = c;
						deact.add(c);
					}
				}
			}
			
			for (int j = 0; j < k; j++) {
				ArrayList<Cell> remove = new ArrayList<>();
				ArrayList<Cell> add_act = new ArrayList<>();
				ArrayList<Cell> add_deact = new ArrayList<>();
				
				for (Cell c : deact) {
					c.timer--;
					
					if (c.timer == 0) {
						c.timer = c.k;
						c.status = 1;
						remove.add(c);
						add_act.add(c);
					}
				}
				
				for (Cell c : remove) {
					deact.remove(c);
				}
				
				for (Cell c : act) {
					if (map[c.x][c.y - 1] == null) {
						Cell temp = new Cell(c.x, c.y - 1, c.k);
						map[c.x][c.y - 1] = temp;
						add_deact.add(temp);
					} else if (map[c.x][c.y - 1].status == 0 && map[c.x][c.y - 1].k == map[c.x][c.y - 1].timer && c.k > map[c.x][c.y - 1].k) {
						map[c.x][c.y - 1].k = c.k;
						map[c.x][c.y - 1].timer = c.k;
					}
					
					if (map[c.x][c.y + 1] == null) {
						Cell temp = new Cell(c.x, c.y + 1, c.k);
						map[c.x][c.y + 1] = temp;
						add_deact.add(temp);
					} else if (map[c.x][c.y + 1].status == 0 && map[c.x][c.y + 1].k == map[c.x][c.y + 1].timer && c.k > map[c.x][c.y + 1].k) {
						map[c.x][c.y + 1].k = c.k;
						map[c.x][c.y + 1].timer = c.k;
					}
					
					if (map[c.x - 1][c.y] == null) {
						Cell temp = new Cell(c.x - 1, c.y, c.k);
						map[c.x - 1][c.y] = temp;
						add_deact.add(temp);
					} else if (map[c.x - 1][c.y].status == 0 && map[c.x - 1][c.y].k == map[c.x - 1][c.y].timer && c.k > map[c.x - 1][c.y].k) {
						map[c.x - 1][c.y].k = c.k;
						map[c.x - 1][c.y].timer = c.k;
					}

					if (map[c.x + 1][c.y] == null) {
						Cell temp = new Cell(c.x + 1, c.y, c.k);
						map[c.x + 1][c.y] = temp;
						add_deact.add(temp);
					} else if (map[c.x + 1][c.y].status == 0 && map[c.x + 1][c.y].k == map[c.x + 1][c.y].timer && c.k > map[c.x + 1][c.y].k) {
						map[c.x + 1][c.y].k = c.k;
						map[c.x + 1][c.y].timer = c.k;
					}
					
					c.timer--;
					if (c.timer == 0) {
						c.status = 2;
						remove.add(c);
					}
				}
				
				for (Cell c : remove) {
					act.remove(c);
				}
				
				remove.clear();
				
				for (Cell c : add_act) {
					act.add(c);
				}

				for (Cell c : add_deact) {
					deact.add(c);
				}
			}
			
			System.out.println("#" + i + " " + (act.size() + deact.size()));
		}
		
		sc.close();
	}
}

class Cell {
	int x;
	int y;
	int k;
	int status;
	int timer;
	
	Cell(int x, int y, int k) {
		this.x = x;
		this.y = y;
		this.k = k;
		this.status = 0;
		this.timer = k;
	}
}
