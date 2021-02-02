import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int test = sc.nextInt();

        for (int t = 1; t <= test; t++) {
            int n = sc.nextInt();
            map = new int[n][n];

            Stair[] stair = new Stair[2];
            LinkedList<Person> people = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();

                    if (map[i][j] == 1) {
                        people.offer(new Person(i, j));
                    } else if (map[i][j] > 1) {
                        if (stair[0] == null) {
                            stair[0] = new Stair(i, j, map[i][j]);
                        } else {
                            stair[1] = new Stair(i, j, map[i][j]);
                        }
                    }
                }
            }

            LinkedList<Person>[] queue = new LinkedList[2];
            queue[0] = new LinkedList<>();
            queue[1] = new LinkedList<>();

            for (int i = 0; i < people.size(); i++) {
                Person p = people.get(i);

                int d0 = Math.abs(p.x - stair[0].x) + Math.abs(p.y - stair[0].y);
                int d1 = Math.abs(p.x - stair[1].x) + Math.abs(p.y - stair[1].y);

                p.d0 = d0;
                p.d1 = d1;

                queue[0].offer(p);
            }

            int result = Integer.MAX_VALUE;
            boolean end = false;

            while (!end) {
                Collections.sort(queue[0], comparator0);
                Collections.sort(queue[1], comparator1);

                int[] time0 = new int[queue[0].size()];

                if (queue[0].size() > 0) {
                    time0[0] = queue[0].get(0).d0 + stair[0].h + 1;
                }

                if (queue[0].size() > 1) {
                    time0[1] = queue[0].get(1).d0 + stair[0].h + 1;
                }

                if (queue[0].size() > 2) {
                    time0[2] = queue[0].get(2).d0 + stair[0].h + 1;
                }

                for (int i = 3; i < time0.length; i++) {
                    time0[i] = Math.max(time0[i - 3] + stair[0].h, queue[0].get(i).d0 + stair[0].h + 1);
                }

                int[] time1 = new int[queue[1].size()];

                if (queue[1].size() > 0) {
                    time1[0] = queue[1].get(0).d1 + stair[1].h + 1;
                }

                if (queue[1].size() > 1) {
                    time1[1] = queue[1].get(1).d1 + stair[1].h + 1;
                }

                if (queue[1].size() > 2) {
                    time1[2] = queue[1].get(2).d1 + stair[1].h + 1;
                }

                for (int i = 3; i < time1.length; i++) {
                    time1[i] = Math.max(time1[i - 3] + stair[1].h, queue[1].get(i).d1 + stair[1].h + 1);
                }

                int temp;

                if (time0.length == 0) {
                    temp = time1[time1.length - 1];
                    end = true;
                } else if (time1.length == 0) {
                    temp = time0[time0.length - 1];
                    queue[1].offer(queue[0].pollLast());
                } else {
                    temp = Math.max(time0[time0.length - 1], time1[time1.length - 1]);
                    queue[1].offer(queue[0].pollLast());
                }

                if (temp < result) {
                    result = temp;
                }
            }

            System.out.println("#" + t + " " + result);
        }

        sc.close();
    }

    static class Stair {
        int x;
        int y;
        int h;

        Stair(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    static class Person {
        int x;
        int y;
        int d0;
        int d1;

        Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Comparator<Person> comparator0 = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.d0 - o2.d0;
        }
    };

    static Comparator<Person> comparator1 = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.d1 - o2.d1;
        }
    };
}
