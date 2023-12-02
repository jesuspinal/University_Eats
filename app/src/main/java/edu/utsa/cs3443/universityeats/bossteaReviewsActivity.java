/**
 * Jesus Pinales owj958
 * bossteaReviewsActivity will handle the reviews section of boss tea.
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
import java.util.function.BooleanSupplier;

public class bossteaReviewsActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String PREFS_NAME = "BossteaReviewPrefs";
    private static final String KEY_REVIEW_COUNT = "ReviewCount";
    private static final String BOSSTEA_PREFIX = "bosstea_";

    private LinearLayout reviewContainer;
    private List<BossteaReviews> reviewList;

    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bosstea_reviews);

        //UI elements, buttons, clickers
        reviewContainer = findViewById(R.id.reviewContainer);
        Button saveButton = findViewById(R.id.saveButton);

        //Review list
        reviewList = new ArrayList<>();

        saveButton.setOnClickListener(v -> saveReview());
        loadReviewsFromPreferences();
        displayReviews();

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener((View.OnClickListener)this);
    }

    /**
     * Display all reviews for boss tea
     */
    private void displayReviews() {
        for(BossteaReviews reviews : reviewList){
            createReviewView(reviews);
        }
    }

    /**
     * Load reviews from SharedPreference
     */
    private void loadReviewsFromPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int reviewCount = sharedPreferences.getInt(KEY_REVIEW_COUNT, 0);

        for(int i = 0; i < reviewCount; i++){
            String title = sharedPreferences.getString(BOSSTEA_PREFIX + "review_title_" + i, "");
            String content = sharedPreferences.getString(BOSSTEA_PREFIX + "review_content_" + i, "");

            BossteaReviews review = new BossteaReviews();
            review.setTitle(title);
            review.setContent(content);

            reviewList.add(review);

        }
    }

    /**
     * Save new reviews of user
     */
    private void saveReview() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        //Review title and content input
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        //Check if title and content are not empty
        if(!title.isEmpty() && !content.isEmpty()){
            String userEmail = getUserEmailFromPreferences();


            BossteaReviews review = new BossteaReviews();
            review.setTitle(title);
            review.setContent(content);
            review.setUserEmail(userEmail);


            reviewList.add(review);
            saveReviewsToPreferences();

            createReviewView(review);
            clearInputFields();

        }
    }

    /**
     * Get the users email from sharedpreference
     * @return return email
     */
    private String getUserEmailFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        return preferences.getString("user_email", "");
    }

    /**
     * After saving a review, clear the input section, for new review
     */
    private void clearInputFields() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        titleEditText.getText().clear();
        contentEditText.getText().clear();
    }

    /**
     * Create a review to display
     * @param review
     */
    private void createReviewView(final BossteaReviews review) {
        View reviewView = getLayoutInflater().inflate(R.layout.review_item, null);
        TextView titleTextView = reviewView.findViewById(R.id.titleTextView);
        TextView contentTextView = reviewView.findViewById(R.id.contentTextView);
        TextView userTextView = reviewView.findViewById(R.id.userTextView);

        //Set the text
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

    private void showDeleteDialog(final BossteaReviews review) {
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
     * Delete review from section
     * @param review refresh UI when deleted review
     */
    private void deleteReviewAndRefresh(BossteaReviews review) {
        reviewList.remove(review);
        saveReviewsToPreferences();
        refreshNoteViews();
    }

    private void refreshNoteViews() {
        reviewContainer.removeAllViews();
        displayReviews();
    }

    /**
     * Save reviews to sharedpreferences
     */
    private void saveReviewsToPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_REVIEW_COUNT, reviewList.size());
        for(int i = 0; i < reviewList.size(); i++){
            BossteaReviews review = reviewList.get(i);
            editor.putString(BOSSTEA_PREFIX + "review_title_" + i, review.getTitle());
            editor.putString(BOSSTEA_PREFIX + "review_content_" + i, review.getContent());
        }
        editor.apply();


    }

    /**
     * Handle the button to go back to main screen, Home
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent i;
        if(v.getId() == R.id.homeButton){
            i = new Intent(this, ScrollHomeActivity.class);
            startActivity(i);
        }

    }

}