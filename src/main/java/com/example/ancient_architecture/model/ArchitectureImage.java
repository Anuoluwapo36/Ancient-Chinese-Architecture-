package com.example.ancient_architecture.model;

import jakarta.persistence.*;

@Entity
@Table(name = "architecture_images")
public class ArchitectureImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "architecture_id")
    private Architecture architecture;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Architecture getArchitecture() { return architecture; }
    public void setArchitecture(Architecture architecture) { this.architecture = architecture; }
}