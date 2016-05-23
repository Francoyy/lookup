package ListWords;

import Beans.Lookup;
import Storage.LookupStorage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: francois
 * Date: 2010-maj-11
 * Time: 11:15:06
 * To change this template use File | Settings | File Templates.
 */
public class ListWordsAction extends ActionSupport {


    private List<Lookup> listLookup;

    public ListWordsAction() {
    }

    public String execute() {

        Map session = ActionContext.getContext().getSession();
        if(session.get("login")!=null) {
            LookupStorage listL = new LookupStorage();
            setListLookup(listL.loadList(session.get("login").toString()));
        }


        return "SUCCESS";
    }

    public List<Lookup> getListLookup() {
        return listLookup;
    }

    public void setListLookup(List<Lookup> listLookup) {
        this.listLookup = listLookup;
    }
    
}
