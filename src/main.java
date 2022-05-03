
import HANDLERS.Cl_LoadTXThandler;
import HANDLERS.Cl_MusicHandler;
import HANDLERS.Cl_QuitHandler;
import HANDLERS.Cl_SaveHandler;

import GUI.*;

public class main{
    public static void main(String[] args){
        Cl_QuitHandler quitHandler = new Cl_QuitHandler();
        Cl_MusicHandler musicHandler = new Cl_MusicHandler();
        Cl_SaveHandler saveHandler = new Cl_SaveHandler(musicHandler);
        Cl_LoadTXThandler loadHandler = new Cl_LoadTXThandler();
        GUI GUI = new GUI(musicHandler, quitHandler, saveHandler, loadHandler);
        GUI.buildGUI();
    }
}