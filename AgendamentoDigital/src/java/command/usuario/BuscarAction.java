/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.usuario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.UsuarioDAO;
import static java.lang.System.out;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Usuario;

/**
 *
 * @author alunocmc
 */
public class BuscarAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Usuario objUsuario = new Usuario();

        objUsuario.setEmail(request.getParameter("email"));

        UsuarioDAO dao = new UsuarioDAO();

        dao.buscar(objUsuario);

        Gson objgson = new GsonBuilder().setPrettyPrinting().create();

        return objgson.toJson(objUsuario);
    }

}
