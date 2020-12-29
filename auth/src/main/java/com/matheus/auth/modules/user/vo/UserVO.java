package com.matheus.auth.modules.user.vo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class UserVO implements Serializable {

    private static final long serialVersionUID = -2336335052580208006L;

    private String userName;
    private String password;

}
