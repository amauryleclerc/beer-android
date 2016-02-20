package fr.aleclerc.beer.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import fr.aleclerc.beer.Listeners.TaskListener;
import fr.aleclerc.beer.beans.Beer;
import fr.aleclerc.beer.services.BeerService;

/**
 * Created by Amaury on 20/02/2016.
 */
public class GetBeersTask extends AsyncTask<Long,Void,List<Beer>>{

    private Context context;
    private TaskListener listener;

    public GetBeersTask(Context context, TaskListener listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected List<Beer> doInBackground(Long... params) {
        BeerService service = new BeerService(context);
        return  service.getBeers(params[0]);
    }

    @Override
    protected void onPostExecute(List<Beer> styles) {

        listener.onTaskFinish();
    }
}
