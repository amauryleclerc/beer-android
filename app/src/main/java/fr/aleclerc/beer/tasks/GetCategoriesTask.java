package fr.aleclerc.beer.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import fr.aleclerc.beer.Listeners.TaskListener;
import fr.aleclerc.beer.beans.Categorie;
import fr.aleclerc.beer.services.CategorieService;

/**
 * Created by Amaury on 20/02/2016.
 */
public class GetCategoriesTask extends AsyncTask<Void,Void,List<Categorie>>{

    private Context context;
    private TaskListener listener;

    public GetCategoriesTask(Context context, TaskListener listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected List<Categorie> doInBackground(Void... params) {
        CategorieService service = new CategorieService(context);
        return  service.getCategorie();
    }

    @Override
    protected void onPostExecute(List<Categorie> categories) {
        listener.onTaskFinish();
    }
}
