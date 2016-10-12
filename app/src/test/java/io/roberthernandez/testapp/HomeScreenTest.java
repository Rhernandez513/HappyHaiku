package io.roberthernandez.testapp;

import android.widget.Button;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by robert on 9/10/16.
 */
public class HomeScreenTest {
    @Test
    public void testOnCreate() throws Exception {
        HomeScreen screen = new HomeScreen();
        assertNotNull( (LinearLayout) screen.background);
        assertNotNull( (Button) screen.btnBlue);
        assertNotNull( (ArrayList<String>) screen.haikus);
        assertNotNull( (Random)screen.rand);
    }
}
