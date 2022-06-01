package com.sparta.testblog.model;

import com.sparta.testblog.dto.SignupRequestDto;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="USERS")
public class Users {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String userId;


    @Column(nullable = false)
    private String password;

    public Users(String userId,String password) {
        this.userId=userId;
        this.password=password;
    }

}