package dev.fabiosimones.ms_sync.dtos.beautyprocedure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "beautyprocedure")
public class BeautyProcedureDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
