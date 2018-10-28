package Micro;

import Micro.Exceptions.*;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MicroDvd {


    public MicroDvd() {

    }

    public void delay(final String in, final String out, int delay, int fps)
            throws WrongFrameSequenceException, WrongLineFormatException, NegativeFrameNumberException, WrongFrameFormatException, IOException, UnknownException {

        File file = new File(in);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(out));
        String line = null;
        int lineCounter = 0;
        while ((line = br.readLine()) != null) {
            lineCounter += 1;
            Pattern pattern = Pattern.compile("\\{(.*)\\}\\{(.*)\\}(.*)");
            Matcher matcher = pattern.matcher(line);

            if (!matcher.matches()) {
                throw new WrongLineFormatException("Wrong line format on line " + lineCounter + '\n' + line);
            }
            String lineQuote = matcher.group(3);
            try {
                int inFrame = Integer.parseInt(matcher.group(1));
                int outFrame = Integer.parseInt(matcher.group(2));

                if (outFrame <= inFrame) {
                    throw new WrongFrameSequenceException("Wrong frame numbers order on line " + lineCounter + '\n' + line);

                } else if (inFrame < 0) {
                    throw new NegativeFrameNumberException("Negative frame number on line " + lineCounter + '\n' + line);
                }
                int frameDelay = delay * fps / 1000;
                inFrame += frameDelay;
                outFrame += frameDelay;
                String newLine = "{" + inFrame + "}{" + outFrame + "}" + lineQuote + '\n';
                writer.append(newLine);

            } catch (WrongFrameSequenceException | NegativeFrameNumberException e) {
                throw e;
            } catch (NumberFormatException e) {
                throw new WrongFrameFormatException("Wrong frame format on line " + lineCounter + '\n' + line);
            } catch (Exception e) {
                throw new Micro.Exceptions.UnknownException("Unknown exception on line " + lineCounter + '\n' + line);
            }

        }
        writer.close();
    }
}
