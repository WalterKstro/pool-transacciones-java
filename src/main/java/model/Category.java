package model;

public class Category {
    private Integer id;
    private String category;

    public Integer getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Category(Integer id) {
        this.id = id;
    }

    public Category(Integer id, String category) {
        this.id = id;
        this.category = category;
    }
}
