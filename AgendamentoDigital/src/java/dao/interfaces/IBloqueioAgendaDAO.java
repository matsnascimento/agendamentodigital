/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import java.util.ArrayList;
import modelos.BloqueioAgenda;

/**
 *
 * @author Rafael Pereira
 */
public interface IBloqueioAgendaDAO {

    public void buscar(BloqueioAgenda bloqueio);

    public ArrayList<BloqueioAgenda> listarBloqueio();

    public boolean alterar(BloqueioAgenda bloqueio);

    public boolean cadastrar(BloqueioAgenda bloqueio);

}
