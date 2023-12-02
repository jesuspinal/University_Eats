/**
 * Jesus Pinales owj958
 * ScrollHomeActivity will be the main screen after logging in, each location is in the grid view and is clickable, we set the clickers here and location to
 * send where user clicked
 */
package edu.utsa.cs3443.universityeats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScrollHomeActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView cafe;
    private CardView freshens;
    private CardView starbucks;
    private CardView chickfila;
    private CardView subway;
    private CardView sushic;
    private CardView RowdyMart;
    private CardView smoothieKing;
    private CardView meximum;
    private CardView pandaExpress;
    private CardView freebirds;
    private CardView risingroll;
    private CardView primegrill;
    private CardView javacity;
    private CardView bosstea;
    private CardView pizzahut;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_home);

        logout = findViewById(R.id.logout);
        logout.setOnClickListener((View.OnClickListener)this);

        cafe = (CardView) findViewById(R.id.cafe);
        freshens = (CardView) findViewById(R.id.freshens);
        starbucks = (CardView) findViewById(R.id.starbucks);
        chickfila = (CardView) findViewById(R.id.chickfila);
        subway = (CardView) findViewById(R.id.subway);
        sushic = (CardView) findViewById(R.id.sushic);
        RowdyMart = (CardView) findViewById(R.id.RowdyMart);
        smoothieKing = (CardView) findViewById(R.id.smoothieKing);
        meximum = (CardView) findViewById(R.id.meximum);
        pandaExpress = (CardView) findViewById(R.id.pandaExpress);
        freebirds = (CardView) findViewById(R.id.freebirds);
        risingroll = (CardView) findViewById(R.id.risingroll);
        primegrill = (CardView) findViewById(R.id.primegrill);
        javacity = (CardView) findViewById(R.id.javacity);
        bosstea = (CardView) findViewById(R.id.bosstea);
        pizzahut = (CardView) findViewById(R.id.pizzahut);

        cafe.setOnClickListener((View.OnClickListener)this);
        freshens.setOnClickListener((View.OnClickListener)this);
        starbucks.setOnClickListener((View.OnClickListener)this);
        chickfila.setOnClickListener((View.OnClickListener)this);
        subway.setOnClickListener((View.OnClickListener)this);
        sushic.setOnClickListener((View.OnClickListener)this);
        RowdyMart.setOnClickListener((View.OnClickListener)this);
        smoothieKing.setOnClickListener((View.OnClickListener)this);
        meximum.setOnClickListener((View.OnClickListener)this);
        pandaExpress.setOnClickListener((View.OnClickListener)this);
        freebirds.setOnClickListener((View.OnClickListener)this);
        risingroll.setOnClickListener((View.OnClickListener)this);
        primegrill.setOnClickListener((View.OnClickListener)this);
        javacity.setOnClickListener((View.OnClickListener)this);
        bosstea.setOnClickListener((View.OnClickListener)this);
        pizzahut.setOnClickListener((View.OnClickListener)this);
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
        else if(v.getId() == R.id.RowdyMart) {
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
        else if(v.getId() == R.id.primegrill) {
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
        else if(v.getId() == R.id.logout)
        {
            i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

    }

}