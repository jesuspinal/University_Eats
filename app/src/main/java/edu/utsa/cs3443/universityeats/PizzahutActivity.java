/**
 * Jesus Pinales owj958
 * PizzahutActivity will be the main poge for this location, user can view days/hours of operation,
 * and a short description of location, allow user to go back home , or leave a review
 */
package edu.utsa.cs3443.universityeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PizzahutActivity extends AppCompatActivity implements View.OnClickListener{
    Button reviewButton;
    Button homeButton;
    TextView reviewsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzahut);

        reviewButton = findViewById(R.id.reviewButton);
        homeButton = findViewById(R.id.homeButton);
        reviewsTextView = findViewById(R.id.reviewsTextView);


        reviewButton.setOnClickListener((View.OnClickListener) this);
        homeButton.setOnClickListener((View.OnClickListener)this);

        List<PizzahutReview> reviewsList = PizzahutReview.readReviewsFromCSV(this);

        // Update the TextView with the information
        StringBuilder reviewsText = new StringBuilder();
        for (PizzahutReview review : reviewsList) {
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
            i = new Intent(this, PizzahutReviewActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.homeButton){
            i = new Intent(this, ScrollHomeActivity.class);
            startActivity(i);
        }

    }
}