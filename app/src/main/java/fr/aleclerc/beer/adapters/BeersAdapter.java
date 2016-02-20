package fr.aleclerc.beer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.aleclerc.beer.R;
import fr.aleclerc.beer.beans.Beer;

/**
 * Created by Amaury on 20/02/2016.
 */
public class BeersAdapter extends ArrayAdapter<Beer> {

    public BeersAdapter(Context context, int resource, List<Beer> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.categorie_item, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(getItem(position).getName());
        return convertView;
    }
}
