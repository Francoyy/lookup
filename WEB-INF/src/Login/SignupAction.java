/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

/**
 *
 * @author Meyyappan Muthuraman
 */

import Storage.LoginStorage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;


public class SignupAction extends ActionSupport {


    private String login;
    private String password;
    private String message;
	private String inputLanguage;
	private String outputLanguage;

    public SignupAction() {
    }

    public String execute() {
        try {
            Map session = ActionContext.getContext().getSession();
            session.remove("login");
            session.remove("password");
            session.remove("authorized");
            if(getLogin()!=null && getPassword()!=null && !getLogin().equals("") && !getPassword().equals("")) {
                if(!getLogin().contains(":::")&&!getPassword().contains(":::")) {
                    LoginStorage loginStorage = new LoginStorage();
                    if(!loginStorage.loginExists(getLogin())) {
						System.out.println(getInputLanguage()+"#############\n\n");
                        boolean result = loginStorage.createAccount(getLogin(), getPassword(), getInputLanguage(), getOutputLanguage());
                        if(result) {
                            session.put("login", getLogin());
                            session.put("password", getPassword());
                            session.put("inputLanguage", getInputLanguage());
                            session.put("outputLanguage", getOutputLanguage());
                        }
                        else {
                            setMessage("Unknown error, please try again later");
                        }
                    }
                    else {
                        setMessage("This login already exists, please select another login");
                    }
                }
                else {
                    setMessage("Login and password can't contain the chain \":::\", please enter new values");
                }
            }
            else {
                setMessage("Please enter some values for login and password");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return "SUCCESS";
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
	
	public String getInputLanguage() {
		return inputLanguage;
	}
	
	public String getOutputLanguage() {
		return outputLanguage;
	}
	
	public void setInputLanguage(String inputLanguage) {
		this.inputLanguage = inputLanguage;
	}
	
	public void setOutputLanguage(String outputLanguage) {
		this.outputLanguage = outputLanguage;
	}
}