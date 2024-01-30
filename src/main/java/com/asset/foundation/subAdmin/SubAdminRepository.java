package com.asset.foundation.subAdmin;

import com.asset.foundation.user.Status;
import com.asset.foundation.user.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubAdminRepository extends JpaRepository<SubAdmin, Long> {

    // @Query("select b from Player b where b.playerName=?1 and b.status=?2")
    // Player findByPlayerNameAndStatus(String name, Status status);

    SubAdmin findByUserAndStatus(User user, Status status);

    List<SubAdmin> findByStatus(Status status);

    @Query("from SubAdmin b where b.id=?1")
    SubAdmin findPlayerById(Long id);

    SubAdmin findPlayerByUser_Id(Long id);

}
