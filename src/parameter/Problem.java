package parameter;

public interface Problem {
    double fit(double a, double b, double c, double d);
    boolean isNeighborBetter(double f0, double f1);
}
