package Storage;

import Beans.Lookup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;

/**
 * Created by IntelliJ IDEA.
 * User: Francoyy
 * Date: 27 juin 2010
 * Time: 18:40:06
 * To change this template use File | Settings | File Templates.
 */
public class LookupStorage {

    public LookupStorage() {
    	
    }

    
    public boolean save(Lookup lookup) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:lookup.db");

            PreparedStatement prep = conn.prepareStatement("INSERT INTO lookups values (?, ?, ?, ?, ?, ?, ?);");
            prep.setString(1, lookup.getLogin());
            prep.setString(2, lookup.getInputLanguage());
            prep.setString(3, lookup.getOutputLanguage());
            prep.setString(4, lookup.getInput());
            prep.setString(5, lookup.getOutput());
            prep.setString(6, ""); //empty col for future possible use
            prep.setString(7, lookup.getDate().toString());
            prep.addBatch();
            
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);
            conn.close();
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * Function that imports the vocabulary of one user in the old .txt file to the new database.
     * Not used, a priori.
     * @param loginUser
     * @return
     */
    public List<Lookup> importLookups(String loginUser) {
        List<Lookup> lookupList = new ArrayList<Lookup>();
        try {
            //read file
            BufferedReader in = new BufferedReader(new FileReader("vocab.txt"));
            String str;
            Lookup lookup;
            while ((str = in.readLine()) != null) {
                String login = str.substring(str.indexOf("login=")+6, str.indexOf("&inputLanguage="));
                String inputLanguage = str.substring(str.indexOf("inputLanguage=")+14, str.indexOf("&outputLanguage="));
                String outputLanguage = str.substring(str.indexOf("outputLanguage=")+15, str.indexOf("&input="));
                String input = str.substring(str.indexOf("input=")+6, str.indexOf("&output="));
                String output = str.substring(str.indexOf("output=")+7, str.indexOf("&date="));
                Timestamp date = Timestamp.valueOf(str.substring(str.indexOf("date=")+5, str.length()));
                lookup = new Lookup(login, inputLanguage, outputLanguage, input, output, date);
                if(login.equals(loginUser)) {
                    lookupList.add(lookup);
                }
            }
            in.close();
            System.out.println("List of words loaded");
			for(Lookup lkup : lookupList) {
				Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager.getConnection("jdbc:sqlite:lookup.db");

				PreparedStatement prep = conn.prepareStatement("INSERT INTO lookups values (?, ?, ?, ?, ?, ?, ?);");
				prep.setString(1, lkup.getLogin());
				prep.setString(2, lkup.getInputLanguage());
				prep.setString(3, lkup.getOutputLanguage());
				prep.setString(4, lkup.getInput());
				prep.setString(5, lkup.getOutput());
				prep.setString(6, ""); //empty col for future possible use
				prep.setString(7, lkup.getDate().toString());
				prep.addBatch();
				
				conn.setAutoCommit(false);
				prep.executeBatch();
				conn.setAutoCommit(true);
				conn.close();
			}
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return lookupList;
    }
    
    

    public List<Lookup> loadList(String loginUser) {
        List<Lookup> lookupList = new ArrayList<Lookup>();
        if(!loginUser.contains("'")) {
	        try {
	            Class.forName("org.sqlite.JDBC");
	            Connection conn = DriverManager.getConnection("jdbc:sqlite:lookup.db");
	            Statement stat = conn.createStatement();
	            conn.setAutoCommit(true);
	
	            ResultSet rs = stat.executeQuery("select * from lookups where login='"+loginUser+"';");
	            
	            Lookup lookup;
	            while (rs.next()) {
	              String login = rs.getString("login");
	              String inputLanguage = rs.getString("inputLanguage");
	              String outputLanguage = rs.getString("outputLanguage");
	              String input = rs.getString("input");
	              String output = rs.getString("output");
	              Timestamp date = Timestamp.valueOf(rs.getString("date"));
	              lookup = new Lookup(login, inputLanguage, outputLanguage, input, output, date);
	              lookupList.add(lookup);
	            }
	            rs.close();
	            conn.close();
	            
	            
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
        }
        return lookupList;
    }


    public List<Lookup> loadList() {
        List<Lookup> lookupList = new ArrayList<Lookup>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:lookup.db");
            Statement stat = conn.createStatement();
            conn.setAutoCommit(true);

            ResultSet rs = stat.executeQuery("select * from lookups;");
            
            Lookup lookup;
            while (rs.next()) {
              String login = rs.getString("login");
              String inputLanguage = rs.getString("inputLanguage");
              String outputLanguage = rs.getString("outputLanguage");
              String input = rs.getString("input");
              String output = rs.getString("output");
              Timestamp date = Timestamp.valueOf(rs.getString("date"));
              lookup = new Lookup(login, inputLanguage, outputLanguage, input, output, date);
              lookupList.add(lookup);
            }
            rs.close();
            conn.close();
           
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return lookupList;
    }


    public boolean updateLookup(String login, String oldInput, String oldOutput, String input, String output) {
    	try {
        
	        Class.forName("org.sqlite.JDBC");
	        Connection conn = DriverManager.getConnection("jdbc:sqlite:lookup.db");

	        PreparedStatement prep = conn.prepareStatement("UPDATE lookups SET input=?, output=? WHERE login=? AND input=? AND output=?");
	        
	        prep.setString(1, input);
	        prep.setString(2, output);
	        prep.setString(3, login);
	        prep.setString(4, oldInput);
	        prep.setString(5, oldOutput);
	        
	        prep.addBatch();
	        
	        conn.setAutoCommit(false);
	        prep.executeBatch();
	        conn.setAutoCommit(true);
	
	        conn.close();
        
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        
        return true;
    }

    public boolean deleteLookup(String login, String oldInput, String oldOutput) {
        try {
            
	        Class.forName("org.sqlite.JDBC");
	        Connection conn = DriverManager.getConnection("jdbc:sqlite:lookup.db");

	        PreparedStatement prep = conn.prepareStatement("DELETE FROM lookups WHERE login=? AND input=? AND output=?");
	        
	        prep.setString(1, login);
	        prep.setString(2, oldInput);
	        prep.setString(3, oldOutput);
	        
	        prep.addBatch();
	        
	        conn.setAutoCommit(false);
	        prep.executeBatch();
	        conn.setAutoCommit(true);
	
	        conn.close();
        
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        return true;
    }
}
