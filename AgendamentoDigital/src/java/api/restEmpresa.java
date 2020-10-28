/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import dao.FuncionarioDAO;
import dao.EmpresaDAO;
import dao.ServicoDAO;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelos.Funcionario;
import modelos.Empresa;
import modelos.Cliente;
import modelos.Servico;
//import testes.ValidarCodigo;

/**
 * REST Web Service
 *
 * @author mathm
 */
@Path("/Empresa")
public class restEmpresa {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of restEmpresa
     */
    public restEmpresa() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/Buscar")
    public Empresa buscar() throws SQLException, ClassNotFoundException {

        Empresa objEmpresa = new Empresa();

        EmpresaDAO empresaDAO = new EmpresaDAO();

        empresaDAO.buscar(objEmpresa);

        return objEmpresa;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Menu/Encontrar")
    public String getEmpresa() throws SQLException, ClassNotFoundException, ParseException {
        Gson objgson = new GsonBuilder().registerTypeAdapter(Empresa.class, new EmpresaAdapter()).setPrettyPrinting().create();

        Empresa objEmpresa = new Empresa();

        EmpresaDAO empresaDAO = new EmpresaDAO();

        empresaDAO.buscar(objEmpresa);

        String json = objgson.toJson(objEmpresa);

        return json;

    }

    private class EmpresaAdapter implements JsonSerializer<Empresa> {

        public JsonElement serialize(Empresa objEmpresa, Type typeofsrc, JsonSerializationContext context) {

            JsonObject obj = new JsonObject();
            obj.add("nome", context.serialize(objEmpresa.getNome()));

            String horainicialtrabalho = new SimpleDateFormat("kk:mm").format(objEmpresa.getHoraInicialTrabalho());
            obj.add("horaInicialTrabalho", context.serialize(horainicialtrabalho));

            String horafinaltrabalho = new SimpleDateFormat("kk:mm").format(objEmpresa.getHoraFinalTrabalho());
            obj.add("horaFinalTrabalho", context.serialize(horafinaltrabalho));
            obj.add("intervaloAgendamentoGeralServico", context.serialize(objEmpresa.getIntervaloAgendamentoGeralServico().toMinutes()));
            obj.add("periodoMinimoCancelamento", context.serialize(objEmpresa.getPeriodoMinimoCancelamento().toMinutes()));

            obj.add("diaSemanaTrabalho", context.serialize(objEmpresa.getDiaSemanaTrabalho()));

            obj.add("telefone", context.serialize(objEmpresa.getTelefone()));

            obj.add("email", context.serialize(objEmpresa.getEmail()));
            return obj;

        }

    }
}
