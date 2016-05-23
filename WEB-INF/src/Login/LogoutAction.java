/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

/**
 *
 * @author Meyyappan Muthuraman
 */

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;


public class LogoutAction extends ActionSupport {



    public LogoutAction() {
    }

    public String execute() {
            Map session = ActionContext.getContext().getSession();

            session.remove("login");
            session.remove("password");
			session.remove("inputLanguage");
			session.remove("outputLanguage");


        return "SUCCESS";
    }
}