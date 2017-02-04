package trainMe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by romab on 9/24/16.
 */
@javax.persistence.Entity
@Table(name = "chat")
public class Chat implements Entity {

    private int id;
    @JsonIgnore
    private String name;
    @JsonIgnore
    private User user1;
    @JsonIgnore
    private User user2;

    @JsonProperty(value = "login")
    public String login() {
        return this.user1.getLogin();
    }

    @JsonProperty(value = "firstname")
    public String firstName() {return this.user1.getFirstName(); }

    @JsonProperty(value = "lastname")
    private  String lastName(){return  this.user1.getLastName(); }

    @JsonProperty(value = "userId")
    private int userId(){return  this.user1.getId(); }



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
