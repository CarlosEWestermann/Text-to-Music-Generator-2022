package ENCODER;

import static Constants.translationConstants.*;

public class Cl_StringEncoder implements If_StringEncoder{

    final int maxOctave = 10;
    final int defaultOctave = 5;
    final int RADIX = 10;
    private int currOctave = 5;
    private int currVolume = 40;
    private int currInstrumentVal = 1;

    @Override
    public String parseString(String Song) {
        int currPosInParsed = 0;
        String instrument;
        String parsedSong = "";

        for (int i = 0; i < Song.length(); i++) {
            switch(Song.charAt(i)){
                case La:
                    parsedSong += 'A';
                    currPosInParsed = currPosInParsed + 1;
                break;
                case Si:
                    parsedSong += 'B';
                    currPosInParsed = currPosInParsed + 1;
                break;
                case Do:
                    parsedSong += 'C';
                    currPosInParsed = currPosInParsed + 1;
                break;
                case Re:
                    parsedSong += 'D';
                    currPosInParsed = currPosInParsed + 1;
                break;
                case Mi:
                    parsedSong += 'E';
                    currPosInParsed = currPosInParsed + 1;
                break;
                case Fa:
                    parsedSong += 'F';
                    currPosInParsed = currPosInParsed + 1;
                break;
                case Sol:
                    parsedSong += 'G';
                    currPosInParsed = currPosInParsed + 1;
                break;
                case LowerA:
                case LowerB:
                case LowerC:
                case LowerD:
                case LowerE:
                case LowerF:
                case LowerG:
                    if( i > 0 && isLastPosNote(i, Song)){
                        parsedSong += Song.charAt(i-1);
                    }
                    else{
                        parsedSong += 'R';
                    }
                    currPosInParsed = currPosInParsed + 1;
                    break;
                case LowerO:
                case LowerU:
                case LowerI:
                case UpperO:
                case UpperU:
                case UpperI:
                    instrument = "I[HARPSICHORD]";
                    currInstrumentVal = 7;
                    parsedSong = parsedSong.concat(instrument);
                    currPosInParsed = currPosInParsed + instrument.length();
                break;
                case Space:
                    if(currVolume * 2 > 127){
                        currVolume = 40;
                    }
                    else{
                        currVolume *= 2;
                    }
                    String addedString = ":CON(7, " + currVolume + ")";
                    parsedSong = parsedSong.concat(addedString);
                    currPosInParsed = currPosInParsed + addedString.length();
                break;
                case Exclamation:
                    instrument = "I[AGOGO]";
                    currInstrumentVal = 114;
                    parsedSong = parsedSong.concat(instrument);
                    currPosInParsed = currPosInParsed + instrument.length();
                break;
                case Question:
                    currOctave += 1;
                    if(currOctave >= maxOctave)
                        currOctave = defaultOctave;
                break;
                case NL:
                    instrument = "I[TUBULAR_BELLS]";
                    currInstrumentVal = 15;
                    parsedSong = parsedSong.concat(instrument);
                    currPosInParsed = currPosInParsed + instrument.length();
                break;
                case Semicolon:
                    instrument = "I[PAN_FLUTE]";
                    currInstrumentVal = 76;
                    parsedSong = parsedSong.concat(instrument);
                    currPosInParsed = currPosInParsed + instrument.length();
                break;
                case Comma:
                    instrument = "I[CHURCH_ORGAN]";
                    currInstrumentVal = 20;
                    parsedSong = parsedSong.concat(instrument);
                    currPosInParsed = currPosInParsed + instrument.length();
                break;
                default:
                    if(Character.isDigit(Song.charAt(i))){
                        StringBuilder numValue = new StringBuilder();
                        while(Character.isDigit(Song.charAt(i))){
                            numValue.append(Song.charAt(i));
                            i = i + 1;
                        }
                        String newInstrument = String.valueOf(currInstrumentVal + Integer.parseInt(numValue.toString(), RADIX));
                        if(Integer.parseInt(newInstrument) < 128 && Integer.parseInt(newInstrument) >= 0){
                            instrument = "I" + newInstrument + " ";
                        }
                        else{
                            instrument = "I0 ";
                        }
                        parsedSong = parsedSong.concat(instrument);
                        currPosInParsed = currPosInParsed + instrument.length();
                    }
                    else{
                        if(i > 0 && isLastPosNote(i, Song)){
                            parsedSong += Song.charAt(i-1);
                        }
                        else{
                            parsedSong += 'R';
                        }
                    }
                break;
            }
            currPosInParsed = currPosInParsed + 1;
            if(isLastPosNote(i + 1, Song)) {
                parsedSong += String.valueOf(currOctave);
                currPosInParsed = currPosInParsed + 1;
            }
            parsedSong += ' ';
        }

        currVolume = 40;
        currOctave = 5;
        currInstrumentVal = 0;
        return parsedSong;
    }

    private boolean isLastPosNote(int i, String Song){
        return Song.charAt(i - 1) == Do ||
                Song.charAt(i - 1) == Re ||
                Song.charAt(i - 1) == Mi ||
                Song.charAt(i - 1) == Fa ||
                Song.charAt(i - 1) == Sol ||
                Song.charAt(i - 1) == La ||
                Song.charAt(i - 1) == Si;
    }
}
