package GUI;

import HANDLERS.Cl_BPMHandler;
import HANDLERS.Cl_MusicHandler;
import HANDLERS.Cl_QuitHandler;
import HANDLERS.Cl_VolumeHandler;

import javax.swing.*;

public class GUI {
    private JPanel MainPanel;
    private JTextArea textArea;
    private JButton saveTextButton;
    private JTextField volumeField;
    private JTextField BPMField;
    private JList instrumentList;
    private JButton QUITButton;
    private JProgressBar songProgressBar;
    private JButton PlayButton;
    private Cl_QuitHandler quitHandler = new Cl_QuitHandler();
    private Cl_BPMHandler BPMHandler = new Cl_BPMHandler();
    private Cl_VolumeHandler volumeHandler = new Cl_VolumeHandler();
    private Cl_MusicHandler musicHandler = new Cl_MusicHandler();

    public GUI() {
        QUITButton.addActionListener(quitHandler);
        BPMField.getDocument().addDocumentListener(BPMHandler);
        volumeField.getDocument().addDocumentListener(volumeHandler);
        textArea.getDocument().addDocumentListener(musicHandler);
        PlayButton.addActionListener(musicHandler);
    }

    public void buildGUI(){
        JFrame frame = new JFrame("Text to Music Generator");
        frame.setContentPane(new GUI().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
