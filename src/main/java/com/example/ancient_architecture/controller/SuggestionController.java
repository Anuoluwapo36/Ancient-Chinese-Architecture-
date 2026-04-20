package com.example.ancient_architecture.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ancient_architecture.model.Architecture;
import com.example.ancient_architecture.repository.ArchitectureRepository;
import com.example.ancient_architecture.service.ArchitectureService;

class ImageResult {
    private Long id;
    private String imageUrl;

    public ImageResult(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public String getImageUrl() { return imageUrl; }
}

@RestController
@RequestMapping("/api")
public class SuggestionController {

    private final ArchitectureService architectureService;
    private final ArchitectureRepository architectureRepository;

    public SuggestionController(ArchitectureService architectureService, ArchitectureRepository architectureRepository) {
        this.architectureService = architectureService;
        this.architectureRepository = architectureRepository;
    }

    @GetMapping("/suggestions")
    public List<String> getSuggestions(@RequestParam String query) {
        List<Architecture> results = architectureService.searchArchitectures(query);
        return results.stream()
                .map(arch -> arch.getChineseName() + " (" + arch.getEnglishName() + ")")
                .limit(8)
                .collect(Collectors.toList());
    }

    @GetMapping("/search-images")
    public List<ImageResult> searchImages(@RequestParam String query) {
        List<Architecture> architectures = architectureRepository.searchForSuggestions(query);

        return architectures.stream()
                .limit(6)
                .map(arch -> {
                    String firstImage = "";
                    if (arch.getImages() != null && !arch.getImages().isEmpty()) {
                        firstImage = arch.getImages().get(0).getImageUrl();
                    }
                    return new ImageResult(arch.getId(), firstImage);
                })
                .collect(Collectors.toList());
    }
}