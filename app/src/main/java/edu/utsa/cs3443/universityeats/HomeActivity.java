package edu.utsa.cs3443.universityeats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView freshens;
    private CardView starbucks;
    private CardView chickfila;
    private CardView subway;
    private CardView sushic;
    Button nextPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView cafe = (CardView) findViewById(R.id.cafe);
        freshens = (CardView) findViewById(R.id.freshens);
        starbucks = (CardView) findViewById(R.id.starbucks);
        chickfila = (CardView) findViewById(R.id.chickfila);
        subway = (CardView) findViewById(R.id.subway);
        sushic = (CardView) findViewById(R.id.sushic);
        nextPage = findViewById(R.id.nextPage);

        nextPage.setOnClickListener((View.OnClickListener) this);
        cafe.setOnClickListener((View.OnClickListener)this);
        freshens.setOnClickListener((View.OnClickListener)this);
        starbucks.setOnClickListener((View.OnClickListener)this);
        chickfila.setOnClickListener((View.OnClickListener)this);
        subway.setOnClickListener((View.OnClickListener)this);
        sushic.setOnClickListener((View.OnClickListener)this);
    }

    @Override
    public void onClick(View v){
        Intent i;
        if(v.getId() == R.id.cafe) {
            i = new Intent(this, CafeActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.freshens) {
            i = new Intent(this, FreshensActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.starbucks) {
            i = new Intent(this, StarbucksActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.chickfila) {
            i = new Intent(this, ChickfilaActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.subway) {
            i = new Intent(this, SubwayActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.sushic) {
            i = new Intent(this, SushicActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.nextPage) {
            i = new Intent(this, HomeActivity2.class);
            startActivity(i);
        }





    }
}