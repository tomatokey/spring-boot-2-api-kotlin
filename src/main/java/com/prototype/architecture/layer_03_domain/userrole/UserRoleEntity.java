package com.prototype.architecture.layer_03_domain.userrole;

import com.prototype.architecture.layer_03_domain.user.UserId;
import com.prototype.architecture.layer_03_domain.Entity;
import com.prototype.architecture.layer_03_domain.user.UserEntity;
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
public class UserRoleEntity extends Entity {

    public static final String COLUMN_NAME_USER_ROLE_TYPE = "role_type";

    @Id
    @Column(UserEntity.COLUMN_NAME_USER_ID)
    private UserId userId;

    @Id
    @Column(COLUMN_NAME_USER_ROLE_TYPE)
    private UserRoleType roleType;

    public UserRolePk getPk() {
        return new UserRolePk(this.userId, this.roleType);
    }

}
