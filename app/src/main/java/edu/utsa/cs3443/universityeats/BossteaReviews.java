/**
 * Jesus Pinales owj958
 * This class will read and write to the bosstea.csv file, and handle the information of the location
 * information consists of hours/days/location and short description if it fits.
 */
package edu.utsa.cs3443.universityeats;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BossteaReviews {

    /**
     * variables for info
     */
    private String title;
    private String content;
    private String userEmail;
    private String location;
    private String day;
    private String hours;
    private String description;

    // Default constructor
    public BossteaReviews() {
    }

    // Parameterized constructor

    /**
     *
     * @param title     title of the review
     * @param content   content or main text of review
     * @param userEmail users email of who created review
     * @param location  location review
     */
    public BossteaReviews(String title, String content, String userEmail, String location) {
        this.title = title;
        this.content = content;
        this.userEmail = userEmail;
        this.location = location != null ? location : "";;
    }

    /**
     *
     * @param day   day in the schedule of bosstea
     * @param hours hours of operation of bosstea
     * @param description   short description of bosstea
     */
    public BossteaReviews(String day, String hours, String description) {
        this.day = day;
        this.hours = hours;
        this.description = description;

    }

    // Getters and setters

    /**
     * get title of review
     * @return title
     */
    public String getTitle(){
        return title;
    }

    /**
     * set title of review
     * @param title set title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * get review content
     * @return  return content
     */
    public String getContent(){
        return content;
    }

    /**
     * set review content
     * @param content   set the review main text
     */
    public void setContent(String content){
        this.content = content;
    }

    /**
     * get users email
     * @return return user email
     */
    public String getUserEmail(){
        return userEmail;
    }

    /**
     * set users email
     * @param userEmail set users email
     */
    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    /**
     * get location of bosstea
     * @return return location
     */
    public String getLocation(){
        return location;
    }

    /**
     * set location of bosstea
     * @param location set location
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * get the days of bosstea is open
     * @return return days open
     */
    public String getDay() {
        return day;
    }

    /**
     * setter
     * @param day set first part of csv file
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * getter
     * @return get second part of csv file
     */
    public String getHours() {
        return hours;
    }

    /**
     * setter
     * @param hours set second part of csv file
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     * getter
     * @return  get the last part of csv file
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter
     * @param description set the last part of csv file
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Read from csv file needed
     * @param context context
     * @return list of info
     */
    public static List<BossteaReviews> readReviewsFromCSV(Context context) {
        List<BossteaReviews> reviewsList = new ArrayList<>();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("BossTea.csv");
            Scanner scanner = new Scanner(inputStream);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Read data lines and create bosstea instances
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String day = parts[0].trim();
                    String hours = parts[1].trim();
                    String description = parts[2].trim();

                    BossteaReviews review = new BossteaReviews(day, hours, description);
                    reviewsList.add(review);
                } else {
                    Log.e("CSV Error", "Invalid CSV line: " + line);
                }
            }

            // Close the scanner
            scanner.close();

        } catch (IOException e) {
            Log.e("CSV Error", "Error reading Freshens.csv: " + e.getMessage());
        }

        return reviewsList;
    }
}
