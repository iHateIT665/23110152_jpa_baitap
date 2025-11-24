package vn.iotstar.model;
import java.io.Serializable;
import lombok.*;

//import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	private String fullname;
	private String password;
}
