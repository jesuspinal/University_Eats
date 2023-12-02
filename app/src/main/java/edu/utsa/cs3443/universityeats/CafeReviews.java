/**
 * Jesus Pinales owj958
 * This class will read the roadrunnercafe.csv file, and handle the information of the location
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

public class CafeReviews {
    /**
     * variables for info
     */
    private String title;
    private String content;
    private String userEmail;
    private String location;
    private String cafeId;
    private String day;
    private String hours;
    private String description;

    // Default constructor
    public CafeReviews() {
    }

    // Parameterized constructor
    /**
     *
     * @param title     title of the review
     * @param content   content or main text of review
     * @param userEmail users email of who created review
     * @param location  location review
     * @param day   day in the schedule of cafe
     * @param hours hours of operation of cafe
     * @param description   short description of cafe
     */
    public CafeReviews(String title, String content, String userEmail, String location, String cafeId, String day, String hours, String description) {
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
     * Getter
     * @return get title of location info
     */
    public String getTitle(){
        return title;
    }

    /**
     * setter
     * @param title set title of location info
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * getter
     * @return get the content of location
     */
    public String getContent(){
        return content;
    }

    /**
     * setter
     * @param content set content of the location
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
     * get location
     * @return get the location for review
     */
    public String getLocation(){
        return location;
    }

    /**
     * setter
     * @param location set the location for review
     */
    public void setLocation(String location){
        this.location = location;
    }

    public String getCafeId(){
        return cafeId;
    }
    public void setCafeId(String cafeId){
        this.cafeId = cafeId;
    }

    /**
     * getter
     * @return get the last part of csv file
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
     * @return get the last part of csv file
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter
     * @param description set last part of csv file
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Read from csv file needed
     * @param context context
     * @return list of info
     */
    public static List<CafeReviews> readReviewsFromCSV(Context context) {
        List<CafeReviews> reviewsList = new ArrayList<>();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("roadrunnercafe.csv");
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

                    CafeReviews review = new CafeReviews(null, null, null, null, null, day, hours, description);
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
