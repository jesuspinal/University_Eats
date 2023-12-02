/**
 * Jesus Pinales owj958
 * RisingrollActivity will be the main page when user is in rising roll, here we display the days/hours of operation
 * and buttons for leaving a review, or go home to screen
 */
package edu.utsa.cs3443.universityeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class RisingrollActivity extends AppCompatActivity implements View.OnClickListener{
    Button reviewButton;
    Button homeButton;
    TextView reviewsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risingroll);
        reviewButton = findViewById(R.id.reviewButton);
        homeButton = findViewById(R.id.homeButton);
        reviewsTextView = findViewById(R.id.reviewsTextView);


        reviewButton.setOnClickListener((View.OnClickListener) this);
        homeButton.setOnClickListener((View.OnClickListener)this);

        List<RisingrollReview> reviewsList = RisingrollReview.readReviewsFromCSV(this);

        // Update the TextView with the information
        StringBuilder reviewsText = new StringBuilder();
        for (RisingrollReview review : reviewsList) {
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
            i = new Intent(this, RisingrollActivityReview.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.homeButton){
            i = new Intent(this, ScrollHomeActivity.class);
            startActivity(i);
        }

    }
}