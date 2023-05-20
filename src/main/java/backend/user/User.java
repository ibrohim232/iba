package backend.user;

import backend.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode( callSuper = true )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity<UUID>
{
    private UserType userType;
    private String name;
    private BigDecimal balance;
    private byte[] password;
    private UUID salt;
}
