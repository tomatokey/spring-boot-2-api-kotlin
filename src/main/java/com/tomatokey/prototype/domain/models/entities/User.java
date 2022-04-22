package com.tomatokey.prototype.domain.models.entities;

import com.tomatokey.prototype.domain.models.values.UserName;
import com.tomatokey.prototype.domain.models.values.id.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Getter
@ToString
@Table("user_t")
public class User {

    @Id
    private UserId userId;

    private UserName userName;

}
