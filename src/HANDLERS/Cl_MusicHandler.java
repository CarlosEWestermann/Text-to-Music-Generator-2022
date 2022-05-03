package HANDLERS;

import org.jetbrains.annotations.NotNull;
import org.jfugue.player.Player;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import org.jfugue.pattern.Pattern;

import ENCODER.Cl_StringEncoder;


public class Cl_MusicHandler implements If_MusicHandler{

    private String originalSongString = null;
    private final Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
    private Pattern currentPattern;

    public Pattern getCurrentPattern() {
        return currentPattern;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(originalSongString != null){
            Player player = new Player();
            String encodedSong = stringEncoder.parseString(originalSongString);
            Pattern pattern = new Pattern(encodedSong);
            currentPattern = pattern;
            player.play(pattern);
        }//add else warning statement
    }

    @Override
    public void insertUpdate(@NotNull DocumentEvent e) {
        int songSize = e.getDocument().getLength();
        try {
            originalSongString = e.getDocument().getText(0, songSize);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        originalSongString = null;
    }

    @Override
    public void changedUpdate(@NotNull DocumentEvent e) {
        int songSize = e.getDocument().getLength();
        try {
            originalSongString = e.getDocument().getText(0, songSize);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }


}
