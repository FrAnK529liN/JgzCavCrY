// 代码生成时间: 2025-09-23 07:32:29
import com.opensymphony.xwork2.ActionSupport;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.result.HttpHeaderResult;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

/**
 * Struts 2 Action for validating URL links.
 */
@Namespace("/urlValidator")
@ResultPath("/WEB-INF/content")
@Results({
    @Result(name = UrlValidatorAction.SUCCESS, location = "urlValidatorSuccess.jsp"),
    @Result(name = UrlValidatorAction.ERROR, location = "urlValidatorError.jsp"),
    @Result(name = UrlValidatorAction.INPUT, location = "urlValidatorInput.jsp")
})
public class UrlValidatorAction extends ActionSupport implements ServletRequestAware, SessionAware {

    private HttpServletRequest request;
    private String urlToValidate;
    private String resultMessage;
    private String resultStatusCode;

    public String validateUrl() {
        try {
            URL url = new URL(urlToValidate);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            resultStatusCode = Integer.toString(responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                resultMessage = "URL is valid and reachable.";
                return UrlValidatorAction.SUCCESS;
            } else {
                resultMessage = "URL is not reachable or invalid.";
                return UrlValidatorAction.ERROR;
            }
        } catch (Exception e) {
            resultMessage = "Error while validating URL: " + e.getMessage();
            return UrlValidatorAction.INPUT;
        }
    }

    @Actions({
        @Action(value = "validate", results = {
            @Result(name = "input", location = "urlValidatorInput.jsp")
        })
    })
    public String execute() {
        return validateUrl();
    }

    // Getters and Setters
    public String getUrlToValidate() {
        return urlToValidate;
    }

    public void setUrlToValidate(String urlToValidate) {
        this.urlToValidate = urlToValidate;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultStatusCode() {
        return resultStatusCode;
    }

    public void setResultStatusCode(String resultStatusCode) {
        this.resultStatusCode = resultStatusCode;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setSession(Object session) {
        // Session handling logic can be added here if needed.
    }
}
