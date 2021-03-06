/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdkjava_pagos;

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
public class BrasilEF {

    public static void main(String[] args) throws PayUException, InvalidParametersException, ConnectionException {
        PayU.apiKey = "676k86ks53la6tni6clgd30jf6"; //Ingresa aquí tu apiKey.
        PayU.apiLogin = "403ba744e9827f3"; //Ingresa aquí tu apiLogin.
        PayU.language = Language.en; //Ingresa aquí el idioma que prefieras.
        PayU.isTest = false; //Dejarlo verdadero cuando sean pruebas.
        LoggerUtil.setLogLevel(Level.ALL); //Incluirlo únicamente si desea ver toda la traza del log; si solo se desea ver la respuesta, se puede eliminar.
        PayU.paymentsUrl = "https://stg.api.payulatam.com/payments-api/"; //Incluirlo únicamente si desea probar en un servidor de pagos específico, e indicar la ruta del mismo.
        PayU.reportsUrl = "https://stg.api.payulatam.com/reports-api/"; //Incluirlo únicamente si desea probar en un servidor de reportes específico, e indicar la ruta del mismo.

        String reference = "payment_test_56163";
        String value = "100";

        Map<String, String> parameters = new HashMap<String, String>();

//Ingrese aquí el identificador de la cuenta.
        parameters.put(PayU.PARAMETERS.ACCOUNT_ID, "500719");
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
        parameters.put(PayU.PARAMETERS.CURRENCY, "" + Currency.BRL.name());

// -- Comprador --
//Ingrese aquí el id del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_ID, "1");
//Ingrese aquí el nombre del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_NAME, "First name and second buyer  name");
//Ingrese aquí el email del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_EMAIL, "buyer_test@test.com");
//Ingrese aquí el teléfono de contacto del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_CONTACT_PHONE, "(11)756312633");
//Ingrese aquí el documento de contacto del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_DNI, "811.807.405-64");
// or 
//parameters.put(PayU.PARAMETERS.BUYER_CNPJ, "32593371000110");
//Ingrese aquí la dirección del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_STREET, "calle 100");
        parameters.put(PayU.PARAMETERS.BUYER_STREET_2, "5555487");
        parameters.put(PayU.PARAMETERS.BUYER_CITY, "Sao paulo");
        parameters.put(PayU.PARAMETERS.BUYER_STATE, "SP");
        parameters.put(PayU.PARAMETERS.BUYER_COUNTRY, "BR");
        parameters.put(PayU.PARAMETERS.BUYER_POSTAL_CODE, "01019-030");
        parameters.put(PayU.PARAMETERS.BUYER_PHONE, "(11)756312633");

// -- pagador --
//Ingrese aquí el nombre del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_NAME, "First name and second payer name");
//Ingrese aquí el nombre del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_DNI, "811.807.405-64");
//Ingrese aquí la dirección del comprador.
        parameters.put(PayU.PARAMETERS.PAYER_STREET, "calle 100");
        parameters.put(PayU.PARAMETERS.PAYER_STREET_2, "5555487");
        parameters.put(PayU.PARAMETERS.PAYER_CITY, "Sao paulo");
        parameters.put(PayU.PARAMETERS.PAYER_STATE, "SP");
        parameters.put(PayU.PARAMETERS.PAYER_COUNTRY, "BR");
        parameters.put(PayU.PARAMETERS.PAYER_POSTAL_CODE, "01019-030");
        parameters.put(PayU.PARAMETERS.PAYER_PHONE, "(11)756312633");

//Ingrese aquí el medio de pago
        parameters.put(PayU.PARAMETERS.PAYMENT_METHOD, "BOLETO_BANCARIO");

//Ingrese aquí la fecha de expiración. 
        parameters.put(PayU.PARAMETERS.EXPIRATION_DATE, "2019-05-20T00:00:00");

//Ingrese aquí el nombre del pais.
        parameters.put(PayU.PARAMETERS.COUNTRY, PaymentCountry.BR.name());

//IP del pagadador
        parameters.put(PayU.PARAMETERS.IP_ADDRESS, "127.0.0.1");
        
        parameters.put(PayU.PARAMETERS.SIGNATURE, "CEE7A8C1F2247C2E2D637C9DE8A726BD");

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
                String url = (String) extraParameters.get("URL_BOLETO_BANCARIO");
            }
            response.getPaymentNetworkResponseCode();
            response.getPaymentNetworkResponseErrorMessage();
            response.getTrazabilityCode();
            response.getResponseCode();
            response.getResponseMessage();
        }
    }
}
