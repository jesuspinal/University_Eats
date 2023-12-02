/**
 * Jesus Pinales owj958
 * SmoothieKingReview class will have getters and setters for reviews and info of smoothieking
 * will also read csv file
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

public class SmoothieKingReview {
    private String title;
    private String content;
    private String userEmail;
    private String location;
    private String day;
    private String hours;
    private String description;

    // Default constructor
    public SmoothieKingReview() {
    }

    // Parameterized constructor

    /**
     *
     * @param title review title
     * @param content review content
     * @param userEmail user email
     * @param location review location
     * @param day days of operation
     * @param hours hours of operation
     * @param description short description
     */
    public SmoothieKingReview(String title, String content, String userEmail, String location, String day, String hours, String description) {
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
     * @param description short description
     */
    public SmoothieKingReview(String day, String hours, String description) {
        this.day = day;
        this.hours = hours;
        this.description = description;
    }

    // Getters and setters

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getUserEmail(){
        return userEmail;
    }
    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static List<SmoothieKingReview> readReviewsFromCSV(Context context) {
        List<SmoothieKingReview> reviewsList = new ArrayList<>();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("Smoothie_King.csv");
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

                    SmoothieKingReview review = new SmoothieKingReview(day, hours, description);
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
