package com.company;

import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {
    public ArrayList<Double> hist;
    public ArrayList<Double> hist_x;
    private int niter;

    public SimulatedAnnealing(int niter) {
        this.niter = niter;
        hist = new ArrayList<>();
        hist_x = new ArrayList<>();
    }

    public double solve(Problem p, double t, double a, double lower, double upper) {
        Random r = new Random();
        double x0 = r.nextDouble() * (upper - lower) + lower;
        return solve(p, t, a, x0, lower, upper);
    }

    // 온도, 냉각률, 초기값, 해의 범위의 최소, 최대
    public double solve(Problem p, double t, double a, double x0, double lower, double upper) {
        Random r = new Random();
        double f0 = p.fit(x0); // 함수값
        hist_x.add(x0);
        hist.add(f0);

        for (int i=0; i<niter; i++) {
            int kt = (int) t;
            for(int j=0; j<kt; j++) {
                double x1 = r.nextDouble() * (upper - lower) + lower; // 이웃해
                double f1 = p.fit(x1); // 이웃해의 함수값

                if(p.isNeighborBetter(f0, f1)) { // 이웃해가 더 좋은경우
                    x0 = x1;
                    f0 = f1;
                    hist_x.add(x0);
                    hist.add(f0);
                } else {
                    double d = Math.sqrt(Math.abs(f1 - f0));
                    double p0 = Math.exp(-d/t);
                    if(r.nextDouble() < p0) {
                        x0 = x1;
                        f0 = f1;
                        hist_x.add(x0);
                        hist.add(f0);
                    }
                }
            }
            t *= a;
        }
        return x0;
    }
}