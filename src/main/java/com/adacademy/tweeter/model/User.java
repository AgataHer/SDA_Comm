package com.adacademy.tweeter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * This class is responsible for handling user data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nick;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long creationTS;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
//    private List<Comments> comments = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
//    private List<Tweet> tweets = new ArrayList<>();
}
