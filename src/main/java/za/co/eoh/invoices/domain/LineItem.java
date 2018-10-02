package za.co.eoh.invoices.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity(name = "LINE_ITEMS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Line items to billed")
public class LineItem {
    @Id
    @GeneratedValue
    @NotNull
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    @ApiModelProperty(notes = "Quantity of the item", example = "14", required = true, position = 1)
    private Long quantity;

    @Size(min = 1, max = 255)
    @NotBlank
    @ApiModelProperty(notes = "Item description", example = "A3 Hardcover Book", required = true, position = 2)
    private String description;

    @Column(name = "UNIT_PRICE")
    @NotNull
    @ApiModelProperty(notes = "Price of the item", example = "1500.45", required = true, position = 3)
    private BigDecimal unitPrice;
}
