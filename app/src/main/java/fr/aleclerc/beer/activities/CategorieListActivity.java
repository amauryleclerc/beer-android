package fr.aleclerc.beer.activities;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import fr.aleclerc.beer.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;


@ContentView(R.layout.activity_categorie_list)
public class CategorieListActivity extends RoboActivity {


    @InjectView(R.id.tvTitre)
    TextView tvTitre;
    @InjectView(R.id.lvCategorie)
    ListView lvCategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitre.setText("Categorie");
    }
}
