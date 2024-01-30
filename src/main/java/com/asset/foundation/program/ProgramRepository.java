package com.asset.foundation.program;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query("from Program b where b.id=?1")
    Optional<Program> findById(Long id);

}
