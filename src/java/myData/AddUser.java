/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myData;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Denis Ruparel
 */
public class AddUser extends HttpServlet {

     private static SessionFactory factory;
    Session s;
    Transaction tx;
    String fname,lname,city,branch;
    Userdata u;
    HttpSession hs;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       hs = request.getSession(true);
        try {
            factory = new Configuration().configure().buildSessionFactory();
            s = factory.openSession();
            tx = s.beginTransaction();
            fname = request.getParameter("fname");
            lname = request.getParameter("lname");
            city = request.getParameter("city");
             branch= request.getParameter("branch");
            
            u= new Userdata();
           u.setFname(fname);
           u.setLname(lname);
           u.setCity(city);
           u.setBranch(branch);
           s.save(u);
            tx.commit();
            hs.setAttribute("status", "User Added Successfully");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            hs.setAttribute("status", "User NOT Added");
        } finally {
            if (s != null) {
                s.close();
            }
            response.sendRedirect("message.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
