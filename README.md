bitpay-api
==========

A Java API for Bitpay Payment Gateway.

https://bitpay.com/bitcoin-payment-gateway-api

### Client

May be used in this manner (see BitpayTest.java):

    Bitpay bitpay = RestProxyFactory.createProxy(Bitpay.class, "https://bitpay.com");
    BasicAuthCredentials credentials = new BasicAuthCredentials("*** REPLACE WITH YOUR API KEY ***", "");

    Invoice paidInvoice = bitpay.getInvoceInfo(credentials, "*** REPLACE WITH AN INVOICE ID ***");
    System.out.println("Paid invoice = " + paidInvoice);


### Server for IPN handling

The project packs into a deployable war that includes an IPN handler at `rest/bitpay/ipn`.

