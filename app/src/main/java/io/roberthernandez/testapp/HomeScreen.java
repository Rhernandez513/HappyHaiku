package io.roberthernandez.testapp;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.String;
import java.util.ArrayList;
import java.util.Random;


public class HomeScreen extends AppCompatActivity implements OnItemSelectedListener {

    LinearLayout background;
    Button btnBlue;
    ArrayList<String> haikus;
    final int numberOfHaikusInFile = 18;
    String [] filenames = {
      "famous_haikus_en.txt"  ,
      "famous_haikus_es.txt"  ,
      "famous_haikus_fr.txt"  ,
      "famous_haikus_chi.txt" ,
      "famous_haikus_ja.txt"
    };
    String filename = filenames[0];
    Random rand;
    Spinner LanguageSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);

        background = (LinearLayout) findViewById(R.id.background);
        btnBlue = (Button) findViewById(R.id.btnBlue);

        rand = new Random();
        //haikus = getHaikuFileContents();

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                background.setBackgroundColor(Color.parseColor("#006699"));
                updateHaiku(getSingleHaiku(getHaikuFileContents(filename)));
            }
        });

        // Language Translation Selector
        LanguageSpinner = (Spinner) findViewById(R.id.LanguageSpinner);
        ArrayAdapter<String> LanguageStringAdapter = new ArrayAdapter<String>(HomeScreen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Languages));
        LanguageStringAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LanguageSpinner.setAdapter(LanguageStringAdapter);
        LanguageSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        setHaikuFileName( (String) parent.getItemAtPosition(pos));
        // updateHaiku((String) parent.getItemAtPosition(pos));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        System.out.println("Nothing Selected");
    }

    protected void setHaikuFileName(String Language) {
        switch (Language) {
            case "English":
                filename = filenames[0];
                break;
            case "Spanish":
                filename = filenames[1];
                break;
            case "French":
                filename = filenames[2];
                break;
            case "Chinese":
                filename = filenames[3];
                break;
            case "Japanese":
                filename = filenames[4];
                break;
            default:
                filename = filenames[0];
        }
    }

    protected String getSingleHaiku(ArrayList<String> list) {
        return list.get(rand.nextInt(list.size()));
    }

    protected void updateHaiku(String contents) {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(contents);
    }

    protected ArrayList<String> getHaikuFileContents(String haiku_file) {

        // only for testing
        TextView textView = (TextView) findViewById(R.id.textView);

        ArrayList<String> haikuList = new ArrayList<String>();

        try {
            final AssetManager assetManager = getApplicationContext().getAssets();
            // final InputStream inputStream = assetManager.open(filename);
            final InputStream inputStream = assetManager.open(haiku_file);
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
