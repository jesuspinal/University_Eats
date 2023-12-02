/**
 * Jesus Pinales owj958
 * cafeReviewActivity will handle the review input from user.
 * here we will create and save the users review, and the option to delete the review too.
 */
package edu.utsa.cs3443.universityeats;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class cafeReviewActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String PREFS_NAME = "CafeReviewPrefs";
    private static final String KEY_REVIEW_COUNT = "ReviewCount";
    private static final String CAFE_PREFIX = "cafe_";
    private static final String LOCATION_PREFIX = "location_"; //for new locations



    private LinearLayout reviewContainer;
    private List<CafeReviews> reviewList;
    private String currentLocationId; // store current location

    Button homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_review);


        // UI elements
        reviewContainer = findViewById(R.id.reviewContainer);
        Button saveButton = findViewById(R.id.saveButton);

        // Initialize variables
        reviewList = new ArrayList<>();

        saveButton.setOnClickListener(v -> saveReview());

        currentLocationId = "Cafe";
        loadReviewsFromPreferences(currentLocationId);
        displayReviews();

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener((View.OnClickListener)this);
        //loadReviewsFromPreferences();

    }

    /**
     * Display reviews in the UI
     */
    private void displayReviews() {
        for(CafeReviews reviews : reviewList){
            createReviewView(reviews);
        }
    }

    /**
     * Load existing reviews from sharedpreference
     * @param locationId location of review
     */
    private void loadReviewsFromPreferences(String locationId) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME + "-" + locationId, MODE_PRIVATE);
        int reviewCount = sharedPreferences.getInt(KEY_REVIEW_COUNT, 0);

        for(int i = 0; i < reviewCount; i++){
            String title = sharedPreferences.getString(CAFE_PREFIX + "review_title_" + i, "");
            String content = sharedPreferences.getString(CAFE_PREFIX + "review_content_" + i, "");

            CafeReviews review = new CafeReviews();
            review.setTitle(title);
            review.setContent(content);

            reviewList.add(review);

        }
    }

    /**
     * Save users review to sharedpreference
     */
    private void saveReview() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        if(!title.isEmpty() && !content.isEmpty()){
            String userEmail = getUserEmailFromPreferences();

            // Create cafe reviews with user review
            CafeReviews review = new CafeReviews();
            review.setTitle(title);
            review.setContent(content);
            review.setUserEmail(userEmail);

            // add review to list
            reviewList.add(review);
            saveReviewsToPreferences(currentLocationId);

            createReviewView(review);
            clearInputFields();

        }
    }

    /**
     * Save users email
     * @param userEmail save email to sharedpreference
     */
    private void saveUserEmailToPreferences(String userEmail){
        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_email", userEmail);
        editor.apply();
    }

    /**
     * @return Retrieve email from shared preference
     */
    private String getUserEmailFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        String userEmail = preferences.getString("user_email", "");
        //return preferences.getString("user_email", "");
        return userEmail;
    }

    /**
     * clear input field after saving review
     */
    private void clearInputFields() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        titleEditText.getText().clear();
        contentEditText.getText().clear();
    }

    /**
     * @param review create review for display review
     */
    private void createReviewView(final CafeReviews review) {
        View reviewView = getLayoutInflater().inflate(R.layout.review_item, null);
        TextView titleTextView = reviewView.findViewById(R.id.titleTextView);
        TextView contentTextView = reviewView.findViewById(R.id.contentTextView);
        TextView userTextView = reviewView.findViewById(R.id.userTextView);

        titleTextView.setText(review.getTitle());
        contentTextView.setText(review.getContent());
        userTextView.setText("User: " + review.getUserEmail());

        reviewView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDeleteDialog(review);
                return false;

            }
        });
        reviewContainer.addView(reviewView);
    }

    /**
     * @param review show confimation for deleted review
     */
    private void showDeleteDialog(final CafeReviews review) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Review");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteReviewAndRefresh(review);


            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();

    }

    /**
     * @param review delete review, save changes
     */
    private void deleteReviewAndRefresh(CafeReviews review) {
        reviewList.remove(review);
        saveReviewsToPreferences(currentLocationId);
        refreshNoteViews();
    }

    /**
     * Refresh UI, display updated reviews
     */
    private void refreshNoteViews() {
        reviewContainer.removeAllViews();
        displayReviews();
    }

    /**
     * @param locationId save reviews to sharedpreference for location
     */
    private void saveReviewsToPreferences(String locationId) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME + "-" + locationId, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_REVIEW_COUNT, reviewList.size());
        for(int i = 0; i < reviewList.size(); i++){
            CafeReviews review = reviewList.get(i);
            editor.putString(CAFE_PREFIX + "review_title_" + i, review.getTitle());
            editor.putString(CAFE_PREFIX + "review_content_" + i, review.getContent());
        }
        editor.apply();


    }
    @Override
    public void onClick(View v) {
        Intent i;
        if (v.getId() == R.id.reviewButton) {
            i = new Intent(this, cafeReviewActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.homeButton){
            i = new Intent(this, ScrollHomeActivity.class);
            startActivity(i);
        }

    }
}