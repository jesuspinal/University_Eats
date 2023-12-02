/**
 * Jesus Pinales owj958
 * PandaReview will handle getters and setters for both reading the info of location from csv file and handle reviews,
 * title, and content
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

public class PandaReview {
    private String title;
    private String content;
    private String userEmail;
    private String location;
    private String day;
    private String hours;
    private String description;

    // Default constructor
    public PandaReview() {
    }

    // Parameterized constructor

    /**
     *
     * @param title title of review
     * @param content content of review
     * @param userEmail users email display
     * @param location location of review
     * @param day days of operation
     * @param hours hours of operation
     * @param description short description of location
     */
    public PandaReview(String title, String content, String userEmail, String location, String day, String hours, String description) {
        this.title = title;
        this.content = content;
        this.userEmail = userEmail;
        this.location = location != null ? location : "";;
        this.day = day;
        this.hours = hours;
        this.description = description;
    }

    /**
     *
     * @param day days of operation
     * @param hours hours of operation
     * @param description description of location
     */
    public PandaReview(String day, String hours, String description) {
        this.day = day;
        this.hours = hours;
        this.description = description;
    }

    // Getters and setters

    /**
     * getter
     * @return get title review
     */
    public String getTitle(){
        return title;
    }

    /**
     * setter
     * @param title set title review
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * getter
     * @return get review content
     */
    public String getContent(){
        return content;
    }

    /**
     * setter
     * @param content set review content
     */
    public void setContent(String content){
        this.content = content;
    }

    /**
     * getter
     * @return get user email
     */
    public String getUserEmail(){
        return userEmail;
    }

    /**
     * setter
     * @param userEmail set user email
     */
    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    /**
     * getter
     * @return get location review
     */
    public String getLocation(){
        return location;
    }

    /**
     * setter
     * @param location set location review
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * getter
     * @return get first part of csv file
     */
    public String getDay() {
        return day;
    }

    /**
     * setter
     * @param day set first part csv file
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * getter
     * @return get second part csv file
     */
    public String getHours() {
        return hours;
    }

    /**
     * setter
     * @param hours set second part csv file
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     * getter
     * @return get third part csv file
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter
     * @param description set third part csv file
     */
    public void setDescription(String description) {
        this.description = description;
    }

    // read csv file here
    public static List<PandaReview> readReviewsFromCSV(Context context) {
        List<PandaReview> reviewsList = new ArrayList<>();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("Panda_Express.csv");
            Scanner scanner = new Scanner(inputStream);


            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Read data lines and create FreshenReviews instances
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String day = parts[0].trim();
                    String hours = parts[1].trim();
                    String description = parts[2].trim();

                    PandaReview review = new PandaReview(day, hours, description);
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
