/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdkjava_tokenizacion;

import com.payu.sdk.PayU;
import com.payu.sdk.PayUPayments;
import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.Currency;
import com.payu.sdk.model.Language;
import com.payu.sdk.model.TransactionResponse;
import com.payu.sdk.utils.LoggerUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author andrea.rosales Autorización o Autorizacion+Captura
 */
public class Cobro_Brasil {

    public static void main(String[] args) throws PayUException, InvalidParametersException, ConnectionException {
        PayU.apiKey = "676k86ks53la6tni6clgd30jf6"; //Ingresa aquí tu apiKey.
        PayU.apiLogin = "403ba744e9827f3"; //Ingresa aquí tu apiLogin.
        PayU.language = Language.en; //Ingresa aquí el idioma que prefieras.
        PayU.isTest = true; //Dejarlo verdadero cuando sean pruebas.
        LoggerUtil.setLogLevel(Level.ALL); //Incluirlo únicamente si desea ver toda la traza del log; si solo se desea ver la respuesta, se puede eliminar.
        PayU.paymentsUrl = "https://stg.api.payulatam.com/payments-api/"; //Incluirlo únicamente si desea probar en un servidor de pagos específico, e indicar la ruta del mismo.
        PayU.reportsUrl = "https://stg.api.payulatam.com/reports-api/"; //Incluirlo únicamente si desea probar en un servidor de reportes específico, e indicar la ruta del mismo.

        String reference = "payment_test_234563";
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
        parameters.put(PayU.PARAMETERS.BUYER_CONTACT_PHONE, "7563126");
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
        parameters.put(PayU.PARAMETERS.BUYER_PHONE, "7563126");

// -- pagador --
//Ingrese aquí el nombre del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_NAME, "APPROVED");

// -- Datos del token -- 
//Ingrese aquí el número del token
        parameters.put(PayU.PARAMETERS.TOKEN_ID, "be64b544-c817-4aa6-a11a-6364632d667d");
//Ingrese aquí el nombre de la tarjeta de crédito
//"VISA" || "AMEX" || "DINERS" || "ELO" || "HIPERCARD" || "MASTERCARD"
        parameters.put(PayU.PARAMETERS.PAYMENT_METHOD, "VISA");
//Ingrese aquí el número de cuotas.
        parameters.put(PayU.PARAMETERS.INSTALLMENTS_NUMBER, "1");
//IP del pagadador
        parameters.put(PayU.PARAMETERS.IP_ADDRESS, "127.0.0.1");
        
        parameters.put(PayU.PARAMETERS.SIGNATURE, "7CECC0196277B3A3CE7288DBD9AF8B69");

//Solicitud de autorización
        TransactionResponse response = PayUPayments.doAuthorization(parameters);

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
