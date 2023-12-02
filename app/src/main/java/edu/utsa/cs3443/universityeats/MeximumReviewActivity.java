/**
 * Jesus Pinales owj958
 * will save reviews user types in and allow user to delete reviews
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

public class MeximumReviewActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String PREFS_NAME = "MeximumReviewPrefs";
    private static final String KEY_REVIEW_COUNT = "ReviewCount";
    private static final String MEXIMUM_PREFIX = "meximum_";

    private LinearLayout reviewContainer;
    private List<MeximumReview> reviewList;

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
     * display reviews here
     */
    private void displayReviews() {
        for(MeximumReview reviews : reviewList){
            createReviewView(reviews);
        }
    }

    /**
     * load reviews from sharedpreference
     */
    private void loadReviewsFromPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int reviewCount = sharedPreferences.getInt(KEY_REVIEW_COUNT, 0);

        for(int i = 0; i < reviewCount; i++){
            String title = sharedPreferences.getString(MEXIMUM_PREFIX + "review_title_" + i, "");
            String content = sharedPreferences.getString(MEXIMUM_PREFIX + "review_content_" + i, "");

            MeximumReview review = new MeximumReview();
            review.setTitle(title);
            review.setContent(content);

            reviewList.add(review);

        }
    }

    /**
     * save reviews here
     */
    private void saveReview() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        if(!title.isEmpty() && !content.isEmpty()){
            String userEmail = getUserEmailFromPreferences();


            MeximumReview review = new MeximumReview();
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
     * @return get email from user and sharedpreference
     */
    private String getUserEmailFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        return preferences.getString("user_email", "");
    }

    /**
     * clear input fields when user submits review
     */
    private void clearInputFields() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        titleEditText.getText().clear();
        contentEditText.getText().clear();
    }

    /**
     *
     * @param review create the view for review
     */
    private void createReviewView(final MeximumReview review) {
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
     * @param review ask user of they are sure about deleting review
     */
    private void showDeleteDialog(final MeximumReview review) {
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
     * @param review delete the review and refresh view
     */
    private void deleteReviewAndRefresh(MeximumReview review) {
        reviewList.remove(review);
        saveReviewsToPreferences();
        refreshNoteViews();
    }

    /**
     * refresh view
     */
    private void refreshNoteViews() {
        reviewContainer.removeAllViews();
        displayReviews();
    }

    /**
     * save reviews from sharedpreference
     */
    private void saveReviewsToPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_REVIEW_COUNT, reviewList.size());
        for(int i = 0; i < reviewList.size(); i++){
            MeximumReview review = reviewList.get(i);
            editor.putString(MEXIMUM_PREFIX + "review_title_" + i, review.getTitle());
            editor.putString(MEXIMUM_PREFIX + "review_content_" + i, review.getContent());
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