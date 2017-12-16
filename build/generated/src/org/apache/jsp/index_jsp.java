package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/header.jsp");
    _jspx_dependants.add("/footer.jsp");
  }

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html class=\"has-navbar-fixed-top\">\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>PollHub</title>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/css/bulma.css\" />        \r\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/css/ionicons.min.css\" />\r\n");
      out.write("\r\n");
      out.write("        <script src=\"assets/vue.js\"></script>\r\n");
      out.write("        <script src=\"assets/axios.min.js\"></script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("\r\n");
      out.write("        <header>\r\n");
      out.write("            <nav class=\"navbar is-fixed-top\" style=\"opacity: 0;\" v-bind:style=\"{ opacity: 1 }\">\r\n");
      out.write("                <div class=\"navbar-brand\">\r\n");
      out.write("                    <a class=\"navbar-item\" href=\"https://bulma.io\">\r\n");
      out.write("                        <h3> LOGO </h3>\r\n");
      out.write("                    </a>\r\n");
      out.write("                    <div class=\"navbar-burger burger\" data-target=\"navbarExampleTransparentExample\">\r\n");
      out.write("                        <span></span>\r\n");
      out.write("                        <span></span>\r\n");
      out.write("                        <span></span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <div id=\"navbarExampleTransparentExample\" class=\"navbar-menu\">\r\n");
      out.write("                    <div class=\"navbar-start\">\r\n");
      out.write("                        <a class=\"navbar-item\" href=\"https://bulma.io/\">\r\n");
      out.write("                            Home\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"navbar-end\">\r\n");
      out.write("                        <div class=\"navbar-item\">\r\n");
      out.write("                            <div v-if=\"!isAuth\"  class=\"field is-grouped\">\r\n");
      out.write("                                <p class=\"control\">\r\n");
      out.write("                                    <a class=\"button\" href=\"user-login.jsp\">\r\n");
      out.write("                                        <span class=\"icon\">\r\n");
      out.write("                                            <i class=\"ion-log-in\"></i>\r\n");
      out.write("                                        </span>\r\n");
      out.write("                                        <span>\r\n");
      out.write("                                            Login\r\n");
      out.write("                                        </span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </p>\r\n");
      out.write("                                <p class=\"control\">\r\n");
      out.write("                                    <a class=\"button is-primary\" href=\"user-register.jsp\">\r\n");
      out.write("                                        <span class=\"icon\">\r\n");
      out.write("                                            <i class=\"ion-android-favorite-outline\"></i>\r\n");
      out.write("                                        </span>\r\n");
      out.write("                                        <span>Sign Up</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </p>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div v-if=\"isAuth\"  class=\"field is-grouped\">\r\n");
      out.write("                                <p class=\"control\">\r\n");
      out.write("                                    <a class=\"button is-primary\" href=\"poll-add.jsp\">\r\n");
      out.write("                                        <span class=\"icon\">\r\n");
      out.write("                                            <i class=\"ion-ios-plus\"></i>\r\n");
      out.write("                                        </span>\r\n");
      out.write("                                        <span>New Poll</span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </p>\r\n");
      out.write("                                <p class=\"control\">\r\n");
      out.write("                                    <a class=\"button\" href=\"user-login.jsp\">\r\n");
      out.write("                                        <span class=\"icon\">\r\n");
      out.write("                                            <i class=\"ion-person\"></i>\r\n");
      out.write("                                        </span>\r\n");
      out.write("                                        <span>\r\n");
      out.write("                                            Welcome, {{username}}\r\n");
      out.write("                                        </span>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </p>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </nav>\r\n");
      out.write("        </header>\r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        <script>\r\n");
      out.write("            new Vue({\r\n");
      out.write("                el:\"nav\",\r\n");
      out.write("                data(){\r\n");
      out.write("                    return { isAuth: ");
      out.print( "false" );
      out.write(" , username: ");
      out.print( "\'Mohamed\'" );
      out.write(" };\r\n");
      out.write("                }\r\n");
      out.write("            });\r\n");
      out.write("        </script>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- intro , some polls form -->\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("    \r\n");
      out.write("    new Vue({\r\n");
      out.write("        \r\n");
      out.write("    });\r\n");
      out.write("    \r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <footer class=\"footer\">\r\n");
      out.write("          <div class=\"container\">\r\n");
      out.write("            <div class=\"content has-text-centered\">\r\n");
      out.write("              <p>\r\n");
      out.write("              </p>\r\n");
      out.write("            </div>\r\n");
      out.write("          </div>\r\n");
      out.write("        </footer>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Get all polls  -->");
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
