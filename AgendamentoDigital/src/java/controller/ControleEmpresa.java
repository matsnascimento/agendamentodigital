/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import command.empresa.ICommand;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rafael Pereira
 */
@WebServlet(name = "ControleEmpresa", urlPatterns = {"/ControleEmpresa",
    "/Administrador/Empresa",
    "/Administrador/Empresa/Atualizar",
    "/Administrador/BloqueioAgenda/Menu",
    "/Administrador/BloqueioAgenda/Cadastrar",
    "/Administrador/BloqueioAgenda/Listar",
    "/Administrador/BloqueioAgenda/Listar/Funcionario",
    "/Administrador/BloqueioAgenda/Cancelar"})
public class ControleEmpresa extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String uri = request.getRequestURI();
            String acao = "";

            if (uri.equals(request.getContextPath() + "/Administrador/Empresa")) {
                acao = "BuscarEmpresa";
            } else if (uri.equals(request.getContextPath() + "/Administrador/Empresa/Atualizar")) {
                acao = "AtualizarEmpresa";
            } else if (uri.equals(request.getContextPath() + "/Administrador/BloqueioAgenda/Menu")) {
                acao = "MenuBloqueio";
            } else if (uri.equals(request.getContextPath() + "/Administrador/BloqueioAgenda/Cadastrar")) {
                acao = "CadastrarBloqueio";
            } else if (uri.equals(request.getContextPath() + "/Administrador/BloqueioAgenda/Listar")) {
                acao = "ListarBloqueio";
            } else if (uri.equals(request.getContextPath() + "/Administrador/BloqueioAgenda/Listar/Funcionario")) {
                acao = "ListarBloqueioFuncionario";
            } else if (uri.equals(request.getContextPath() + "/Administrador/BloqueioAgenda/Cancelar")) {
                acao = "CancelarBloqueio";
            } else {
                response.sendRedirect("404.jsp");
            }

            String nomedaclasse = "command.empresa." + acao + "Action";

            //perceba que estamos usando um FACTORY
            Class classeAction = Class.forName(nomedaclasse);

            //Aqui estamos chamando a Acao que queremos
            ICommand comando_acao = (ICommand) classeAction.newInstance();

            String objEmpresa = comando_acao.executar(request, response);

            String pagina = request.getAttribute("pagina").toString();

            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            request.setAttribute("msg", objEmpresa);
            rd.forward(request, response);

        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            request.setAttribute("erro", e);
            rd.forward(request, response);
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
