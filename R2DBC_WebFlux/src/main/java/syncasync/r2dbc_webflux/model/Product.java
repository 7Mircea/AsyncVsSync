package syncasync.r2dbc_webflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @Column("id_prod")
    public Integer idProd; //NUMBER(10) in Oracle
    @Column("prod_name")
    public String prodName;
    @Column("id_supplier")
    public int idSupplier;
    public String availability;
    public String category;
    @Column("add_info")
    public String addInfo;

    public Product(Integer idProd) {
        this.idProd = idProd;
    }
}
