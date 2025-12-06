public class Lagrange {

    // Retorna um polinômio interpolador F(x) usando o método de Lagrange
    public static Function1D polinomioLagrange(double[] x, double[] y) {

        return (double xp) -> {
            double soma = 0;

            for (int i = 0; i < x.length; i++) {

                double Li = 1;

                for (int j = 0; j < x.length; j++) {
                    if (i != j)
                        Li *= (xp - x[j]) / (x[i] - x[j]);
                }

                soma += y[i] * Li;
            }

            return soma;
        };
    }
}
