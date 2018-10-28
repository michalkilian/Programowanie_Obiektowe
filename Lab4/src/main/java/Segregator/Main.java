package Segregator;

import Segregator.Exceptions.FolderEmptyException;
import Segregator.Exceptions.NotImageException;
import io.indico.api.utils.IndicoException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Segregator.segregate(args[0]);
        } catch (FolderEmptyException e) {
            e.printStackTrace();
        } catch (IOException | IndicoException | NotImageException e) {
            System.out.println(e.getMessage());
        }
    }
}

