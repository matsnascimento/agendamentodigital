/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import java.util.ArrayList;
import modelos.Servico;

/**
 *
 * @author profe
 */
public interface IServicoDAO {

    public String cadastrar(Servico servico);

    public ArrayList<Servico> listar();

    public ArrayList<Servico> listarPorCategoria(Servico servico);

    public void buscar(Servico servico);
    
    public void buscar_dados_basicos(Servico servico);

    public Servico buscaCompleta(Servico servico);

    public String alterar(Servico servico);

    public String deletar(Servico servico);

}
