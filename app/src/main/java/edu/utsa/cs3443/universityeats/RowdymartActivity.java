/**
 * Jesus Pinales owj958
 * RowdymartActivity will be the main page of rowdymart, here we display hours/days of operation and short description.
 * also buttons for review or home screen
 */
package edu.utsa.cs3443.universityeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class RowdymartActivity extends AppCompatActivity implements View.OnClickListener{
    Button reviewButton;
    Button homeButton;
    TextView reviewsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rowdymart);

        reviewButton = findViewById(R.id.reviewButton);
        homeButton = findViewById(R.id.homeButton);
        reviewsTextView = findViewById(R.id.reviewsTextView);


        reviewButton.setOnClickListener((View.OnClickListener) this);
        homeButton.setOnClickListener((View.OnClickListener)this);

        List<RowdymartReview> reviewsList = RowdymartReview.readReviewsFromCSV(this);

        // Update the TextView with the information
        StringBuilder reviewsText = new StringBuilder();
        for (RowdymartReview review : reviewsList) {
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
            i = new Intent(this, RowdyReviewActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.homeButton){
            i = new Intent(this, ScrollHomeActivity.class);
            startActivity(i);
        }

    }
}