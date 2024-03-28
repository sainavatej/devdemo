package com.digisite.core.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;

@Component(service = Servlet.class, immediate = true,
    property = {
        "sling.servlet.paths=/bin/NodePropertiesServlet1"
    }
)

public class NodePropertiesServlet extends SlingAllMethodsServlet {
    private static final Logger log = LoggerFactory.getLogger(NodePropertiesServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        ResourceResolver resolver = request.getResourceResolver();
        
       
        log.info("NodePropertiesServlet invoked.");

       
        Resource parentResource = resolver.getResource("/content/digisite/us/en/subscribe/jcr:content");

        if (parentResource != null) {
            Iterator<Resource> children = parentResource.listChildren();
            StringBuilder tableContent = new StringBuilder();
            tableContent.append("<table border=\"1\"><tr><th>First Name</th><th>Last Name</th><th>Email</th></tr>");

            while (children.hasNext()) {
                Resource child = children.next();
                // Accessing properties of each child node
                String firstName = child.getValueMap().get("First Name", String.class);
                String lastName = child.getValueMap().get("Last Name", String.class);
                String email = child.getValueMap().get("Email Id", String.class);

                // Add the row to the table
                tableContent.append("<tr><td>").append(firstName).append("</td><td>").append(lastName).append("</td><td>").append(email).append("</td></tr>");
                
                // Log node information
                log.info("Node properties: First Name: {}, Last Name: {}, Email: {}", firstName, lastName, email);
            }

            tableContent.append("</table>");

            // Write the table to the response
            response.getWriter().write(tableContent.toString());
        } else {
            response.getWriter().write("Parent resource not found.");
            // Log error message
            log.error("Parent resource not found.");
        }
        
        // Log servlet completion
        log.info("NodePropertiesServlet completed.");
    }
}
