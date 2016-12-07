package trainMe.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import trainMe.jacksonProfiles.View;

import javax.persistence.*;

/**
 * Created by romab on 9/24/16.
 */
@Entity
@Table(name = "feedback")
//@JsonIgnoreProperties({"author","user"})
@JsonIgnoreProperties({"author","user"})
public class Feedback implements Item {


    private int id;

    @JsonIgnore
    private User author;
    @JsonIgnore
    private User user;

    private String text;


    @JsonProperty(value = "authorId")
    public int authorId() {
        return this.author.getId();
    }

    @JsonProperty(value = "authorFirstName")
    public String authorFirstNameId(){return this.user.getFirstName(); }



    public Feedback(){

    }

    public Feedback(User author, User user, String text){
        this.author = author;
        this.user = user;
        this.text = text;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
