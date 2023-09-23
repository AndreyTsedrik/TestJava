import queue.ToyQueue;

public class Main {
    public static void main(String[] args) {
        // Создадим объект ToyQueue с 3 игрушками
        ToyQueue myToyQueue = new ToyQueue("1 конструктор 2", "2 робот 2", "3 кукла 6");

        // Выводим информацию об игрушках в очереди
        System.out.println("Текущий список игрушек:");
        myToyQueue.printQueue();

        // Выбираем игрушку из очереди с вероятностью, соответствующей их весу
        System.out.println("\nВыбранная игрушка с вероятностью, соответствующей весу:");
        int chosenToyId = myToyQueue.get();
        System.out.printf("ID выбранной игрушки: %d\n", chosenToyId);

        // Записываем выборку из метода get 10 раз в файл
        String outputFile = "toy_selection.txt";
        myToyQueue.saveQueueToFile(outputFile);
        System.out.printf("\nРезультат выборки сохранен в файл %s", outputFile);
    }
}