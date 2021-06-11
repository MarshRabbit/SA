package parameter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class SimulatedAnnealing {
    public void solve(Problem p, double t, double a, double lower, double upper, double[] X_hat) {
        Random r = new Random();
        //초기값
        double a0 = 0;
        double b0 = 0;
        double c0 = 0;
        double d0 = 0;

        while (true) {
            a0 = r.nextDouble() * (upper - lower) + lower;
            b0 = r.nextDouble() * (upper - lower) + lower;
            c0 = r.nextDouble() * (upper - lower) + lower;
            d0 = r.nextDouble() * (upper - lower) + lower;

            if (b0*b0 > 3*a0*c0 && a0>0 && b0<0 && c0>0 && d0>0) break; // 3차함수의 개형 설정
        }

        double f0 = p.fit(a0, b0, c0, d0);


        while (t > 0.01) {
            int kt = (int) t;
            for (int j=0; j<kt; j++) {
                // 이웃해
                double a1 = 0;
                double b1 = 0;
                double c1 = 0;
                double d1 = 0;

                while (true) {
                    a1 = r.nextDouble() * (upper - lower) + lower;
                    b1 = r.nextDouble() * (upper - lower) + lower;
                    c1 = r.nextDouble() * (upper - lower) + lower;
                    d1 = r.nextDouble() * (upper - lower) + lower;

                    if (b1*b1 > 3*a1*c1 && a1>0 && b1<0 && c1>0 && d1>0) // 3차함수의 개형 설정
                        break;
                }

                double f1 = p.fit(a1, b1, c1, d1);

                if(p.isNeighborBetter(f0, f1)) {
                    a0 = a1;
                    b0 = b1;
                    c0 = c1;
                    d0 = d1;
                    f0 = f1;
                } else {
                    double d = Math.sqrt(Math.abs(f1 - f0));
                    double p0 = Math.exp(-d/t);
                    if (r.nextDouble() < p0) {
                        a0 = a1;
                        b0 = b1;
                        c0 = c1;
                        d0 = d1;
                        f0 = f1;
                    }
                }

                Arrays.sort(X_hat);
                if (X_hat[0] < 0) { // 원래 데이터엔 음수가 없으므로 만들어진 함수에 음수가 있을땐 다시 만든다
                    j--;
                    continue;
                }
            }
            t *= a;
        }
        System.out.printf("%.2fx^3 + %.2fx^2 + %.2fx + %.2f\n", a0, b0, c0, d0);
    }
}
