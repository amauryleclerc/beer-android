package fr.aleclerc.beer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
public class CategorieListActivity extends RoboActivity implements TaskListener, AdapterView.OnItemClickListener {

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
        lvCategorie.setOnItemClickListener(this);
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
            CategoriesAdapter adapter = new CategoriesAdapter(this, R.layout.categorie_item, categories);
            lvCategorie.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Categorie categorie = (Categorie) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, StyleListActivity.class);
        intent.putExtra("categorie_id", categorie.getId());
        intent.putExtra("categorie_name", categorie.getName());
        this.startActivity(intent);

    }
}
