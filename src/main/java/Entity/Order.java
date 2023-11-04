package Entity;

import Service.UserService;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User id_user;
    @Column(name = "product")
    private String product;
    @Column(name = "price")
    private int price;

    public Order() {
    }

    public Order(int id, User id_user, String product, int price) {
        this.id = id;
        this.id_user = id_user;
        this.product = product;
        this.price = price;
    }
    public Order(String product, int price) {
        this.product = product;
        this.price = price;
    }
    public Order( User id_user, String product, int price) {
        this.id_user = id_user;
        this.product = product;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getId() == order.getId() &&  getPrice() == order.getPrice() && Objects.equals(getProduct(), order.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProduct(), getPrice());
    }

    @Override
    public String toString() {
        return "\nOrder{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", price=" + price +
                '}' ;
    }
}
