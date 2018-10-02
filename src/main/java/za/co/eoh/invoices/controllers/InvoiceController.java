package za.co.eoh.invoices.controllers;

import za.co.eoh.invoices.domain.Invoice;
import za.co.eoh.invoices.services.InvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/invoices/", produces = "application/json")
@Api(description = "Endpoints for Creating and Retrieving Invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    @ApiOperation("${controller.invoice.viewAllInvoices}")
    public List<Invoice> viewAllInvoices() {
        return invoiceService.viewAllInvoices();
    }

    @GetMapping("/{invoiceId}")
    @ApiOperation("${controller.invoice.viewInvoice}")
    public Invoice viewInvoice(@ApiParam(value = "${controller.invoice.viewInvoice.invoiceid}", required = true)
                                    @PathVariable Long invoiceId) {
        return invoiceService.viewInvoice(invoiceId);
    }

    @PostMapping
    @ApiOperation("${controller.invoice.addInvoice}")
    public Invoice addInvoice(@ApiParam(value = "${controller.invoice.addInvoice.body}", required = true)
                                @RequestBody Invoice invoice) {
        return invoiceService.addInvoice(invoice);
    }
}
