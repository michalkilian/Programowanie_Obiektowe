package Segregator;

import Segregator.Exceptions.FolderEmptyException;
import Segregator.Exceptions.NotImageException;
import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import io.indico.api.results.BatchIndicoResult;
import io.indico.api.utils.IndicoException;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


public class Segregator {
    /**
     * method to segregate images into different directories based on image context
     * You need to have your indico api key in secret.txt file and insert directory with images to sort as a first program parameter
     *
     * @param directory directory contains images to segregate
     * @throws FolderEmptyException if given directory does not exist
     * @throws IOException          if secret.txt with api key is missing or is empty
     * @throws IndicoException      when api key is not correct
     * @throws NotImageException    when file in given directory is not an image
     */


    public static void segregate(String directory) throws FolderEmptyException, IOException, IndicoException, NotImageException {

        String projectDir = System.getProperty("user.dir");

        File secret = new File("secret.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(secret)));
        String apiKey = br.readLine();
        Indico indico = new Indico("e16da90ddf773e55eabcd52160251bbf");

        File folder = new File(directory);
        File[] contents = folder.listFiles();
        if (contents == null) {
            throw new FolderEmptyException("Folder does not contain images");
        }
        Map mapA = new HashMap();
        mapA.put("top_n", 1);

        File dir = new File(projectDir + "\\sorted_foto");
        if (!dir.exists()) {
            dir.mkdir();
        }


        for (File f : contents) {
            try {
                IndicoResult single = indico.imageRecognition.predict(f, mapA);
                String result = (String) single.getImageRecognition().keySet().toArray()[0];
                File outDir = new File(dir.getAbsolutePath() + "\\" + result);
                if (!outDir.exists()) {
                    outDir.mkdir();
                }
                File tagFile = new File(outDir, f.getName());


                String filePatch = dir.getAbsolutePath() + f.getName();
                if (!tagFile.exists()) {
                    Files.copy(f.toPath(), tagFile.toPath());
                }
            } catch (Exception e) {
                throw new NotImageException("file " + f.getName() + " is not an image \n Cannot predict");
            }


        }

    }
}
