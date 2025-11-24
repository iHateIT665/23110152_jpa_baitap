package vn.iotstar.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private int categoryId;

    // --- THÊM ĐOẠN NÀY ---
    @Column(name = "Categorycode", columnDefinition = "NVARCHAR(50) NOT NULL")
    private String categoryCode; 
    // ---------------------

    @Column(name = "Categoryname", columnDefinition = "NVARCHAR(200) NOT NULL")
    private String categoryname;

    @Column(name = "Images", columnDefinition = "NVARCHAR(500)")
    private String images;

    @Column(name = "Status")
    private int status;
 // Trong file Category.java

    @Column(name = "manager_id")
    private Integer managerId; // Lưu ID của người tạo danh mục này


    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Video> videos;
}