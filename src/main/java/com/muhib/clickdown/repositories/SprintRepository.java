package com.muhib.clickdown.repositories;

import com.muhib.clickdown.models.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Integer> {
    public List<Sprint> findByBoardId(int boardId);
}
