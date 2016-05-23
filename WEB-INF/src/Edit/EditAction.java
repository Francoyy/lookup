package Edit;

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
public class EditAction extends ActionSupport {


    private List<Lookup> listLookup;

    private String input;
    private String output;
    private String oldInput;
    private String oldOutput;
    private String delete;
    private String add;
    private String newInputL;
    private String newOutputL;

    public EditAction() {
    }

    public String execute() {

        Map session = ActionContext.getContext().getSession();
        if(session.get("login")!=null) {
            LookupStorage lookupStorage = new LookupStorage();
            if(getInput()!=null && getOutput()!=null && !getInput().equals("") && !getOutput().equals("") && getOldInput()!=null && getOldOutput()!=null && getDelete()==null && getAdd()==null) {
                lookupStorage.updateLookup(session.get("login").toString(), getOldInput(), getOldOutput(), getInput(), getOutput());
            }
            if(getOldInput()!=null && getOldOutput()!=null && getDelete()!=null && getDelete().equals("true")) {
                lookupStorage.deleteLookup(session.get("login").toString(), getOldInput(), getOldOutput());
            }
            if(getNewInputL()!=null && getNewOutputL()!=null && getInput()!=null && getOutput()!=null && getAdd()!=null && getAdd().equals("true")) {
                lookupStorage.save(new Lookup(session.get("login").toString(), getNewInputL(), getNewOutputL(), getInput(), getOutput()));
            }

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

    public String getOldInput() {
        return oldInput;
    }

    public void setOldInput(String oldInput) {
        this.oldInput = oldInput;
    }

    public String getOldOutput() {
        return oldOutput;
    }

    public void setOldOutput(String oldOutput) {
        this.oldOutput = oldOutput;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getNewInputL() {
        return newInputL;
    }

    public void setNewInputL(String newInputL) {
        this.newInputL = newInputL;
    }

    public String getNewOutputL() {
        return newOutputL;
    }

    public void setNewOutputL(String newOutputL) {
        this.newOutputL = newOutputL;
    }
}