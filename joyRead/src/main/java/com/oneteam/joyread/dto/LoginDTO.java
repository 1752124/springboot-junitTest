package com.oneteam.joyread.dto;

import com.oneteam.joyread.entity.User;
import lombok.Data;

@Data
public class LoginDTO {
    private Integer userId;

    private String name;

    private String phone;

    private String avatar;

    private int status;

    private String authorization;

    public LoginDTO(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.avatar = user.getAvatar();
        this.status = user.getStatus();
        this.phone = user.getPhone();
    }
}
