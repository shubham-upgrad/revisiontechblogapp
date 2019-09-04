package revisiontechblogapp.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /*
     *   Generation Types
     *       1) AUTO : (hibernate decided)let hibernate decide(based on dialect)..which strategy is to be
     *                   used to generate values of ID(mostly it uses SEQUENCE)
     *       2) IDENTITY : use AutoIncrement column but is inefficient for hibernate
     *       3) SEQUENCE  : (user decided)a Sequence table is used to generate next value of ID
     *       4) TABLE : instead of a sequence we use a normal table(which obviously are
     *                       less efficient for simulating a sequence)
     *
     * */

    @Column
    private String title;

    @Column
    private String body;

    @Column(name="post_date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(fetch=FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Category> categories = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
