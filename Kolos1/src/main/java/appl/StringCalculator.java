package appl;

import excp.BadInputException;
import excp.NothingToSubtractFromException;
import excp.TooBigNumberException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by student on 2018-11-06.
 */
public class StringCalculator extends Calculator implements Cloneable {
    private String result;


    public StringCalculator() {
        this.result = "";
    }

    public StringCalculator(String baseString) {
        this.result = baseString;

    }

    public String getResult() {
        return result;
    }

    public void setResult(String string) {
        this.result = string;
    }


    @Override
    public void saveToFile(String x, String filename) throws IOException {
        File file = new File(filename);
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.append(x);
        writer.close();

    }

    public void add(String string) {
        String newResult = this.getResult() + string;
        this.setResult(newResult);
    }

    public void subtract(String string) throws NothingToSubtractFromException, BadInputException {
        if(this.getResult().equals("")){
            throw new excp.NothingToSubtractFromException("NothingToSubtractFrom");
        }
        if(!this.getResult().contains(string)){
            throw new excp.BadInputException("Bad Input");
        }
        String newResult = this.getResult().replaceAll(string, "");

        this.setResult(newResult);
    }

    public void multiply(int number) throws TooBigNumberException {
        if(number > 5){
            throw new excp.TooBigNumberException("Number greater than 5");
        }
        String stringToAdd = this.getResult();
        String newResult = this.getResult();
        for (int i = 1; i < number; ++i) {
            newResult += stringToAdd;
        }
        this.setResult(newResult);
    }

    @Override
    public StringCalculator clone() {
        StringCalculator foo = new StringCalculator(this.result);
        foo.setResult(this.getResult());
        return foo;
    }

    public static void main(String[] args) {
        try{
            StringCalculator calc = new StringCalculator();
            calc.setResult("abba");
            System.out.println(calc.getResult());

            calc.add("aaa");
            calc.subtract("b");
            calc.multiply(4);
            StringCalculator secondCalc = calc.clone();
            secondCalc.saveToFile(secondCalc.getResult(), "res.txt");
            System.out.println(secondCalc.getResult());

        } catch (BadInputException | NothingToSubtractFromException | TooBigNumberException | IOException e) {
            e.printStackTrace();
        }
    }


}
