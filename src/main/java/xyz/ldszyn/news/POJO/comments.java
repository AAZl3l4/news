package xyz.ldszyn.news.POJO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class comments {
    public int id;
    public String userId;
    @NotNull
    public int newsId;
    @NotEmpty
    public String text;
    public LocalDateTime time;
}
