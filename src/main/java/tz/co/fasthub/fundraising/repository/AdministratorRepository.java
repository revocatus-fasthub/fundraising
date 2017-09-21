package tz.co.fasthub.fundraising.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tz.co.fasthub.fundraising.model.Administrator;

/**
 * Created by naaminicharles on 9/19/17.
 */
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Administrator findByUsername(String username);

}
