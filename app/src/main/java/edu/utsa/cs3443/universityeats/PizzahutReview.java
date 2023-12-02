/**
 * Jesus Pinales owj958
 * PizzahutReview will handle getters and setters, and csv file reading for location of pizzahut
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

public class PizzahutReview {
    private String title;
    private String content;
    private String userEmail;
    private String location;
    private String day;
    private String hours;
    private String description;

    // Default constructor
    public PizzahutReview() {
    }

    // Parameterized constructor

    /**
     *
     * @param title title of review
     * @param content content of review
     * @param userEmail user email for review
     * @param location location of review
     */
    public PizzahutReview(String title, String content, String userEmail, String location) {
        this.title = title;
        this.content = content;
        this.userEmail = userEmail;
        this.location = location != null ? location : "";;
    }

    /**
     *
     * @param day days of operation
     * @param hours hours of operation
     * @param description short description
     */
    public PizzahutReview(String day, String hours, String description) {
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
     * @param location set location
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * getter
     * @return get first part of csv
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
     * @return get second part of csv
     */
    public String getHours() {
        return hours;
    }

    /**
     * setter
     * @param hours set second part of csv
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     * getter
     * @return get third part csv
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter
     * @param description set third part csv
     */
    public void setDescription(String description) {
        this.description = description;
    }

    // read csv file here
    public static List<PizzahutReview> readReviewsFromCSV(Context context) {
        List<PizzahutReview> reviewsList = new ArrayList<>();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("Pizza_Hut.csv");
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

                    PizzahutReview review = new PizzahutReview(day, hours, description);
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
