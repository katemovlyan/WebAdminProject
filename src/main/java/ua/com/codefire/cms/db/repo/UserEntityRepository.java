package ua.com.codefire.cms.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.codefire.cms.db.entity.ArticleEntity;
import ua.com.codefire.cms.db.entity.UserEntity;

import java.util.List;

/**
 * Created by User on 27.12.2016.
 */
@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
//    @Query("select b from UserEntity b where b.username = :name")
//    UserEntity getUserByName(@Param("name") String name);

    UserEntity findByUsername(String username);
    UserEntity findByUsernameAndPassword(String username, String password);
}