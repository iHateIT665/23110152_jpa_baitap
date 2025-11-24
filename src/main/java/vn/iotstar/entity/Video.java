package vn.iotstar.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "videos")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "VideoId")
    private String videoId; // Lưu ý: VideoId là String hay Int tùy DB của bạn

    @Column(name = "Title", columnDefinition = "NVARCHAR(200) NOT NULL")
    private String title;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "Active")
    private int active;
    
    @Column(name = "Poster")
    private String poster;

    @Column(name = "Views")
    private int views;

    // QUAN TRỌNG: Quan hệ N-1 với Category
    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;
}