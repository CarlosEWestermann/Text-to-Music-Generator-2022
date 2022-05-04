package handlers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import GUI.InvalidFileDialog;

import static constants.NumericConstants.txtExtensionLength;

public class Cl_LoadTXTHandler implements If_LoadTXTHandler{
    public FileDialog fd;
    private JTextArea textArea;
    private final InvalidFileDialog dialog = new InvalidFileDialog();
    public void setTextArea(JTextArea textArea){
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        fd.setVisible(true);

        String path = fd.getDirectory() + fd.getFile();
        if (path.length() >= txtExtensionLength && path.substring(path.length()-txtExtensionLength).equals(".txt")) {
            // ve se eh txt
            System.out.println(path);

            FileReader fr = null;
            try {
                fr = new FileReader(path);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            int i = 0;
            String readString = "";
            while (true) {
                try {
                    if ((i = fr.read()) == -1) break;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                readString += (char) i;
            }
            textArea.setText(readString);
        }
        else {
            dialog.pack();
            dialog.setVisible(true);
        }
    }
}
