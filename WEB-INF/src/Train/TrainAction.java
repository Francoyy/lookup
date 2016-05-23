package Train;

import Beans.Lookup;
import Storage.LookupStorage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Francoyy
 * Date: 25 juin 2010
 * Time: 19:04:51
 * To change this template use File | Settings | File Templates.
 */
public class TrainAction extends ActionSupport {

    List<Lookup> listLookup;

    String action;
    String info;
    String noFurtherWeek;
    String noFurtherMonth;

    Date beginning;
    Date end;
    Calendar calendar;

    public TrainAction() {
    
    }

    public String execute() {

        Map session = ActionContext.getContext().getSession();
        calendar = Calendar.getInstance();

        if(getAction()!=null && session.get("beginning") != null && session.get("end")!=null) {
            beginning = (Date)session.get("beginning");
            end = (Date)session.get("end");
            calendar.setTime(beginning);
            if(getAction().equals("nextW")) {
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                setBeginAndEnd(6);
            }
            else if(getAction().equals("prevW")) {
                calendar.add(Calendar.DAY_OF_YEAR, -7);
                setBeginAndEnd(6);
            }
            else if(getAction().equals("nextM")) {
                calendar.add(Calendar.MONTH, 1);
                setBeginAndEnd(30);
            }
            else if(getAction().equals("prevM")) {
                calendar.add(Calendar.MONTH, -1);
                setBeginAndEnd(30);
            }
        }
        else {

            int day = calendar.get(Calendar.DAY_OF_WEEK)-2; //sunday=1, so now monday=0
            calendar.add(Calendar.DAY_OF_YEAR,-day); //first day of the week
            setBeginAndEnd(6);
        }
        
            LookupStorage lookupStorage = new LookupStorage();
            String login = session.get("login")!=null?session.get("login").toString():"";
            List<Lookup> listRaw = lookupStorage.loadList(login);
            List<Lookup> listFiltered = new ArrayList<Lookup>();


        for(Lookup lookup : listRaw) {
            if("all".equals(getAction()) || (lookup.getDate().after(beginning) && lookup.getDate().before(end))) {
                listFiltered.add(lookup);
            }
        }

        session.put("beginning", beginning);
        session.put("end", end);

        //set of informational date text
        calendar.setTime(beginning);
        int month = calendar.get(Calendar.MONTH)+1;
        info = calendar.get(Calendar.DAY_OF_MONTH)+"/"+month+"/"+calendar.get(Calendar.YEAR);
        calendar.setTime(end);
        month = calendar.get(Calendar.MONTH)+1;
        info += " - "+calendar.get(Calendar.DAY_OF_MONTH)+"/"+month+"/"+calendar.get(Calendar.YEAR);

        calendar = Calendar.getInstance();
        if(beginning.before(calendar.getTime()) && end.after(calendar.getTime()))
            setNoFurtherWeek("true");
        else
            setNoFurtherWeek("false");
       
        calendar.setTime(beginning);
        calendar.add(Calendar.MONTH, 1);
        Date plusOneMonth = calendar.getTime();

        calendar = Calendar.getInstance();
        if(plusOneMonth.after(calendar.getTime()))
            setNoFurtherMonth("true");
        else 
            setNoFurtherMonth("false");


        if(!"all".equals(getAction())) {
        	setInfo(info);
        }
        else {
        	setInfo("Complete list");
        }
        setListLookup(listFiltered);


        return "SUCCESS";
    }

    public void setBeginAndEnd(int interval) {
        calendar.roll(Calendar.HOUR, -calendar.get(Calendar.HOUR));
        calendar.roll(Calendar.MINUTE, -calendar.get(Calendar.MINUTE));
        calendar.roll(Calendar.SECOND, -calendar.get(Calendar.SECOND));
        beginning = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, interval+1); //last day of the week + 1 = midnight the day after
        calendar.add(Calendar.SECOND, -1); //minus 1 second = last day of the week, 23h59
        end = calendar.getTime();
    }

    public List<Lookup> getListLookup() {
        return listLookup;
    }

    public void setListLookup(List<Lookup> listLookup) {
        this.listLookup = listLookup;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNoFurtherWeek() {
        return noFurtherWeek;
    }

    public void setNoFurtherWeek(String noFurtherWeek) {
        this.noFurtherWeek = noFurtherWeek;
    }

    public String getNoFurtherMonth() {
        return noFurtherMonth;
    }

    public void setNoFurtherMonth(String noFurtherMonth) {
        this.noFurtherMonth = noFurtherMonth;
    }
}
