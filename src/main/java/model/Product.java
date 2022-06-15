package model;

public class Product {
    private Integer id;
    private String name;
    private float price;
    private String sku;

    private Category category;

    public Product(Integer id, String name, float price, String sku,  Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.category = category;
    }

    public Product(String name, float price, String sku, Category category) {
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.category = category;
    }

    public Product(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", sku='").append(sku).append('\'');
        sb.append(", category=").append(category.getName());
        sb.append('}');
        return sb.toString();
    }
}
