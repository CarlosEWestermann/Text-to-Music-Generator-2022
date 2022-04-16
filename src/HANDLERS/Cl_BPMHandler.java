package HANDLERS;

import javax.swing.event.DocumentEvent;

import javax.swing.text.BadLocationException;


public class Cl_BPMHandler implements If_BPMHandler {

    private float Current_BPM;

    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            int length = e.getDocument().getLength();
            Current_BPM = Float.parseFloat(e.getDocument().getText(0, length));
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        Current_BPM = 0;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        try {
            int length = e.getDocument().getLength();
            Current_BPM = Float.parseFloat(e.getDocument().getText(0, length));
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public float Get_Current_BPM() {
        return Current_BPM;
    }
}
