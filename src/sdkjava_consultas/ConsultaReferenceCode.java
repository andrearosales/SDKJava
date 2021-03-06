/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdkjava_consultas;

import com.payu.sdk.PayU;
import com.payu.sdk.PayUReports;
import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.Language;
import com.payu.sdk.model.Order;
import com.payu.sdk.model.Transaction;
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
public class ConsultaReferenceCode {

    public static void main(String[] args) throws PayUException, InvalidParametersException, ConnectionException {
        PayU.apiKey = "6u39nqhq8ftd0hlvnjfs66eh8c"; //Ingresa aquí tu apiKey.
        PayU.apiLogin = "11959c415b33d0c"; //Ingresa aquí tu apiLogin.
        PayU.language = Language.en; //Ingresa aquí el idioma que prefieras.
        PayU.isTest = false; //Dejarlo verdadero cuando sean pruebas.
        LoggerUtil.setLogLevel(Level.ALL); //Incluirlo únicamente si desea ver toda la traza del log; si solo se desea ver la respuesta, se puede eliminar.
        PayU.paymentsUrl = "https://stg.api.payulatam.com/payments-api/"; //Incluirlo únicamente si desea probar en un servidor de pagos específico, e indicar la ruta del mismo.
        PayU.reportsUrl = "https://stg.api.payulatam.com/reports-api/"; //Incluirlo únicamente si desea probar en un servidor de reportes específico, e indicar la ruta del mismo.

        Map<String, String> parameters = new HashMap<String, String>();

//Ingresa aquí el código de referencia de la orden.
        parameters.put(PayU.PARAMETERS.REFERENCE_CODE, "payment_test_6514");

        List<Order> orders_response = PayUReports.getOrderDetailByReferenceCode(parameters);
        Iterator<Order> orders_iterator = orders_response.iterator();

// -- recorrer las órdenes con la referencia consultada--
        while (orders_iterator.hasNext()) {
            Order order = (Order) orders_iterator.next();

            if (order != null) {
                order.getAccountId();
                order.getStatus();
                order.getReferenceCode();
                order.getAdditionalValue("TX_VALUE").getValue();
                order.getAdditionalValue("TX_TAX").getValue();

                if (order.getBuyer() != null) {
                    order.getBuyer().getEmailAddress();
                    order.getBuyer().getFullName();
                }

                // -- recorrer las transacciones asociadas a la orden--
                List<Transaction> transactions = order.getTransactions();
                Iterator<Transaction> transactions_iterator = transactions.iterator();

                while (transactions_iterator.hasNext()) {
                    Transaction transaction = (Transaction) transactions_iterator.next();
                    transaction.getType();
                    transaction.getTransactionResponse().getState();
                    transaction.getTransactionResponse().getPaymentNetworkResponseCode();
                    transaction.getTransactionResponse().getTrazabilityCode();
                    transaction.getTransactionResponse().getResponseCode();
                    if (transaction.getPayer() != null) {
                        transaction.getPayer().getFullName();
                        transaction.getPayer().getEmailAddress();
                    }
                }
            }
        }
    }
}
