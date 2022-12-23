package com.bank.Blood.Bank.model;

import com.bank.Blood.Bank.appuser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@DiscriminatorValue("admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private boolean isFirstLogin;
}
