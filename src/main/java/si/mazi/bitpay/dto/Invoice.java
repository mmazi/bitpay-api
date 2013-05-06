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
package si.mazi.bitpay.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Matija Mazi <br/>
 */
public class Invoice {

    private final String id;
    private final String url;
    private final Object posData;
    private final Status status;
    private final BigDecimal price;
    private final String currency;
    private final BigDecimal btcPrice;
    private final Date invoiceTime;
    private final Date expirationTime;
    private final Date currentTime;

    public Invoice(
            @JsonProperty("id") String id,
            @JsonProperty("url") String url,
            @JsonProperty("posData") Object posData,
            @JsonProperty("status") Status status,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("currency") String currency,
            @JsonProperty("btcPrice") BigDecimal btcPrice,
            @JsonProperty("invoiceTime") Date invoiceTime,
            @JsonProperty("expirationTime") Date expirationTime,
            @JsonProperty("currentTime") Date currentTime) {

        this.id = id;
        this.url = url;
        this.posData = posData;
        this.status = status;
        this.price = price;
        this.currency = currency;
        this.btcPrice = btcPrice;
        this.invoiceTime = invoiceTime;
        this.expirationTime = expirationTime;
        this.currentTime = currentTime;
    }

    public String getId() {

        return id;
    }

    public String getUrl() {

        return url;
    }

    public Object getPosData() {

        return posData;
    }

    public Status getStatus() {

        return status;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public String getCurrency() {

        return currency;
    }

    public BigDecimal getBtcPrice() {

        return btcPrice;
    }

    public Date getInvoiceTime() {

        return invoiceTime;
    }

    public Date getExpirationTime() {

        return expirationTime;
    }

    public Date getCurrentTime() {

        return currentTime;
    }

    @Override
    public String toString() {

        return String.format(
                "Invoice{id='%s', url='%s', posData=%s, status=%s, price=%s, currency='%s', btcPrice=%s, invoiceTime=%s, expirationTime=%s, currentTime=%s}",
                id, url, posData, status, price, currency, btcPrice, invoiceTime, expirationTime, currentTime);
    }

    /**
     * A BitPay.com invoice can be in one of the following states: “new”, “paid”, “confirmed”, “complete”, “expired” or
     * “invalid”. Payments sent to the bitcoin address associated with an invoice will only be credited to the invoice
     * when it is in the “new” state.
     */
    public static enum Status {

        /**
         * An invoice starts in this state. When in this state and only in this state, payments to the associated bitcoin
         * address are credited to the invoice. If an invoice has received a partial payment, it will still reflect a status
         * of new to the merchant (from a merchant system perspective, an invoice is either paid or not paid, partial
         * payments and over payments are handled by bitpay.com by either refunding the customer or applying the funds to a
         * new invoice.
         */
        NEW,

        /**
         * As soon as full payment (or over payment) is received, an invoice goes into the paid status.
         */
        PAID,

        /**
         * The transaction speed preference of an invoice determines when an invoice is confirmed. For the high speed
         * setting, it will confirmed as soon as full payment is received on the bitcoin network (note, the invoice will go
         * from a status of new to confirmed, bypassing the paid status). For the medium speed setting, the invoice is
         * confirmed after the payment transaction(s) have been confrimed by 1 block on the bitcoin network. For the low
         * speed setting, 6 blocks on the bitcoin network are required. Invoices are considered complete after 6 blocks on
         * the bitcoin network, therefore an invoice will go from a paid status directly to a complete status if the
         * transaction speed is set to low.
         */
        CONFIRMED,

        /**
         * When an invoice is complete, it means that BitPay.com has credited the merchant’s account for the invoice.
         * Currently, 6 confirmation blocks on the bitcoin network are required for an invoice to be complete. Note, in the
         * future (for qualified payers), invoices may move to a complete status immediately upon payment, in which case the
         * invoice will move directly from a new status to a complete status.
         */
        COMPLETE,

        /**
         * n expired invoice is one where payment was not received and the 15 minute payment window has elapsed.
         */
        EXPIRED,

        /**
         * An invoice is considered invalid when it was paid, but payment was not confirmed within 1 hour after receipt. It
         * is possible that some transactions on the bitcoin network can take longer than 1 hour to be included in a block.
         * In such circumstances, once payment is confirmed, BitPay.com will make arrangements with the merchant regarding
         * the funds (which can either be credited to the merchant account on another invoice, or returned to the buyer).
         */
        INVALID;

        @JsonCreator
        public static Status getInstance(String name) {

            return valueOf(name.toUpperCase());
        }


        @Override
        public String toString() {

            return name().toLowerCase();
        }
    }

}
