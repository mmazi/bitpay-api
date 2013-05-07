bitpay-api
==========

A Java API for Bitpay Payment Gateway.

https://bitpay.com/bitcoin-payment-gateway-api

### Client

May be used in the following manner (see BitpayTest.java):

    // Create the client
    Bitpay bitpay = RestProxyFactory.createProxy(Bitpay.class, "https://bitpay.com");
    BasicAuthCredentials credentials = new BasicAuthCredentials("*** REPLACE WITH YOUR API KEY ***", "");

    // Use it: get invoice info
    Invoice paidInvoice = bitpay.getInvoceInfo(credentials, "*** REPLACE WITH AN INVOICE ID ***");
    System.out.println("Paid invoice = " + paidInvoice);

    // Create a new invoice
    Invoice invoice = bitpay.createInvoice(...);


### Server for IPN handling

The project packs into a deployable war that includes an IPN handler at `rest/bitpay/ipn`.
This has been tested with JBoss AS 7.1.

