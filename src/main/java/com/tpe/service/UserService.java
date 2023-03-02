package com.tpe.service;

import com.tpe.domain.MyUser;
import com.tpe.domain.Role;
import com.tpe.domain.enums.RoleType;
import com.tpe.dto.UserRequest;
import com.tpe.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    //if use AllArgsConstructor, do not need Autowired
    private UserRepository userRepository;

    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    public void saveUser(UserRequest userRequest){
        MyUser myUser = new MyUser();
        myUser.setUserName(userRequest.getUserName());
        myUser.setFirstName(userRequest.getFirstName());
        myUser.setLastName(userRequest.getLastName());

        String password = userRequest.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        myUser.setPassword(encodePassword);

        //ROLE must be set!
        Role role = roleService.getRoleTypeByName(RoleType.ROLE_INSTRUCTOR);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        myUser.setRoles(roles);

        userRepository.save(myUser);

    }

}
