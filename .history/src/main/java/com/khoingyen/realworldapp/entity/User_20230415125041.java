package com.khoingyen.realworldapp.entity;

import lombok.*;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String username;
    private String bio;
    private String image;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_follow",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> followers;// người mà theo dõi mình

    @ManyToMany(mappedBy = "followers")
    private Set<User> following;//người mà mình theo dõi

    @OneToMany(mappedBy = "author")
    private List<Article> articles;

    @ManyToMany()
    @JoinTable(name = "user_favorite_article", 
    @JoinColumn = @JoinColumn(name = "user_id"),
    inv)
    private 
}
