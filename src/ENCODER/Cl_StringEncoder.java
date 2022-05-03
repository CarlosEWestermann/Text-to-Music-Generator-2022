package ENCODER;

import static CONSTANTS.translationConstants.*;
import static CONSTANTS.NumericConstants.*;

public class Cl_StringEncoder implements If_StringEncoder{

    private int currOctave = DefaultOctave;
    private int currVolume = DefaultVolume;
    private int currInstrumentVal = DefaultInstrumentValue;

    @Override
    public String parseString(String Song) {
        boolean lastWasNote = false;
        char lastNote = ' ';
        String instrument;
        String parsedSong = "";

        for (int i = 0; i < Song.length(); i++) {
            switch(Song.charAt(i)){
                case La:
                    parsedSong += La;
                    lastNote = La;
                    lastWasNote = true;
                break;
                case Si:
                    parsedSong += Si;
                    lastNote = Si;
                    lastWasNote = true;
                break;
                case Do:
                    parsedSong += Do;
                    lastNote = Do;
                    lastWasNote = true;
                break;
                case Re:
                    parsedSong += Re;
                    lastNote = Re;
                    lastWasNote = true;
                break;
                case Mi:
                    parsedSong += Mi;
                    lastNote = Mi;
                    lastWasNote = true;
                break;
                case Fa:
                    parsedSong += Fa;
                    lastNote = Fa;
                    lastWasNote = true;
                break;
                case Sol:
                    parsedSong += Sol;
                    lastNote = Sol;
                    lastWasNote = true;
                break;
                case LowerA:
                case LowerB:
                case LowerC:
                case LowerD:
                case LowerE:
                case LowerF:
                case LowerG:
                    if(lastWasNote){
                        parsedSong += lastNote;
                        lastWasNote = true;
                    }
                    else{
                        parsedSong += Pause;
                        lastWasNote = false;
                    }

                    break;
                case LowerO:
                case LowerU:
                case LowerI:
                case UpperO:
                case UpperU:
                case UpperI:
                    instrument = "I[HARPSICHORD]";
                    currInstrumentVal = HarpsiChord;
                    parsedSong = parsedSong.concat(instrument);

                    lastWasNote = false;
                break;
                case IncreaseVolume:
                    if(currVolume * 2 > MaximalVolume){
                        currVolume = DefaultVolume;
                    }
                    else{
                        currVolume *= 2;
                    }
                    String addedString = ":CON(7, " + currVolume + ")";
                    parsedSong = parsedSong.concat(addedString);

                    lastWasNote = false;
                break;
                case SwitchToAgogo:
                    instrument = "I[AGOGO]";
                    currInstrumentVal = Agogo;
                    parsedSong = parsedSong.concat(instrument);

                    lastWasNote = false;
                break;
                case IncreaseOctave:
                    currOctave += 1;
                    if(currOctave >= MaxOctave)
                        currOctave = DefaultOctave;
                    lastWasNote = false;
                break;
                case SwitchToTubularBells:
                    instrument = "I[TUBULAR_BELLS]";
                    currInstrumentVal = TubularBells;
                    parsedSong = parsedSong.concat(instrument);

                    lastWasNote = false;
                break;
                case SwitchToPanFlute:
                    instrument = "I[PAN_FLUTE]";
                    currInstrumentVal = PanFlute;
                    parsedSong = parsedSong.concat(instrument);

                    lastWasNote = false;
                break;
                case SwitchToChurchOrgan:
                    instrument = "I[CHURCH_ORGAN]";
                    currInstrumentVal = ChurchOrgan;
                    parsedSong = parsedSong.concat(instrument);

                    lastWasNote = false;
                break;
                default:
                    if(Character.isDigit(Song.charAt(i))){
                        StringBuilder numValue = new StringBuilder();
                        while(Character.isDigit(Song.charAt(i))){
                            numValue.append(Song.charAt(i));
                            i = i + 1;
                        }
                        String newInstrument = String.valueOf(currInstrumentVal + Integer.parseInt(numValue.toString(), RADIX));
                        if(Integer.parseInt(newInstrument) < MaximalInstrumentValue && Integer.parseInt(newInstrument) >= DefaultInstrumentValue){
                            instrument = "I" + newInstrument + " ";
                        }
                        else{
                            instrument = "I0 ";
                        }
                        parsedSong = parsedSong.concat(instrument);

                        lastWasNote = false;
                    }
                    else{
                        if(lastWasNote){
                            parsedSong += lastNote;
                            lastWasNote = true;
                        }
                        else{
                            parsedSong += Pause;
                            lastWasNote = false;
                        }
                    }
                break;
            }

            if(lastWasNote) {
                parsedSong += String.valueOf(currOctave);

            }
            parsedSong += ' ';
        }

        currVolume = DefaultVolume;
        currOctave = DefaultOctave;
        currInstrumentVal = DefaultInstrumentValue;
        return parsedSong;
    }
}
