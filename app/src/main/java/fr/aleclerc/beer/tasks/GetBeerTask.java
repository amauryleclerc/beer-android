package fr.aleclerc.beer.tasks;

import android.content.Context;
import android.os.AsyncTask;

import fr.aleclerc.beer.Listeners.TaskListener;
import fr.aleclerc.beer.beans.Beer;
import fr.aleclerc.beer.services.BeerService;

/**
 * Created by Amaury on 20/02/2016.
 */
public class GetBeerTask extends AsyncTask<String,Void,Beer>{

    private Context context;
    private TaskListener listener;

    public GetBeerTask(Context context, TaskListener listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Beer doInBackground(String... params) {
        BeerService service = new BeerService(context);
        return  service.getBeer(params[0]);
    }

    @Override
    protected void onPostExecute(Beer beer) {

        listener.onTaskFinish();
    }
}
