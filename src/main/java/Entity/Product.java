package Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CollectionId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "userList")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter(value = AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "productList",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<User> userList = new ArrayList<>();

    @Column(name = "product")
    private String product;

    @Column(name = "detail_information")
    private String detail_information;

    @Column(name = "price")
    private int price;

    public Product(String product,String detail_information,int price){
        this.detail_information = detail_information;
        this.price = price;
        this.product = product;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product1)) return false;
        return getId() == product1.getId() && getPrice() == product1.getPrice() && Objects.equals(getProduct(), product1.getProduct()) && Objects.equals(getDetail_information(), product1.getDetail_information());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProduct(), getDetail_information(), getPrice());
    }
}
