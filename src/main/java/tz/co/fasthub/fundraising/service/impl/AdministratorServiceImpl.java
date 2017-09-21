package tz.co.fasthub.fundraising.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tz.co.fasthub.fundraising.model.Administrator;
import tz.co.fasthub.fundraising.repository.AdministratorRepository;
import tz.co.fasthub.fundraising.service.AdministratorService;

/**
 * Created by naaminicharles on 9/19/17.
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Administrator findByUsername(String username) {
        return administratorRepository.findByUsername(username);
    }

    @Override
    public void save(Administrator administrator) {
        administratorRepository.save(administrator);
    }
}
