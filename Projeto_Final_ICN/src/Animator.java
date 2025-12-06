import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;

// Exibe animação simples mostrando PNGs em sequência
public class Animator {

    public static void animar(List<String> frames, String titulo, int delayMs) {

        SwingUtilities.invokeLater(() -> {

            JFrame janela = new JFrame(titulo);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel label = new JLabel();
            janela.add(label);

            janela.pack();
            janela.setSize(800, 600);
            janela.setVisible(true);

            new Timer(delayMs, e -> {

                if (frames.isEmpty()) return;

                String fn = frames.remove(0);

                try {
                    label.setIcon(new ImageIcon(ImageIO.read(new File(fn))));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }).start();
        });
    }
}
