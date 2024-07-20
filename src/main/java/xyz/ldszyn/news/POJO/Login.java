package xyz.ldszyn.news.POJO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @NotEmpty
    public String username;
    @NotEmpty
    public String password;
}
