package fr.aleclerc.beer.beans;

import java.util.List;

/**
 * Created by Amaury on 20/02/2016.
 */
public abstract class Data<T> {
    private String status;
    private String message;
    private List<T> data;

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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
