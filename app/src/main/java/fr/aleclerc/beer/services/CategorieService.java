package fr.aleclerc.beer.services;

import android.content.Context;
import android.util.Log;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

import fr.aleclerc.beer.R;
import fr.aleclerc.beer.beans.Categorie;
import fr.aleclerc.beer.beans.CategorieData;

/**
 * Created by Amaury on 20/02/2016.
 */
public class CategorieService  extends  RestService{

    private static String LOG_TAG="CategorieService";
    private Context context=null;

    public CategorieService(Context context) {
        this.context = context.getApplicationContext();
    }

    public  List<Categorie> getCategorie(  ){
        final String url =  context.getString(R.string.url_categories);
        final String key =  context.getString(R.string.key);
        Log.d(LOG_TAG, "Invoke url " + url );
        List<Categorie> categories = new ArrayList<Categorie>();
        try {
            Log.i("test", "test");
            ResponseEntity<CategorieData> responseEntity = getRestTemplate().exchange(getURI(url,key)
                    , HttpMethod.GET, getHttpEntity(), CategorieData.class);

            Log.d(LOG_TAG, "nb categories: " + responseEntity.getBody().getData().size());
            Log.d(LOG_TAG, responseEntity.getBody().getMessage() );
            categories.addAll(responseEntity.getBody().getData());
        } catch (RestClientException e) {
            Log.e(LOG_TAG,"RestException dans le chargement des donnees serveur ARRET",e);
        }
        return categories;
    }

}
