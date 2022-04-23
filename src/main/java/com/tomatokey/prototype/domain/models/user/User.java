package com.tomatokey.prototype.domain.models.user;

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

    public static final String COLUMN_NAME_USER_ID = "user_id";
    public static final String COLUMN_NAME_USER_NAME = "user_name";

    @Id
//    @Column(COLUMN_NAME_USER_ID)
    private UserId userId;

//    @Column(COLUMN_NAME_USER_NAME)
    private UserName userName;

}
