package com.company;

public class Main {
    public static void main(String[] args) {
        SimulatedAnnealing sa = new SimulatedAnnealing(1000);
        Problem p = new Problem() {
            @Override
            public double fit(double x) {
                return -3*x*x*x*x + 4*x*x*x - 1;
                //return -x*x + 38*x + 80;
                // x=19 , f(x)=441
            }

            @Override
            public boolean isNeighborBetter(double f0, double f1) { //최대값 찾
                return f0 < f1;
            }
        };
        double x = sa.solve(p, 100, 0.99, 0, 0, 31);
        System.out.println(x);
        System.out.println(p.fit(x));
        //System.out.println(sa.hist);
    }
}
