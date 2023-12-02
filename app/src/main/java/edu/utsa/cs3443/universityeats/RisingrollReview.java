/**
 * Jesus Pinales owj958
 * RisingrollReview will handle getters and setters for reviews and info, class will also read csv file
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

public class RisingrollReview {
    private String title;
    private String content;
    private String userEmail;
    private String location;
    private String day;
    private String hours;
    private String description;

    // Default constructor
    public RisingrollReview() {
    }

    // Parameterized constructor

    /**
     *
     * @param title title for review
     * @param content content for review
     * @param userEmail user email
     * @param location location of review
     * @param day days of operation
     * @param hours hours of operation
     * @param description description of location
     */
    public RisingrollReview(String title, String content, String userEmail, String location, String day, String hours, String description) {
        this.title = title;
        this.content = content;
        this.userEmail = userEmail;
        this.location = location != null ? location : "";
        ;
        this.day = day;
        this.hours = hours;
        this.description = description;
    }

    /**
     *
     * @param day days of operation
     * @param hours hours of operation
     * @param description description of operation
     */
    public RisingrollReview(String day, String hours, String description) {
        this.day = day;
        this.hours = hours;
        this.description = description;
    }


    // Getters and setters

    /**
     * getter
     * @return get title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter
     * @param title set title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getter
     * @return get content review
     */
    public String getContent() {
        return content;
    }

    /**
     * setter
     * @param content set content review
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * getter
     * @return set user email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * setter
     * @param userEmail set user email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * getter
     * @return get location
     */
    public String getLocation() {
        return location;
    }

    /**
     * setter
     * @param location set locattion
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * getter
     * @return get day
     */
    public String getDay() {
        return day;
    }

    /**
     * setter
     * @param day set days
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * getter
     * @return get hours
     */
    public String getHours() {
        return hours;
    }

    /**
     * setter
     * @param hours set hours
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     * getter
     * @return get description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter
     * @param description set description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    // read csv file

    public static List<RisingrollReview> readReviewsFromCSV(Context context) {
        List<RisingrollReview> reviewsList = new ArrayList<>();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("Rising_Roll.csv");
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

                    RisingrollReview review = new RisingrollReview(day, hours, description);
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