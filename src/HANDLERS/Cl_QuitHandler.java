package HANDLERS;

import java.awt.event.ActionEvent;

public class Cl_QuitHandler implements If_QuitHandler {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
