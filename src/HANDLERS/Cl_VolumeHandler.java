package HANDLERS;

import org.jetbrains.annotations.NotNull;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

public class Cl_VolumeHandler implements If_VolumeHandler {

    private float Current_Volume;

    @Override
    public void insertUpdate(@NotNull DocumentEvent e) {
        try {
            int length = e.getDocument().getLength();
            Current_Volume = Float.parseFloat(e.getDocument().getText(0, length));
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        Current_Volume = 0;
    }

    @Override
    public void changedUpdate(@NotNull DocumentEvent e) {
        try {
            int length = e.getDocument().getLength();
            Current_Volume = Float.parseFloat(e.getDocument().getText(0, length));
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public float Get_current_Volume() {
        return Current_Volume;
    }
}
