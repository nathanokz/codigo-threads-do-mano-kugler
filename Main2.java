import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

class thread extends Thread {
    public File pasta;
    public File[] arquivos;
    public int inicio;
    public int fim;
    public HashMap<Character, Integer> hashMap = new HashMap<>();
    public HashMap<Character, Integer> hashMap2 = new HashMap<>();
    public List<Character> letrasPossiveis = List.of('a','b','c','d','e','f','g','h','i','j','k'
    ,'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
    public List<Character> letrasPossiveisMaiusculas = List.of('A','B','C','D','E','F','G','H',
            'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');


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
                        if (letrasPossiveis.contains(letra)) {
                            if (hashMap.containsKey(letra)) {
                                int totalLetras = hashMap.get(letra);
                                hashMap.put(letra, totalLetras + 1);
                            } else {
                                hashMap.put(letra, 1);
                            }
                        } else if (letrasPossiveisMaiusculas.contains(letra)) {
                            if (hashMap2.containsKey(letra)) {
                                int totalLetras = hashMap2.get(letra);
                                hashMap2.put(letra, totalLetras + 1);
                            } else {
                                hashMap2.put(letra, 1);
                            }
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
        System.out.println("quantidade de letras minusculas t1: " + thread1.hashMap);
        System.out.println("quantidade de letras maiusculas t1: " + thread1.hashMap2);
        System.out.println("quantidade de letras minusculas t2: " + thread2.hashMap);
        System.out.println("quantiadade de letras maiusculas t2: " + thread2.hashMap2);
    }
}




