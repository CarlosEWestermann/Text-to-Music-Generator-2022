package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvalidFileDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextPane itSeemsLikeYouTextPane;
    private JTextPane yourSongSeemsToTextPane;


    public InvalidFileDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

}
