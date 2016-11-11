
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author leo
 */
public class WaveTextEffectPanel extends JPanel {
    
    private BufferedImage offscreen;
    private String textMessage = "THIS IS A WAVE TEXT EFFECT DEMO !!!";
    private Font textFont = new Font("impact", Font.PLAIN, 150);
    private double textPositionX, textPositionY;
    
    public WaveTextEffectPanel(int width, int height) {
        offscreen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        textPositionX = width;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderWaveText(offscreen.getGraphics());
        g.drawImage(offscreen, 0, 0, null);
        textPositionX -= 2;
        repaint();
    }
    
    private void renderWaveText(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setFont(textFont);
        textPositionY = getHeight() / 1.75;
        g.drawString(textMessage, (int) textPositionX, (int) textPositionY);
        for (int x = 0; x < getWidth(); x++) {
            int y = (int) (-50 + 50 * Math.sin(x * 0.015));
            y += (int) (-25 + 25 * Math.cos(x * 0.03 + System.currentTimeMillis() * 0.01));
            g.drawImage(offscreen, x, y, x + 1, y + offscreen.getHeight()
                    , x, 0, x + 1, offscreen.getHeight(), null);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setTitle("Wave Text Effect Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.getContentPane().add(new WaveTextEffectPanel(800, 600));
                frame.setVisible(true);
            }
        });
    }
    
}
