package xyz.ldszyn.news.POJO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class news {
    public int id;
    @NotEmpty
    public String title;
    @NotEmpty
    public String content;
    @NotEmpty
    public String typeId;
    public LocalDateTime createTime;
    public int userId;
}
