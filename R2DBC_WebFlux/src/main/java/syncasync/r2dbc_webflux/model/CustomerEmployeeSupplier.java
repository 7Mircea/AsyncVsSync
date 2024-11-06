package syncasync.r2dbc_webflux.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import static syncasync.r2dbc_webflux.utils.Constants.CUSTOMER_EMPLOYEE_SUPPLIER_TABLE;

@Data
@Table(value = CUSTOMER_EMPLOYEE_SUPPLIER_TABLE)
public class CustomerEmployeeSupplier {
    @Id
    @Column(value = "id_ces")
    private int idCES;
    @Column
    private String name;
    @Column
    private String EIN;
    @Column(value = "type_ces")
    private char typeCES;
    private String address;
    @Column
    private String IBAN;
    private String SSN;
}
