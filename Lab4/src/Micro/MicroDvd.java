package Micro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by student on 2018-10-23.
 */
public class MicroDvd {

    String fileIn;
    String fileOut;
    int delay;
    int fps;

    public MicroDvd(String fileIn, String fileOut, int delay, int fps) {
        this.fileIn = fileIn;
        this.fileOut = fileOut;
        this.delay = delay;
        this.fps = fps;

    }

    public String[] delay(final String in, final String out, int delay, int fps) {


        Pattern pattern = Pattern.compile("\\{(.*)\\}\\{(.*)\\}(.*)");
        Matcher matcher = pattern.matcher(in);


        try {
            int inFrame = Integer.parseInt(matcher.group(1));
            int outFrame = Integer.parseInt(matcher.group(2));
            String lineQuote = matcher.group(3);
            if (outFrame <= inFrame){
                throw new WrongFrameSequenceException(lineQuote);
            }
            else if ()
            int frameDelay = delay / 1000 * fps;
            inFrame += frameDelay;
            outFrame += frameDelay;
        } catch (NumberFormatException e) {

        }

    }
}
