// 代码生成时间: 2025-10-25 09:20:27
package com.example.crawler;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebContentCrawler extends Action {

    /*
     * The execute method is called when the action is triggered.
     * It takes the URL as input, scrapes the content, and returns the HTML string.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for any form beans
     * @param request The HTTP request we are processing.
     * @param response The HTTP response we are creating.
     * @return The ActionForward instance describing where and how control
     *         should be forwarded to.
     * @throws Exception if an error occurs.
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        String urlString = request.getParameter("url");
        if (urlString == null || urlString.trim().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be empty");
        }
        try {
            // Connect to the URL and retrieve the HTML content
            Document doc = Jsoup.connect(urlString).get();

            // Extract the desired content
            Elements content = doc.select("body"); // Example: Selecting the body of the document

            // Convert the content to a String
            String contentString = content.outerHtml();

            // Set the result as a request attribute
            request.setAttribute("content", contentString);

            // Forward to the result page
            return mapping.findForward("success");
        } catch (IOException e) {
            // Handle exceptions
            request.setAttribute("error", "Failed to retrieve content: " + e.getMessage());
            return mapping.findForward("error");
        }
    }
}
