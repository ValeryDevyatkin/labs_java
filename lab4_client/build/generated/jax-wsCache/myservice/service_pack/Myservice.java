
package service_pack;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "myservice", targetNamespace = "http://service_pack/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Myservice {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getBookList", targetNamespace = "http://service_pack/", className = "service_pack.GetBookList")
    @ResponseWrapper(localName = "getBookListResponse", targetNamespace = "http://service_pack/", className = "service_pack.GetBookListResponse")
    @Action(input = "http://service_pack/myservice/getBookListRequest", output = "http://service_pack/myservice/getBookListResponse")
    public String getBookList();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getBook", targetNamespace = "http://service_pack/", className = "service_pack.GetBook")
    @ResponseWrapper(localName = "getBookResponse", targetNamespace = "http://service_pack/", className = "service_pack.GetBookResponse")
    @Action(input = "http://service_pack/myservice/getBookRequest", output = "http://service_pack/myservice/getBookResponse")
    public String getBook(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}