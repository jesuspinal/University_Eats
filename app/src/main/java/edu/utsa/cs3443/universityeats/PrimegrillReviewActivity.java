/**
 * Jesus Pinalws owj958
 * PrimegrillReviewActivity will handle users input from review, let user upload review, delete, and go home button
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

public class PrimegrillReviewActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String PREFS_NAME = "PrimeReviewPrefs";
    private static final String KEY_REVIEW_COUNT = "ReviewCount";
    private static final String PRIME_PREFIX = "prime_";

    private LinearLayout reviewContainer;
    private List<PrimegrillReview> reviewList;

    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bosstea_reviews);

        reviewContainer = findViewById(R.id.reviewContainer);
        Button saveButton = findViewById(R.id.saveButton);

        reviewList = new ArrayList<>();

        saveButton.setOnClickListener(v -> saveReview());
        loadReviewsFromPreferences();
        displayReviews();

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener((View.OnClickListener)this);
    }

    /**
     * display review here
     */
    private void displayReviews() {
        for(PrimegrillReview reviews : reviewList){
            createReviewView(reviews);
        }
    }

    /**
     * load review from shared preference here
     */
    private void loadReviewsFromPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int reviewCount = sharedPreferences.getInt(KEY_REVIEW_COUNT, 0);

        for(int i = 0; i < reviewCount; i++){
            String title = sharedPreferences.getString(PRIME_PREFIX + "review_title_" + i, "");
            String content = sharedPreferences.getString(PRIME_PREFIX + "review_content_" + i, "");

            PrimegrillReview review = new PrimegrillReview();
            review.setTitle(title);
            review.setContent(content);

            reviewList.add(review);

        }
    }

    /**
     * save a new review here
     */
    private void saveReview() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        if(!title.isEmpty() && !content.isEmpty()){
            String userEmail = getUserEmailFromPreferences();


            PrimegrillReview review = new PrimegrillReview();
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
     *
     * @return get users email and save
     */
    private String getUserEmailFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        return preferences.getString("user_email", "");
    }

    /**
     * clear what user wrote into input fields after they upload the review
     */
    private void clearInputFields() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        titleEditText.getText().clear();
        contentEditText.getText().clear();
    }

    /**
     * create review view
     * @param review view for review
     */
    private void createReviewView(final PrimegrillReview review) {
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
     *
     * @param review ask user if they are sure about deleting a review
     */
    private void showDeleteDialog(final PrimegrillReview review) {
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
     * delete review and refresh
     * @param review refresh view
     */
    private void deleteReviewAndRefresh(PrimegrillReview review) {
        reviewList.remove(review);
        saveReviewsToPreferences();
        refreshNoteViews();
    }

    private void refreshNoteViews() {
        reviewContainer.removeAllViews();
        displayReviews();
    }

    /**
     * save reviews to show later when needed
     */
    private void saveReviewsToPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_REVIEW_COUNT, reviewList.size());
        for(int i = 0; i < reviewList.size(); i++){
            PrimegrillReview review = reviewList.get(i);
            editor.putString(PRIME_PREFIX + "review_title_" + i, review.getTitle());
            editor.putString(PRIME_PREFIX + "review_content_" + i, review.getContent());
        }
        editor.apply();


    }
    @Override
    public void onClick(View v) {
        Intent i;
        if(v.getId() == R.id.homeButton){
            i = new Intent(this, ScrollHomeActivity.class);
            startActivity(i);
        }

    }
}