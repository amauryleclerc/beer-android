package fr.aleclerc.beer.beans;

/**
 * Created by Amaury on 20/02/2016.
 */
public class Style {
    private long id;
    private String name;
    private long categoryId;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
