package io.roberthernandez.testapp;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.String;
import java.util.ArrayList;
import java.util.Random;


public class HomeScreen extends AppCompatActivity {

    LinearLayout background;
    Button btnBlue;
    ArrayList<String> haikus;
    final int numberOfHaikusInFile = 18;
    String filename = "famous_haikus.txt";
    Random rand;
    Spinner LanguageSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);

        background = (LinearLayout) findViewById(R.id.background);
        btnBlue = (Button) findViewById(R.id.btnBlue);

        rand = new Random();
        haikus = getHaikus();

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                background.setBackgroundColor(Color.parseColor("#006699"));
                updateHaiku(getSingleHaiku(haikus));
            }
        });

        // Language Translation Selector
        LanguageSpinner = (Spinner) findViewById(R.id.LanguageSpinner);
        ArrayAdapter<String> LanguageStringAdapter = new ArrayAdapter<String>(HomeScreen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Languages));
        LanguageStringAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LanguageSpinner.setAdapter(LanguageStringAdapter);
    }

    protected String getSingleHaiku(ArrayList<String> list) {
        return list.get(rand.nextInt(list.size()));
    }

    protected void updateHaiku(String contents) {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(contents);
    }

    protected ArrayList<String> getHaikus() {

        // only for testing
        TextView textView = (TextView) findViewById(R.id.textView);

        ArrayList<String> haikuList = new ArrayList<String>();

        try {
            final AssetManager assetManager = getApplicationContext().getAssets();
            final InputStream inputStream = assetManager.open(filename);
            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            for (int i = 0; i < numberOfHaikusInFile; ++i ) {
                final StringBuilder builder = new StringBuilder();
                // Each Haiku is 3 lines plus a blank line, then a line containing the author
                for (int j = 0; j < 6; ++j) {
                    builder.append(bufferedReader.readLine() + "\n");
                }
                String builderContents = builder.toString();
                haikuList.add(builderContents);
            }

        } catch (Exception e) {
            textView.setText(e.getMessage());
        }
        return haikuList;
    }
}
