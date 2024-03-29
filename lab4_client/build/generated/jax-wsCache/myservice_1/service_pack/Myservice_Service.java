
package service_pack;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "myservice", targetNamespace = "http://service_pack/", wsdlLocation = "http://localhost:8080/lab4_service/myservice?wsdl")
public class Myservice_Service
    extends Service
{

    private final static URL MYSERVICE_WSDL_LOCATION;
    private final static WebServiceException MYSERVICE_EXCEPTION;
    private final static QName MYSERVICE_QNAME = new QName("http://service_pack/", "myservice");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/lab4_service/myservice?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MYSERVICE_WSDL_LOCATION = url;
        MYSERVICE_EXCEPTION = e;
    }

    public Myservice_Service() {
        super(__getWsdlLocation(), MYSERVICE_QNAME);
    }

    public Myservice_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), MYSERVICE_QNAME, features);
    }

    public Myservice_Service(URL wsdlLocation) {
        super(wsdlLocation, MYSERVICE_QNAME);
    }

    public Myservice_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MYSERVICE_QNAME, features);
    }

    public Myservice_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Myservice_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Myservice
     */
    @WebEndpoint(name = "myservicePort")
    public Myservice getMyservicePort() {
        return super.getPort(new QName("http://service_pack/", "myservicePort"), Myservice.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Myservice
     */
    @WebEndpoint(name = "myservicePort")
    public Myservice getMyservicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service_pack/", "myservicePort"), Myservice.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MYSERVICE_EXCEPTION!= null) {
            throw MYSERVICE_EXCEPTION;
        }
        return MYSERVICE_WSDL_LOCATION;
    }

}
