package syncasync.r2dbc_webflux.model.custom_query_return;

import lombok.Data;

@Data
public class SalesCostMonthYear {
    float sales_;
    float costs_;
    int month_;
    int year_;
}
