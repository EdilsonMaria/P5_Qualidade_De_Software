package br.com.edilsonmaria.medicalconsult.user.repository;

import br.com.edilsonmaria.medicalconsult.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
