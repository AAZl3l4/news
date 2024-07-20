package xyz.ldszyn.news.POJO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class type {
    public int id;
    @NotEmpty
    public String typeName;
    public int userId;
    public LocalDate createTime;
}
