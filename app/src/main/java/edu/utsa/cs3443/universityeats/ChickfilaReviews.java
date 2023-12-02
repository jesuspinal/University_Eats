/**
 * Jesus Pinales owj958
 * ChickfilaReviews represents reviews for Chick-fil-A, containing information such as title, content,
 * user email, location, day, hours, and description.
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

public class ChickfilaReviews {
    private String title;
    private String content;
    private String userEmail;
    private String location;
    private String day;
    private String hours;
    private String description;

    // Default constructor
    public ChickfilaReviews() {
    }

    // Parameterized constructor

    /**
     * @param title     title of the review
     * @param content   content or main text of review
     * @param userEmail users email of who created review
     * @param location  location review
     * @param day   day in the schedule of chickfila
     * @param hours hours of operation of chickfila
     * @param description   short description of chickfila
     */
    public ChickfilaReviews(String title, String content, String userEmail, String location, String day, String hours, String description) {
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
     * @param day  days in the schedule of chick
     * @param hours hours of operation
     * @param description   short description
     */
    public ChickfilaReviews(String day, String hours, String description) {
        this.day = day;
        this.hours = hours;
        this.description = description;

    }

    // Getters and setters

    /**
     * getter
     * @return get the title of review
     */
    public String getTitle(){
        return title;
    }

    /**
     * setter
     * @param title set the title of review
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * getter
     * @return get the content of the review
     */
    public String getContent(){
        return content;
    }

    /**
     * setter
     * @param content set the content of review
     */
    public void setContent(String content){
        this.content = content;
    }

    /**
     * getter
     * @return get the user email
     */
    public String getUserEmail(){
        return userEmail;
    }

    /**
     * setter
     * @param userEmail set the user email
     */
    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    /**
     * getter
     * @return get the location of chick
     */
    public String getLocation(){
        return location;
    }

    /**
     * setter
     * @param location set the location of chick
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * getter
     * @return get the first part of csv file
     */
    public String getDay() {
        return day;
    }

    /**
     * setter
     * @param day set the first part of csv file
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
     * @return get last part of csv file
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

    // read chickfila info from csv

    public static List<ChickfilaReviews> readReviewsFromCSV(Context context) {
        List<ChickfilaReviews> reviewsList = new ArrayList<>();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("Chick-fil-A.csv");
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

                    ChickfilaReviews review = new ChickfilaReviews (day, hours, description);
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
