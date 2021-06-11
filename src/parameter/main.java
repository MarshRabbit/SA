package parameter;

public class main {
    public static void main(String[] args) {
        SimulatedAnnealing sa = new SimulatedAnnealing();
        int[] X = {1765, 3538, 8800, 9443, 11195, 14560, 5762, 17232};
        double[] X_hat = new double[8];

        Problem p = new Problem() {
            @Override
            public double fit(double a, double b, double c, double d) {
                double sum = 0;
                for (int i=0; i<8; i++) {
                    X_hat[i] = a*i*i*i + b*i*i + c*i + d;
                    sum += Math.abs(X[i] - X_hat[i]);
                }
                return sum;
            }

            @Override
            public boolean isNeighborBetter(double f0, double f1) {
                return f0 > f1;
            }
        };

        sa.solve(p, 1000, 0.996, -20000, 20000, X_hat);
    }
}
