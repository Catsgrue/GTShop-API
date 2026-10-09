package GTShop.Backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reward")
public class Reward {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Column(name="points_required")
    private int price;

    @Column(name="image_url")
    private String image;

    @Column(name="category")
    private String category;

    @Column(name="is_active")
    private boolean inStock;

    @Column(name="stock_count")
    private int stockCount;

    public Reward(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getPrice() { return price; }
}
