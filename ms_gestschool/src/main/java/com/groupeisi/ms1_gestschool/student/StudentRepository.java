package com.groupeisi.ms1_gestschool.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

    Optional<StudentEntity> findByEmailPro (String emailPro);
    Optional<StudentEntity> findByEmailPerso (String emailPerso);
    Optional<StudentEntity> findByPhoneNumber (String phoneNumber);
    Optional<StudentEntity> findByRegistrationNu (String registrationNu);
}
