import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {
    private File file;
    private Clip clip;
    public Audio(String path) {
        file=new File(path);
        try {
            clip= AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
        }catch (Exception err) {
            System.out.println("Error playing file");
        }
    }
    public void play() {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void playNormal(){
        clip.start();
    }
}
