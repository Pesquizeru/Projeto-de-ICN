public class Taylor {

    // fDerivs[n] = n-ésima derivada de f avaliada no ponto 'a'
    public static double taylor(double x, double a, double[] fDerivs, int ordem) {
        double resultado = 0;
        double termo = 1;

        for (int n = 0; n <= ordem && n < fDerivs.length; n++) {
            if (n > 0) termo *= (x - a);
            resultado += fDerivs[n] * termo / fatorial(n);
        }

        return resultado;
    }

    // Calcula fatorial simples
    private static long fatorial(int n) {
        long r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }
}
