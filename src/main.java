
import handlers.Cl_LoadTXTHandler;
import handlers.Cl_MusicHandler;
import handlers.Cl_QuitHandler;
import handlers.Cl_SaveHandler;

import GUI.*;
import org.jfugue.pattern.Pattern;

public class main{
    public static void main(String[] args){
        Pattern pattern = new Pattern();
        Cl_QuitHandler quitHandler = new Cl_QuitHandler();
        Cl_MusicHandler musicHandler = new Cl_MusicHandler(pattern);
        Cl_SaveHandler saveHandler = new Cl_SaveHandler(pattern);
        Cl_LoadTXTHandler loadHandler = new Cl_LoadTXTHandler();
        GUI GUI = new GUI(musicHandler, quitHandler, saveHandler, loadHandler);
        GUI.buildGUI();
        loadHandler.setTextArea(GUI.getTextArea());
    }


}