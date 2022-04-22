package com.tomatokey.prototype.domain.models.entities;

import com.tomatokey.prototype.domain.constant.RoleType;
import com.tomatokey.prototype.domain.models.values.id.RoleId;
import com.tomatokey.prototype.domain.models.values.id.UserId;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Getter
@ToString
@Table("user_role_t")
public class Role {

    @Id
    private RoleId roleId;

    private RoleType roleType;

    private UserId userId;

}
