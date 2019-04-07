/**
 * Created by hk on 2019/4/7.
 */
public class Fib {

	 /**
     * 返回斐波那契数第n个值,n从0开始
     * 实现方式，基于递归实现
     */
    public static int getFib(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return getFib(n - 1) + getFib(n - 2);
        }
    }

    /**
     * 返回斐波那契数第n个值,n从0开始
     * 实现方式，基于变量实现
     */
    public static int getFib2(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            int c = 0, a = 1, b = 1;
            for (int i = 3; i <= n; i++) {
                c = a + b;
                a = b;
                b = c;
            }
            return c;
        }
    }
}
