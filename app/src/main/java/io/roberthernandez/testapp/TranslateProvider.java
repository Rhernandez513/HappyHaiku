package io.roberthernandez.testapp;

// import android.content.res.AssetManager;
// import android.graphics.Color;
// import android.support.v7.app.AppCompatActivity;
// import android.os.Bundle;
// import android.view.View;
// import android.widget.Button;
// import android.widget.LinearLayout;
// import android.widget.TextView;

// import java.io.BufferedReader;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.lang.String;
// import java.util.ArrayList;
// import java.util.Random;


public class TranslateProvider extends AppCompatActivity {

    LinearLayout background;
    Button btnBlue;
    Button btnFlagDropDown;
    ArrayList<String> haikus;
    final int numberOfHaikusInFile = 18;
    String filename = "famous_haikus.txt";
    Random rand;

    // TODO: Add a JAVA Enumerable/Struct/Whatever HERE
    // ENUMERABLE Country_Flags = ["USA", "China", "Japan", "Spain", "France"];
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.linear_layout);
//        Change layout to a button with "Translate" type of logo
//        It can then have a drop down with country flags representing a handful of languages
//        for example
//
//        Chinese 
//        Japanese
//        English
//        Spanish
//        French

        background = (LinearLayout) findViewById(R.id.background);
        btnFlagDropDown = (Button) findViewById(R.id.btnFlagDropDown);
        
        btnFlagDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                // Gui Thread
                background.setBackgroundColor(Color.parseColor("#006699"));
            }
        });

        rand = new Random();
    }

    @Override
    protected void onReceiveLocalData(Bundle savedInstanceState) { } 

    @Override
    protected void onReceiveRemoteData(Bundle savedInstanceState) { }

    @Override
    protected void onTranslate(Bundle savedInstanceState) {  }

    @Override
    protected void onTransmissionFailure(Bundle savedInstanceState) {  }
}


