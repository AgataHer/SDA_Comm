package com.adacademy.tweeter.model;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This class is responsible for handling tweets data and list of comments.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tweets")
public class Tweet implements BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long creationTS;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User author;

    @NonNull
    @Column(nullable = false)
    private String message;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Tweet")
//    private List<Comments> comments = new ArrayList<>();
}
