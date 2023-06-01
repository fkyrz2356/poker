package jp.kobe_u.cs27.Poker.application.repository;

import jp.kobe_u.cs27.Poker.application.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
