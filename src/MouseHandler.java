import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

class MouseHandler implements MouseListener { 
    ImagePanel _imagePanel;

    public MouseHandler(ImagePanel imagePanel) {
        _imagePanel = imagePanel;
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) { 
        if (_imagePanel.processInput(e.getX(), e.getY())) {
            playClickSound();
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    private void playClickSound() { 
        try
        {
            String audioClickFileName = "do.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioClickFileName).getAbsoluteFile());
            Clip audioClick = AudioSystem.getClip();
            audioClick.open(audioInputStream);
            audioClick.start();
        } catch (Exception e) {}
    }
}
