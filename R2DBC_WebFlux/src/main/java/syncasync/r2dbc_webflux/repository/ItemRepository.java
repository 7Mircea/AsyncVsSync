package syncasync.r2dbc_webflux.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import syncasync.r2dbc_webflux.model.Item;
import syncasync.r2dbc_webflux.model.custom_query_return.ProdQuantity1;

public interface ItemRepository extends R2dbcRepository<Item, Integer> {
    @Query(value = "select p.prod_name,idProdQ.quantity from products p inner join (select i.id_prod id_prod,sum(i.quantity) quantity\n" +
            "from item i inner join invoice on i.invoice_nr = invoice.nr and i.invoice_Date = invoice.invoice_Date\n" +
            "where EXTRACT(month from invoice.invoice_Date) = 10 and EXTRACT(year from invoice.invoice_Date) = 2022 and invoice.type='c'\n" +
            "group by i.id_prod) idProdQ on p.id_prod=idProdQ.id_prod;")
    Flux<ProdQuantity1> findItemsBetweenDates();
}
