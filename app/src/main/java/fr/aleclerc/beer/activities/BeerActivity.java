package fr.aleclerc.beer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import fr.aleclerc.beer.Listeners.TaskListener;
import fr.aleclerc.beer.R;
import fr.aleclerc.beer.beans.Beer;
import fr.aleclerc.beer.tasks.GetBeerTask;
import fr.aleclerc.beer.tasks.GetImageTask;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Amaury on 20/02/2016.
 */
@ContentView(R.layout.activity_beer)
public class BeerActivity extends RoboActivity implements TaskListener {

    @InjectView(R.id.tvTitre)
    private TextView tvTitre;
    @InjectView(R.id.tvDescription)
    private TextView tvDescription;
    @InjectView(R.id.ivLabel)
    private ImageView ivLabel;
    private GetBeerTask task;
    private String beer_id;
    private Beer beer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        beer_id =	intent.getStringExtra("beer_id");
        String beer_name =	intent.getStringExtra("beer_name");
        tvTitre.setText(beer_name);
        tvDescription.setText("");
    }


    @Override
    protected void onResume() {
        super.onResume();
        task = new GetBeerTask(this, this);
        task.execute(beer_id);
    }

    @Override
    public void onTaskFinish() {
        try {

            beer = task.get();
            if(beer.getDescription() != null){
                tvDescription.setText(beer.getDescription());
            }
            if(beer.getLabels() != null && beer.getLabels().getLarge() != null){
                GetImageTask imageTask = new GetImageTask(ivLabel);
                imageTask.execute(beer.getLabels().getLarge());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
