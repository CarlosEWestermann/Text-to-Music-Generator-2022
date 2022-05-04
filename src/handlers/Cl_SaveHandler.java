package handlers;

import GUI.EmptySongDialog;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Cl_SaveHandler implements If_SaveHandler{

    private Pattern pattern;
    private final EmptySongDialog dialog = new EmptySongDialog();

    public Cl_SaveHandler(Pattern pattern){
        this.pattern = pattern;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!Objects.equals(pattern.toString(), "")) {
            try {
                File filePath = new File(System.getProperty("user.home") + "\\Desktop\\YourSong.midi");
                MidiFileManager.savePatternToMidi(pattern, filePath);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            dialog.pack();
            dialog.setVisible(true);
        }
    }
}
