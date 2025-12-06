import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

// Classe simples para gerar gráficos em PNG usando Java2D
public class SimplePlot {

    public static void plotXY(double[] xs, double[] ys, String titulo, String arquivo) {

        int w = 800, h = 600, margem = 60;

        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();

        // Fundo branco
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);

        g.setColor(Color.BLACK);
        g.drawString(titulo, margem, 20);

        // Descobre limites dos dados
        double xmin = xs[0], xmax = xs[0];
        double ymin = ys[0], ymax = ys[0];

        for (int i = 0; i < xs.length; i++) {
            xmin = Math.min(xmin, xs[i]);
            xmax = Math.max(xmax, xs[i]);
            ymin = Math.min(ymin, ys[i]);
            ymax = Math.max(ymax, ys[i]);
        }

        if (Math.abs(xmax - xmin) < 1e-12) xmax = xmin + 1;
        if (Math.abs(ymax - ymin) < 1e-12) ymax = ymin + 1;

        // Eixos
        g.drawRect(margem, margem, w - 2*margem, h - 2*margem);

        // Desenha linhas
        for (int i = 0; i < xs.length - 1; i++) {

            int x1 = margem + (int)((xs[i] - xmin) / (xmax - xmin) * (w - 2*margem));
            int y1 = margem + (int)((ymax - ys[i]) / (ymax - ymin) * (h - 2*margem));

            int x2 = margem + (int)((xs[i+1] - xmin) / (xmax - xmin) * (w - 2*margem));
            int y2 = margem + (int)((ymax - ys[i+1]) / (ymax - ymin) * (h - 2*margem));

            g.drawLine(x1, y1, x2, y2);
        }

        try {
            ImageIO.write(img, "png", new File(arquivo));
        } catch (Exception e) {
            e.printStackTrace();
        }

        g.dispose();
    }
}
