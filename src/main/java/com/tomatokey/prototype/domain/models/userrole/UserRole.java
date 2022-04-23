package com.tomatokey.prototype.domain.models.userrole;

import com.tomatokey.prototype.domain.models.user.User;
import com.tomatokey.prototype.domain.models.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@AllArgsConstructor
@Getter
@ToString
@Table("user_role_t")
public class UserRole {

    public static final String COLUMN_NAME_USER_ROLE_TYPE = "role_type";

    @Id
    @Column(User.COLUMN_NAME_USER_ID)
    private UserId userId;

    @Id
    @Column(COLUMN_NAME_USER_ROLE_TYPE)
    private UserRoleType roleType;

    public UserRolePk getPk() {
        return new UserRolePk(this.userId, this.roleType);
    }

}
