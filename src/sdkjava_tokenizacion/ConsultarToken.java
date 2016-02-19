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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author andrea.rosales
 */
public class ConsultarToken {

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

        // -- Operación "Consultar token" --
        Map<String, String> parameters = new HashMap<String, String>();
// -- Parámetros opcionales
//Ingresa aquí el identificador del pagador.
        //parameters.put(PayU.PARAMETERS.PAYER_ID, "10");
//Ingresa aquí el identificador del token.
        parameters.put(PayU.PARAMETERS.TOKEN_ID, "b168a6d7-61af-448a-a58b-bdcac45df75f");
//Ingresa aquí la fecha inicial desde donde filtrar con la fecha final hasta donde filtrar.
        parameters.put(PayU.PARAMETERS.START_DATE, "2016-02-18T09:00:00");
        parameters.put(PayU.PARAMETERS.END_DATE, "2016-02-19T10:25:00");

        List<CreditCardToken> response = PayUTokens.find(parameters);
        Iterator<CreditCardToken> tokens_iterator = response.iterator();

        while (tokens_iterator.hasNext()) {
            CreditCardToken token = (CreditCardToken) tokens_iterator.next();

            token.getTokenId();
            token.getMaskedNumber();
            token.getPayerId();
            token.getIdentificationNumber();
            token.getPaymentMethod();

        }
    }
}
