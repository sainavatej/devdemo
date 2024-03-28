package com.digisite.core.servlets;
 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
 
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
 
@Component(service = Servlet.class, immediate = true,
        property = {
                "sling.servlet.paths=/bin/subscribe1"
        }
)
public class SubscribeServlet extends SlingAllMethodsServlet {
 
    private static final long serialVersionUID = 1L;
    private static final AtomicInteger counter = new AtomicInteger(0);
 
    public void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException {
 
        try {
            String firstname = req.getParameter("First Name");
            String lastname = req.getParameter("Last Name");
            String email = req.getParameter("Email Id");
            ResourceResolver resolver = req.getResourceResolver();
 
            Map<String, Object> map = new HashMap<>();
 
            map.put("First Name", firstname);
            map.put("Last Name", lastname);
            map.put("Email Id", email);
 
            // Generate a unique identifier for the node name
            String identifier = String.valueOf(counter.getAndIncrement());
 
            // Generate a unique timestamp for the node name
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
 
            // Construct the node name with the timestamp and identifier
            String nodeName =  timeStamp + "_" + identifier;
 
            // Construct the node path
            String nodePath = "/content/digisite/us/en/subscribe/jcr:content/" + nodeName;
 
            // Create the new node
            Resource parentResource = resolver.getResource("/content/digisite/us/en/subscribe/jcr:content");
            resolver.create(parentResource, nodeName, map);
 
            resp.getWriter().write("Thank you for submitting");
            resolver.commit();
 
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing the request");
        }
    }
}