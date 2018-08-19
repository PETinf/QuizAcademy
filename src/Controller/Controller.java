/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author User
 */
public abstract class Controller {
    public String caminhoPadrao(){
        String path = System.getProperty("user.dir") + "/Imagens/";
        return path;
    }
}
