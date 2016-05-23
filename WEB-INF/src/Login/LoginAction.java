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


public class LoginAction extends ActionSupport {


    private String login;
    private String password;

    public LoginAction() {
    }

    public String execute() {
        try {
            Map session = ActionContext.getContext().getSession();
            session.remove("login");
            session.remove("password");
            session.remove("authorized");

            if(new LoginStorage().checkLogin(getLogin(), getPassword())) {
                session.put("login", getLogin());
                session.put("password", getPassword());
				session.put("inputLanguage", new LoginStorage().getInputLanguage(getLogin()));
				session.put("outputLanguage", new LoginStorage().getOutputLanguage(getLogin()));
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

}