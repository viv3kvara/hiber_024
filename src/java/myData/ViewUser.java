/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myData;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Denis Ruparel
 */
public class ViewUser extends HttpServlet {

     private static SessionFactory factory;
    Session s;
    Transaction tx;
     int id;
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
            id = Integer.parseInt(request.getParameter("id"));
            Query q = s.createQuery("from Userdata u where u.id = " + id);
            List<Userdata> li = q.list();
            for (Userdata u : li) {
                hs.setAttribute("op", "Id: " + u.getId()+ ", First Name: " + u.getFname() + ", Last Name: " + u.getLname()+", City: "+u.getCity() +",Branch: "+u.getBranch());
            }
            tx.commit();
            hs.setAttribute("status", "User Rtrieved Successfully");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            hs.setAttribute("status", "User NOT Retrieved");
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
