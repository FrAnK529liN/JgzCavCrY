// 代码生成时间: 2025-10-22 15:17:52
// ContentManagementSystem.java
// This class represents a simple Content Management System using the Struts framework.

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.DispatchAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

// Define the Content class to represent content items
class Content {
    private int id;
    private String title;
    private String body;

    // Constructor, getters and setters
    public Content(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}

// Define the ContentForm class for Struts form handling
class ContentForm extends ActionForm {
    private int id;
    private String title;
    private String body;

    // Constructor, getters and setters
    public ContentForm() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    // Validate the form data
    public void validate(ActionMapping mapping,(HttpServletRequest request) {
        if (title == null || title.trim().isEmpty()) {
            ActionsMessages messages = new ActionsMessages();
            messages.add("title",
                      new ActionError("error.required", "Title"));
            saveErrors(request, messages);
        }

        if (body == null || body.trim().isEmpty()) {
            ActionsMessages messages = new ActionsMessages();
            messages.add("body",
                      new ActionError("error.required", "Body"));
            saveErrors(request, messages);
        }
    }
}

// Define the ContentAction class that extends DispatchAction
class ContentAction extends DispatchAction {
    private List<Content> contentList = new ArrayList<>();

    // Constructor
    public ContentAction() {
        // Initialize with some sample content
        contentList.add(new Content(1, "Introduction", "Welcome to our CMS."));
        contentList.add(new Content(2, "About Us", "Learn more about our company."));
    }

    // Method to handle adding new content
    public ActionForward addContent(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        ContentForm contentForm = (ContentForm)form;
        Content newContent = new Content(getNextId(), contentForm.getTitle(), contentForm.getBody());
        contentList.add(newContent);
        return mapping.findForward("success");
    }

    // Method to handle editing content
    public ActionForward editContent(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        ContentForm contentForm = (ContentForm)form;
        Content existingContent = findContentById(contentForm.getId());
        if (existingContent != null) {
            existingContent.setTitle(contentForm.getTitle());
            existingContent.setBody(contentForm.getBody());
        }
        return mapping.findForward("success");
    }

    // Helper method to find content by ID
    private Content findContentById(int id) {
        for (Content content : contentList) {
            if (content.getId() == id) {
                return content;
            }
        }
        return null;
    }

    // Helper method to get the next available ID
    private int getNextId() {
        int maxId = 0;
        for (Content content : contentList) {
            if (content.getId() > maxId) {
                maxId = content.getId();
            }
        }
        return maxId + 1;
    }
}
?>