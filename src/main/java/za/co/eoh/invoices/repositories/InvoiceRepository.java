package za.co.eoh.invoices.repositories;

import org.springframework.data.repository.CrudRepository;
import za.co.eoh.invoices.domain.Invoice;

public interface InvoiceRepository  extends CrudRepository<Invoice, Long> {
}
