package com.adacademy.tweeter.model;

import lombok.*;
import javax.persistence.*;
/**
 * This class is responsible for handling comments data.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "comments")
public class Comments implements BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet Tweet;

    @Column(nullable = false)
    private Long creationTS;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private String text;
}
