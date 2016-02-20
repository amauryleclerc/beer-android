package fr.aleclerc.beer.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import fr.aleclerc.beer.Listeners.TaskListener;
import fr.aleclerc.beer.beans.Style;
import fr.aleclerc.beer.services.StyleService;

/**
 * Created by Amaury on 20/02/2016.
 */
public class GetStylesTask extends AsyncTask<Void,Void,List<Style>>{

    private Context context;
    private TaskListener listener;

    public GetStylesTask(Context context, TaskListener listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected List<Style> doInBackground(Void... params) {
        StyleService service = new StyleService(context);
        return  service.getStyles();
    }

    @Override
    protected void onPostExecute(List<Style> styles) {

        listener.onTaskFinish();
    }
}
