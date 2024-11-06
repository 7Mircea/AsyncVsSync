package syncasync.jdbc_webflux.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @Column(name = "id_prod", nullable = false, unique = true)
    private Integer idProd; //NUMBER(10) in Oracle
    @Column(name = "prod_name", length = 50, nullable = false)
    private String prodName;
    @ManyToOne
    @JoinColumn(name = "id_supplier", referencedColumnName = "id_ces", nullable = false)
    private CustomerEmployeeSupplier ces;
    @Column(length = 20, nullable = false)
    private String availability;
    @Column(length = 20, nullable = false)
    private String category;
    @Column(name = "add_info", length = 100, nullable = true)
    private String addInfo;


    public Product(Integer idProd) {
        this.idProd = idProd;
    }

    @Override
    public int hashCode() {
        return idProd;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!obj.getClass().getName().equals(this.getClass().getName())) return false;
        return Objects.equals(idProd, ((Product) obj).idProd);
    }


}
