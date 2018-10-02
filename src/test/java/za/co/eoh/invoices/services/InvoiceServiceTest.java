package za.co.eoh.invoices.services;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.eoh.invoices.domain.Invoice;
import za.co.eoh.invoices.domain.LineItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "spring.profiles.active=tests")
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:invoice_create.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:invoice_drop.sql")
})
public class InvoiceServiceTest {

    @Autowired
    InvoiceService invoiceService;

    @Test
    public void viewAllInvoices() {
        List<Invoice> invoices = invoiceService.viewAllInvoices();
        assertEquals(2, invoices.size());
        assertEquals("ABC Giant Corp", invoices.get(0).getClient());
    }

    @Test
    public void viewInvoice_Success() {
        Long invoiceId = invoiceService.viewAllInvoices().get(1).getId();

        Invoice invoice = invoiceService.viewInvoice(invoiceId);
        assertNotNull(invoice);
        assertEquals("QWERTY Giant Corp", invoice.getClient());
    }

    @Test(expected = NoSuchElementException.class)
    public void viewInvoice_Fail() {
        Invoice invoice = invoiceService.viewInvoice(345345L);
    }

    @Test
    @Ignore(value =  "Hibernate sequencing doesn't work in this test case")
    public void addInvoice() {
        Invoice invoice  = new Invoice();
        invoice.setClient("A Test Client");
        invoice.setVatRate(15L);

        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem = new LineItem();
        lineItem.setDescription("A Test Description");
        lineItem.setQuantity(22L);
        lineItem.setUnitPrice(new BigDecimal(1234.00));
        lineItems.add(lineItem);

        invoice.setLineItems(lineItems);
        Invoice addedInvoice = invoiceService.addInvoice(invoice);
        assertNotNull(addedInvoice.getId());

        List<Invoice> invoices = invoiceService.viewAllInvoices();
        assertEquals(3, invoices.size());
        assertEquals("A Test Client", invoices.get(3).getClient());
    }
}