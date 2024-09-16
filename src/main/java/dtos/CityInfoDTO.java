package dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import entities.CityInfo;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityInfoDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("primærtnavn")
    private String name;
    @JsonProperty("href")
    private String href;
    @JsonProperty("visueltcenter")
    private Double[] visualCenter;

    public CityInfoDTO(CityInfo cityInfo){
        this.id = cityInfo.getId();
        this.name = cityInfo.getCityName();
        this.href = cityInfo.getHref();
        this.visualCenter = new Double[]{cityInfo.getVisualCenterX(), cityInfo.getVisualCenterY()};
    }

}
