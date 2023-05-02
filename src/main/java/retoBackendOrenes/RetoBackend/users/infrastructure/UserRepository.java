package retoBackendOrenes.RetoBackend.users.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import retoBackendOrenes.RetoBackend.users.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAll();
}
