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
import fr.aleclerc.beer.adapters.StylesAdapter;
import fr.aleclerc.beer.beans.Style;
import fr.aleclerc.beer.tasks.GetStylesTask;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Amaury on 20/02/2016.
 */
@ContentView(R.layout.activity_style_list)
public class StyleListActivity  extends RoboActivity implements TaskListener, AdapterView.OnItemClickListener {

    @InjectView(R.id.tvTitre)
    private TextView tvTitre;
    @InjectView(R.id.lvStyle)
    private ListView lvStyle;

    private GetStylesTask task;
    private Long categorie_id;
    private List<Style> styles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        categorie_id =	intent.getLongExtra("categorie_id", 0);
        String categorie_name =	intent.getStringExtra("categorie_name");
        tvTitre.setText("Styles de "+categorie_name);
        this.styles = new ArrayList<Style>();
        lvStyle.setOnItemClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        task = new GetStylesTask(this, this);
        task.execute();
    }

    @Override
    public void onTaskFinish() {
        try {

            List<Style> lesStyles = task.get();
            for(Style style: lesStyles){
                    if(style.getCategoryId() == categorie_id){
                        this.styles.add(style);
                    }
            }
            StylesAdapter adapter = new StylesAdapter(this, R.layout.style_item, styles );
            lvStyle.setAdapter(adapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Style style = (Style) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, BeerListActivity.class);
        intent.putExtra("style_id", style.getId());
        intent.putExtra("style_name", style.getName());
        this.startActivity(intent);
    }
}
