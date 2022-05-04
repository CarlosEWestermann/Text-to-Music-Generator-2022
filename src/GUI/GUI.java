package GUI;

import constants.translationConstants;
import handlers.Cl_LoadTXTHandler;
import handlers.Cl_MusicHandler;
import handlers.Cl_QuitHandler;
import handlers.Cl_SaveHandler;

import javax.swing.*;
import java.awt.*;



public class GUI {


    private JPanel MainPanel;
    private JTextArea textArea;
    private JButton loadTextButton;
    private JButton QUITButton;
    private JButton PlayButton;
    private JTextPane InstructionsPane;
    private JButton SaveMIDIButton;
    private Cl_QuitHandler quitHandler;
    private Cl_MusicHandler musicHandler;
    private Cl_SaveHandler saveHandler;
    private Cl_LoadTXTHandler loadHandler;
    public GUI(Cl_MusicHandler musicHandler, Cl_QuitHandler quitHandler, Cl_SaveHandler saveHandler, Cl_LoadTXTHandler loadHandler) {
        this.musicHandler = musicHandler;
        this.quitHandler = quitHandler;
        this.saveHandler = saveHandler;
        this.loadHandler = loadHandler;
        QUITButton.addActionListener(quitHandler);
        textArea.getDocument().addDocumentListener(musicHandler);
        PlayButton.addActionListener(musicHandler);
        SaveMIDIButton.addActionListener(saveHandler);
        loadTextButton.addActionListener(loadHandler);
        setInstructionsPane();
    }

    public void buildGUI(){
        JFrame frame = new JFrame("Text to Music Generator");
        frame.setContentPane(new GUI(musicHandler, quitHandler, saveHandler, loadHandler).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        loadHandler.fd = new FileDialog(frame, "File Explorer", FileDialog.LOAD);
    }

    public JTextArea getTextArea(){
        return textArea;
    }

    private void setInstructionsPane(){
        String string = "COMMANDS:\n\n";

        string += translationConstants.La + " -> note Lá\n";
        string += translationConstants.Si + " -> note Si\n";
        string += translationConstants.Do + " -> note Dó\n";
        string += translationConstants.Re + " -> note Ré\n";
        string += translationConstants.Mi + " -> note Mi\n";
        string += translationConstants.Fa + " -> note Fá\n";
        string += translationConstants.Sol + " -> note Sol\n\n";

        string += translationConstants.IncreaseVolume + " -> double volume (MAX 127)\n";
        string += translationConstants.IncreaseOctave + " -> increase one octave (MAX 10)\n\n";

        string += "! -> switch instrument to Agogo (#114)\n";
        string += "\\n -> switch instrument to Tubular Bells (#15)\n";
        string += "; -> switch instrument to Pan Flute (#76)\n";
        string += ", -> switch instrument to Church Organ (#20)\n";
        string += "U, u, I, i, O, o -> switch instrument to Harpsichord (#7)\n";
        string += "Digit odd or even -> switch instrumento to #current instrument + digit\n\n";

        string += "a, b, c, d, e, f, g, consonants that aren't a note and any other character -> if last char is a" +
                "note (A to G), repeat it, else pause\n\n";

        string += "Please play your song before saving! :)\n";

        InstructionsPane.setText(string);
    }
}
