/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LookupWord;


import Beans.Lookup;
import Storage.LookupStorage;

import com.memetix.mst.translate.Translate;
import com.memetix.mst.language.Language;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;


public class LookupWordAction extends ActionSupport {

    private String message;

    private String inputWord;
    private String login;
    private String password;
    private String inputLanguage;
    private String outputLanguage;
    
    public LookupWordAction() {
    }

    public String execute() {
        try {
            Map session = ActionContext.getContext().getSession();
            if(session.get("login")==null) {
            	return "FAILURE";
            }
            setInputWord(getInputWord());
            System.out.println("Requested word: "+getInputWord());
            Translate.setKey("479503BD4BE256878435E2535A39F6B63A9C6AAC");
            
            String outputWord = "";
            outputWord = Translate.execute(getInputWord(), Language.fromString(getInputLanguage()), Language.fromString(getOutputLanguage()));

            System.out.println("Translation found: "+outputWord);
            setMessage(outputWord);
            setInputLanguage(getInputLanguage());
            setOutputLanguage(getOutputLanguage());
            
            if(session.get("login")!=null) {
                Lookup lookup = null;

                lookup = new Lookup((String) session.get("login"), getInputLanguage(), getOutputLanguage(), getInputWord(), outputWord, new Timestamp(new Date().getTime()));
                
                LookupStorage lookupStorage = new LookupStorage();       
                //save into file
                System.out.println("Saving word");
                boolean result = lookupStorage.save(lookup);
                if(result) {
                    System.out.println("Word saved!");
                }
                else {
                    System.out.println("Error during save");
                    return "FAILURE";
                }
            }
        } catch(Exception e) {
            setMessage("exception");
            e.printStackTrace();
            return "FAILURE";
        }

        return "SUCCESS";
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the inputWord
     */
    public String getInputWord() {
        return inputWord;
    }

    /**
     * @param inputWord the inputWord to set
     */
    public void setInputWord(String inputWord) {
        this.inputWord = inputWord;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    
//    public static void main(String[] args) {
//    	
//    	LookupWordAction lookup = new LookupWordAction();
//    	lookup.setInputLanguage("en");
//    	lookup.setOutputLanguage("sv");
//    	lookup.setInputWord("hello there");
//    	lookup.execute();
//    	
//    }
}