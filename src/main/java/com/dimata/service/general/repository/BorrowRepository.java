package com.dimata.service.general.repository;

import com.dimata.service.general.model.entitiy.Borrow;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BorrowRepository implements PanacheRepository<Borrow> {

}
