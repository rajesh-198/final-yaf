package com.asset.foundation.donation;

import com.asset.foundation.user.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("from Donation b where b.id=?1")
    Optional<Donation> findById(Long id);

    List<Donation> findByStatus(Status status);


}
