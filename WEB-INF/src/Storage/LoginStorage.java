package Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import Beans.Lookup;


/**
 * Created by IntelliJ IDEA.
 * User: francois
 * Date: 2010-jul-29
 * Time: 11:37:15
 * To change this template use File | Settings | File Templates.
 */
public class LoginStorage {

    public LoginStorage() {
    	
    }

    public boolean checkLogin(String login, String password) {
    	ResultSet rs = null;
    	Connection conn = null;
        try {
            if (!login.contains("'")) {
	        	Class.forName("org.sqlite.JDBC");
	            conn = DriverManager.getConnection("jdbc:sqlite:lookup.db");
	            Statement stat = conn.createStatement();
	            conn.setAutoCommit(true);
	
	            rs = stat.executeQuery("select * from users where login='"+login+"';");

	            if (rs.next()) {
	              if(password.equals(rs.getString("password"))) return true;
	              else return false; //wrong password
	              
	            }
	            else {
	            	return false; //no such login
	            }
	            
            }
            else return false;            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
        	try {
	        	rs.close();
	            conn.close();
        	}
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean loginExists(String login) {
    	ResultSet rs = null;
    	Connection conn = null;
        try {
            if (!login.contains("'")) {
	        	Class.forName("org.sqlite.JDBC");
	            conn = DriverManager.getConnection("jdbc:sqlite:lookup.db");
	            Statement stat = conn.createStatement();
	            conn.setAutoCommit(true);
	
	            rs = stat.executeQuery("select * from users where login='"+login+"';");
	            
	            if (rs.next()) {
	              return true;
	              
	            }
	            else {
	            	return false; //no such login
	            }
	            
            }
            else return false;            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
        	try {
	        	rs.close();
	            conn.close();
        	}
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
	
	public String getInputLanguage(String login) {
		ResultSet rs = null;
    	Connection conn = null;
        try {
            if (!login.contains("'")) {
	        	Class.forName("org.sqlite.JDBC");
	            conn = DriverManager.getConnection("jdbc:sqlite:lookup.db");
	            Statement stat = conn.createStatement();
	            conn.setAutoCommit(true);
	
	            rs = stat.executeQuery("select * from users where login='"+login+"';");
	            
	            if (rs.next()) {
	              return rs.getString("defaultInputL");
	            }
	            else return "sv"; //default input language (should not happen)
            }
            else return "sv"; //default input language (should not happen)           
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
        	try {
	        	rs.close();
	            conn.close();
        	}
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return "sv"; //default input language (should not happen)
	}
	
	public String getOutputLanguage(String login) {
		ResultSet rs = null;
    	Connection conn = null;
        try {
            if (!login.contains("'")) {
	        	Class.forName("org.sqlite.JDBC");
	            conn = DriverManager.getConnection("jdbc:sqlite:lookup.db");
	            Statement stat = conn.createStatement();
	            conn.setAutoCommit(true);
	
	            rs = stat.executeQuery("select * from users where login='"+login+"';");
	            
	            if (rs.next()) {
	              return rs.getString("defaultOutputL");
	            }
	            else return "en"; //default output language (should not happen)
            }
            else return "en"; //default output language (should not happen)           
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
        	try {
	        	rs.close();
	            conn.close();
        	}
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return "en"; //default output language (should not happen)
	}

    public boolean createAccount(String login, String password, String inputLanguage, String outputLanguage) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:lookup.db");

            PreparedStatement prep = conn.prepareStatement("INSERT INTO users values (?, ?, ?, ?);");
            prep.setString(1, login);
            prep.setString(2, password);
            prep.setString(3, inputLanguage);
            prep.setString(4, outputLanguage);
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
}
