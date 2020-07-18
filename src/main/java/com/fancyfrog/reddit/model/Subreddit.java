package com.fancyfrog.reddit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "subreddits")
public class Subreddit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Community name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @OneToMany(mappedBy = "subreddit",fetch = FetchType.LAZY)
    private List<Post> posts;

    @Builder.Default
    private Instant createdDate = Instant.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
