package net.avishkar.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String url;
    @Lob
    @Column(nullable = false,columnDefinition = "LONGTEXT")
    private String content;
    @Lob
    private String shortDescription;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name = "created_by",nullable = false)
    private User createdBy;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();
}
