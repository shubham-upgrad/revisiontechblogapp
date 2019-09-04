package revisiontechblogapp.models;

import org.hibernate.mapping.FetchProfile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Strategy for generating Pk values
    private int id;
    @Column
    private String username;
    @Column
    private String password;

    // FetchType Eager : when User is retrieved from the database...retrieve its profile also
    // FetchType Lazy  : when User is retreived from the database....DO NOT
    // retrieve its profile UNTIL and UNLESS SOMEONE ASKS FOR IT

    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="profile_id")
    private UserProfile profile;

    @OneToMany(mappedBy = "user",cascade=CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
