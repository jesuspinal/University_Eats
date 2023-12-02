/**
 * Jesus Pinales owj958
 * FreebirdReview represents a review for the Freebirds World Burrito location.
 * It includes functionality to read reviews from a CSV file and provides getters and setters for review properties.
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

public class FreebirdReview {
    private String title;
    private String content;
    private String userEmail;
    private String location;
    private String day;
    private String hours;
    private String description;

    // Default constructor
    public FreebirdReview() {
    }

    // Parameterized constructor

    /**
     *
     * @param title title of review
     * @param content content/ main text of review
     * @param userEmail user email
     * @param location location of review
     * @param day day in the schedule of freebirds
     * @param hours hours of operation of freebirds
     * @param description short description of freebird
     */
    public FreebirdReview(String title, String content, String userEmail, String location, String day, String hours, String description) {
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
     * @param day day in the schedule of freebird
     * @param hours hours of operation of freebird
     * @param description short description of freebird
     */
    public FreebirdReview(String day, String hours, String description) {
        this.day = day;
        this.hours = hours;
        this.description = description;
    }

    // Getters and setters

    /**
     * getter
     * @return get title
     */
    public String getTitle(){
        return title;
    }

    /**
     * setter
     * @param title set title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * getter
     * @return get content of review
     */
    public String getContent(){
        return content;
    }

    /**
     * setter
     * @param content set content of review
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
     * @return get location of review
     */
    public String getLocation(){
        return location;
    }

    /**
     * setter
     * @param location set location of review
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * getter
     * @return get first part of the csv
     */
    public String getDay() {
        return day;
    }

    /**
     * setter
     * @param day set first part of csv
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * getter
     * @return get middle part of csv
     */
    public String getHours() {
        return hours;
    }

    /**
     * setter
     * @param hours set middle part of csv
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     * getter
     * @return get description, last part of the csv
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter
     * @param description set the last part of csv
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * read csv file for freebirds
     */
    public static List<FreebirdReview> readReviewsFromCSV(Context context) {
        List<FreebirdReview> reviewsList = new ArrayList<>();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("Freebirds_World_Burrito.csv");
            Scanner scanner = new Scanner(inputStream);

            // Skip the header line
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

                    FreebirdReview review = new FreebirdReview(day, hours, description);
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
