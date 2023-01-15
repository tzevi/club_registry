package gr.hua.ds.club_registry.db.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import gr.hua.ds.club_registry.db.enums.ActivityStatus;
import gr.hua.ds.club_registry.db.enums.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(
        name="users"
)
@Data
@NoArgsConstructor
public class User {

        @Id
        @Column(name= "username",unique = true)
        @NotBlank(message = "Username should not be null")
        @Size(min = 4, max=25)
        @Pattern(regexp = "[a-zA-Z0-9']+", message ="Only alphanumeric characters allowed!")
        private String username;


        @Column(name = "email")
        @Email(message = "email format is xxxx@xxx.xx")
        private String email;

        @NotBlank
        @Size(min = 5, message = "First name must have at least 5 characters")
        @Column(name = "first_name", nullable = false)
        String first_name;

        @NotBlank
        @Size(min = 5, message = "Last name must have at least 5 characters")
        @Column(name = "last_name", nullable = false)
        String last_name;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @Column(name = "password")
        @NotBlank(message = "Password should not be null or empty!")
        private String password;

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "tax_no_club", referencedColumnName = "tax_no")
        private Club associated_club;

        @Enumerated(EnumType.STRING)
        @Column(name = "enabled", columnDefinition = "varchar(32) default 'SHOP'")
        private ActivityStatus enabled;

        @Column(name = "role",nullable = false)
        @Schema(description = "Role should ROLE_GGA, ROLE_ADMIN, ROLE_CLUB_SUPERVISOR, ROLE_POLICE")
        private Roles userRole;




}
