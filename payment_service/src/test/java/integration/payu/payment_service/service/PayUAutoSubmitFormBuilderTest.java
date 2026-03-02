package integration.payu.payment_service.service;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PayUAutoSubmitFormBuilderTest {

    private final PayUAutoSubmitFormBuilder formBuilder = new PayUAutoSubmitFormBuilder();

    @Test
    void shouldBuildAutoSubmitHtmlForm() {
        String html = formBuilder.build(Map.of("txnid", "txn-001", "amount", "10.00"));

        assertTrue(html.contains("<form action='https://test.payu.in/_payment' method='post'>"));
        assertTrue(html.contains("name='txnid' value='txn-001'"));
        assertTrue(html.contains("name='amount' value='10.00'"));
        assertTrue(html.contains("onload='document.forms[0].submit()'"));
    }
}

