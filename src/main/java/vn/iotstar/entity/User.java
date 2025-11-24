package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username", columnDefinition = "NVARCHAR(50) NOT NULL")
	private String username;

	@Column(name = "password", columnDefinition = "NVARCHAR(100) NOT NULL")
	private String password;

	@Column(name = "email", columnDefinition = "NVARCHAR(100)")
	private String email;

	@Column(name = "fullname", columnDefinition = "NVARCHAR(100)")
	private String fullname;

	@Column(name = "phone", columnDefinition = "NVARCHAR(20)")
	private String phone;

	@Column(name = "images", columnDefinition = "NVARCHAR(500)")
	private String images;

	@Column(name = "roleid")
	private int roleid; // 1: manager, 2: Manager, 3: User (Tùy quy định của bạn)

	@Column(name = "createDate")
	private Date createDate;
	
	@Column(name = "status")
	private int status;
}