package com.company;

public class Main {
    public static void main(String[] args) {
        SimulatedAnnealing sa_q = new SimulatedAnnealing(1000);
        SimulatedAnnealing sa_c = new SimulatedAnnealing(1000);

        Problem p_quartic = new Problem() {
            @Override
            public double fit(double x) {
                return -3*x*x*x*x + 4*x*x*x - 1; //1에서 최대
            }

            @Override
            public boolean isNeighborBetter(double f0, double f1) { //최대값 찾기
                return f0 < f1;
            }
        };

        Problem p_cubic = new Problem() {
            @Override
            public double fit(double x) {
                return -2*x*x*x - 2*x*x + 84*x; // 3.423에서 극대 -4.09에서 극소
            }

            @Override
            public boolean isNeighborBetter(double f0, double f1) {
                return f0 > f1; // 극소값
            }
        };

        double x1 = sa_q.solve(p_quartic, 1000, 0.95,-1, -5, 5);
        double x2 = sa_c.solve(p_cubic, 1000, 0.99, -7,6);

        System.out.println(x1);
        System.out.println(p_quartic.fit(x1));
       // System.out.println(sa_q.hist_x+"\n");

        System.out.println(x2);
        System.out.println(p_cubic.fit(x2));
    }
}
