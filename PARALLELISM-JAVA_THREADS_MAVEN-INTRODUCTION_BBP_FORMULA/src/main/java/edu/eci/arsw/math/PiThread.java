package edu.eci.arsw.math;

public class PiThread extends Thread{

    private int start;
    private int count;
    private byte[] res;

    public PiThread(int limInf, int limSup ){
        this.start = limInf;
        this.count = limSup;
    }
    @Override
    public void run() {
        res = PiDigits.getDigits(start,count);
    }

    public byte[] getRes() {
        return res;
    }
}



