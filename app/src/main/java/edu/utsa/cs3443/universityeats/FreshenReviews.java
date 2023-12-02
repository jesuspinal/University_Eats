/**
 * Jesus Pinales owj958
 * This class will read the Freshens csv file, it will display the days of operation, hours, and a short description
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

public class FreshenReviews {
    private String title;
    private String content;
    private String userEmail;
    private String location;
    private String cafeId;
    private String day;
    private String hours;
    private String description;

    // Default constructor
    public FreshenReviews() {
    }

    // Parameterized constructor

    /**
     *
     * @param  title of the review
     * @param content content main text review
     * @param userEmail user email of who created review
     * @param location location of review
     * @param cafeId
     * @param day day in schedule of cafe
     * @param hours hours of operation
     * @param description short description
     */

    public FreshenReviews(String title, String content, String userEmail, String location, String cafeId, String day, String hours, String description) {
        this.title = title;
        this.content = content;
        this.userEmail = userEmail;
        this.location = location != null ? location : "";;
        this.cafeId = cafeId;
        this.day = day;
        this.hours = hours;
        this.description = description;
    }

    // Getters and setters

    /**
     * getter
     * @return return title
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
     * @return return content
     */
    public String getContent(){
        return content;
    }

    /**
     * setter
     * @param content set content
     */
    public void setContent(String content){
        this.content = content;
    }

    /**
     * getter
     * @return return users email
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
     * @return return location of review
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
     * @return return cafeID
     */
    public String getCafeId(){
        return cafeId;
    }

    /**
     * setter
     * @param cafeId set cafe ID
     */
    public void setCafeId(String cafeId){
        this.cafeId = cafeId;
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
     * @return get third part of csv file
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter
     * @param description set third part of csv file
     */
    public void setDescription(String description) {
        this.description = description;
    }

    // read freshens csv file

    public static List<FreshenReviews> readReviewsFromCSV(Context context) {
        List<FreshenReviews> reviewsList = new ArrayList<>();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("Freshens.csv");
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

                    FreshenReviews review = new FreshenReviews(null, null, null, null, null, day, hours, description);
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

