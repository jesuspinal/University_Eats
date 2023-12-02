/**
 * Jesus Pinales
 * SessionManager will manage users logged in
 */
package edu.utsa.cs3443.universityeats;

public class SessionManager {
    /**
     * Define class
     */
    private static User loggedInUser;

    /**
     * Constructor
     */
    private SessionManager(){

    }

    /**
     * Getter for logged in user
     * @return  Will assign and return user
     */
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Setter for logged in user
     * @param user  Will set value for logged in user
     */
    public static void setLoggedInUser(User user){
        loggedInUser = user;
    }
}
