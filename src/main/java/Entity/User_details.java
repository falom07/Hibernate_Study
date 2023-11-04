package Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user_detail_id")
@Table(name = "user_details")
public class User_details {
    @Id
    private int id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id",referencedColumnName = "id_users")
    private User user_detail_id;

    @Column(name = "age")
    private byte age;


    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;


    public User_details( byte age, String address, String phone) {
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User_details that)) return false;
        return getId() == that.getId() && getAge() == that.getAge()  && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAge(), getAddress(), getPhone());
    }


}
