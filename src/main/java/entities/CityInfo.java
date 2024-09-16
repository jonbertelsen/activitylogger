package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dtos.CityInfoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne(mappedBy = "cityInfo")
    private Activity activity;

    public CityInfo(CityInfoDTO cityInfo) {
        this.id = cityInfo.getId();
        this.cityName = cityInfo.getName();
        this.href = cityInfo.getHref();
        this.visualCenterX = cityInfo.getVisualCenter()[0];
        this.visualCenterY = cityInfo.getVisualCenter()[1];
    }
}
