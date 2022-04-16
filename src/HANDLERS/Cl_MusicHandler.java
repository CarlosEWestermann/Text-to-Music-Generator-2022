package HANDLERS;

import org.jetbrains.annotations.NotNull;
import org.jfugue.player.Player;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import org.jfugue.pattern.Pattern;


public class Cl_MusicHandler implements If_MusicHandler{

    private String encodedSong = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(encodedSong);
        if(encodedSong != null){
            Player player = new Player();
            Pattern pattern = new Pattern(encodedSong);
            player.play(pattern);
        }//add else warning statement
    }

    @Override
    public void insertUpdate(@NotNull DocumentEvent e) {
        int songSize = e.getDocument().getLength();
        try {
            encodedSong = e.getDocument().getText(0, songSize);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        encodedSong = null;
    }

    @Override
    public void changedUpdate(@NotNull DocumentEvent e) {
        int songSize = e.getDocument().getLength();
        try {
            encodedSong = e.getDocument().getText(0, songSize);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
}
