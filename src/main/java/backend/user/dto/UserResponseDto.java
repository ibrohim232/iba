package backend.user.dto;

import backend.user.User;
import backend.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDto
{
    private UUID id;
    private UserType userType;
    private String name;
    private BigDecimal balance;
    private LocalDateTime created;
    private LocalDateTime updated;

    public UserResponseDto( User user )
    {
        id = user.getId();
        userType = user.getUserType();
        name = user.getName();
        balance = user.getBalance();
        created = user.getCreated();
        updated = user.getUpdated();
    }
}
