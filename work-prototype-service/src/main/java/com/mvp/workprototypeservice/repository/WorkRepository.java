package com.mvp.workprototypeservice.repository;

import com.mvp.workprototypeservice.model.Work;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends CrudRepository<Work, Long> {

}
