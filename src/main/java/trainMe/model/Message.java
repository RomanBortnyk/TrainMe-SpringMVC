package trainMe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by romab on 9/24/16.
 */
@javax.persistence.Entity
@Table(name = "message")
public class Message implements Entity {

    @JsonIgnore
    private int id;
    private String text;
    @JsonIgnore
    private Timestamp createdAt;
    @JsonIgnore
    private Chat chat;
    @JsonIgnore
    private User author;

    @JsonProperty("authorId")
    public int authorId(){ return this.author.getId(); }

    @JsonProperty("authorFirstName")
    public String authorFirstName(){ return this.author.getFirstName(); }

    @JsonProperty("authorLastName")
    public String authorLastName(){ return this.author.getLastName(); }

    @JsonProperty("chatId")
    public int chatId(){ return this.chat.getId(); }

    @JsonProperty("authorLogin")
    public String authorLogin(){ return this.author.getLogin(); }

    @JsonProperty("time")
    public String createdTime(){
        Date date = new Date(this.createdAt.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return sdf.format(date);
    }

    public Message(){

    }

    public Message(String text, Chat chat, User author) {
        this.text = text;
        this.chat = chat;
        this.author = author;
        this.createdAt = new Timestamp(Calendar.getInstance().getTime().getTime());

    }


    @ManyToOne
    @JoinColumn(name = "author_id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }

    @ManyToOne
    @JoinColumn(name = "chat_id")
    public Chat getChat() {
        return chat;
    }


    public void setChat(Chat chat) {
        this.chat = chat;
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

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


}
