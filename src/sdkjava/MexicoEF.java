/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdkjava;

import com.payu.sdk.PayU;
import com.payu.sdk.PayUPayments;
import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.Currency;
import com.payu.sdk.model.Language;
import com.payu.sdk.model.PaymentCountry;
import com.payu.sdk.model.TransactionResponse;
import com.payu.sdk.model.TransactionState;
import com.payu.sdk.utils.LoggerUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author andrea.rosales
 */
public class MexicoEF {

    public static void main(String[] args) throws PayUException, InvalidParametersException, ConnectionException {
        PayU.apiKey = "6u39nqhq8ftd0hlvnjfs66eh8c"; //Ingresa aquí tu apiKey.
        PayU.apiLogin = "11959c415b33d0c"; //Ingresa aquí tu apiLogin.
        PayU.language = Language.en; //Ingresa aquí el idioma que prefieras.
        PayU.isTest = false; //Dejarlo verdadero cuando sean pruebas.
        LoggerUtil.setLogLevel(Level.ALL); //Incluirlo únicamente si desea ver toda la traza del log; si solo se desea ver la respuesta, se puede eliminar.
        PayU.paymentsUrl = "https://stg.api.payulatam.com/payments-api/"; //Incluirlo únicamente si desea probar en un servidor de pagos específico, e indicar la ruta del mismo.
        PayU.reportsUrl = "https://stg.api.payulatam.com/reports-api/"; //Incluirlo únicamente si desea probar en un servidor de reportes específico, e indicar la ruta del mismo.

        String reference = "payment_test_9816";
        String value = "100";

//para realizar un pago en efectivo ---------------------------------
        Map<String, String> parameters = new HashMap<String, String>();

//Ingrese aquí el identificador de la cuenta.
        parameters.put(PayU.PARAMETERS.ACCOUNT_ID, "500547");
//Ingrese aquí el código de referencia.
        parameters.put(PayU.PARAMETERS.REFERENCE_CODE, "" + reference);
//Ingrese aquí la descripción.
        parameters.put(PayU.PARAMETERS.DESCRIPTION, "payment test");
//Ingrese aquí el idima de la orden.
        parameters.put(PayU.PARAMETERS.LANGUAGE, "Language.es");

// -- Valores --
//Ingrese aquí el valor.
        parameters.put(PayU.PARAMETERS.VALUE, "" + value);
//Ingrese aquí la moneda.
        parameters.put(PayU.PARAMETERS.CURRENCY, "" + Currency.MXN.name());

//Ingrese aquí el email del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_EMAIL, "buyer_test@test.com");

//Ingrese aquí el nombre del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_NAME, "First name and second payer name");

//Ingrese aquí el nombre del medio de pago en efectivo
//"SEVEN_ELEVEN" || "SCOTIABANK" || "IXE" || "SANTANDER" || "BANCOMER" || "OXXO" || "BANAMEX"
        parameters.put(PayU.PARAMETERS.PAYMENT_METHOD, "OXXO");

//Ingrese aquí el nombre del pais.
        parameters.put(PayU.PARAMETERS.COUNTRY, PaymentCountry.MX.name());

//Ingrese aquí la fecha de expiración. Sólo para OXXO y SEVEN_ELEVEN 
        parameters.put(PayU.PARAMETERS.EXPIRATION_DATE, "2016-05-20T00:00:00");

//IP del pagadador
        parameters.put(PayU.PARAMETERS.IP_ADDRESS, "127.0.0.1");

//Solicitud de autorización y captura
        TransactionResponse response = PayUPayments.doAuthorizationAndCapture(parameters);

//Respuesta
        if (response != null) {
            response.getOrderId();
            response.getTransactionId();
            response.getState();
            if (response.getState().equals(TransactionState.PENDING)) {
                response.getPendingReason();
                Map extraParameters = response.getExtraParameters();

                //obtener la url del comprobante de pago
                String url = (String) extraParameters.get("URL_PAYMENT_RECEIPT_HTML");
            }
            response.getPaymentNetworkResponseCode();
            response.getPaymentNetworkResponseErrorMessage();
            response.getTrazabilityCode();
            response.getResponseCode();
            response.getResponseMessage();
        }
    }
}
