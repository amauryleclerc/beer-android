package fr.aleclerc.beer.activities;

import android.os.Bundle;

import fr.aleclerc.beer.Listeners.TaskListener;
import roboguice.activity.RoboActivity;

/**
 * Created by Amaury on 20/02/2016.
 */
public class StyleListActivity  extends RoboActivity implements TaskListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onTaskFinish() {

    }
}
