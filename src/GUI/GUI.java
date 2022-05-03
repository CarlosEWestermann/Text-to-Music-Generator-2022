package GUI;

import HANDLERS.Cl_LoadTXThandler;
import HANDLERS.Cl_MusicHandler;
import HANDLERS.Cl_QuitHandler;
import HANDLERS.Cl_SaveHandler;

import javax.swing.*;

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
    private Cl_LoadTXThandler loadHandler;
    public GUI(Cl_MusicHandler musicHandler, Cl_QuitHandler quitHandler, Cl_SaveHandler saveHandler, Cl_LoadTXThandler loadHandler) {
        this.musicHandler = musicHandler;
        this.quitHandler = quitHandler;
        this.saveHandler = saveHandler;
        this.loadHandler = loadHandler;
        QUITButton.addActionListener(quitHandler);
        textArea.getDocument().addDocumentListener(musicHandler);
        PlayButton.addActionListener(musicHandler);
        SaveMIDIButton.addActionListener(saveHandler);
        loadTextButton.addActionListener(loadHandler);
    }


    public void buildGUI(){
        JFrame frame = new JFrame("Text to Music Generator");
        frame.setContentPane(new GUI(musicHandler, quitHandler, saveHandler, loadHandler).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
