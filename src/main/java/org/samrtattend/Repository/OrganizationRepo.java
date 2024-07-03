package org.samrtattend.Repository;

import org.samrtattend.Model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrganizationRepo extends JpaRepository<Organization,Integer> {
    Organization findByEmail(String email);
}
