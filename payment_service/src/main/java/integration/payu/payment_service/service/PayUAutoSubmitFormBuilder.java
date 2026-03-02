package integration.payu.payment_service.service;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PayUAutoSubmitFormBuilder implements PaymentFormBuilder {

    private static final String PAYU_FORM_ACTION = "https://test.payu.in/_payment";

    @Override
    public String build(Map<String, String> requestData) {
        StringBuilder html = new StringBuilder();

        html.append("<html>");
        html.append("<body onload='document.forms[0].submit()'>");
        html.append("<form action='").append(PAYU_FORM_ACTION).append("' method='post'>");

        requestData.forEach((key, value) -> {
            html.append("<input type='hidden' name='")
                    .append(key)
                    .append("' value='")
                    .append(value)
                    .append("'/>");
        });

        html.append("</form>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}

