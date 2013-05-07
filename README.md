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

### Getting started

#### Create invoices and get invoice information

1. Create a merchant account at https://bitpay.com/merchant-signup
2. Create an API key at https://bitpay.com/api-keys
3. Get the client code: `git clone git://github.com/mmazi/bitpay-api.git`
4. Either build it (`mvn clean install`) and use it in your maven project, or simply copy any code you need to your project.
5. Change the code to use your API key (see above or BitpayTest.java).

This depends on https://github.com/mmazi/rescu which is a light-weight REST/json client library, but may be easily
adapted to use another library like RestEasy or Jersey.

#### Use Instant Payment Notifications

1. Copy `BitpayRestApp.java` and `NotificationListener.java` to your project. Adapt the NotificationListener code to suit your needs.
2. Deploy in a JAX-RS supporting Java EE applicatino server like JBoss AS 7.1. This currently depends on Jackson so it will work out-of-the-box with a REST library that uses Jackson (like RestEasy that is used in JBoss AS).
