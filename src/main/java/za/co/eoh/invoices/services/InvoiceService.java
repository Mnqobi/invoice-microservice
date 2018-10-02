package za.co.eoh.invoices.services;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.eoh.invoices.domain.Invoice;
import org.springframework.stereotype.Service;
import za.co.eoh.invoices.repositories.InvoiceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    public List<Invoice> viewAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        invoiceRepository.findAll().forEach(invoice -> invoices.add(invoice));
        return invoices;
    }

    public Invoice viewInvoice(long id) {
        return invoiceRepository.findById(id).get();
    }

    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}
