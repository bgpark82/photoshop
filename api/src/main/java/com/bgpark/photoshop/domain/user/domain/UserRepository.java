package com.bgpark.photoshop.domain.user.domain;

import com.bgpark.photoshop.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.of(user);
    }

    public Optional<User> findByEmail(String email) {
        User user = (User) em.createQuery("SELECT u FROM User u WHERE u.email = :email")
                .setParameter("email", email)
                .getSingleResult();

        return Optional.of(user);
    }
}
