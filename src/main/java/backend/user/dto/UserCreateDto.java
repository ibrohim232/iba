package backend.user.dto;

import backend.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto
{
    private UserType userType;
    private String name;
    private BigDecimal balance;
    private String password;
}
