package tz.co.fasthub.fundraising.service;

import tz.co.fasthub.fundraising.model.Administrator;

/**
 * Created by naaminicharles on 9/19/17.
 */
public interface AdministratorService {

    public Administrator findByUsername(String username);
    public void save(Administrator administrator);
}
