package com.asset.foundation.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("from Event b where b.id=?1")
    Optional<Event> findById(Long id);

}
