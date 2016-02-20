package fr.aleclerc.beer.services;

import android.content.Context;
import android.util.Log;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

import fr.aleclerc.beer.R;
import fr.aleclerc.beer.beans.Style;
import fr.aleclerc.beer.beans.StyleData;

/**
 * Created by Amaury on 20/02/2016.
 */
public class StyleService extends RestService {

    private static String LOG_TAG="StyleService";
    private Context context=null;

    public StyleService(Context context) {
        this.context = context.getApplicationContext();
    }

    public List<Style> getStyles(   ){
        final String url =  context.getString(R.string.url_styles);
        final String key =  context.getString(R.string.key);
        Log.d(LOG_TAG, "Invoke url " + url);
        List<Style> categories = new ArrayList<Style>();
        try {
            Log.i("test", "test");
            ResponseEntity<StyleData> responseEntity = getRestTemplate().exchange(getURI(url,key)
                    , HttpMethod.GET, getHttpEntity(), StyleData.class );
            Log.d(LOG_TAG, "nb style: " + responseEntity.getBody().getData().size());
            Log.d(LOG_TAG, responseEntity.getBody().getMessage() );
            categories.addAll(responseEntity.getBody().getData());
        } catch (RestClientException e) {
            Log.e(LOG_TAG,"RestException dans le chargement des donnees serveur ARRET",e);
        }
        return categories;
    }
}
