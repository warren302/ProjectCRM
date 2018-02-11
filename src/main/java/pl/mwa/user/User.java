package pl.mwa.user;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mwa.position.Position;
import pl.mwa.role.Role;



@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String username;
	
	@NotBlank
	@Column
	private String password;
	
	@NotBlank
	@Column(nullable = false)
	private String firstname;
	
	@NotBlank
	@Column(nullable = false)
	private String lastname;
	
	@NotBlank
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	
	@Pattern(regexp="\\d{3}-\\d{3}-\\d{3}|\\d{2}-\\d{3}-\\d{2}-\\d{2}") // tel. pattern XXX-XXX-XXX or XX-XXX-XX-XX
	private String phone;
	
	@NotBlank
	private boolean active;
	
	private LocalDateTime created;
	
	
	@ManyToMany
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	
	@ManyToOne
	private Position position;
	
}