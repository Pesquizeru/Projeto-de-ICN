public class DiferencasFinitas {

    // Aproxima solução de EDO usando o método de Euler
    // OBS: aqui fxy(x) representa RHS com dependência simplificada
    public static double[] euler(Function1D fxy, double x0, double y0, double h, int n) {

        double[] y = new double[n + 1];
        y[0] = y0;

        double x = x0;

        for (int i = 0; i < n; i++) {

            double dy = fxy.f(x);  // Caso simples — pode ser substituído por função com y incluído

            y[i + 1] = y[i] + h * dy;

            x += h;
        }

        return y;
    }
}
