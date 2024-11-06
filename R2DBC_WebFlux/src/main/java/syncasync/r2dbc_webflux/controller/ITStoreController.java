package syncasync.r2dbc_webflux.controller;

import syncasync.r2dbc_webflux.model.*;
import syncasync.r2dbc_webflux.model.custom_query_return.*;
import syncasync.r2dbc_webflux.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/itStore")
public class ITStoreController {
    @Autowired
    ProductsRepository productRepository;
    @Autowired
    CharacteristicRepository characteristicRepository;
    @Autowired
    CustomerEmployeeSupplierRepository customerEmployeeSupplierRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    List<String> source;



    @GetMapping("/products")
    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable Integer id) {
        return productRepository.findById(id);
    }

    @GetMapping("/first_N_products")
    public Flux<Product> getFirstNProducts(@RequestParam int N) {
        return productRepository.findProductsByIdProdIsBefore(N);
    }

    @GetMapping("/product_max_profit")
    public Flux<CategoryProductProfit> getProductWithMaxProfit() {
        return productRepository.getProductWithGreatestProfitInCategory();
    }
    @GetMapping("/category_profit")
    public Flux<CategoryProfit> getCategoryProfit() {
        return productRepository.findProfitForEachCategory();
    }

    @GetMapping("/characteristics")
    public Flux<Characteristic> getAllCharacteristics() {
        return characteristicRepository.findAll();
    }

    @GetMapping("/characteristics_for_product")
    public Flux<Characteristic> getCharacteristicsForProduct(@RequestParam(name = "productId") Integer productId) {
        return characteristicRepository.findCharacteristicByIdProd(productId);
    }

    @GetMapping("/customer_employee_suppliers")
    public Flux<CustomerEmployeeSupplier> getAllCustomerEmployeeSupplier() {
        return customerEmployeeSupplierRepository.findAll();
    }
    @GetMapping("/employee")
    public Flux<String> getEmployeeWithGreatestNrOfInvoices() {
        return customerEmployeeSupplierRepository.findEmployeeWithGreatestNrOfInvoices();
    }

    @GetMapping("/ces_by_type")
    public Flux<CustomerEmployeeSupplier> getCustomerEmployeeSupplierByTypeCES(@RequestParam(name = "type") char type) {
        return customerEmployeeSupplierRepository.findCustomerEmployeeSupplierByTypeCES(type);
    }

    @GetMapping("/invoices")
    public Flux<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping("/max_invoice")
    public Mono<Float> getMaxInvoice() {
        return invoiceRepository.findMaxValue();
    }

    @GetMapping("/month_with_greatest_sale")
    public Flux<MonthSale> getMonthWithGreatestSale() {
        return invoiceRepository.getMonthWithGreatestSales();
    }

    @GetMapping("/sales_cost")
    public Flux<SalesCostMonthYear> getSalesCost() {
        return invoiceRepository.findSalesCostMonthYear();
    }

    @GetMapping("/profit_on_each_product")
    public Flux<ProfitOnEachProduct> getProfitOnEachProduct() {
        return invoiceRepository.findProfitOnEachProduct();
    }

    @GetMapping("/items")
    public Flux<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/items_between_dates")
    public Flux<ProdQuantity1> getItemsBetweenDates() {
        return  itemRepository.findItemsBetweenDates();
    }


    @GetMapping("/test_controller_only")
    public Flux<String> testControllerOnly() {
        return Flux.fromIterable(source);
    }
}
