package fr.aleclerc.beer.services;

import android.content.Context;
import android.util.Log;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import fr.aleclerc.beer.R;
import fr.aleclerc.beer.beans.Beer;
import fr.aleclerc.beer.beans.BeerData;
import fr.aleclerc.beer.beans.BeerListData;

/**
 * Created by Amaury on 20/02/2016.
 */
public class BeerService extends RestService {

    private static String LOG_TAG="BeerService";
    private Context context=null;

    public BeerService(Context context) {
        this.context = context.getApplicationContext();
    }

    public List<Beer> getBeers(  Long styleId ){
        final String url =  context.getString(R.string.url_beers);
        final String key =  context.getString(R.string.key);
        Log.d(LOG_TAG, "Invoke url " + url);
        List<Beer> beers = new ArrayList<Beer>();
        try {
            Log.i("test", "test");
            ResponseEntity<BeerListData> responseEntity = getRestTemplate().exchange(getURI(url,key,styleId )
                    , HttpMethod.GET, getHttpEntity(), BeerListData.class );
            Log.d(LOG_TAG, "nb beers: " + responseEntity.getBody().getData().size());
         //    Log.d(LOG_TAG, responseEntity.getBody().getMessage() );
            beers.addAll(responseEntity.getBody().getData());
        } catch (RestClientException e) {
            Log.e(LOG_TAG,"RestException dans le chargement des donnees serveur ARRET",e);
        }
        return beers;
    }
    public Beer getBeer(  String  beerId ){
        final String url =  context.getString(R.string.url_beer);
        final String key =  context.getString(R.string.key);
        Log.d(LOG_TAG, "Invoke url " + url);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .path(beerId)
                .queryParam("key", key);
        URI uri = builder.build().encode().toUri();
        Beer beer =null;
        try {
            Log.i("test", "test");
            ResponseEntity<BeerData> responseEntity = getRestTemplate().exchange(uri
                    , HttpMethod.GET, getHttpEntity(), BeerData.class );
            //Log.d(LOG_TAG, "nb beers: " + responseEntity.getBody().getData().size());
            //  Log.d(LOG_TAG, responseEntity.getBody().getMessage() );
            beer = responseEntity.getBody().getData();
        } catch (RestClientException e) {
            Log.e(LOG_TAG,"RestException dans le chargement des donnees serveur ARRET",e);
        }
        return beer;
    }
}
