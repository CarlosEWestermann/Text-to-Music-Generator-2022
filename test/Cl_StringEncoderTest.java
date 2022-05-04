import constants.NumericConstants;
import encoder.Cl_StringEncoder;
import org.junit.Assert;
import org.junit.Test;

public class Cl_StringEncoderTest {
    @Test
    public void shouldReturnAnEmptyParsedStringWhenSongIsEmpty(){
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "";
        String expectedParsedSong = "";

        Assert.assertEquals(expectedParsedSong, stringEncoder.parseString(song));
    }

    @Test
    public void shouldReturnParsedStringWithAllNotesWithDefaultOctaveWhenSongIsAllNotes(){
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "ABCDE";
        String expectedParsedSong = "A5 B5 C5 D5 E5 ";

        Assert.assertEquals(expectedParsedSong, stringEncoder.parseString(song));
    }

    @Test
    public void shouldChangeLastNoteCharWhenLastCharEncodedIsANote()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "AB";
        char lastNoteExpected = 'B';

        stringEncoder.parseString(song);

        Assert.assertEquals(lastNoteExpected, stringEncoder.getLastNote());
    }

    @Test
    public void shouldNotChangeLastNoteCharWhenLastCharEncodedIsNotANote()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "A?";
        char lastNoteExpected = 'A';

        stringEncoder.parseString(song);

        Assert.assertEquals(lastNoteExpected, stringEncoder.getLastNote());
    }

    @Test
    public void lastNoteBooleanShouldBeTrueWhenLastCharEncodedIsANote()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "AB";

        stringEncoder.parseString(song);

        Assert.assertTrue(stringEncoder.lastWasNote());
    }

    @Test
    public void lastNoteBooleanShouldBeFalseWhenLastCharEncodedIsntANote()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "A?";

        stringEncoder.parseString(song);

        Assert.assertFalse(stringEncoder.lastWasNote());
    }

    @Test
    public void shouldParseAPauseWhenLastTwoCharsAreAnythingButNoteAndLowerA()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "?a";
        String expectedParsedSong = " R ";

        Assert.assertEquals(expectedParsedSong, stringEncoder.parseString(song));
    }

    @Test
    public void shouldParseAPauseWhenLastTwoCharsAreAnythingButNoteAndAnyOtherChar()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "?&";
        String expectedParsedSong = " R ";

        Assert.assertEquals(expectedParsedSong, stringEncoder.parseString(song));
    }

    @Test
    public void shouldParseANoteWhenLastTwoCharsAreNoteAndLowerA()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "Ba";
        String expectedParsedSong = "B5 B5 ";

        Assert.assertEquals(expectedParsedSong, stringEncoder.parseString(song));
    }

    @Test
    public void shouldChangeInstrumentToAgogo114WhenEncodeExclamationPoint()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "B!C";
        int expectedInstrument = 114;

        stringEncoder.parseString(song);

        Assert.assertEquals(expectedInstrument, stringEncoder.getCurrInstrumentVal());
    }

    @Test
    public void shouldChangeInstrumentToHarpsichord7WhenEncodeOoUuIi()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "BOCoBUAuCIDi";
        int expectedInstrument = 7;

        stringEncoder.parseString(song);

        Assert.assertEquals(expectedInstrument, stringEncoder.getCurrInstrumentVal());
    }

    @Test
    public void shouldChangeInstrumentToCurrentPlusDigitWhenEncodeDigit()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "BO15";
        int expectedInstrument = 13;

        stringEncoder.parseString(song);

        Assert.assertEquals(expectedInstrument, stringEncoder.getCurrInstrumentVal());
    }

    @Test
    public void shouldChangeInstrumentToTubularBells15WhenEncodeNewLine()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "B\nC";
        int expectedInstrument = 15;

        stringEncoder.parseString(song);

        Assert.assertEquals(expectedInstrument, stringEncoder.getCurrInstrumentVal());
    }

    @Test
    public void shouldChangeInstrumentToPanFlute76WhenEncodeSemiColon()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "B;C";
        int expectedInstrument = 76;

        stringEncoder.parseString(song);

        Assert.assertEquals(expectedInstrument, stringEncoder.getCurrInstrumentVal());
    }

    @Test
    public void shouldChangeInstrumentToChurchOrgan20WhenEncodeComma()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "B,C";
        int expectedInstrument = 20;

        stringEncoder.parseString(song);

        Assert.assertEquals(expectedInstrument, stringEncoder.getCurrInstrumentVal());
    }

    @Test
    public void shouldDoubleVolumeWhenEncodeSpaceAndVolumeDoubledIsntGreaterThanMax()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "B  C";
        int expectedVolume = NumericConstants.DefaultVolume * 4;

        stringEncoder.parseString(song);

        Assert.assertEquals(expectedVolume, stringEncoder.getCurrVolume());
    }

    @Test
    public void shouldSetVolumeToDefaultWhenEncodeSpaceAndVolumeDoubledIsGreaterThanMax()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "B   C";
        int expectedVolume = NumericConstants.DefaultVolume;

        stringEncoder.parseString(song);

        Assert.assertEquals(expectedVolume, stringEncoder.getCurrVolume());
    }

    @Test
    public void shouldIncreaseOctaveWhenEncodeQuestionMarkAndOctaveIncreasedIsntGreaterThanMax()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "B????C";
        int expectedOctave = NumericConstants.DefaultOctave + 4;

        stringEncoder.parseString(song);

        Assert.assertEquals(expectedOctave, stringEncoder.getCurrOctave());
    }

    @Test
    public void shouldsetOctaveToDefaultWhenEncodeQuestionMarkAndOctaveIncreasedIsGreaterOrEqualToMax()
    {
        Cl_StringEncoder stringEncoder = new Cl_StringEncoder();
        String song = "B?????C";
        int expectedOctave = NumericConstants.DefaultOctave;

        stringEncoder.parseString(song);

        Assert.assertEquals(expectedOctave, stringEncoder.getCurrOctave());
    }
}
