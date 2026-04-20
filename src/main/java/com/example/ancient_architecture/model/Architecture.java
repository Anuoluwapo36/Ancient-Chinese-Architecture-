package com.example.ancient_architecture.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "architectures")
public class Architecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chineseName;
    private String englishName;
    private String pinyinName;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String dynasty;
    private String buildingType;
    private String yearBuild;
    @Column(columnDefinition = "TEXT")
    private String historicalSignificance;
    private String location;
    private String architectureStyle;
    private double totalArea;
    private String constructionMaterials;
    @Column(columnDefinition = "TEXT")
    private String briefDescription;

    @OneToMany(mappedBy = "architecture", cascade = CascadeType.ALL)
    private List<ArchitectureImage> images;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getChineseName() { return chineseName; }
    public void setChineseName(String chineseName) { this.chineseName = chineseName; }
    public String getEnglishName() { return englishName; }
    public void setEnglishName(String englishName) { this.englishName = englishName; }
    public String getPinyinName() { return pinyinName; }
    public void setPinyinName(String pinyinName) { this.pinyinName = pinyinName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDynasty() { return dynasty; }
    public void setDynasty(String dynasty) { this.dynasty = dynasty; }
    public String getBuildingType() { return buildingType; }
    public void setBuildingType(String buildingType) { this.buildingType = buildingType; }
    public String getYearBuild() { return yearBuild; }
    public void setYearBuild(String yearBuild) { this.yearBuild = yearBuild; }
    public String getHistoricalSignificance() { return historicalSignificance; }
    public void setHistoricalSignificance(String historicalSignificance) { this.historicalSignificance = historicalSignificance; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getArchitectureStyle() { return architectureStyle; }
    public void setArchitectureStyle(String architectureStyle) { this.architectureStyle = architectureStyle; }
    public double getTotalArea() { return totalArea; }
    public void setTotalArea(double totalArea) { this.totalArea = totalArea; }
    public String getConstructionMaterials() { return constructionMaterials; }
    public void setConstructionMaterials(String constructionMaterials) { this.constructionMaterials = constructionMaterials; }
    public String getBriefDescription() { return briefDescription; }
    public void setBriefDescription(String briefDescription) { this.briefDescription = briefDescription; }
    public List<ArchitectureImage> getImages() { return images; }
    public void setImages(List<ArchitectureImage> images) { this.images = images; }
}