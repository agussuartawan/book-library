package com.dimata.service.general.repository;

import com.dimata.service.general.model.entitiy.Return;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReturnRepository implements PanacheRepository<Return> {
}
