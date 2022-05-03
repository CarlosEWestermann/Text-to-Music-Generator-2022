package HANDLERS;

import GUI.EmptySongDialog;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Cl_SaveHandler implements If_SaveHandler{

    private Cl_MusicHandler musicHandler;
    private EmptySongDialog dialog = new EmptySongDialog();

    public Cl_SaveHandler(Cl_MusicHandler musicHandler){
        this.musicHandler = musicHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Pattern currentPattern = musicHandler.getCurrentPattern();
        if(currentPattern != null) {
            try {
                File filePath = new File(System.getProperty("user.home") + "\\Desktop\\YourSong.midi");
                MidiFileManager.savePatternToMidi(currentPattern, filePath);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            dialog.pack();
            dialog.setVisible(true);
        }
    }
}
