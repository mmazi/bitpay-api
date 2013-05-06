package si.mazi.bitpay.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.mazi.bitpay.dto.Invoice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * @author Matija Mazi <br/>
 * @created 5/6/13 6:56 PM
 */

@Path("bitpay")
public class NotificationListener {
    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);

    @POST
    @Path("ipn")
    @Consumes(MediaType.APPLICATION_JSON)
    public void onNotification(Invoice invoice) {
        log.info("invoice updated: {}", invoice);
    }
}
