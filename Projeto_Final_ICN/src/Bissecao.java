public class Bissecao {

    // Método da Bisseção para encontrar raiz em intervalo [a,b] com tolerância 'tol'
    public static double bissecao(Function1D f, double a, double b, double tol) {

        double fa = f.f(a);
        double fb = f.f(b);

        // Verifica troca de sinal
        if (fa * fb > 0) {
            throw new RuntimeException("Não há troca de sinal no intervalo.");
        }

        double m = (a + b) / 2.0;
        double fm = f.f(m);

        // Critério de parada: intervalo pequeno ou f(m) próximo de zero
        while ((b - a) / 2 > tol && Math.abs(fm) > tol) {
            m = (a + b) / 2.0;
            fm = f.f(m);

            if (fa * fm <= 0) {
                b = m;
                fb = fm;
            } else {
                a = m;
                fa = fm;
            }
        }

        return m;
    }
}
