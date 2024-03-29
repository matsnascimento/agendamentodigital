/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.FuncionarioDAO;
import dao.AgendamentoDAO;
import dao.EmpresaDAO;
import dao.ServicoDAO;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import modelos.Agendamento;
import modelos.Cliente;
import modelos.Empresa;
import modelos.Servico;
import modelos.StatusAgendamento;

/**
 * REST Web Service
 *
 * @author mathm
 */
@Path("/Agendamento")
public class restAgendamento {

    AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of restAgendamento
     */
    public restAgendamento() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/Listar")
    public String listar() throws SQLException, ClassNotFoundException {

        Gson objgson = new GsonBuilder().setPrettyPrinting().create();

        AgendamentoDAO objAgendamentoDao = new AgendamentoDAO();
        FuncionarioDAO objFuncionarioDao = new FuncionarioDAO();
        ServicoDAO objServicoDao = new ServicoDAO();

        ArrayList<Agendamento> arr = objAgendamentoDao.listar();

        for (Agendamento objAgendamento : arr) {
            objFuncionarioDao.listarCompletoId(objAgendamento.getFuncionario());
            objServicoDao.buscar_dados_basicos(objAgendamento.getServico());
        }

        return objgson.toJson(arr);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/Testes/Servico/{idServico}/Cliente/{idCliente}/Funcionario/{idFuncionario}/{dataEntrada}/{horaEntrada}")
    public String verificarHorarioOcupado(@PathParam("idServico") Integer idServico, @PathParam("idCliente") Integer idCliente, @PathParam("idFuncionario") Integer idFuncionario, @PathParam("dataEntrada") String dataEntrada, @PathParam("horaEntrada") String horaEntrada) throws SQLException, ClassNotFoundException {

        Agendamento objAgendamento = new Agendamento();
        objAgendamento.setCliente(new Cliente(idCliente));
        objAgendamento.setFuncionario(new Funcionario(idFuncionario));
        objAgendamento.setServico(new Servico(idServico));
        objAgendamento.setStatus(StatusAgendamento.AGUARDANDOATENDIMENTO);
        Date dataAgendamento = null;
        Time horaAgendamento = null;
        //Parse dataAgendamento
        try {
            dataAgendamento = new SimpleDateFormat("yyyy-MM-dd").parse(dataEntrada);
        } catch (ParseException ex1) {
            try {
                dataAgendamento = new SimpleDateFormat("dd/MM/yyyy").parse(dataEntrada);
            } catch (ParseException ex2) {
                Logger.getLogger(restAgendamento.class.getName()).log(Level.SEVERE, null, ex2);
            }
            Logger.getLogger(restAgendamento.class.getName()).log(Level.SEVERE, null, ex1);
        }
        objAgendamento.setDataAgendamento(dataAgendamento);

        DateFormat formatter = new SimpleDateFormat("kk:mm");
        //Parse horaAgendamento
        try {
            horaAgendamento = new java.sql.Time(formatter.parse(horaEntrada).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(restAgendamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        objAgendamento.setHoraAgendamento(horaAgendamento);

        String validoParaAgendar = validoParaAgendar(objAgendamento);

        return validoParaAgendar;
    }

    public String validoParaAgendar(Agendamento objAgendamento) {

        String statusValidacao = "";

        //Retorna horário ocupados pelo funcionário e cliente no dia solicitado
        ArrayList<Map<String, String>> arrayHorariosOcupados = new ArrayList<>();
        arrayHorariosOcupados = agendamentoDAO.listarHorariosOcupados(objAgendamento);

        //Obtém os valores padrão de agendamento definido pela empresa
        Empresa objEmpresa = new Empresa();
        EmpresaDAO objEmpresaDAO = new EmpresaDAO();
        objEmpresaDAO.buscar(objEmpresa);
        //Integer intervaloAgendamentoHoraMin = objEmpresa.getIntervaloAgendamentoGeralServico().getHours() * 60;
        //Integer intervaloAgendamentoMin = objEmpresa.getIntervaloAgendamentoGeralServico().getMinutes();
        //Integer intervaloAgendamento = intervaloAgendamentoHoraMin + intervaloAgendamentoMin;

        ServicoDAO objServicoDAO = new ServicoDAO();
        objServicoDAO.buscar(objAgendamento.getServico());

        Long duracaoServicoLong = objAgendamento.getServico().getDuracao().toMinutes();
        Integer duracaoServico = Integer.parseInt(duracaoServicoLong.toString());

        //Mantém o maior intervalo de agendamento como referência
//        if (duracaoServico >= intervaloAgendamento) {
//            intervaloAgendamento = duracaoServico;
//        }

        //Calcula as horas ocupadas do funcionário e do cliente, respectivamente, atualizando conforme a duração de cada serviço já agendado
        //ArrayList<Map<String, String>> arrayHorariosOcupados_new = calcularHorasOcupadas(arrayHorariosOcupados, intervaloAgendamento);
        //Verifica se o funcionário e cliente é válido, ou seja, se os horários que ele possui agendado tem conflito com o novo agendamento
        statusValidacao = validarHorasOcupadas(objAgendamento, arrayHorariosOcupados);

        return statusValidacao;

    }

//    private ArrayList<Map<String, String>> calcularHorasOcupadas(ArrayList<Map<String, String>> arrayHorariosOcupados, Integer intervaloAgendamento) {
//
//        ArrayList<Map<String, String>> arrayHorariosOcupados_new = new ArrayList<>();
//
//        //Remove do array montado acima os horarios ocupados por esse funcionario e as horas entre o intervalo de duração
//        for (int i = 0; i < arrayHorariosOcupados.size(); i++) {
//            Map<String, String> mapHorarioOcupado = arrayHorariosOcupados.get(i);
//            String horaOcupada = mapHorarioOcupado.get("horarioAgendamento");
//            String horaFinalOcupada = mapHorarioOcupado.get("horarioFinalAgendamento");
//            String cliente = mapHorarioOcupado.get("cliente");
//
//            //Remove do array montado acima os horarios que estão entre o horario ocupado e sua duracao
//            Integer horas = Integer.parseInt(horaOcupada.split(":")[0]);
//            Integer minutos = Integer.parseInt(horaOcupada.split(":")[1]);
//            Integer horaOcupadaEmMinutos = (horas * 60) + minutos;
//            Integer horasFinal = Integer.parseInt(horaFinalOcupada.split(":")[0]);
//            Integer minutosFinal = Integer.parseInt(horaFinalOcupada.split(":")[1]);
//            Integer horaFinalOcupadaEmMinutos = (horasFinal * 60) + minutosFinal;
//
//            for (Integer j = horaOcupadaEmMinutos; j < horaFinalOcupadaEmMinutos; j = j + intervaloAgendamento) {
//                Map<String, String> hashHorasOcupadas = new HashMap<String, String>();
//                hashHorasOcupadas.put("minutos", j.toString());
//                Integer horas_ocupado = j / 60;
//                Integer minutos_ocupado = (j % 60);
//                hashHorasOcupadas.put("horas", horas_ocupado.toString() + ":" + minutos_ocupado);
//                hashHorasOcupadas.put("cliente", cliente);
//                arrayHorariosOcupados_new.add(hashHorasOcupadas);
//            }
//        }
//
//        return arrayHorariosOcupados_new;
//    }
//
//    private String validarHorasOcupadas(Agendamento objAgendamento, ArrayList<Map<String, String>> arrayHorariosOcupados) {
//
//        String msgRetorno = "VALIDO";
//        
//        for (int i = 0; i < arrayHorariosOcupados.size(); i++) {
//            Map<String, String> horaOcupada = arrayHorariosOcupados.get(i);
//            Integer horaSelecionada = objAgendamento.getHoraAgendamento().getHours() * 60;
//            Integer minutoSelecionado = objAgendamento.getHoraAgendamento().getMinutes();
//            Integer minutoTotalSelecionado = horaSelecionada + minutoSelecionado;
//            if (minutoTotalSelecionado == Integer.parseInt(horaOcupada.get("minutos"))) {
//                if (objAgendamento.getCliente().getIdCliente() == Integer.parseInt(horaOcupada.get("cliente"))) {
//                    msgRetorno = "CLIENTE_OCUPADO";
//                } else {
//                    msgRetorno = "FUNCIONARIO_OCUPADO";
//                }
//            }
//        }
//        return msgRetorno;
//    }
    private String validarHorasOcupadas(Agendamento objAgendamento, ArrayList<Map<String, String>> arrayHorariosOcupados) {

        String msgRetorno = "valido";

        for (int i = 0; i < arrayHorariosOcupados.size(); i++) {
            Map<String, String> horaOcupada = arrayHorariosOcupados.get(i);
            if (objAgendamento.getCliente().getIdCliente() == Integer.parseInt(horaOcupada.get("cliente"))) {
                msgRetorno = "cliente_ocupado";
                break;
            } else {
                msgRetorno = "funcionario_ocupado";
            }
        }
        return msgRetorno;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/HorariosOcupados/Funcionario/{idFuncionario}/{dataEntrada}")
    public String listarHorariosOcupadosFuncionario(@PathParam("idFuncionario") Integer idFuncionario, @PathParam("dataEntrada") String dataEntrada) throws SQLException, ClassNotFoundException {

        Gson objgson = new GsonBuilder().setPrettyPrinting().create();

        Date dataAgendamento = null;
        //Parse dataAgendamento
        try {
            dataAgendamento = new SimpleDateFormat("yyyy-MM-dd").parse(dataEntrada);
        } catch (ParseException ex) {
            Logger.getLogger(restAgendamento.class.getName()).log(Level.SEVERE, null, ex);
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setFuncionario(new Funcionario(idFuncionario));
        agendamento.setDataAgendamento(dataAgendamento);

        AgendamentoDAO objAgendamentoDao = new AgendamentoDAO();

        ArrayList<Map<String, String>> horariosOcupados = objAgendamentoDao.listarHorariosOcupados(agendamento);

        return objgson.toJson(horariosOcupados);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/HorariosOcupados/Cliente/{idCliente}/{dataEntrada}")
    public String listarHorariosOcupadosCliente(@PathParam("idCliente") Integer idCliente, @PathParam("dataEntrada") String dataEntrada) throws SQLException, ClassNotFoundException {

        Gson objgson = new GsonBuilder().setPrettyPrinting().create();

        Date dataAgendamento = null;
        //Parse dataAgendamento
        try {
            dataAgendamento = new SimpleDateFormat("yyyy-MM-dd").parse(dataEntrada);
        } catch (ParseException ex) {
            Logger.getLogger(restAgendamento.class.getName()).log(Level.SEVERE, null, ex);
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(new Cliente(idCliente));
        agendamento.setDataAgendamento(dataAgendamento);

        AgendamentoDAO objAgendamentoDao = new AgendamentoDAO();

        ArrayList<Map<String, String>> horariosOcupados = objAgendamentoDao.listarHorariosOcupados(agendamento);

        return objgson.toJson(horariosOcupados);
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
//    @Path("/HoraDisponivel/Funcionario/{id}")
//    public String validarDisponibilidadeFuncionario(@PathParam("id") Integer id) throws SQLException, ClassNotFoundException {
//
//        Gson objgson = new GsonBuilder().setPrettyPrinting().create();
//
//        AgendamentoDAO objAgendamentoDao = new AgendamentoDAO();
//        FuncionarioDAO objFuncionarioDao = new FuncionarioDAO();
//
//        Funcionario objFuncionario = new Funcionario();
//        objFuncionario.setIdFuncionario(id);
//
//        Agendamento objAgendamento = new Agendamento();
//        objAgendamento.setFuncionario(objFuncionario);
//        
//        ArrayList<Time> horariosDisponiveis = new ArrayList<>();
//        
//        horariosDisponiveis = objAgendamentoDao.validarDisponibilidadeFuncionario(objAgendamento);
//
//        if (objAgendamento.getIdAgendamento() != null) {
//            for (Funcionario objFuncionario : objAgendamento.getFuncionarios()) {
//                objFuncionarioDao.listarCompletoId(objFuncionario);
//            }
//        }
//
//        return objgson.toJson(objAgendamento);
//    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Listar/Cliente/{idCliente}")
    public String listarCliente(@PathParam("idCliente") Integer idCliente) throws SQLException, ClassNotFoundException {

        Gson objgson = new GsonBuilder().setPrettyPrinting().create();

        AgendamentoDAO objAgendamentoDao = new AgendamentoDAO();
        FuncionarioDAO objFuncionarioDao = new FuncionarioDAO();
        ServicoDAO objServicoDao = new ServicoDAO();

        ArrayList<Agendamento> arr = objAgendamentoDao.listarCliente(new Agendamento(new Cliente(idCliente)));

        for (Agendamento objAgendamento : arr) {
            objFuncionarioDao.buscar(objAgendamento.getFuncionario());
            objServicoDao.buscar_dados_basicos(objAgendamento.getServico());
        }

        return objgson.toJson(arr);
    }
}
