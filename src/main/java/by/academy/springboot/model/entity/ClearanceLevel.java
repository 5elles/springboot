package by.academy.springboot.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clearance_level")
public class ClearanceLevel {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "level_name")
    private String levelName;
    @Column(name = "has_safe_deposit_access")
    private boolean hasSafeDepositAccess;
    @Column(name = "has_banking_records_access")
    private boolean hasBankingRecordsAccess;
    @Column(name = "has_personnel_records_access")
    private boolean hasPersonnelRecordsAccess;
    @Column(name = "has_storage_rooms_access")
    private boolean hasStorageRoomsAccess;
}
