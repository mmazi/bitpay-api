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

/**
 * The transaction speed preference of an invoice determines when an invoice is confirmed. For the high speed setting,
 * it will confirmed as soon as full payment is received on the bitcoin network (note, the invoice will go from a status
 * of new to confirmed, bypassing the paid status). For the medium speed setting, the invoice is confirmed after the
 * payment transaction(s) have been confrimed by 1 block on the bitcoin network. For the low speed setting, 6 blocks on
 * the bitcoin network are required. Invoices are considered complete after 6 blocks on the bitcoin network, therefore
 * an invoice will go from a paid status directly to a complete status if the transaction speed is set to low.
 *
 * @author Matija Mazi <br/>
 */
public enum TransactionSpeed {
    /**
     * 0 confirmations - confirmed as soon as full payment is received on the bitcoin network
     */
    high,

    /**
     * 1 confirmation
     */
    medium,

    /**
     * 6 confirmations
     */
    low
}
