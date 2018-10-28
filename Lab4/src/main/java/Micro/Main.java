package Micro;

import Micro.Exceptions.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        MicroDvd microDvD = new MicroDvd();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert input filename:");
        String inFile = scanner.nextLine();
        System.out.println("Insert output filename:");
        String outFile = scanner.nextLine();
        System.out.println("Insert delay in miliseconds:");
        int delay = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Insert frame rate in frames per second:");
        int fps = scanner.nextInt();

        try {
            microDvD.delay(inFile, outFile, delay, fps);
            System.out.println("Subtitles succesfully delayed");
        } catch (WrongFrameSequenceException | WrongFrameFormatException | WrongLineFormatException | NegativeFrameNumberException |
                UnknownException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("File can not be opened or does not exist");
        }


    }
}
