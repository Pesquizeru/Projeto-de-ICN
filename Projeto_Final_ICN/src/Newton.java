public class Newton {

    // Método de Newton para raízes: precisa da derivada df
    public static double newton(Function1D f, Function1D df, double x0, double tol, int maxIter) {

        double x = x0;

        for (int i = 0; i < maxIter; i++) {

            double fx = f.f(x);
            double dfx = df.f(x);

            if (Math.abs(dfx) < 1e-14)
                throw new RuntimeException("Derivada muito pequena — risco de divisão por zero.");

            double x1 = x - fx / dfx;

            if (Math.abs(x1 - x) < tol)
                return x1;

            x = x1;
        }

        return x;
    }
}
