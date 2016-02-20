package fr.aleclerc.beer;

import android.os.Bundle;
import android.widget.TextView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;


@ContentView(R.layout.activity_categorie_list)
public class CategorieListActivity extends RoboActivity {


    @InjectView(R.id.tvTitre)
    TextView tvTitre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitre.setText("Categorie");
    }
}
