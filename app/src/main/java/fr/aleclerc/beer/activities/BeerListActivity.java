package fr.aleclerc.beer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.aleclerc.beer.Listeners.TaskListener;
import fr.aleclerc.beer.R;
import fr.aleclerc.beer.adapters.BeersAdapter;
import fr.aleclerc.beer.beans.Beer;
import fr.aleclerc.beer.tasks.GetBeersTask;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Amaury on 20/02/2016.
 */
@ContentView(R.layout.activity_beer_list)
public class BeerListActivity extends RoboActivity implements TaskListener, AdapterView.OnItemClickListener {

    @InjectView(R.id.tvTitre)
    TextView tvTitre;
    @InjectView(R.id.lvBeer)
    ListView lvBeer;

    private GetBeersTask task;
    private Long style_id;
    private List<Beer> beers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        style_id =	intent.getLongExtra("style_id", 0);
        String style_name =	intent.getStringExtra("style_name");
        tvTitre.setText(style_name);
        this.beers = new ArrayList<Beer>();
        lvBeer.setOnItemClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        task = new GetBeersTask(this, this);
        task.execute(style_id);
    }

    @Override
    public void onTaskFinish() {
        try {

            beers = task.get();
            BeersAdapter adapter = new BeersAdapter(this, R.layout.beer_item, beers );
            lvBeer.setAdapter(adapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Beer beer = (Beer) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, BeerActivity.class);
        intent.putExtra("beer_id", beer.getId());
        intent.putExtra("beer_name", beer.getName());
        this.startActivity(intent);
    }
}
