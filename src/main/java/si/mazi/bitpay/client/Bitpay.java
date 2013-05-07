/*
 * Copyright (C) 2013 Matija Mazi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package si.mazi.bitpay.client;

import si.mazi.bitpay.dto.Invoice;
import si.mazi.bitpay.dto.TransactionSpeed;
import si.mazi.rescu.BasicAuthCredentials;

import javax.ws.rs.*;
import java.math.BigDecimal;

/**
 * The BitPay.com JSON API is accessible at https://bitpay.com/api/. The merchant must obtain an API key from the bitpay
 * website by logging into their merchant account and clicking on My Account, API Access keys. A merchant can create
 * multiple keys for use with different e- commerce stores or API functions. Once an API key has been created, BitPay
 * will use this API key to authenticate your API connections.
 * <p/>
 * The merchant’s API key must remain private and should never be visible on any client-facing code. Should it ever be
 * compromised, the merchant can generate a new key in their BitPay account.
 * <p/>
 * When connecting to BitPay, use HTTP Basic Authentication with the username as your API key and
 * leave the password blank (the following page describes the HTTP Basic authentication protocol in detail: http:/
 * /www.ietf.org/rfc/rfc2617.txt). You should also only communicate with the server if you can validate the bitpay.com
 * SSL certificate with a certificate authority. Most HTTPS client libraries make this as simple as setting a switch.
 * Similarly, inbound notification connections should only be recognized when the SSL certificate is validated. Taking
 * both of these steps will ensure that you are always communicating with the Bitpay server and that your API key will
 * never be exposed.
 *
 * @author Matija Mazi
 */
@Path("api")
public interface Bitpay {

    // todo: error responses

    @POST
    @Path("invoice")
    public Invoice createInvoice(
            @HeaderParam("Authorization") BasicAuthCredentials credentials,
            // Required POST fields
            @FormParam("price") BigDecimal price,
            @FormParam("currency") String currency,
            // Optional Payment Notification (IPN) fields
            @FormParam("posData") Object posData,
            @FormParam("notificationURL") String notificationURL,
            @FormParam("transactionSpeed") TransactionSpeed transactionSpeed,
            @FormParam("fullNotifications") boolean fullNotifications,
            @FormParam("notificationEmail") String notificationEmail,
            // Optional Order Handling fields
            @FormParam("redirectURL") String redirectURL,
            // Optional Buyer Information to display
            @FormParam("orderID") String orderID,
            @FormParam("itemDesc") String itemDesc,
            @FormParam("itemCode") String itemCode,
            @FormParam("physical") boolean physical,
            @FormParam("buyerName") String buyerName,
            @FormParam("buyerAddress1") String buyerAddress1,
            @FormParam("buyerAddress2") String buyerAddress2,
            @FormParam("buyerCity") String buyerCity,
            @FormParam("buyerState") String buyerState,
            @FormParam("buyerZip") String buyerZip,
            @FormParam("buyerCountry") String buyerCountry,
            @FormParam("buyerEmail") String buyerEmail,
            @FormParam("buyerPhone") String buyerPhone
    );

    @POST
    @Path("invoice")
    public Invoice createInvoice(
            @HeaderParam("Authorization") BasicAuthCredentials credentials,
            // Required POST fields
            @FormParam("price") BigDecimal price,
            @FormParam("currency") String currency
    );

    @GET
    @Path("invoice/{id}")
    public Invoice getInvoceInfo(
            @HeaderParam("Authorization") BasicAuthCredentials credentials, @PathParam("id") String id);

}
