package Beans;

import java.sql.Timestamp;
import java.util.Date;


public class Lookup {
    String login;
    String inputLanguage;
    String outputLanguage;
    String input;
    String output;
    Timestamp date;


    public Lookup(String login, String inputLanguage, String outputLanguage, String input, String output) {
        this.login = login;
        this.inputLanguage = inputLanguage;
        this.outputLanguage = outputLanguage;
        this.input = input;
        this.output = output;
        this.date = new Timestamp(new Date().getTime());
    }

    public Lookup(String login, String inputLanguage, String outputLanguage, String input, String output, Timestamp date) {
        this.login = login;
        this.inputLanguage = inputLanguage;
        this.outputLanguage = outputLanguage;
        this.input = input;
        this.output = output;
        this.date = date;
    }


    public String getInputLanguage() {
        return inputLanguage;
    }

    public void setInputLanguage(String inputLanguage) {
        this.inputLanguage = inputLanguage;
    }

    public String getOutputLanguage() {
        return outputLanguage;
    }

    public void setOutputLanguage(String outputLanguage) {
        this.outputLanguage = outputLanguage;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
