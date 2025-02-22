package syncasync.r2dbc_webflux.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table
@Data
public class Invoice {
    private int nr;
    @Column(value = "invoice_date")
    private LocalDate invoiceDate;
    @Column("id_vendor")
    private int vendor;
    private char type;
    private float value;
    private float vat;
    @Column("id_employee")
    private int employee;
    @Column("id_buyer")
    private int buyer;
}


