package queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Iterator;

import models.Toy;
import java.io.FileWriter;
import java.io.IOException;

public class ToyQueue {
    // Объявляем очередь приоритетов для игрушек
    private PriorityQueue<Toy> toyQueue;

     // Конструктор для инициализации очереди игрушек и добавления игрушек
     public ToyQueue(String toy1, String toy2, String toy3) {
        toyQueue = new PriorityQueue<>(Comparator.comparingInt(t -> t.weight));
        put(toy1);
        put(toy2);
        put(toy3);
    }

    // Метод для добавления новой игрушки в очередь
    public void put(String toy) {
        String[] data = toy.split(" ");
        int toyId = Integer.parseInt(data[0]);
        String toyName = data[1];
        int toyWeight = Integer.parseInt(data[2]);
        toyQueue.add(new Toy(toyId, toyName, toyWeight));  
    }
    // Метод для выбора игрушки из очереди с вероятностью
    public int get() {
        int totalWeight = 0;
        for (Toy toy : toyQueue) {
            totalWeight += toy.weight;
        }

        int randomWeight = new Random().nextInt(totalWeight);
        Iterator<Toy> iterator = toyQueue.iterator();
        Toy selectedToy = iterator.next();

        while (randomWeight >= selectedToy.weight) {
            randomWeight -= selectedToy.weight;
            selectedToy = iterator.next();
        }

        return selectedToy.id;
    }

    // Метод для вывода информации об игрушках в очереди
    public void printQueue() {
        for (Toy toy : toyQueue) {
            System.out.printf("ID: %d, Name: %s, Weight: %d\n", toy.id, toy.name, toy.weight);
        }
    }

    // Записываем выборку из метода get 10 раз в файл
    public void saveQueueToFile(String filename) {
        try (FileWriter outfile = new FileWriter(filename)) {
            for (int i = 0; i < 10; ++i) {
                outfile.write(String.valueOf(get()) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
