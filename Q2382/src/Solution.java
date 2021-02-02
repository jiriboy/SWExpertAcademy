import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    static int[][] max;
    static Group[][] map;
    static ArrayList<Group> group;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int test = sc.nextInt();

        for (int t = 1; t <= test; t++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();

            max = new int[n][n];
            map = new Group[n][n];
            group = new ArrayList<>();

            for (int i = 0; i < k; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int num = sc.nextInt();
                int dir = sc.nextInt();

                Group g = new Group(x, y, num, dir);

                map[x][y] = g;
                group.add(g);
            }

            for (int i = 0; i < m; i++) {
                Simulate(n);
            }

            int result = 0;

            for (Group g: group) {
                result += g.num;
            }

            System.out.println("#" + t + " " + result);
        }

        sc.close();
    }

    static void Simulate(int n) {
        for (Group g: group) {
            map[g.x][g.y] = null;
            max[g.x][g.y] = 0;

            if (g.dir == 1) {
                g.x--;
            } else if (g.dir == 2) {
                g.x++;
            } else if (g.dir == 3) {
                g.y--;
            } else {
                g.y++;
            }

            if (g.x == 0 || g.x == n - 1 || g.y == 0 || g.y == n - 1) {
                g.num /= 2;

                if (g.dir == 1) {
                    g.dir = 2;
                } else if (g.dir == 2) {
                    g.dir = 1;
                } else if (g.dir == 3) {
                    g.dir = 4;
                } else {
                    g.dir = 3;
                }
            }
        }

        ArrayList<Group> remove = new ArrayList<>();

        for (Group g: group) {
            int x = g.x;
            int y = g.y;

            if (max[x][y] < g.num) {
                max[x][y] = g.num;

                if (map[x][y] != null) {
                    remove.add(map[x][y]);
                    g.num += map[x][y].num;
                }

                map[x][y] = g;
            } else if (max[x][y] > g.num){
                remove.add(g);
                map[x][y].num += g.num;
            } else if (g.num == 0) {
                remove.add(g);
            }
        }

        for (Group r: remove) {
            group.remove(r);
        }
    }

    static class Group {
        int x;
        int y;
        int num;
        int dir;

        Group(int x, int y, int num, int dir) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }
    }
}