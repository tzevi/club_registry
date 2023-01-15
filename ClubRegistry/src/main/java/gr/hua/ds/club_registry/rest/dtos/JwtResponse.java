package gr.hua.ds.club_registry.rest.dtos;

import gr.hua.ds.club_registry.db.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private Roles role;

    public JwtResponse(String token, String username, Roles role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

}
