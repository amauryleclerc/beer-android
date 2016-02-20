package fr.aleclerc.beer.beans;

/**
 * Created by Amaury on 20/02/2016.
 */
public  class BeerData {
    private String status;
    private String message;
    private Beer data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Beer getData() {
        return data;
    }

    public void setData(Beer data) {
        this.data = data;
    }
}
