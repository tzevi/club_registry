package gr.hua.ds.club_registry.db.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import gr.hua.ds.club_registry.db.enums.ShopType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(
        name="shops",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"address"})
)
@AllArgsConstructor
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int  id;

    @Column(name="active",columnDefinition = "boolean default false", nullable = false)
    private Boolean active = false;

    @Column(name = "city")
    @NotBlank(message = "City should not be empty or null")
    @Pattern(regexp = "[a-zA-Z\\s']+", message = "Alphabetical characters are only allowed")
    @Size(min=2, max=80)
    private String city;

    @Column(name = "address")
    @NotBlank(message = "Address should not be empty or null")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "shop_type", columnDefinition = "varchar(32) default 'SHOP'")
    //@NotBlank(message = "Shop Type should be either 'SHOP' or 'ROOM'")
    private ShopType shopType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "club_tax_no",referencedColumnName = "tax_no", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Club club;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String clubTaxNo;

    public Shop() {

    }
}
