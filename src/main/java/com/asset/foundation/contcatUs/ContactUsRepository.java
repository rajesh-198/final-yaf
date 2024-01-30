package com.asset.foundation.contcatUs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUS, Long> {

    @Query("from Event b where b.id=?1")
    Optional<ContactUS> findById(Long id);

}
