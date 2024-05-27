public class _test {
    public static boolean moveRightStep(int []a, boolean []merged) {
        boolean res = false;
        for (int i = a.length - 1; i >= 0; i --) {
            if (a[i] == 0) {
                if (i != 0 && a[i - 1] != 0) {
                    a[i] = a[i - 1];
                    merged[i] = merged[i - 1];
                    a[i - 1] = 0;
                    res = true;
                }
            } else {
                if (i != 0 && a[i - 1] == a[i] && !merged[i] && !merged[i - 1]) {
                    a[i - 1] = 0;
                    a[i] *= 2;
                    merged[i] = true;
                    res = true;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int []a = {2, 2, 0, 4, 2, 0};
        int n = a.length;
        boolean []merged = new boolean[n];
        int round = 0;
        while (true) {
            if (!moveRightStep(a, merged))
                break;
            round ++;
            System.out.printf("round %d:", round);
            for (int i = 0; i < n; i ++) {
                System.out.printf(" %d", a[i]);
            }
            System.out.println();
        }
    }
}
