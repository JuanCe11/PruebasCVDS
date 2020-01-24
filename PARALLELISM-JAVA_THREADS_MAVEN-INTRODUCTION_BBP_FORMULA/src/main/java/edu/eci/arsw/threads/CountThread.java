/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThread extends Thread{

    private int limInf;
    private int limSup;

    public CountThread(int limInf, int limSup ){
        this.limInf = limInf;
        this.limSup = limSup;
    }
    public void run(){
        for (int i = limInf; i <= limSup; i++) {
            System.out.println(i);
        }
    }


}



