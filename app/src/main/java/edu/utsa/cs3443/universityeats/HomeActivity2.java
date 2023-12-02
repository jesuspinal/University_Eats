/**
 * Jesus Pinales owj958
 * This was part of the demo, this is a second home/main menu, before scrolling user would press "Next page" button
 * and bring them here, not used in app
 */
package edu.utsa.cs3443.universityeats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity2 extends AppCompatActivity implements View.OnClickListener{
    private CardView jplRowdyMart, smoothieKing, meximum, pandaExpress, freebirds, risingroll;
    Button nextPage;
    Button backPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        CardView RowdyMart = (CardView) findViewById(R.id.RowdyMart);
        CardView smoothieKing = (CardView) findViewById(R.id.smoothieKing);
        CardView meximum = (CardView) findViewById(R.id.meximum);
        CardView pandaExpress = (CardView) findViewById(R.id.pandaExpress);
        CardView freebirds = (CardView) findViewById(R.id.freebirds);
        CardView risingroll = (CardView) findViewById(R.id.risingroll);
        nextPage = findViewById(R.id.nextPage);
        backPage = findViewById(R.id.backPage);

        backPage.setOnClickListener((View.OnClickListener)this);
        nextPage.setOnClickListener((View.OnClickListener) this);
        RowdyMart.setOnClickListener((View.OnClickListener)this);
        smoothieKing.setOnClickListener((View.OnClickListener)this);
        meximum.setOnClickListener((View.OnClickListener)this);
        pandaExpress.setOnClickListener((View.OnClickListener)this);
        freebirds.setOnClickListener((View.OnClickListener)this);
        risingroll.setOnClickListener((View.OnClickListener)this);
    }

    @Override
    public void onClick(View v){
        Intent i;

        if(v.getId() == R.id.RowdyMart) {
            i = new Intent(this, RowdymartActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.smoothieKing){
            i = new Intent(this, SmoothiekingActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.meximum){
            i = new Intent(this, MeximumActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.pandaExpress){
            i = new Intent(this, PandaexpressActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.freebirds){
            i = new Intent(this, FreebirdsActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.risingroll){
            i = new Intent(this, RisingrollActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.nextPage){
            i = new Intent(this, HomeActivity3.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.backPage){
            i = new Intent(this, HomeActivity.class);
            startActivity(i);
        }

    }
}