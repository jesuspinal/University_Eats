/**
 * Jesus Pinales owj958
 * This class is used for the boss tea activity, here we set up the information text and buttons for user
 * to navigate when they are in the in the boss tea activity
 */
package edu.utsa.cs3443.universityeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BossteaActivity extends AppCompatActivity implements View.OnClickListener{

    Button reviewButton;
    Button homeButton;
    TextView reviewsTextView;

    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bosstea);

        //UI elements
        reviewButton = findViewById(R.id.reviewButton);
        homeButton = findViewById(R.id.homeButton);
        reviewsTextView = findViewById(R.id.reviewsTextView);

        //Click listeners for buttons
        reviewButton.setOnClickListener((View.OnClickListener) this);
        homeButton.setOnClickListener((View.OnClickListener)this);

        //Read location info, hours, open/closed, location
        List<BossteaReviews> reviewsList = BossteaReviews.readReviewsFromCSV(this);

        // Update the TextView with the information
        StringBuilder reviewsText = new StringBuilder();
        for (BossteaReviews review : reviewsList) {
            reviewsText.append(review.getDay())
                    .append(", ").append(review.getHours())
                    .append(", ").append(review.getDescription())
                    .append("\n\n");
        }
        reviewsTextView.setText(reviewsText.toString());
    }

    /**
     * Buttons for user to leave a review or go back to home screen
     * @param v The view that was clicked.
     */

    @Override
    public void onClick(View v) {
        Intent i;
        if (v.getId() == R.id.reviewButton) {
            i = new Intent(this, bossteaReviewsActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.homeButton){
            i = new Intent(this, ScrollHomeActivity.class);
            startActivity(i);
        }

    }
}