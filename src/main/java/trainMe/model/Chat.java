package trainMe.model;

import javax.persistence.*;

/**
 * Created by romab on 9/24/16.
 */
@Entity
@Table(name = "chat")
public class Chat implements Item{

    private int id;
    private String name;
    private User user1;
    private User user2;

    public Chat (){

    }

    public Chat (User user1, User user2){
        this.name = user1.getLogin()+ " - " + user2.getLogin();
        this.user1 = user1;
        this.user2 = user2;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    @ManyToOne
    @JoinColumn(name = "user1_id")
    public User getUser1() {
        return user1;
    }

    @ManyToOne
    @JoinColumn(name = "user2_id")
    public User getUser2() {
        return user2;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        if (id != chat.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
