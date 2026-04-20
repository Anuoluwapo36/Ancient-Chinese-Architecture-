package com.example.ancient_architecture.controller;

import com.example.ancient_architecture.model.Architecture;
import com.example.ancient_architecture.model.Contact;
import com.example.ancient_architecture.repository.ArchitectureRepository;
import com.example.ancient_architecture.repository.ContactRepository;
import com.example.ancient_architecture.service.ArchitectureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArchitectureController {
    @Autowired
    private ContactRepository contactRepository;
    private final ArchitectureService service;
    private final ArchitectureRepository architectureRepository;

    public ArchitectureController(
            ArchitectureService service,
            ArchitectureRepository architectureRepository
        
    ) {
        this.service = service;
        this.architectureRepository = architectureRepository;
        
    }

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/gallery")
public String galleryPage(Model model) {
    List<Architecture> allList = architectureRepository.findAll();
    model.addAttribute("architectures", allList);
    return "gallery";
}

    @GetMapping("/search")
    public String search(
        @RequestParam(value = "query", defaultValue = "") String query,
        Model model
) {
    List<Architecture> results;
    Architecture item = null;

    if (query.startsWith("id:")) {
        try {
            String idStr = query.replace("id:", "").trim();
            Long id = Long.parseLong(idStr);
            
            item = architectureRepository.findById(id).orElse(null);
            results = item != null ? List.of(item) : List.of();
        } catch (Exception e) {
            results = List.of();
        }
    } else {
        if (query.isBlank()) {
            results = architectureRepository.findAll();
        } else {
            results = service.searchArchitectures(query);
        }
        
        if (results != null && !results.isEmpty()) {
            item = results.get(0);
        }
    }

    model.addAttribute("item", item);
    model.addAttribute("searchQuery", query);
    return "search-result";
}

  

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }
    @PostMapping("/contact/send")
    public String sendMessage(@NonNull Contact contact) {
    contactRepository.save(contact);
    return "redirect:/contact?success";
    }
}