package fr.aleclerc.beer.activities;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.aleclerc.beer.Listeners.TaskListener;
import fr.aleclerc.beer.R;
import fr.aleclerc.beer.adapters.CategoriesAdapter;
import fr.aleclerc.beer.beans.Categorie;
import fr.aleclerc.beer.tasks.GetCategoriesTask;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;


@ContentView(R.layout.activity_categorie_list)
public class CategorieListActivity extends RoboActivity implements TaskListener{

    @InjectView(R.id.tvTitre)
    TextView tvTitre;
    @InjectView(R.id.lvCategorie)
    ListView lvCategorie;
    GetCategoriesTask task;
    List<Categorie> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitre.setText("Catégories");
    }

    @Override
    protected void onResume() {
        super.onResume();
        task = new GetCategoriesTask(this, this);
        task.execute();
    }

    @Override
    public void onTaskFinish() {
        try {
            categories = task.get();
            CategoriesAdapter adatpter = new CategoriesAdapter(this, R.layout.categorie_item, categories);
            lvCategorie.setAdapter(adatpter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
