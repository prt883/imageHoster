package ImageHoster.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//@Entity annotation specifies that the corresponding class is a JPA entity
@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'comments'. Hence the table named 'comments' will be created in the database with all the columns mapped to all the attributes in 'Image' class
@Table(name = "comments")
public class Comment {

    //@Id annotation specifies that the corresponding attribute is a primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column annotation specifies that the attribute will be mapped to the column in the database.
    //Here the column name is explicitly mentioned as 'id'
    @Column(name = "id")
    private Integer id;


    // Text is a Postgres specific column type that allows you to save
    // text based data that will be longer than 256 characters
    // this is a base64 encoded version of the image
    @Column(columnDefinition = "TEXT")
    private String text;


    @Column(name = "date")
    private Date date;

    //The 'comments' table is mapped to 'users' table with Many:One mapping
    //One comment can have only one user (owner) but one user can have multiple comments
    //FetchType is EAGER
    @ManyToOne(fetch = FetchType.EAGER)
    //Below annotation indicates that the name of the column in 'users' table referring the primary key in 'users' table will be 'user_id'
    @JoinColumn(name = "user_id")
    private User users;

    //The 'comments' table is mapped to 'images' table with Many:One mapping
    //One comment can have only one image (owner) but one image can have multiple comments
    //FetchType is EAGER
    @ManyToOne(fetch = FetchType.EAGER)
    //Below annotation indicates that the name of the column in 'comment' table referring the primary key in 'images' table will be 'image_id'
    @JoinColumn(name = "image_id")
    private Image images;


    public Comment() { }


    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public User getUsers() { return users; }

    public void setUsers(User users) { this.users = users;  }

    public Image getImages() { return images; }

    public void setImages(Image images) { this.images = images; }
}
