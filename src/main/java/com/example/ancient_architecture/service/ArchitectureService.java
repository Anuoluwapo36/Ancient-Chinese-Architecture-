package com.example.ancient_architecture.service;

import com.example.ancient_architecture.model.Architecture;
import com.example.ancient_architecture.repository.ArchitectureRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArchitectureService {

    private final ArchitectureRepository architectureRepository;
 public ArchitectureService(ArchitectureRepository architectureRepository) {
        this.architectureRepository = architectureRepository;
    }

    public List<Architecture> searchArchitectures(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        return architectureRepository.searchByKeyword(query.trim());
    }
    
}