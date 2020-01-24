package edu.eci.arsw.primefinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.swing.Timer;

public class PrimeFinderThread extends Thread{
	int a,b;
	
	private List<Integer> primes=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public void run(){
		int interval = (b-a)/3;
		SubPrimeFinderThread s1 = new SubPrimeFinderThread(a,a+interval);
		SubPrimeFinderThread s2 = new SubPrimeFinderThread(a+interval,a+(2*interval));
		SubPrimeFinderThread s3 = new SubPrimeFinderThread(a+(2*interval),b);
		Timer timer = new Timer (5000, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					CountdownLatch 
					s1.wait();
					s2.wait();
					s3.wait();
					primes.addAll(s1.getPrimes());
					primes.addAll(s2.getPrimes());
					primes.addAll(s3.getPrimes());
					for (int i = 0; i < primes.size(); i++) {
						System.out.println(primes.get(i));
					}
					Scanner sc = new Scanner(System.in);
					sc.next();

				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}

			}
		});
		s1.start();
		s2.start();
		s3.start();
		timer.start();

	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
}