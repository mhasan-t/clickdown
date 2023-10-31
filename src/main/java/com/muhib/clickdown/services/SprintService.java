package com.muhib.clickdown.services;

import com.muhib.clickdown.models.Sprint;
import com.muhib.clickdown.repositories.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintService {
    private final SprintRepository sprintRepository;


    public Sprint findSprintById(int id){
        return sprintRepository.findById(id).get();
    }

    public Sprint getSprintRefById(int id){ // makes not db connection, gives proxy object
        return sprintRepository.getReferenceById(id);
    }

    public Sprint save(Sprint sprint){
        return sprintRepository.save(sprint);
    }

    public List<Sprint> findByBoard(int id){
        return sprintRepository.findByBoardId(id);
    }
}
