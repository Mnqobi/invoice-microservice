package za.co.eoh.invoices.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "EOH Client Invoice")
public class Invoice {
    @Id
    @GeneratedValue
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    @ApiModelProperty(notes = "Client", example = "XYZ Big Corporate", required = true, position = 1)
    private String client;

    @Column(name = "VAT_RATE")
    @NotNull
    @ApiModelProperty(notes = "Vat Rate", example = "15", required = true, position = 2)
    private Long vatRate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @NotNull
    @ApiModelProperty(notes = "Invoice items", required = true, position = 3)
    private List<LineItem> lineItems;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @ApiModelProperty(notes = "Date of invoice", required = true, example = "2018-10-02T01:53:07.353+0000", position = 4)
    private Date invoiceDate;
}
