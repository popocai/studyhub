package demo.ws.client;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;

public class DynamicClient {
    public static void main(String[] args) throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:9999/cxf/HelloWorld?wsdl");
        Endpoint endpoint = client.getEndpoint();

        ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
        QName bindingName = new QName("http://localhost:9999/cxf/HelloWorld", "HelloWorldImplServiceSoapBinding");
        BindingInfo binding = serviceInfo.getBinding(bindingName);
        QName opName = new QName("http://localhost:9999/cxf/HelloWorld", "sayHi");
        BindingOperationInfo boi = binding.getOperation(opName);
        // Operation name is processOrder
        BindingMessageInfo inputMessageInfo = null;
        if (!boi.isUnwrapped()) {
            //OrderProcess uses document literal wrapped style.
            inputMessageInfo = boi.getWrappedOperation().getInput();
        } else {
            inputMessageInfo = boi.getUnwrappedOperation().getInput();
        }
        List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();
        MessagePartInfo partInfo = parts.get(0); // Input class is Order
        // Get the input class Order
        Class<?> orderClass = partInfo.getTypeClass();
        Object orderObject = "Patrick";
        // Populate the Order bean

        // Invoke the processOrder() method and print the result
        // The response class is String
        Object[] result = client.invoke(opName, orderObject);
        System.out.println("The order ID is " + result[0]);

    }
}
