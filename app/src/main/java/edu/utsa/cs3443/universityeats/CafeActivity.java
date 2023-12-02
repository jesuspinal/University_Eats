/**
 * Jesus Pinales owj958
 * CafeActivity will display the Cafe information, and will set up buttons to use on this activity.
 *
 */
package edu.utsa.cs3443.universityeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CafeActivity extends AppCompatActivity implements View.OnClickListener{
    Button reviewButton;
    Button homeButton;
    TextView reviewsTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);

        // UI elements
        reviewButton = findViewById(R.id.reviewButton);
        homeButton = findViewById(R.id.homeButton);
        reviewsTextView = findViewById(R.id.reviewsTextView);

        // Set click listeners for buttons
        reviewButton.setOnClickListener((View.OnClickListener) this);
        homeButton.setOnClickListener((View.OnClickListener)this);

        //User loggedInUser = SessionManager.getLoggedInUser();

        //if(loggedInUser != null) {
            List<CafeReviews> reviewsList = CafeReviews.readReviewsFromCSV(this);

            // Update the TextView with the information
            StringBuilder reviewsText = new StringBuilder();
            for (CafeReviews review : reviewsList) {
                reviewsText.append(review.getDay())
                        .append(", ").append(review.getHours())
                        .append(", ").append(review.getDescription())
                        .append("\n\n");
            }
            reviewsTextView.setText(reviewsText.toString());
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