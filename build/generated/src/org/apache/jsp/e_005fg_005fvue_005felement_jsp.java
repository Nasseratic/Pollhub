package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class e_005fg_005fvue_005felement_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<div id=\"NavBar\">\r\n");
      out.write("    <div v-for=\"user in users\" >\r\n");
      out.write("        {{ user.name}}\r\n");
      out.write("        {{ user.id }}\r\n");
      out.write("        {{ user.isAdmin }}\r\n");
      out.write("    </div>\r\n");
      out.write("    <button @click=\"checkIfUserExist()\"> what ever </button>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("    new Vue({\r\n");
      out.write("        el: '#NavBar',\r\n");
      out.write("        data() {\r\n");
      out.write("            return {\r\n");
      out.write("                userExist: false,\r\n");
      out.write("                users: [{\r\n");
      out.write("                        name: \"Mohamed\",\r\n");
      out.write("                        id: \"1\",\r\n");
      out.write("                        isAdmin: \"0\"\r\n");
      out.write("                    },\r\n");
      out.write("                    {\r\n");
      out.write("                        name: \"Dawood\",\r\n");
      out.write("                        id: \"2\",\r\n");
      out.write("                        isAdmin: \"1\"\r\n");
      out.write("                    },\r\n");
      out.write("                    {\r\n");
      out.write("                        name: \"Mohamed\",\r\n");
      out.write("                        id: \"1\",\r\n");
      out.write("                        isAdmin: \"0\"\r\n");
      out.write("                    }\r\n");
      out.write("                ]\r\n");
      out.write("            }\r\n");
      out.write("        },\r\n");
      out.write("        methods: {\r\n");
      out.write("            checkIfUserExist() {\r\n");
      out.write("                axios.get(\"userNameCheck/\" + this.username).then((res) => {\r\n");
      out.write("                    this.userExist = res;\r\n");
      out.write("                }).catch((err) => {\r\n");
      out.write("                    alert(err)\r\n");
      out.write("                });\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("</script>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
