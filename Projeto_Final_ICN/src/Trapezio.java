public class Trapezio {

    // Integração numérica pelo método do trapézio
    public static double trapezio(Function1D f, double a, double b, int n) {

        if (n <= 0) throw new IllegalArgumentException("n deve ser > 0");

        double h = (b - a) / n;
        double soma = 0.5 * (f.f(a) + f.f(b));

        for (int i = 1; i < n; i++)
            soma += f.f(a + i * h);

        return soma * h;
    }
}
