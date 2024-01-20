package com.resumebuilder.detailsMS.repository;

import com.resumebuilder.detailsMS.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerDetails, Integer> {

    CustomerDetails findByUsername(String username);

    boolean existsCustomerDetailsByUsername(String username);

}
