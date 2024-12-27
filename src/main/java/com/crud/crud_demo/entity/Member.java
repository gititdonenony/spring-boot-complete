package com.crud.crud_demo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "members")

public class Member {
    @Id
    @Column(name = "user_id", length = 50, nullable = false)
    private String userId;

    @Column(name = "pw", length = 68, nullable = false)
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active;

    public Member(String userId, String password, boolean active) {
        this.userId = userId;
        this.password = password;
        this.active = active;
    }

    public Member() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
