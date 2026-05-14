import java.io.*;

class thread extends Thread {
    public int contagemDeA;
    public File pasta;
    public File[] arquivos;
    public int inicio;
    public int fim;

    public thread(File pasta, File[] arquivos, int inicio, int fim) {
        this.pasta = pasta;
        this.arquivos = arquivos;
        this.inicio = inicio;
        this.fim = fim;
    }

    public void run() {
        for (int i = this.inicio; i < this.fim; i ++) {
            File arquivo = arquivos[i];
            try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = leitor.readLine()) != null) {
                    for (char letra : linha.toCharArray()) {
                        if (letra == 'a') {
                            this.contagemDeA += 1;
                        }
                    }
                }
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}

public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        File pasta = new File("amostra");
        File[] arquivos = pasta.listFiles();
        thread thread1 = new thread(pasta, arquivos, 0, 50);
        thread thread2 = new thread(pasta, arquivos,50, 100);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        int total = thread1.contagemDeA + thread2.contagemDeA;
        System.out.println("quantidade de a: " + total);
    }
}




