package book.shop.users;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UsersEntity {

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID userId;

    private String userName;

}
