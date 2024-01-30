package com.asset.foundation.publication;

import com.asset.foundation.program.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    @Query("from Publication b where b.id=?1")
    Optional<Publication> findById(Long id);
}