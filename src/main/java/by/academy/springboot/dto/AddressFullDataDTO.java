package by.academy.springboot.dto;

import lombok.Data;

import java.util.List;
@Data
public class AddressFullDataDTO {
    List<CountryDTO> countries;
    List<RegionDTO> regions;
    List<SettlementTypeDTO> settlementTypes;
    List<SettlementDTO> settlements;
    List<StreetTypeDTO> streetTypes;
}
