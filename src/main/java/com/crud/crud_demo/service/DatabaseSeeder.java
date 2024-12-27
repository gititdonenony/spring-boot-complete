package com.crud.crud_demo.service;
import com.crud.crud_demo.entity.Member;
import com.crud.crud_demo.entity.Role;
import com.crud.crud_demo.repository.MemberRepository;
import com.crud.crud_demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DatabaseSeeder(MemberRepository memberRepository, RoleRepository roleRepository) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert Members
        Member john = new Member();
        john.setUserId("john");
        john.setPassword("{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q");
        john.setActive(true);

        Member mary = new Member();
        mary.setUserId("mary");
        mary.setPassword("{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q");
        mary.setActive(true);

        Member susan = new Member();
        susan.setUserId("susan");
        susan.setPassword("{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q");
        susan.setActive(true);

        memberRepository.save(john);
        memberRepository.save(mary);
        memberRepository.save(susan);

        // Insert Roles
        roleRepository.save(new Role(null, "john", "ROLE_EMPLOYEE"));
        roleRepository.save(new Role(null, "mary", "ROLE_EMPLOYEE"));
        roleRepository.save(new Role(null, "mary", "ROLE_MANAGER"));
        roleRepository.save(new Role(null, "susan", "ROLE_EMPLOYEE"));
        roleRepository.save(new Role(null, "susan", "ROLE_MANAGER"));
        roleRepository.save(new Role(null, "susan", "ROLE_ADMIN"));
    }
}
