package backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity<ID>
{
    protected ID id;
    protected LocalDateTime created;
    protected LocalDateTime updated;
}
