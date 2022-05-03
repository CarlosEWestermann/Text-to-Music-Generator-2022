package HANDLERS;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Cl_SaveHandler implements If_SaveHandler{

    private Cl_MusicHandler musicHandler;

    public Cl_SaveHandler(Cl_MusicHandler musicHandler){
        this.musicHandler = musicHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Pattern currentPattern = musicHandler.getCurrentPattern();
        try {
            File filePath = new File(System.getProperty("user.home"));
            MidiFileManager.savePatternToMidi(currentPattern, filePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
