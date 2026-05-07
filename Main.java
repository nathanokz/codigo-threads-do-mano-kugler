import java.util.Random;

class Threadzinha extends Thread {
    private final Random random = new Random();
    public double[] numeros;
    private final int inicio;
    private final int fim;

    public Threadzinha(double[] numeros, int i, int i1) {
        this.numeros = numeros;
        this.inicio = i;
        this.fim = i1;
    }

    public void run() {
        try {
            Thread.sleep(10);
            for (int i = this.inicio; i < this.fim; i ++) {
                this.numeros[i] = random.nextDouble();
            }
        } catch (InterruptedException exception) {
            throw new RuntimeException("deu erro bobão");
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        double[] numeros = new double[2000000];
        int valores = 0;
        Threadzinha threadzinha1 = new Threadzinha(numeros, 0, 1000000);
        Threadzinha threadzinha2 = new Threadzinha(numeros, 1000000, 2000000);
        threadzinha1.start();
        threadzinha2.start();
        threadzinha1.join();
        threadzinha2.join();
        for (int i = 0; i < numeros.length; i++){
            Double numero = numeros[i];
            if (numero > 0.25 && numero < 0.75) {
                valores += 1;
            }
        }
        System.out.println(valores);
        System.out.println("encerrou a inicialização");
    }
}
