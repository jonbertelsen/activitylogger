package entities;

import dtos.CityInfoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityInfo {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    private String cityName;
    private String href;
    private double visualCenterX;
    private double visualCenterY;

    @OneToMany(mappedBy = "cityInfo")
    private List<Activity> activities;

    public CityInfo(CityInfoDTO cityInfoDTO) {
        this.id = cityInfoDTO.getId();
        this.cityName = cityInfoDTO.getName();
        this.href = cityInfoDTO.getHref();
        this.visualCenterX = cityInfoDTO.getVisualCenter()[0];
        this.visualCenterY = cityInfoDTO.getVisualCenter()[1];
    }
}
