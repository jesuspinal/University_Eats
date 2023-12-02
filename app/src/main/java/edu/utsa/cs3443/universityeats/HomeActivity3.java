/**
 * Jesus Pinales owj958
 * Part of the demo, this was the third page displaying the last location after user pressed "Next Page",
 * button again. Not used in app
 */
package edu.utsa.cs3443.universityeats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity3 extends AppCompatActivity implements View.OnClickListener{
    private CardView primegrill, javacity, bosstea, pizzahut;

    Button backPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);

        CardView primegrill = (CardView) findViewById(R.id.primegrill);
        CardView javacity = (CardView) findViewById(R.id.javacity);
        CardView bosstea = (CardView) findViewById(R.id.bosstea);
        CardView pizzahut = (CardView) findViewById(R.id.pizzahut);
        backPage = findViewById(R.id.backPage);

        backPage.setOnClickListener((View.OnClickListener) this);
        primegrill.setOnClickListener((View.OnClickListener) this);
        javacity.setOnClickListener((View.OnClickListener) this);
        bosstea.setOnClickListener((View.OnClickListener) this);
        pizzahut.setOnClickListener((View.OnClickListener) this);
    }
        @Override
        public void onClick(View v){
            Intent i;

            if(v.getId() == R.id.primegrill) {
                i = new Intent(this, PrimegrillActivity.class);
                startActivity(i);
            }
            else if(v.getId() == R.id.javacity){
                i = new Intent(this, JavacityActivity.class);
                startActivity(i);
            }
            else if(v.getId() == R.id.bosstea){
                i = new Intent(this, BossteaActivity.class);
                startActivity(i);
            }
            else if(v.getId() == R.id.pizzahut){
                i = new Intent(this, PizzahutActivity.class);
                startActivity(i);
            }
            else if(v.getId() == R.id.backPage){
                i = new Intent(this, HomeActivity2.class);
                startActivity(i);
            }

        }

}