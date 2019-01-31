package com.wfs.landpricing.dao;

import com.wfs.landpricing.model.CustomerSalesMarkup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerSalesMarkupDao extends JpaRepository<CustomerSalesMarkup, Long>  {

}
