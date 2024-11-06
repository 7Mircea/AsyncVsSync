package syncasync.r2dbc_webflux.model.custom_query_return;

import lombok.Data;

@Data
public class CategoryProductProfit {
        String category;
        String product_name;
        float profit;
}
