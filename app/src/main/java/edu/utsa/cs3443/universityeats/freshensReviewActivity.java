/**
 * Jesus Pinales owj958
 * freshensReviewActivity will let users write a review and delete them
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

public class freshensReviewActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String PREFS_NAME = "FreshensReviewPrefs";
    private static final String KEY_REVIEW_COUNT = "ReviewCount";
    private static final String FRESHENS_PREFIX = "freshens_";

    private LinearLayout reviewContainer;
    private List<FreshenReviews> reviewList;

    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bosstea_reviews);

        // UI elements
        reviewContainer = findViewById(R.id.reviewContainer);
        Button saveButton = findViewById(R.id.saveButton);

        // review list
        reviewList = new ArrayList<>();

        saveButton.setOnClickListener(v -> saveReview());
        loadReviewsFromPreferences();
        displayReviews();

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener((View.OnClickListener)this);
    }

    /**
     * display reviews to user
     */
    private void displayReviews() {
        for(FreshenReviews reviews : reviewList){
            createReviewView(reviews);
        }
    }

    /**
     * load reviews from sharedpreferences
     */
    private void loadReviewsFromPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int reviewCount = sharedPreferences.getInt(KEY_REVIEW_COUNT, 0);

        for(int i = 0; i < reviewCount; i++){
            String title = sharedPreferences.getString(FRESHENS_PREFIX + "review_title_" + i, "");
            String content = sharedPreferences.getString(FRESHENS_PREFIX + "review_content_" + i, "");

            FreshenReviews review = new FreshenReviews();
            review.setTitle(title);
            review.setContent(content);

            reviewList.add(review);

        }
    }

    /**
     * save review
     */
    private void saveReview() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        if(!title.isEmpty() && !content.isEmpty()){
            String userEmail = getUserEmailFromPreferences();


            FreshenReviews review = new FreshenReviews();
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
     * @return get user email from sharedpreferences
     */
    private String getUserEmailFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        return preferences.getString("user_email", "");
    }

    /**
     * clear input fields after a review is written
     */
    private void clearInputFields() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        titleEditText.getText().clear();
        contentEditText.getText().clear();
    }

    /**
     *
     * @param review create view for a review
     */
    private void createReviewView(final FreshenReviews review) {
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
     * @param review show dialog to confirm deletion of review
     */
    private void showDeleteDialog(final FreshenReviews review) {
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
     *
     * @param review delete and refresh UI
     */
    private void deleteReviewAndRefresh(FreshenReviews review) {
        reviewList.remove(review);
        saveReviewsToPreferences();
        refreshNoteViews();
    }

    /**
     * refresh UI
     */
    private void refreshNoteViews() {
        reviewContainer.removeAllViews();
        displayReviews();
    }

    /**
     * save reviews
     */
    private void saveReviewsToPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_REVIEW_COUNT, reviewList.size());
        for(int i = 0; i < reviewList.size(); i++){
            FreshenReviews review = reviewList.get(i);
            editor.putString(FRESHENS_PREFIX + "review_title_" + i, review.getTitle());
            editor.putString(FRESHENS_PREFIX + "review_content_" + i, review.getContent());
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