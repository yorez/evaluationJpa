package com.evaluation.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.evaluation.domain.Admin;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomAdmin extends User {

    private static final String ROLE_PREFIX = "ROLE_";

    private static final long serialVersionUID = 1L;

    private Admin admin;

    public CustomAdmin(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomAdmin(Admin vo) {
        super(vo.getUid(), vo.getUpw(), makeGrantedAuthority(vo.getRoles()));
        this.admin = vo;
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<String> roles) {
        List<GrantedAuthority> list = new ArrayList<>();

        roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role)));

        return list;
    }
}