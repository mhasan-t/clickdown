package com.muhib.clickdown.repositories;

import com.muhib.clickdown.models.BoardUser;
import com.muhib.clickdown.services.types.BoardUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardUserRepository extends JpaRepository<BoardUser, BoardUserKey> {
}
