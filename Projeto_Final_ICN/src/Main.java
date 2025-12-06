import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Executando Mini-Biblioteca de ICN\n");

        //
        // ======= TAYLOR =======
        //
        System.out.println("=== Taylor ===");

        double[] eDerivs = {1,1,1,1,1};
        System.out.println("Aprox e^0.5 (ordem 4): " + Taylor.taylor(0.5, 0, eDerivs, 4));

        double[] sinDerivs = {0,1,0,-1,0,1};
        System.out.println("Aprox sin(0.5) (ordem 5): " + Taylor.taylor(0.5, 0, sinDerivs, 5));

        double[] lnSeries = {0,1,-1,2.0/3.0,-6.0/24.0};
        System.out.println("Aprox ln(1.3) (ordem 3): " + Taylor.taylor(0.3, 0, lnSeries, 3));

        //
        // ======= RAÍZES =======
        //
        System.out.println("\n=== Bisseção e Newton ===");

        double r1b = Bissecao.bissecao(x -> x*x - 2, 0, 2, 1e-8);
        double r1n = Newton.newton(x -> x*x - 2, x -> 2*x, 1, 1e-12, 100);
        System.out.println("sqrt(2): bisseção=" + r1b + ", newton=" + r1n);

        double r2b = Bissecao.bissecao(x -> Math.cos(x) - x, 0, 1, 1e-8);
        double r2n = Newton.newton(x -> Math.cos(x) - x, x -> -Math.sin(x) - 1, 0.5, 1e-12, 100);
        System.out.println("cos(x)-x: bisseção=" + r2b + ", newton=" + r2n);

        double r3b = Bissecao.bissecao(x -> x*x*x - 2*x - 5, 1, 3, 1e-8);
        double r3n = Newton.newton(x -> x*x*x - 2*x - 5, x -> 3*x*x - 2, 2, 1e-12, 100);
        System.out.println("x^3-2x-5: bisseção=" + r3b + ", newton=" + r3n);

        //
        // ======= LAGRANGE =======
        //
        System.out.println("\n=== Lagrange ===");

        double[] xa = {0,1,2};
        double[] ya = {1,3,2};

        Function1D P = Lagrange.polinomioLagrange(xa, ya);
        System.out.println("P(1.5) = " + P.f(1.5));

        // Gera gráfico
        int N = 200;
        double[] xs = new double[N];
        double[] ys = new double[N];

        for (int i=0;i<N;i++) {
            xs[i] = i * (2.0/(N-1));
            ys[i] = P.f(xs[i]);
        }

        SimplePlot.plotXY(xs, ys, "Polinômio de Lagrange", "src/lagrange.png");

        //
        // ======= TRAPÉZIO =======
        //
        System.out.println("\n=== Trapézio ===");

        double I1 = Trapezio.trapezio(Math::sin, 0, Math.PI, 1000);
        System.out.println("Integral sin de 0 a pi = " + I1);

        //
        // ======= DIFERENÇAS FINITAS =======
        //
        System.out.println("\n=== Diferenças Finitas (Euler) ===");

        double h = 0.01;
        int passos = 300;

        Function1D rhs = (x) -> -2 * Math.exp(-2*x);

        double[] ysol = DiferencasFinitas.euler(rhs, 0, 1, h, passos);

        //
        // Gera animação
        //


        List<String> frames = new ArrayList<>();

        for (int f=0; f<20; f++) {
            int fim = Math.max(2, (int)((f+1) / 20.0 * ysol.length));

            double[] xframe = new double[fim];
            double[] yframe = new double[fim];

            for (int i=0; i<fim; i++) {
                xframe[i] = i*h;
                yframe[i] = ysol[i];
            }

            String fn = "src/frames/frame_" + f + ".png";
            SimplePlot.plotXY(xframe, yframe, "Euler parcial", fn);
            frames.add(fn);
        }

        Animator.animar(frames, "Animação Euler", 300);

        //
        // Relatório
        //
        try (PrintWriter out = new PrintWriter(new FileWriter("src/relatorio.txt"))) {
            out.println("Relatório gerado automaticamente");
            out.println("Aproximação sin integral = " + I1);
            out.println("Raiz sqrt(2) Newton = " + r1n);
        }

        System.out.println("\nArquivos prontos na pasta src/frames");
    }
}
