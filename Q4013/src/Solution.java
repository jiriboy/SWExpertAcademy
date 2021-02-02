import java.util.LinkedList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int test = sc.nextInt();

        for (int t = 1; t <= test; t++) {
            int k = sc.nextInt();

            LinkedList<Integer>[] mag = new LinkedList[4];

            for (int i = 0; i < 4; i++) {
                mag[i] = new LinkedList<>();
                for (int j = 0; j < 8; j++) {
                    mag[i].offer(sc.nextInt());
                }
            }

            for (int i = 0; i < k; i++) {
                int mag_num = sc.nextInt() - 1;
                int direction = sc.nextInt();

                int[] rotate = new int[4];
                rotate[mag_num] = direction;

                for (int j = mag_num; j > 0; j--) {
                    if (mag[j].get(6) != mag[j - 1].get(2)) {
                        rotate[j - 1] = -rotate[j];
                    } else {
                        break;
                    }
                }

                for (int j = mag_num; j < 3; j++) {
                    if (mag[j].get(2) != mag[j + 1].get(6)) {
                        rotate[j + 1] = -rotate[j];
                    } else {
                        break;
                    }
                }

                for (int j = 0; j < 4; j++) {
                    if (rotate[j] == -1) {
                        int temp = mag[j].poll();
                        mag[j].offer(temp);
                    } else if (rotate[j] == 1) {
                        int temp = mag[j].pollLast();
                        mag[j].offerFirst(temp);
                    }
                }
            }

            int result = 0;

            for (int i = 0; i < 4; i++) {
                if (mag[i].get(0) == 1) {
                    result += Math.pow(2, i);
                }
            }

            System.out.println("#" + t + " " + result);
        }

        sc.close();
    }
}
