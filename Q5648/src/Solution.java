import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	static ArrayList<Atom> atoms;
	static int[][] map = new int[4001][4001];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int T = 1; T <= t; T++) {
			int n = sc.nextInt();
			atoms = new ArrayList<>();
			
			for (int i = 0; i < n; i++) {
				int x = sc.nextInt() + 1000;
				int y = sc.nextInt() + 1000;
				int direction = sc.nextInt();
				int energy = sc.nextInt();
				
				atoms.add(new Atom(2 * x, 2 * y, direction, energy == 0 ? 101 : energy));
			}
			
			int result = Solve();
			System.out.println("#" + T + " " + result);
		}
		
		sc.close();
	}
	
	static int Solve() {
		int result = 0;
		
		while (atoms.size() > 1) {
			ArrayList<Atom> remove = new ArrayList<>();
			
			for (Atom a: atoms) {
				map[a.x][a.y] = 0;
				
				if (a.direction == 0) {
					a.y++;
				} else if (a.direction == 1) {
					a.y--;
				} else if (a.direction == 2) {
					a.x--;
				} else {
					a.x++;
				}
				
				if (a.x < 0 || a.y < 0 || a.x > 4000 || a.y > 4000) {
					remove.add(a);
					continue;
				}
				
				map[a.x][a.y] += a.energy;
			}
			
			while (!remove.isEmpty()) {
				atoms.remove(remove.remove(0));
			}
			
			for (Atom a: atoms) {
				if (map[a.x][a.y] > a.energy) {
					result += map[a.x][a.y];
					map[a.x][a.y] = 0;
					remove.add(a);
				} else if (map[a.x][a.y] == 0) {
					remove.add(a);
				}
			}
			
			while (!remove.isEmpty()) {
				Atom a = remove.remove(0);
				if (a.energy == 101) {
					result -= 101;
				}
				atoms.remove(a);
			}
		}
		
		while (!atoms.isEmpty()) {
			Atom a = atoms.remove(0);
			map[a.x][a.y] = 0;
		}
		
		return result;
	}
	
	static class Atom {
		int x;
		int y;
		int direction;
		int energy;
		
		Atom(int x, int y, int direction, int energy) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.energy = energy;
		}
	}
}