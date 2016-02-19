/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdkjava_tokenizacion;

import com.payu.sdk.PayU;
import com.payu.sdk.PayUTokens;
import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.CreditCardToken;
import com.payu.sdk.model.Language;
import com.payu.sdk.utils.LoggerUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author andrea.rosales
 */
public class CrearToken {

    public static void main(String[] args) throws PayUException, InvalidParametersException, ConnectionException {
        PayU.apiKey = "6u39nqhq8ftd0hlvnjfs66eh8c"; //Ingresa aquí tu apiKey.
        //PayU.apiKey = "676k86ks53la6tni6clgd30jf6"; //Brasil
        PayU.apiLogin = "11959c415b33d0c"; //Ingresa aquí tu apiLogin.
        //PayU.apiLogin = "403ba744e9827f3"; //Brasil
        PayU.language = Language.en; //Ingresa aquí el idioma que prefieras.
        PayU.isTest = false; //Dejarlo verdadero cuando sean pruebas.
        LoggerUtil.setLogLevel(Level.ALL); //Incluirlo únicamente si desea ver toda la traza del log; si solo se desea ver la respuesta, se puede eliminar.
        PayU.paymentsUrl = "https://stg.api.payulatam.com/payments-api/"; //Incluirlo únicamente si desea probar en un servidor de pagos específico, e indicar la ruta del mismo.
        PayU.reportsUrl = "https://stg.api.payulatam.com/reports-api/"; //Incluirlo únicamente si desea probar en un servidor de reportes específico, e indicar la ruta del mismo.

        // -- Operación "Crear Token" --
        Map<String, String> parameters = new HashMap<String, String>();
//Ingrese aquí el nombre del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_NAME, "APPROVED");
//Ingrese aquí el identificador del pagador.
        parameters.put(PayU.PARAMETERS.PAYER_ID, "id_pagador_004");
//Ingrese aquí el documento de identificación del comprador.
        parameters.put(PayU.PARAMETERS.PAYER_DNI, "1234567890");
//Ingrese aquí el número de la tarjeta de crédito
        parameters.put(PayU.PARAMETERS.CREDIT_CARD_NUMBER, "5455040000000005");
//Ingrese aquí la fecha de vencimiento de la tarjeta de crédito
        parameters.put(PayU.PARAMETERS.CREDIT_CARD_EXPIRATION_DATE, "2018/06");
//Ingrese aquí el nombre de la tarjeta de crédito
        parameters.put(PayU.PARAMETERS.PAYMENT_METHOD, "MASTERCARD");
        CreditCardToken response = PayUTokens.create(parameters);

        if (response != null) {
            response.getTokenId();
            response.getMaskedNumber();
            response.getPayerId();
            response.getIdentificationNumber();
            response.getPaymentMethod();
        }
    }
}
