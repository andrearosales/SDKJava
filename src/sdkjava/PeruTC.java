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
import com.payu.sdk.utils.LoggerUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author andrea.rosales
 */
public class PeruTC {

    public static void main(String[] args) throws PayUException, InvalidParametersException, ConnectionException {
        PayU.apiKey = "6u39nqhq8ftd0hlvnjfs66eh8c"; //Ingresa aquí tu apiKey.
        PayU.apiLogin = "11959c415b33d0c"; //Ingresa aquí tu apiLogin.
        PayU.language = Language.en; //Ingresa aquí el idioma que prefieras.
        PayU.isTest = true; //Dejarlo verdadero cuando sean pruebas.
        LoggerUtil.setLogLevel(Level.ALL); //Incluirlo únicamente si desea ver toda la traza del log; si solo se desea ver la respuesta, se puede eliminar.
        PayU.paymentsUrl = "https://stg.api.payulatam.com/payments-api/"; //Incluirlo únicamente si desea probar en un servidor de pagos específico, e indicar la ruta del mismo.
        PayU.reportsUrl = "https://stg.api.payulatam.com/reports-api/"; //Incluirlo únicamente si desea probar en un servidor de reportes específico, e indicar la ruta del mismo.

        String reference = "payment_test_651323";
        String value = "100";

        Map<String, String> parameters = new HashMap<String, String>();

//Ingrese aquí el identificador de la cuenta.
        parameters.put(PayU.PARAMETERS.ACCOUNT_ID, "500546");
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
        parameters.put(PayU.PARAMETERS.CURRENCY, "" + Currency.PEN.name());

// -- Comprador --
//Ingrese aquí el id del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_ID, "1");
//Ingrese aquí el nombre del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_NAME, "First name and second buyer  name");
//Ingrese aquí el email del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_EMAIL, "buyer_test@test.com");
//Ingrese aquí el teléfono de contacto del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_CONTACT_PHONE, "7563126");
//Ingrese aquí el documento de contacto del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_DNI, "5415668464654");
//Ingrese aquí la dirección del comprador.
        parameters.put(PayU.PARAMETERS.BUYER_STREET, "Avenida de la poesia");
        parameters.put(PayU.PARAMETERS.BUYER_STREET_2, "160");
        parameters.put(PayU.PARAMETERS.BUYER_CITY, "Cuzco");
        parameters.put(PayU.PARAMETERS.BUYER_STATE, "CU");
        parameters.put(PayU.PARAMETERS.BUYER_COUNTRY, "PE");
        parameters.put(PayU.PARAMETERS.BUYER_POSTAL_CODE, "000000");
        parameters.put(PayU.PARAMETERS.BUYER_PHONE, "7563126");

// -- pagador --
//Ingrese aquí el id del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_ID, "1");
//Ingrese aquí el nombre del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_NAME, "APPROVED");
//Ingrese aquí el email del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_EMAIL, "payer_test@test.com");
//Ingrese aquí el teléfono de contacto del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_CONTACT_PHONE, "7563126");
//Ingrese aquí el documento de contacto del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_DNI, "5415668464654");
//Ingrese aquí la dirección del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_STREET, "av abancay");
        parameters.put(PayU.PARAMETERS.PAYER_STREET_2, "cra 4");
        parameters.put(PayU.PARAMETERS.PAYER_CITY, "Iquitos");
        parameters.put(PayU.PARAMETERS.PAYER_STATE, "LO");
        parameters.put(PayU.PARAMETERS.PAYER_COUNTRY, "PE");
        parameters.put(PayU.PARAMETERS.PAYER_POSTAL_CODE, "000000");
        parameters.put(PayU.PARAMETERS.PAYER_PHONE, "7563126");

// -- Datos de la tarjeta de crédito -- 
//Ingrese aquí el número de la tarjeta de crédito
        parameters.put(PayU.PARAMETERS.CREDIT_CARD_NUMBER, "4907840000000005");
//Ingrese aquí la fecha de vencimiento de la tarjeta de crédito
        parameters.put(PayU.PARAMETERS.CREDIT_CARD_EXPIRATION_DATE, "2018/12");
//Ingrese aquí el código de seguridad de la tarjeta de crédito
        parameters.put(PayU.PARAMETERS.CREDIT_CARD_SECURITY_CODE, "321");
//Ingrese aquí el nombre de la tarjeta de crédito
//"VISA" || "MASTERCARD" || "AMEX"
        parameters.put(PayU.PARAMETERS.PAYMENT_METHOD, "VISA");

//Ingrese aquí el número de cuotas.
        parameters.put(PayU.PARAMETERS.INSTALLMENTS_NUMBER, "1");
//Ingrese aquí el nombre del pais.
        parameters.put(PayU.PARAMETERS.COUNTRY, PaymentCountry.PE.name());

//Session id del device.
        parameters.put(PayU.PARAMETERS.DEVICE_SESSION_ID, "vghs6tvkcle931686k1900o6e1");
//IP del pagadador
        parameters.put(PayU.PARAMETERS.IP_ADDRESS, "127.0.0.1");
//Cookie de la sesión actual.
        parameters.put(PayU.PARAMETERS.COOKIE, "pt1t38347bs6jc9ruv2ecpv7o2");
//Cookie de la sesión actual.
        parameters.put(PayU.PARAMETERS.USER_AGENT, "Mozilla/5.0 (Windows NT 5.1; rv:18.0) Gecko/20100101 Firefox/18.0");

        parameters.put(PayU.PARAMETERS.SIGNATURE, "1557FBAAC3ADC9D4AC5E324EF1968013");
        
//Solicitud de autorización y captura
        TransactionResponse response = PayUPayments.doAuthorizationAndCapture(parameters);

//Respuesta
        if (response != null) {
            response.getOrderId();
            response.getTransactionId();
            response.getState();
            if (response.getState().toString().equalsIgnoreCase("PENDING")) {
                response.getPendingReason();
            }
            response.getPaymentNetworkResponseCode();
            response.getPaymentNetworkResponseErrorMessage();
            response.getTrazabilityCode();
            response.getResponseCode();
            response.getResponseMessage();
        }
    }
}
