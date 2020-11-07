/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.empresa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import dao.BloqueioAgendaDAO;
import dao.FuncionarioDAO;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Agendamento;
import modelos.BloqueioAgenda;

/**
 *
 * @author Rafael Pereira
 */
public class ListarBloqueioAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Gson objgson = new GsonBuilder()
                .registerTypeAdapter(BloqueioAgenda.class, new BloqueioAdapter())
                .setPrettyPrinting().create();

        ArrayList<BloqueioAgenda> arrBloqueio = new ArrayList<BloqueioAgenda>();

        BloqueioAgendaDAO bloqueioDAO = new BloqueioAgendaDAO();

        FuncionarioDAO objFuncionarioDao = new FuncionarioDAO();

        String funcaoMsg = "Carregando";
        String funcaoStatus = "info";

        arrBloqueio = bloqueioDAO.listarBloqueio();

        for (BloqueioAgenda objBloqueio : arrBloqueio) {
            objFuncionarioDao.buscar(objBloqueio.getFuncionario());
        }
        if (funcaoMsg!="Funcionario não encontrado\nRedirecionando para lista geral")
        {
        funcaoMsg = "Lista carregada";
        funcaoStatus = "success";
        }else
        {
            funcaoMsg="Funcionario não encontrado\nRedirecionando para lista geral";
            funcaoStatus="error";
        }
        request.setAttribute("pagina", "/pages/admin/empresa/listarBloqueio.jsp");
        request.setAttribute("funcaoMsg", funcaoMsg);
        request.setAttribute("funcaoStatus", funcaoStatus);
        return objgson.toJson(arrBloqueio);

    }

    private class BloqueioAdapter implements JsonSerializer<BloqueioAgenda> {

        public JsonElement serialize(BloqueioAgenda bloqueio, Type typeofsrc, JsonSerializationContext context) {

            JsonObject obj = new JsonObject();
            obj.add("idBloqueio", context.serialize(bloqueio.getIdBloquieio()));

            // bloqueio.setDataBloqueio(dataBloqueio);
            String dataBloqueio = new SimpleDateFormat("dd/MM/yyyy").format(bloqueio.getDataBloqueio());
            obj.add("dataBloqueio", context.serialize(dataBloqueio));

            String horainicial = new SimpleDateFormat("kk:mm").format(bloqueio.getHoraInicial());
            obj.add("horaInicial", context.serialize(horainicial));

            obj.add("duracaoBloqueio", context.serialize(bloqueio.getDuracaoBloqueio().toMinutes()));

            obj.add("funcionario", context.serialize(bloqueio.getFuncionario()));

            return obj;

        }

    }

}
