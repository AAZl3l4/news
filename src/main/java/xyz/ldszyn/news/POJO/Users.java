package xyz.ldszyn.news.POJO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    public int id;
    @NotEmpty
    public String name;
    @NotEmpty
    public String username;
    @NotEmpty
    public String password;
    @NotNull
    public Byte age;
    @NotNull
    public Byte sex;
    @NotEmpty
    public String tel;
    public LocalDate createTime;
    public String headImg;
    public int isban;
}

