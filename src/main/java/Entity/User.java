package Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@ToString(exclude = {"orderList","productList"} )
//@ToString

@Setter
@Getter
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id_users")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_user")
    @SequenceGenerator(name = "seq_user",sequenceName = "sequence_id_user",allocationSize = 1)
    private int id;

    @OneToMany(mappedBy = "id_user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();

    @OneToOne(mappedBy = "user_detail_id",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private User_details userDetails_join;

    @Setter(value = AccessLevel.PRIVATE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "chopping_cart",
            joinColumns = @JoinColumn(name = "id_buyer"),
            inverseJoinColumns = @JoinColumn(name = "id_product"))

    private List<Product> productList = new ArrayList<>();

    @Column(name = "name")
    private String name;

    public User(){
    }
    public User(String name){
        this.name = name;

    }
    public User(int id,String name){
        this.id = id;
        this.name = name;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", age=" + userDetails_join.getAge() +
//                ", address='" + userDetails_join.getAddress() + '\'' +
//                ", phone='" + userDetails_join.getPhone() + '\'' +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
