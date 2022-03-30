package com.example.rolepermission_security.component;

import com.example.rolepermission_security.entity.Company;
import com.example.rolepermission_security.entity.Role;
import com.example.rolepermission_security.entity.User;
import com.example.rolepermission_security.entity.enums.PermissionEnum;
import com.example.rolepermission_security.entity.enums.RoleEnum;
import com.example.rolepermission_security.repository.CompanyRepository;
import com.example.rolepermission_security.repository.RoleRepository;
import com.example.rolepermission_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class Dataloader implements CommandLineRunner {


    @Value("${spring.sql.init.mode}")
    private String mode;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always") && ddl.equals("create")) {
            Role admin = new Role();
            admin.setName(RoleEnum.ADMIN);
            admin.setPermissionEnumSet(Arrays.stream(PermissionEnum.values()).collect(Collectors.toSet()));

            Role user = new Role();
            user.setName(RoleEnum.USER);
            user.setPermissionEnumSet(new HashSet<>(Arrays.asList(
                    PermissionEnum.READ_ALL_EMPLOYEE,
                    PermissionEnum.DELETE_EMPLOYEE,
                    PermissionEnum.ADD_EMPLOYEE
            )));

            Role manager = new Role();
            manager.setName(RoleEnum.MANAGER);
            manager.setPermissionEnumSet(new HashSet<>(Arrays.asList(PermissionEnum.READ_ALL_EMPLOYEE, PermissionEnum.READ_EMPLOYEE, PermissionEnum.EDIT_EMPLOYEE)));

            roleRepository.save(admin);
            roleRepository.save(user);
            roleRepository.save(manager);

            Company company = new Company();
            company.setName("PDP");
            companyRepository.save(company);

            Set<Role> roles = new HashSet<>();
            roles.add(admin);
            roles.add(user);
            roles.add(manager);

            User user1 = new User("Bexruz Izzatullayev", roles, "bekhruz", "000",true);
            userRepository.save(user1);

        }
    }
}
