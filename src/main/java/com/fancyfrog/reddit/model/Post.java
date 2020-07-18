package com.fancyfrog.reddit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotBlank(message = "Post Name Cannot be empty or null")
    private String postName;

    @Nullable
    private String url;

    @Nullable
    @Lob
    private String description;

    @Builder.Default
    private Integer voteCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private User user;

    @Builder.Default
    private Instant createdDate = Instant.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subredditId", referencedColumnName = "id")
    private Subreddit subreddit;
}
