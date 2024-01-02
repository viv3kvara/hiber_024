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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Denis Ruparel
 */
public class UpdateUser extends HttpServlet {

    private static SessionFactory factory;
    Session s;
    Transaction tx;
    int id;
    String fname, lname, branch, city;
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
            fname = request.getParameter("fname");
            lname = request.getParameter("lname");
            city = request.getParameter("city");
            branch = request.getParameter("branch");
            Query q;
            if (!"".equals(fname) && !"".equals(lname) && !"".equals(city) && !"".equals(branch)) {
                q = s.createQuery("update Userdata u set u.fname='" + fname + "', u.lname='" + lname + "',u.city='"+city+"',u.branch='"+branch+"' where u.id=" + id);
                q.executeUpdate();
            } 
            else if (!"".equals(fname) && "".equals(lname) && "".equals(city) && "".equals(branch)) {
                q = s.createQuery("update Userdata u set  u.fname='" + fname + "'where u.id=" + id);
                q.executeUpdate();
            } 
             else if ("".equals(fname) && !"".equals(lname) && "".equals(city) && "".equals(branch)) {
                q = s.createQuery("update Userdata u set u.lname='" + lname + "' where u.id=" + id);
                q.executeUpdate();
            } 
                else if ("".equals(fname) && "".equals(lname) && !"".equals(city) && "".equals(branch)) {
                q = s.createQuery("update Userdata u set u.city='"+city+"' where u.id=" + id);
                q.executeUpdate();
            } 
            else if ("".equals(fname) && "".equals(lname) && "".equals(city) && !"".equals(branch)) {
                q = s.createQuery("update Userdata u set u.branch='"+branch+"' where u.id=" + id);
                q.executeUpdate();
            } 
            

            tx.commit();
            hs.setAttribute("status", "User Updated Successfully");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            hs.setAttribute("status", "User NOT Updated");
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
