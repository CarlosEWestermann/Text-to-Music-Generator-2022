package encoder;

import static constants.translationConstants.*;
import static constants.NumericConstants.*;

public class Cl_StringEncoder implements If_StringEncoder{

    private int currOctave;
    private int currVolume;
    private String parsedSong;
    private int currInstrumentVal;
    boolean lastWasNote;
    private char lastNote;

    public char getLastNote() {
        return lastNote;
    }

    public boolean lastWasNote() {
        return lastWasNote;
    }

    public int getCurrInstrumentVal() {
        return currInstrumentVal;
    }

    public int getCurrOctave() {
        return currOctave;
    }

    public int getCurrVolume() {
        return currVolume;
    }

    @Override
    public String parseString(String Song) {
        setInitialState();
        for (int i = 0; i < Song.length(); i++) {
            switch(Song.charAt(i)){
                case La:
                    encodeNote(La);
                break;

                case Si:
                    encodeNote(Si);
                break;

                case Do:
                    encodeNote(Do);
                break;

                case Re:
                    encodeNote(Re);
                break;

                case Mi:
                    encodeNote(Mi);
                break;

                case Fa:
                    encodeNote(Fa);
                break;

                case Sol:
                    encodeNote(Sol);
                break;

                case LowerA:
                case LowerB:
                case LowerC:
                case LowerD:
                case LowerE:
                case LowerF:
                case LowerG:
                    if(lastWasNote){
                        encodeNote(lastNote);
                    }
                    else{
                        encodePause();
                    }
                break;

                case LowerO:
                case LowerU:
                case LowerI:
                case UpperO:
                case UpperU:
                case UpperI:
                    encodeInstrument(HarpsiChord);
                break;

                case SwitchToAgogo:
                    encodeInstrument(Agogo);
                break;

                case SwitchToTubularBells:
                    encodeInstrument(TubularBells);
                break;

                case SwitchToPanFlute:
                    encodeInstrument(PanFlute);
                break;

                case SwitchToChurchOrgan:
                    encodeInstrument(ChurchOrgan);
                break;

                case IncreaseVolume:
                    updateVolume();
                break;

                case IncreaseOctave:
                    updateOctave();
                break;

                default:
                    if(Character.isDigit(Song.charAt(i))){
                        updateInstrumentFromNumeric(i, Song);
                    }
                    else{
                        if(lastWasNote){
                            encodeNote(lastNote);
                        }
                        else{
                            encodePause();
                        }
                    }
                break;
            }

            addOctave();
        }
        System.out.print(parsedSong);
        return parsedSong;
    }

    private void encodeNote(char Note){
        parsedSong += Note;
        lastNote = Note;
        lastWasNote = true;
    }

    private void encodePause(){
        parsedSong += Pause;
        lastWasNote = false;
    }

    private void encodeInstrument(int newInstrument){
        String instrument = "I" + newInstrument + " ";
        currInstrumentVal = newInstrument;
        parsedSong = parsedSong.concat(instrument);
        lastWasNote = false;
    }

    private void updateVolume(){
        if(currVolume * 2 > MaximalVolume){
            currVolume = DefaultVolume;
        }
        else{
            currVolume *= 2;
        }
        String addedString = ":CON(7, " + currVolume + ")";
        parsedSong = parsedSong.concat(addedString);
        lastWasNote = false;
    }

    private void updateOctave(){
        currOctave += 1;
        if(currOctave >= MaxOctave)
            currOctave = DefaultOctave;
        lastWasNote = false;
    }

    private void updateInstrumentFromNumeric(int currentIndex, String Song){
        int newInstrument = currInstrumentVal + Character.getNumericValue(Song.charAt(currentIndex));
        if(newInstrument < MaximalInstrumentValue && newInstrument >= DefaultInstrumentValue){
            encodeInstrument(newInstrument);
        }
        else{
            encodeInstrument(0);
        }
    }

    private void addOctave(){
        if(lastWasNote) {
            parsedSong += String.valueOf(currOctave);
        }
        parsedSong += ' ';
    }

    private void setInitialState(){
        parsedSong = "";
        currVolume = DefaultVolume;
        currOctave = DefaultOctave;
        currInstrumentVal = DefaultInstrumentValue;
        lastWasNote = false;
        lastNote = ' ';
    }
}
