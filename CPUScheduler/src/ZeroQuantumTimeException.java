/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aevan
 */
public class ZeroQuantumTimeException extends Exception{
    @Override
    public String getMessage(){
        return "Quantum Time cannot be zero or less";
    }
    
}
